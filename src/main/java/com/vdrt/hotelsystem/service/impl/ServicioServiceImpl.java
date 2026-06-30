package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.dto.servicio.ServicioMapper;
import com.vdrt.hotelsystem.dto.servicio.ServicioRequestDTO;
import com.vdrt.hotelsystem.dto.servicio.ServicioResponseDTO;
import com.vdrt.hotelsystem.exception.ResourceNotFoundException;
import com.vdrt.hotelsystem.model.Servicio;
import com.vdrt.hotelsystem.repository.ServicioRepository;
import com.vdrt.hotelsystem.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepo;
    private final ServicioMapper mapper;


    public ServicioServiceImpl(ServicioRepository servicioRepository, ServicioMapper mapper) {
        this.servicioRepo = servicioRepository;
        this.mapper = mapper;
        }

    @Override
    public List<ServicioResponseDTO> listarTodos() {
        return servicioRepo.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public List<ServicioResponseDTO> listarDisponibles() {
        return servicioRepo.findByDisponible(true).stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public Optional<ServicioResponseDTO> buscarPorId(Long id) {
        return servicioRepo.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    public ServicioResponseDTO crear(ServicioRequestDTO servicio) {
        Servicio service = mapper.toEntity(servicio);
        servicioRepo.save(service);
        return mapper.toResponseDTO(service);
    }

    @Override
public ServicioResponseDTO actualizar(Long id, ServicioRequestDTO servicio) {
    Servicio servicioExistente = servicioRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    String.format("No existe servicio con id: %s", id)));

    servicioExistente.setNombre(servicio.getNombre());
    servicioExistente.setDescripcion(servicio.getDescripcion());
    servicioExistente.setPrecio(servicio.getPrecio());
    servicioExistente.setDisponible(servicio.isDisponible());

    Servicio servicioActualizado = servicioRepo.save(servicioExistente);

    return mapper.toResponseDTO(servicioActualizado);
}

    @Override
    public void eliminar(Long id) {
        servicioRepo.deleteById(id);
    }
}
