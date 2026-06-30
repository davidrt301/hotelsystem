package com.vdrt.hotelsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vdrt.hotelsystem.model.Servicio;


public interface ServicioRepository extends JpaRepository<Servicio, Long>{

    List<Servicio> findByDisponible(Boolean disponible);

    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

}
