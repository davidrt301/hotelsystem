package com.vdrt.hotelsystem.dto.servicio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;
}
