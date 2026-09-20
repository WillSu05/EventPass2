package com.example.Evento.Service;

import com.example.Evento.DTO.Request.AsistenteRequestDTO;
import com.example.Evento.DTO.Response.AsistenteResponseDTO;

import java.util.List;

public interface AsistenteService {
    AsistenteResponseDTO registrar(AsistenteRequestDTO dto);
    AsistenteResponseDTO obtenerPorId(Long id);
    List<AsistenteResponseDTO> listarTodos();
    AsistenteResponseDTO actualizar(Long id, AsistenteRequestDTO dto);
    void eliminar(Long id);
}