package com.vdrt.hotelsystem.dto.servicio;

import org.springframework.stereotype.Component;

import com.vdrt.hotelsystem.model.Servicio;

@Component
public class ServicioMapper {

    public ServicioResponseDTO toResponseDTO(Servicio servicio) {
    ServicioResponseDTO dto = new ServicioResponseDTO();
    dto.setId(servicio.getId());
    dto.setNombre(servicio.getNombre());
    dto.setDescripcion(servicio.getDescripcion());
    dto.setPrecio(servicio.getPrecio());
    dto.setDisponible(servicio.getDisponible());
    return dto;
}

public Servicio toEntity(ServicioRequestDTO dto) {
    Servicio servicio = new Servicio();
    servicio.setNombre(dto.getNombre());
    servicio.setDescripcion(dto.getDescripcion());
    servicio.setPrecio(dto.getPrecio());
    servicio.setDisponible(dto.isDisponible());
    return servicio;
}
}
