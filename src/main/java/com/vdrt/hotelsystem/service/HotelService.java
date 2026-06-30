package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.model.Hotel;

public interface HotelService {

    List<Hotel> listarTodos(); // retorna todos los hoteles

    Optional<Hotel> buscarPorId(Long id);

    List<Hotel> buscarPorCiudad(String ciudad);

    Hotel crear(Hotel hotel); // persiste un nuevo hotel

    Hotel actualizar(Long id, Hotel hotel); // actualiza un hotel existente

    void eliminar(Long id);

    List<Hotel> buscarConHabitacionesDisponibles(); // usa la consulta JPQL del repositorio
}
