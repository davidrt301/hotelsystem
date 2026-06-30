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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdrt.hotelsystem.dto.huesped.HuespedRequestDTO;
import com.vdrt.hotelsystem.dto.huesped.HuespedResponseDTO;
import com.vdrt.hotelsystem.service.HuespedService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/huespedes")
public class HuespedController {

    private final HuespedService service;

    public HuespedController (HuespedService huesped){
        this.service = huesped;
    }

    @GetMapping
    public ResponseEntity<List<HuespedResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuespedResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(h -> ResponseEntity.ok(h))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<HuespedResponseDTO>> buscarPorNombreOApellido(@RequestParam String texto) {
        List<HuespedResponseDTO> huespedes = service.buscarPorNombreOApellido(texto);

        if (huespedes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(huespedes);
    }

    @PostMapping
    public ResponseEntity<HuespedResponseDTO> crear(@Valid @RequestBody HuespedRequestDTO huesped) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(huesped));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HuespedResponseDTO> actualizar(@PathVariable Long id,@Valid @RequestBody HuespedRequestDTO huesped) {
        return ResponseEntity.ok(service.actualizar(id, huesped));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
