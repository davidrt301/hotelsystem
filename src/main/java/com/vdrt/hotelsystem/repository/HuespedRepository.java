package com.vdrt.hotelsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vdrt.hotelsystem.model.Huesped;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long>{

    Optional<Huesped> findByEmail(String email);
    Optional<Huesped> findByDocumentoIdentidad(String documento);

    List<Huesped>  findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);


}
