package com.example.Evento.Service;

import com.example.Evento.DTO.Request.OrganizadorRequestDTO;
import com.example.Evento.DTO.Response.OrganizadorResponseDTO;

import java.util.List;

public interface OrganizadorService {
    OrganizadorResponseDTO registrar(OrganizadorRequestDTO dto);
    OrganizadorResponseDTO obtenerPorId(Long id);
    List<OrganizadorResponseDTO> listarTodos();
    OrganizadorResponseDTO actualizar(Long id, OrganizadorRequestDTO dto);
    void eliminar(Long id);
}