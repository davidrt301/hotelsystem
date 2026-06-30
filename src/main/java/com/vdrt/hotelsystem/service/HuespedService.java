package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.dto.huesped.HuespedRequestDTO;
import com.vdrt.hotelsystem.dto.huesped.HuespedResponseDTO;

public interface HuespedService {

    List<HuespedResponseDTO> listarTodos();

    Optional<HuespedResponseDTO> buscarPorId(Long id);

    HuespedResponseDTO crear(HuespedRequestDTO huesped);

    HuespedResponseDTO actualizar(Long id, HuespedRequestDTO huesped);

    void eliminar(Long id);

    List<HuespedResponseDTO> buscarPorNombreOApellido(String texto);

}
