package com.example.Evento.Service;

import com.example.Evento.DTO.Request.RolRequestDTO;
import com.example.Evento.DTO.Response.RolResponseDTO;
import com.example.Evento.Entity.Rol;
import java.util.List;

public interface RolService {
    List<RolResponseDTO> listarRoles();
    RolResponseDTO crearRol(RolRequestDTO requestDTO);
}