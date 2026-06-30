package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.dto.habitacion.HabitacionMapper;
import com.vdrt.hotelsystem.dto.habitacion.HabitacionRequestDTO;
import com.vdrt.hotelsystem.dto.habitacion.HabitacionResponseDTO;
import com.vdrt.hotelsystem.exception.ResourceNotFoundException;
import com.vdrt.hotelsystem.model.Habitacion;
import com.vdrt.hotelsystem.model.Hotel;
import com.vdrt.hotelsystem.repository.HabitacionRepository;
import com.vdrt.hotelsystem.repository.HotelRepository;
import com.vdrt.hotelsystem.service.HabitacionService;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository habitacionRepo;
    private final HotelRepository hotelRepo;
    private final HabitacionMapper mapper;

    public HabitacionServiceImpl(HabitacionRepository habitacionRepository, HotelRepository hotelRepository, HabitacionMapper mapper) {
        this.habitacionRepo = habitacionRepository;
        this.hotelRepo = hotelRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HabitacionResponseDTO> listarTodas() {
        return habitacionRepo.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public Optional<HabitacionResponseDTO> buscarPorId(Long id) {
        return habitacionRepo.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    public List<HabitacionResponseDTO> listarPorHotel(Long hotelId) {
        return habitacionRepo.findByHotelId(hotelId).stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public List<HabitacionResponseDTO> listarDisponiblesPorHotel(Long hotelId) {
        return habitacionRepo.findByDisponibleAndHotelId(true, hotelId).stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public HabitacionResponseDTO crear(HabitacionRequestDTO habitacion, Long hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        Habitacion nuevaHabitacion = mapper.toEntity(habitacion, hotel);

        nuevaHabitacion = habitacionRepo.save(nuevaHabitacion);

        return mapper.toResponseDTO(nuevaHabitacion);
    }

    @Override
public HabitacionResponseDTO actualizar(Long id, HabitacionRequestDTO habitacion) {
    Habitacion habitacionExistente = habitacionRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    String.format("No existe habitación con id: %s", id)));

    Hotel hotelExistente = hotelRepo.findById(habitacion.getHotelid())
            .orElseThrow(() -> new ResourceNotFoundException(
                    String.format("No existe hotel con id: %s", habitacion.getHotelid())));

    habitacionExistente.setNumero(habitacion.getNumero());
    habitacionExistente.setTipo(habitacion.getTipo());
    habitacionExistente.setPrecioNoche(habitacion.getPrecioNoche());
    habitacionExistente.setDisponible(habitacion.getDisponible());
    habitacionExistente.setHotel(hotelExistente);

    Habitacion habitacionActualizada = habitacionRepo.save(habitacionExistente);

    return mapper.toResponseDTO(habitacionActualizada);
}

    @Override
    public void eliminar(Long id) {
        habitacionRepo.deleteById(id);
    }

    @Override
    public List<HabitacionResponseDTO> buscarPorPrecioMaximo(Double precioMaximo) {
        return habitacionRepo.findByHabitacionCuyoPrecioSeaMenor(precioMaximo).stream().map(mapper::toResponseDTO).toList();
    }
}
