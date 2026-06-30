package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.model.Habitacion;

public interface HabitacionService {

    List<Habitacion> listarTodas();

    Optional<Habitacion> buscarPorId(Long id);

    List<Habitacion> listarPorHotel(Long hotelId);

    List<Habitacion> listarDisponiblesPorHotel(Long hotelId);

    Habitacion crear(Habitacion habitacion, Long hotelId);

    Habitacion actualizar(Long id, Habitacion habitacion);

    void eliminar(Long id);

    List<Habitacion> buscarPorPrecioMaximo(Double precioMaximo);
}
