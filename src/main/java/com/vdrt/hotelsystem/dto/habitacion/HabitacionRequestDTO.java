package com.vdrt.hotelsystem.dto.habitacion;

import com.vdrt.hotelsystem.model.enums.Tipo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitacionRequestDTO {

    @NotBlank(message = "El número de habitación es obligatorio")
    private String numero;

    @NotNull(message = "El tipo es obligatorio")
    private Tipo tipo;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private Double precioNoche;

    private Boolean disponible = true;

    @NotNull(message = "El hotel es obligatorio")
    private Long hotelid;
}
