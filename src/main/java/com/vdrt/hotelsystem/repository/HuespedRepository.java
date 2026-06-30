package com.vdrt.hotelsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vdrt.hotelsystem.dto.huesped.HuespedResponseDTO;
import com.vdrt.hotelsystem.model.Huesped;

public interface HuespedRepository extends JpaRepository<Huesped, Long>{

    Optional<HuespedResponseDTO> findByEmail(String email);
    Optional<HuespedResponseDTO> findByDocumentoIdentidad(String documento);

    List<HuespedResponseDTO>  findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);


}
