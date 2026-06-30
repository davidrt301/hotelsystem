package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.model.Habitacion;
import com.vdrt.hotelsystem.service.HabitacionService;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Override
    public List<Habitacion> listarTodas() {
        return null;
    }

    @Override
    public Optional<Habitacion> buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Habitacion> listarPorHotel(Long hotelId) {
        return null;
    }

    @Override
    public List<Habitacion> listarDisponiblesPorHotel(Long hotelId) {
        return null;
    }

    @Override
    public Habitacion crear(Habitacion habitacion, Long hotelId) {
        return null;
    }

    @Override
    public Habitacion actualizar(Long id, Habitacion habitacion) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
    }

    @Override
    public List<Habitacion> buscarPorPrecioMaximo(Double precioMaximo) {
        return null;
    }
}
