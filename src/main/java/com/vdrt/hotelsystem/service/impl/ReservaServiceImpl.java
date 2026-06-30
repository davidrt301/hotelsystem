package com.vdrt.hotelsystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.exception.ConflictException;
import com.vdrt.hotelsystem.exception.ResourceNotFoundException;
import com.vdrt.hotelsystem.model.Habitacion;
import com.vdrt.hotelsystem.model.Huesped;
import com.vdrt.hotelsystem.model.Reserva;
import com.vdrt.hotelsystem.model.Servicio;
import com.vdrt.hotelsystem.model.enums.Estado;
import com.vdrt.hotelsystem.repository.HabitacionRepository;
import com.vdrt.hotelsystem.repository.HuespedRepository;
import com.vdrt.hotelsystem.repository.ReservaRepository;
import com.vdrt.hotelsystem.repository.ServicioRepository;
import com.vdrt.hotelsystem.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepo;
    private final HabitacionRepository habitacionRepo;
    private final HuespedRepository huespedRepo;
    private final ServicioRepository servicioRepo;

    public ReservaServiceImpl(
            ReservaRepository reservaRepository,
            HabitacionRepository habitacionRepository,
            HuespedRepository huespedRepository,
            ServicioRepository servicioRepository) {
        this.reservaRepo = reservaRepository;
        this.habitacionRepo = habitacionRepository;
        this.huespedRepo = huespedRepository;
        this.servicioRepo = servicioRepository;
    }

    @Override
    public List<Reserva> listarTodas() {
        return reservaRepo.findAll();
    }

    @Override
    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepo.findById(id);
    }

    @Override
    public Reserva crear(Reserva reserva) {
        Habitacion habitacion = buscarHabitacion(reserva);
        Huesped huesped = buscarHuesped(reserva);

        validarDisponibilidad(habitacion, reserva, null);

        if (reserva.getEstado() == null) {
            reserva.setEstado(Estado.PENDIENTE);
        }

        reserva.setHabitacion(habitacion);
        reserva.setHuesped(huesped);

        return reservaRepo.save(reserva);
    }

    @Override
    public Reserva actualizar(Long id, Reserva reserva) {
        Reserva reservaExistente = reservaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe reserva con id: %s", id)));

        Habitacion habitacion = buscarHabitacion(reserva);
        Huesped huesped = buscarHuesped(reserva);

        validarDisponibilidad(habitacion, reserva, id);

        reservaExistente.setFechaIngreso(reserva.getFechaIngreso());
        reservaExistente.setFechaSalida(reserva.getFechaSalida());
        reservaExistente.setEstado(reserva.getEstado());
        reservaExistente.setTotalCalculado(reserva.getTotalCalculado());
        reservaExistente.setHabitacion(habitacion);
        reservaExistente.setHuesped(huesped);
        reservaExistente.setServicios(reserva.getServicios());

        return reservaRepo.save(reservaExistente);
    }

    @Override
    public void cancelar(Long id) {
        Reserva reservaExistente = reservaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe reserva con id: %s", id)));

        reservaExistente.setEstado(Estado.CANCELADA);
        reservaRepo.save(reservaExistente);
    }

    @Override
    public List<Reserva> listarPorHuesped(Long huespedId) {
        return reservaRepo.findByHuespedId(huespedId);
    }

    @Override
    public List<Reserva> listarPorEstado(Estado estado) {
        return reservaRepo.findByEstado(estado);
    }

    @Override
    public Reserva agregarServicio(Long reservaId, Long servicioId) {
        Reserva reserva = reservaRepo.findById(reservaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe reserva con id: %s", reservaId)));
        Servicio servicio = servicioRepo.findById(servicioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe servicio con id: %s", servicioId)));

        if (reserva.getServicios() == null) {
            reserva.setServicios(new ArrayList<>());
        }

        reserva.getServicios().add(servicio);
        return reservaRepo.save(reserva);
    }

    //---metodos auxiliares---

    private Habitacion buscarHabitacion(Reserva reserva) {
        if (reserva.getHabitacion() == null || reserva.getHabitacion().getId() == null) {
            throw new ResourceNotFoundException("Debe indicar una habitacion valida para la reserva");
        }

        return habitacionRepo.findById(reserva.getHabitacion().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe habitacion con id: %s", reserva.getHabitacion().getId())));
    }

    private Huesped buscarHuesped(Reserva reserva) {
        if (reserva.getHuesped() == null || reserva.getHuesped().getId() == null) {
            throw new ResourceNotFoundException("Debe indicar un huesped valido para la reserva");
        }

        return huespedRepo.findById(reserva.getHuesped().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No existe huesped con id: %s", reserva.getHuesped().getId())));
    }

    private void validarDisponibilidad(Habitacion habitacion, Reserva reserva, Long reservaIdExcluir) {
        if (Boolean.FALSE.equals(habitacion.getDisponible())) {
            throw new ConflictException(
                    String.format("La habitacion con id: %s no esta disponible", habitacion.getId()));
        }

        boolean existeCruce = reservaRepo.findReservasActivasPorHabitacion(habitacion.getId()).stream()
                .filter(reservaActiva -> !reservaActiva.getId().equals(reservaIdExcluir))
                .anyMatch(reservaActiva -> fechasSeCruzan(reservaActiva, reserva));

        if (existeCruce) {
            throw new ConflictException(
                    String.format("La habitacion con id: %s ya tiene una reserva activa en esas fechas",
                            habitacion.getId()));
        }
    }

    private boolean fechasSeCruzan(Reserva reservaActiva, Reserva nuevaReserva) {
        return !reservaActiva.getFechaSalida().isBefore(nuevaReserva.getFechaIngreso())
                && !reservaActiva.getFechaIngreso().isAfter(nuevaReserva.getFechaSalida());
    }
}
