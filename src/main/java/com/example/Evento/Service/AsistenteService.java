package com.example.Evento.Service;

import com.example.Evento.DTO.Request.AsistenteRequestDTO;
import com.example.Evento.DTO.Response.AsistenteResponseDTO;
import com.example.Evento.DTO.Response.UsuarioResponseDTO;

import java.util.List;

public interface AsistenteService {
    List<AsistenteResponseDTO> listartodos();
    AsistenteResponseDTO registrar(AsistenteRequestDTO dto);
    AsistenteResponseDTO obtenerPorId(Long id);
    boolean tieneOrdenesPendientes(Long asistenteId);
}