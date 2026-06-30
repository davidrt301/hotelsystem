package com.vdrt.hotelsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdrt.hotelsystem.dto.habitacion.HabitacionRequestDTO;
import com.vdrt.hotelsystem.dto.habitacion.HabitacionResponseDTO;
import com.vdrt.hotelsystem.service.HabitacionService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class HabitacionController {

    private final HabitacionService service;

    public HabitacionController (HabitacionService hService){
        this.service = hService;
    }

    @GetMapping
    public ResponseEntity<List<HabitacionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
    
    @GetMapping("/habitaciones/{id}")
    public ResponseEntity<HabitacionResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(h -> ResponseEntity.ok(h))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hotels/{hotelId}/habitaciones/disponibles")
    public ResponseEntity<List<HabitacionResponseDTO>> listarDisponiblesPorHotel(@PathVariable(name = "hotelId") Long id) {
        if (service.listarDisponiblesPorHotel(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.listarDisponiblesPorHotel(id));
    }
    
    @GetMapping("/habitaciones/precio-maximo")//GET /api/habitaciones/precio-maximo?precio=200000
    public ResponseEntity<List<HabitacionResponseDTO>> habitacionesPorPrecio(@RequestParam Double precio) {
        return ResponseEntity.ok(service.buscarPorPrecioMaximo(precio));
    }

    @PostMapping("/hotels/{hotelId}/habitaciones")
    public ResponseEntity<HabitacionResponseDTO> crearHabitacion(@RequestBody @Valid HabitacionRequestDTO h,@PathVariable(name = "hotelId") Long id) {
        return ResponseEntity.ok(service.crear(h, id));
    }

    @PutMapping("/habitaciones/{id}")
    public ResponseEntity<HabitacionResponseDTO> actualizarHabitacion(@PathVariable Long id, @RequestBody @Valid HabitacionRequestDTO h) {
        return ResponseEntity.ok(service.actualizar(id, h));
    }
    
    @DeleteMapping("/habitaciones/{id}")
    public ResponseEntity<Void> eliminar(Long id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
