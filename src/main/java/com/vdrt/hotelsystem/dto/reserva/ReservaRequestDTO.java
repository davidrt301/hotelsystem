package com.vdrt.hotelsystem.dto.reserva;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequestDTO {

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @FutureOrPresent(message = "La fecha de ingreso no puede ser en el pasado")
    private LocalDate fechaIngreso;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Future(message = "La fecha de salida debe ser en el futuro")
    private LocalDate fechaSalida;

    @NotNull(message = "El huésped es obligatorio")
    private Long huespedId;

    @NotNull(message = "La habitación es obligatoria")
    private Long habitacionId;

    // Lista de IDs de servicios ya existentes en BD
    private List<Long> servicioIds;
}
