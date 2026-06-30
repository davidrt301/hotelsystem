package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.dto.hotel.HotelRequestDTO;
import com.vdrt.hotelsystem.dto.hotel.HotelResponseDTO;

public interface HotelService {

    List<HotelResponseDTO> listarTodos(); // retorna todos los hoteles

    Optional<HotelResponseDTO> buscarPorId(Long id);

    List<HotelResponseDTO> buscarPorCiudad(String ciudad);

    HotelResponseDTO crear(HotelRequestDTO hotel); // persiste un nuevo hotel

    HotelResponseDTO actualizar(Long id, HotelRequestDTO dto); // actualiza un hotel existente

    void eliminar(Long id);

    List<HotelResponseDTO> buscarConHabitacionesDisponibles(); // usa la consulta JPQL del repositorio
}
