package com.vdrt.hotelsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdrt.hotelsystem.dto.hotel.HotelRequestDTO;
import com.vdrt.hotelsystem.dto.hotel.HotelResponseDTO;
import com.vdrt.hotelsystem.service.HotelService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService service;

    public HotelController (HotelService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> listarTodos() {
        return new ResponseEntity<>(service.listarTodos(),HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(h -> ResponseEntity.ok(h))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<HotelResponseDTO>> buscarConHabitacionesDisponibles() {
        return ResponseEntity.ok(service.buscarConHabitacionesDisponibles());
    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> crearHotel(@RequestBody @Valid HotelRequestDTO h) {
        return new ResponseEntity<>(service.crear(h),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> actualizar(@PathVariable Long id, @RequestBody @Valid HotelRequestDTO hotel) {
        return ResponseEntity.status(HttpStatus.OK).body(service.actualizar(id, hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){

        if(service.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    
    

}
