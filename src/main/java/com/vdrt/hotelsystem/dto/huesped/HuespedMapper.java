package com.vdrt.hotelsystem.dto.huesped;

import org.springframework.stereotype.Component;

import com.vdrt.hotelsystem.dto.perfilcontacto.PerfilContactoResponseDTO;
import com.vdrt.hotelsystem.model.Huesped;
import com.vdrt.hotelsystem.model.PerfilContacto;

@Component
public class HuespedMapper {

    public HuespedResponseDTO toResponseDTO(Huesped huesped) {
    HuespedResponseDTO dto = new HuespedResponseDTO();
    dto.setId(huesped.getId());
    dto.setNombre(huesped.getNombre());
    dto.setApellido(huesped.getApellido());
    dto.setDocumentoIdentidad(huesped.getDocumentoIdentidad());
    dto.setEmail(huesped.getEmail());

    if (huesped.getPerfilContacto() != null) {
        PerfilContactoResponseDTO perfilDTO = new PerfilContactoResponseDTO();
        perfilDTO.setId(huesped.getPerfilContacto().getId());
        perfilDTO.setTelefono(huesped.getPerfilContacto().getTelefono());
        perfilDTO.setTelefonoEmergencia(huesped.getPerfilContacto().getTelefonoEmergencia());
        perfilDTO.setDireccion(huesped.getPerfilContacto().getDireccion());
        perfilDTO.setPais(huesped.getPerfilContacto().getPais());
        perfilDTO.setNacionalidad(huesped.getPerfilContacto().getNacionalidad());
        dto.setPerfilContacto(perfilDTO);
    }

    return dto;
}

public Huesped toEntity(HuespedRequestDTO dto) {
    Huesped huesped = new Huesped();
    huesped.setNombre(dto.getNombre());
    huesped.setApellido(dto.getApellido());
    huesped.setDocumentoIdentidad(dto.getDocumentoIdentidad());
    huesped.setEmail(dto.getEmail());

    if (dto.getPerfilContacto() != null) {
        PerfilContacto perfil = new PerfilContacto();
        perfil.setTelefono(dto.getPerfilContacto().getTelefono());
        perfil.setTelefonoEmergencia(dto.getPerfilContacto().getTelefonoEmergencia());
        perfil.setDireccion(dto.getPerfilContacto().getDireccion());
        perfil.setPais(dto.getPerfilContacto().getPais());
        perfil.setNacionalidad(dto.getPerfilContacto().getNacionalidad());
        huesped.setPerfilContacto(perfil);
    }

    return huesped;
}

}
