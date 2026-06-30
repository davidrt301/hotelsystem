package com.vdrt.hotelsystem.dto.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelResponseDTO {

    private Long id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private Integer categoria;
    private String telefono;
    private int totalHabitaciones; // dato calculado, no relación anidada
}

