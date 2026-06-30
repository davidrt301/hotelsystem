package com.vdrt.hotelsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vdrt.hotelsystem.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCiudad(String ciudad);

    List<Hotel> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT DISTINCT h FROM Hotel h JOIN h.habitaciones hb WHERE hb.disponible = true")
    List<Hotel> findHotelesConHabitacionesDisponibles();

}
