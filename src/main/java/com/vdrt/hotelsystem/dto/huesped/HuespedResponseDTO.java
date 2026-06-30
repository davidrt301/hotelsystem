package com.vdrt.hotelsystem.dto.huesped;

import com.vdrt.hotelsystem.dto.perfilcontacto.PerfilContactoResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HuespedResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private String email;
    private PerfilContactoResponseDTO perfilContacto; // aquí sí anidamos, es @OneToOne propio

}
