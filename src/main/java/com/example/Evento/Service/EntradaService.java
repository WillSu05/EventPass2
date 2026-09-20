package com.example.Evento.Service;

import com.example.Evento.DTO.Response.EntradaResponseDTO;

public interface EntradaService {
    EntradaResponseDTO obtenerPorId(Long id);
    EntradaResponseDTO obtenerPorCodigo(String codigo);
}