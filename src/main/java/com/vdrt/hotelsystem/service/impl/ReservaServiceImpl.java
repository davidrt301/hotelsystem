package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.model.Reserva;
import com.vdrt.hotelsystem.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Override
    public List<Reserva> listarTodas() {
        return null;
    }

    @Override
    public Optional<Reserva> buscarPorId(Long id) {
        return null;
    }

    @Override
    public Reserva crear(Reserva reserva) {
        return null;
    }

    @Override
    public Reserva actualizar(Long id, Reserva reserva) {
        return null;
    }

    @Override
    public void cancelar(Long id) {
    }

    @Override
    public List<Reserva> listarPorHuesped(Long huespedId) {
        return null;
    }

    @Override
    public Reserva agregarServicio(Long reservaId, Long servicioId) {
        return null;
    }
}
