package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.model.Huesped;
import com.vdrt.hotelsystem.service.HuespedService;

@Service
public class HuespedServiceImpl implements HuespedService {

    @Override
    public List<Huesped> listarTodos() {
        return null;
    }

    @Override
    public Optional<Huesped> buscarPorId(Long id) {
        return null;
    }

    @Override
    public Huesped crear(Huesped huesped) {
        return null;
    }

    @Override
    public Huesped actualizar(Long id, Huesped huesped) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
    }

    @Override
    public List<Huesped> buscarPorNombreOApellido(String texto) {
        return null;
    }
}
