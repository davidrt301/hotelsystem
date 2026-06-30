package com.vdrt.hotelsystem.dto.habitacion;

import com.vdrt.hotelsystem.model.enums.Tipo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitacionResponseDTO {

    private Long id;
    private String numero;
    private Tipo tipo;
    private double precioNoche;
    private boolean disponible;

    // Datos del hotel aplanados (no objeto anidado completo)
    private Long hotelId;
    private String hotelNombre;
    private String hotelCiudad;

}
