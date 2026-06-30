package com.vdrt.hotelsystem.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vdrt.hotelsystem.model.enums.Tipo;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    @Column(nullable = false)
    private Double precioNoche;
    private Boolean disponible;

    @JsonBackReference// evitar recursión con Hotel
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reservas = new ArrayList<>();

}
