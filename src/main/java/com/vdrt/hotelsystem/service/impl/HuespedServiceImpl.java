package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.dto.huesped.HuespedMapper;
import com.vdrt.hotelsystem.dto.huesped.HuespedRequestDTO;
import com.vdrt.hotelsystem.dto.huesped.HuespedResponseDTO;
import com.vdrt.hotelsystem.exception.ResourceNotFoundException;
import com.vdrt.hotelsystem.model.Huesped;
import com.vdrt.hotelsystem.model.PerfilContacto;
import com.vdrt.hotelsystem.repository.HuespedRepository;
import com.vdrt.hotelsystem.service.HuespedService;

@Service
public class HuespedServiceImpl implements HuespedService {

    private final HuespedRepository huespedRepo;
    private final HuespedMapper mapper;

    public HuespedServiceImpl(HuespedRepository huespedRepository, HuespedMapper mapper) {
        this.huespedRepo = huespedRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HuespedResponseDTO> listarTodos() {
        return huespedRepo.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public Optional<HuespedResponseDTO> buscarPorId(Long id) {
        return huespedRepo.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    public HuespedResponseDTO crear(HuespedRequestDTO huesped) {
        Huesped h = mapper.toEntity(huesped);
        huespedRepo.save(h);

        return mapper.toResponseDTO(h);
    }

    @Override
    public HuespedResponseDTO actualizar(Long id, HuespedRequestDTO huesped) {
        Huesped huespedExistente = huespedRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe huesped con id: %s", id)));

        huespedExistente.setNombre(huesped.getNombre());
        huespedExistente.setApellido(huesped.getApellido());
        huespedExistente.setDocumentoIdentidad(huesped.getDocumentoIdentidad());
        huespedExistente.setEmail(huesped.getEmail());

        if (huesped.getPerfilContacto() != null) {
            PerfilContacto perfil = huespedExistente.getPerfilContacto();

            if (perfil == null) {
                perfil = new PerfilContacto();
                huespedExistente.setPerfilContacto(perfil);
            }

            perfil.setTelefono(huesped.getPerfilContacto().getTelefono());
            perfil.setTelefonoEmergencia(huesped.getPerfilContacto().getTelefonoEmergencia());
            perfil.setDireccion(huesped.getPerfilContacto().getDireccion());
            perfil.setPais(huesped.getPerfilContacto().getPais());
            perfil.setNacionalidad(huesped.getPerfilContacto().getNacionalidad());
        }

        Huesped huespedActualizado = huespedRepo.save(huespedExistente);

        return mapper.toResponseDTO(huespedActualizado);
    }

    @Override
    public void eliminar(Long id) {
        huespedRepo.deleteById(id);
    }

    @Override
    public List<HuespedResponseDTO> buscarPorNombreOApellido(String texto) {
        return huespedRepo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(texto, texto);
    }
}
