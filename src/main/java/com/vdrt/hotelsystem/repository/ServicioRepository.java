package com.vdrt.hotelsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vdrt.hotelsystem.model.Hotel;
import com.vdrt.hotelsystem.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{

    List<Servicio> findByDisponible(Boolean disponible);

    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

}
