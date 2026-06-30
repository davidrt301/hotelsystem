package com.vdrt.hotelsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vdrt.hotelsystem.model.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Long>{

    List<Habitacion> findByHotelId(Long hotelId);

    List<Habitacion>  findByDisponibleAndHotelId(Boolean disponible, Long hotelId);

    List<Habitacion> findByTipo(String tipo);

    @Query("SELECT h FROM Habitacion h WHERE h.precioNoche <= ?1")
    List<Habitacion> findByHabitacionCuyoPrecioSeaMenor(Double maxNoche);

}
