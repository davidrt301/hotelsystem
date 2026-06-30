package com.vdrt.hotelsystem.dto.perfilcontacto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilContactoRequestDTO {

    private String telefono;
    private String telefonoEmergencia;
    private String direccion;
    private String pais;
    private String nacionalidad;

}
