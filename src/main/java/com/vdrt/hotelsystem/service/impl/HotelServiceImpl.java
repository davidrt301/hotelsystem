package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.model.Hotel;
import com.vdrt.hotelsystem.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Override
    public List<Hotel> listarTodos() {
        return null;
    }

    @Override
    public Optional<Hotel> buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Hotel> buscarPorCiudad(String ciudad) {
        return null;
    }

    @Override
    public Hotel crear(Hotel hotel) {
        return null;
    }

    @Override
    public Hotel actualizar(Long id, Hotel hotel) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
    }

    @Override
    public List<Hotel> buscarConHabitacionesDisponibles() {
        return null;
    }
}
