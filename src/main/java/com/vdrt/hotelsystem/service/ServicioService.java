package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.dto.servicio.ServicioRequestDTO;
import com.vdrt.hotelsystem.dto.servicio.ServicioResponseDTO;

public interface ServicioService {

    List<ServicioResponseDTO> listarTodos();

    List<ServicioResponseDTO> listarDisponibles();

    Optional<ServicioResponseDTO> buscarPorId(Long id);

    ServicioResponseDTO crear(ServicioRequestDTO dto);

    ServicioResponseDTO actualizar(Long id, ServicioRequestDTO dto);

    void eliminar(Long id);
}
