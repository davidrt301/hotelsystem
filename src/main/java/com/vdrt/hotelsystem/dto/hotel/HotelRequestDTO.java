package com.vdrt.hotelsystem.dto.hotel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    private String direccion;

    @Min(value = 1, message = "La categoría mínima es 1")
    @Max(value = 5, message = "La categoría máxima es 5")
    private Integer categoria;

    private String telefono;
}
