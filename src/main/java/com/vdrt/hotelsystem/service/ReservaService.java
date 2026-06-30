package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.model.Reserva;
import com.vdrt.hotelsystem.model.enums.Estado;

public interface ReservaService {

    List<Reserva> listarTodas();

    Optional<Reserva> buscarPorId(Long id);

    Reserva crear(Reserva reserva); // debe verificar disponibilidad de la habitación

    Reserva actualizar(Long id, Reserva reserva);

    void cancelar(Long id); // cambia el estado a CANCELADA

    List<Reserva> listarPorHuesped(Long huespedId);

    List<Reserva> listarPorEstado(Estado estado);

    Reserva agregarServicio(Long reservaId, Long servicioId); // agrega un servicio a una reserva existente

}
