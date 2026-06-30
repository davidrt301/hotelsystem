package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.dto.habitacion.HabitacionRequestDTO;
import com.vdrt.hotelsystem.dto.habitacion.HabitacionResponseDTO;

public interface HabitacionService {

    List<HabitacionResponseDTO> listarTodas();

    Optional<HabitacionResponseDTO> buscarPorId(Long id);

    List<HabitacionResponseDTO> listarPorHotel(Long hotelId);

    List<HabitacionResponseDTO> listarDisponiblesPorHotel(Long hotelId);

    HabitacionResponseDTO crear(HabitacionRequestDTO dto, Long hotelId);

    HabitacionResponseDTO actualizar(Long id, HabitacionRequestDTO dto);

    void eliminar(Long id);

    List<HabitacionResponseDTO> buscarPorPrecioMaximo(Double precioMaximo);
}
