package com.vdrt.hotelsystem.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoteles") // Especifica el nombre de la tabla en la base de datos
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String ciudad;
    
    private String direccion;
    private Integer categoria;
    private String telefono;

    @JsonManagedReference // Para evitar problemas de referencia circular al serializar a JSON
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,orphanRemoval = true) //El atributo mappedBy en @OneToMany le dice a JPA que la otra entidad (Habitacion) es la que tiene la columna de clave foránea, y que el atributo que la contiene se llama 'hotel'.
    private List<Habitacion> habitaciones = new ArrayList<>();

}
