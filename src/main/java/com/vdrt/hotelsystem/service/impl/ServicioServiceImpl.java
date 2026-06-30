package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.model.Servicio;
import com.vdrt.hotelsystem.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Override
    public List<Servicio> listarTodos() {
        return null;
    }

    @Override
    public List<Servicio> listarDisponibles() {
        return null;
    }

    @Override
    public Optional<Servicio> buscarPorId(Long id) {
        return null;
    }

    @Override
    public Servicio crear(Servicio servicio) {
        return null;
    }

    @Override
    public Servicio actualizar(Long id, Servicio servicio) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
    }
}
