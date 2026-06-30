package com.vdrt.hotelsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vdrt.hotelsystem.dto.hotel.HotelMapper;
import com.vdrt.hotelsystem.dto.hotel.HotelRequestDTO;
import com.vdrt.hotelsystem.dto.hotel.HotelResponseDTO;
import com.vdrt.hotelsystem.exception.ResourceNotFoundException;
import com.vdrt.hotelsystem.model.Hotel;
import com.vdrt.hotelsystem.repository.HotelRepository;
import com.vdrt.hotelsystem.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepo;
    private final HotelMapper mapper;

    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper mapper) {
        this.hotelRepo = hotelRepository;
        this.mapper = mapper;
    }

    @Override
    public List<HotelResponseDTO> listarTodos() {
        return hotelRepo.findAll().stream().map(h->mapper.toResponseDTO(h)).toList();
    }

    @Override
    public Optional<HotelResponseDTO> buscarPorId(Long id) {
        return hotelRepo.findById(id).map(mapper::toResponseDTO);
    }

    @Override
    public List<HotelResponseDTO> buscarPorCiudad(String ciudad) {
        return hotelRepo.findByCiudad(ciudad).stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public HotelResponseDTO crear(HotelRequestDTO hotel) {
        Hotel crearHotel = hotelRepo.save(mapper.toEntity(hotel));
        return mapper.toResponseDTO(crearHotel);
    }

    @Override
public HotelResponseDTO actualizar(Long id, HotelRequestDTO hotel) {
    Hotel hotelExistente = hotelRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    String.format("No existe hotel con id: %s", id)));

    hotelExistente.setNombre(hotel.getNombre());
    hotelExistente.setCiudad(hotel.getCiudad());
    hotelExistente.setDireccion(hotel.getDireccion());
    hotelExistente.setTelefono(hotel.getTelefono());
    hotelExistente.setCategoria(hotel.getCategoria());

    Hotel hotelActualizado = hotelRepo.save(hotelExistente);

    return mapper.toResponseDTO(hotelActualizado);
}

    @Override
    public void eliminar(Long id) {
        hotelRepo.deleteById(id);
    }

    @Override
    public List<HotelResponseDTO> buscarConHabitacionesDisponibles() {
        return hotelRepo.findHotelesConHabitacionesDisponibles().stream().map(mapper::toResponseDTO).toList();
    }
}
