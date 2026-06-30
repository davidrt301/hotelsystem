package com.vdrt.hotelsystem.model;

import com.vdrt.hotelsystem.model.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Double totalCalculado;
    @ManyToOne
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    @ManyToMany
    @JoinTable(
            name = "reserva_servicio",
            joinColumns = @JoinColumn(name = "reserva_id"))
    private List<Servicio> servicios;
}
