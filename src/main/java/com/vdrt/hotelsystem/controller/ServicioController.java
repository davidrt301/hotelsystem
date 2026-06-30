package com.vdrt.hotelsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdrt.hotelsystem.dto.servicio.ServicioRequestDTO;
import com.vdrt.hotelsystem.dto.servicio.ServicioResponseDTO;
import com.vdrt.hotelsystem.service.ServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService service;

    public ServicioController (ServicioService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ServicioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ServicioResponseDTO>> listarDisponibles() {
        List<ServicioResponseDTO> servicios = service.listarDisponibles();

        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(s -> ResponseEntity.ok(s))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServicioResponseDTO> crear(@RequestBody @Valid ServicioRequestDTO servicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioResponseDTO> actualizar(@PathVariable Long id, @RequestBody ServicioRequestDTO servicio) {
        return ResponseEntity.ok(service.actualizar(id, servicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
