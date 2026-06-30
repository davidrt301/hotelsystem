package com.vdrt.hotelsystem.dto.reserva;


import java.util.List;

import org.springframework.stereotype.Component;

import com.vdrt.hotelsystem.dto.servicio.ServicioResponseDTO;
import com.vdrt.hotelsystem.model.Reserva;

@Component
public class ReservaMapper {

    public ReservaResponseDTO toResponseDTO(Reserva reserva) {
    ReservaResponseDTO dto = new ReservaResponseDTO();
    dto.setId(reserva.getId());
    dto.setFechaIngreso(reserva.getFechaIngreso());
    dto.setFechaSalida(reserva.getFechaSalida());
    dto.setEstado(reserva.getEstado());
    dto.setTotalCalculado(reserva.getTotalCalculado());

    // Huésped aplanado
    dto.setHuespedId(reserva.getHuesped().getId());
    dto.setHuespedNombre(reserva.getHuesped().getNombre());
    dto.setHuespedApellido(reserva.getHuesped().getApellido());
    dto.setHuespedDocumento(reserva.getHuesped().getDocumentoIdentidad());

    // Habitación aplanada
    dto.setHabitacionId(reserva.getHabitacion().getId());
    dto.setHabitacionNumero(reserva.getHabitacion().getNumero());
    dto.setHabitacionTipo(reserva.getHabitacion().getTipo());
    dto.setHabitacionPrecioNoche(reserva.getHabitacion().getPrecioNoche());

    // Servicios
    List<ServicioResponseDTO> serviciosDTO = reserva.getServicios().stream()
        .map(s -> {
            ServicioResponseDTO sDto = new ServicioResponseDTO();
            sDto.setId(s.getId());
            sDto.setNombre(s.getNombre());
            sDto.setDescripcion(s.getDescripcion());
            sDto.setPrecio(s.getPrecio());
            sDto.setDisponible(s.getDisponible());
            return sDto;
        })
        .toList();
    dto.setServicios(serviciosDTO);

    return dto;
}
}
