package com.vdrt.hotelsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfiles_contacto")
public class PerfilContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefono;
    private String telefonoEmergencia;
    private String direccion;
    private String pais;
    private String nacionalidad;

    @JsonIgnore
    @OneToOne(mappedBy = "perfilContacto")
    private Huesped huesped;
}
