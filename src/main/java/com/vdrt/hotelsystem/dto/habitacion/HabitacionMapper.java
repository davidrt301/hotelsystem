package com.vdrt.hotelsystem.dto.habitacion;

import org.springframework.stereotype.Component;

import com.vdrt.hotelsystem.model.Habitacion;
import com.vdrt.hotelsystem.model.Hotel;

@Component
public class HabitacionMapper {

    public HabitacionResponseDTO toResponseDTO(Habitacion habitacion) {
    HabitacionResponseDTO dto = new HabitacionResponseDTO();
    dto.setId(habitacion.getId());
    dto.setNumero(habitacion.getNumero());
    dto.setTipo(habitacion.getTipo());
    dto.setPrecioNoche(habitacion.getPrecioNoche());
    dto.setDisponible(habitacion.getDisponible());
    dto.setHotelId(habitacion.getHotel().getId());
    dto.setHotelNombre(habitacion.getHotel().getNombre());
    dto.setHotelCiudad(habitacion.getHotel().getCiudad());
    return dto;
}

public Habitacion toEntity(HabitacionRequestDTO dto, Hotel hotel) {
    Habitacion habitacion = new Habitacion();
    habitacion.setNumero(dto.getNumero());
    habitacion.setTipo(dto.getTipo());
    habitacion.setPrecioNoche(dto.getPrecioNoche());
    habitacion.setDisponible(dto.getDisponible());
    habitacion.setHotel(hotel); // hotel ya buscado en el service
    return habitacion;
}



}
