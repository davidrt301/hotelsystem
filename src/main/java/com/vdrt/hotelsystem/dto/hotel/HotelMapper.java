package com.vdrt.hotelsystem.dto.hotel;

import org.springframework.stereotype.Component;

import com.vdrt.hotelsystem.model.Hotel;

@Component
public class HotelMapper {

    // Entidad → ResponseDTO
public HotelResponseDTO toResponseDTO(Hotel hotel) {
    HotelResponseDTO dto = new HotelResponseDTO();
    dto.setId(hotel.getId());
    dto.setNombre(hotel.getNombre());
    dto.setCiudad(hotel.getCiudad());
    dto.setDireccion(hotel.getDireccion());
    dto.setTelefono(hotel.getTelefono());
    dto.setCategoria(hotel.getCategoria());
    dto.setTotalHabitaciones(
        hotel.getHabitaciones() != null ? hotel.getHabitaciones().size() : 0
    );
    return dto;
}

// RequestDTO → Entidad
public Hotel toEntity(HotelRequestDTO dto) {
    Hotel hotel = new Hotel();
    hotel.setNombre(dto.getNombre());
    hotel.setCiudad(dto.getCiudad());
    hotel.setDireccion(dto.getDireccion());
    hotel.setTelefono(dto.getTelefono());
    hotel.setCategoria(dto.getCategoria());
    return hotel;
}

}
