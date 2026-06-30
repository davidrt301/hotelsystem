package com.vdrt.hotelsystem.dto.reserva;

import java.time.LocalDate;
import java.util.List;

import com.vdrt.hotelsystem.dto.servicio.ServicioResponseDTO;
import com.vdrt.hotelsystem.model.enums.Estado;
import com.vdrt.hotelsystem.model.enums.Tipo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaResponseDTO {

    private Long id;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Estado estado;
    private Double totalCalculado;

    // Datos aplanados del huésped
    private Long huespedId;
    private String huespedNombre;
    private String huespedApellido;
    private String huespedDocumento;

    // Datos aplanados de la habitación
    private Long habitacionId;
    private String habitacionNumero;
    private Tipo habitacionTipo;
    private double habitacionPrecioNoche;

    // Servicios como lista de DTOs (ya son datos simples, no generan ciclos)
    private List<ServicioResponseDTO> servicios;
}
