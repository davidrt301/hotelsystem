package com.vdrt.hotelsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vdrt.hotelsystem.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByHuespedId(Long huespedId);

    List<Reserva> findByEstado(String estado);

    @Query("SELECT r FROM Reserva r WHERE r.fechaIngreso <= :fechaFin AND r.fechaSalida >= :fechaInicio")
    List<Reserva> findReservasEntreFechas(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("""
            SELECT r FROM Reserva r
            WHERE r.habitacion.id = :habitacionId
            AND r.estado IN (
                com.vdrt.hotelsystem.model.enums.Estado.CONFIRMADA,
                com.vdrt.hotelsystem.model.enums.Estado.PENDIENTE
            )
            """)
    List<Reserva> findReservasActivasPorHabitacion(@Param("habitacionId") Long habitacionId);

}
