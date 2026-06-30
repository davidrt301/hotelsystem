package com.vdrt.hotelsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "huespedes")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true,nullable = false)
    private String documentoIdentidad;
    @Column(unique = true,nullable = false)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_contacto_id")
    private PerfilContacto perfilContacto;
    @OneToMany(mappedBy = "huesped", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserva> reservas;
}
