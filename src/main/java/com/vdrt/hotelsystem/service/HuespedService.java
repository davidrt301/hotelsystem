package com.vdrt.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.vdrt.hotelsystem.model.Huesped;

public interface HuespedService {

    List<Huesped> listarTodos();

    Optional<Huesped> buscarPorId(Long id);

    Huesped crear(Huesped huesped);

    Huesped actualizar(Long id, Huesped huesped);

    void eliminar(Long id);

    List<Huesped> buscarPorNombreOApellido(String texto);

}
