package com.example.Evento.Service;

import com.example.Evento.DTO.Request.ValidacionRequestDTO;
import com.example.Evento.DTO.Response.ValidacionResponseDTO;

import java.util.List;

public interface ValidacionService {
    ValidacionResponseDTO validarEntrada(Long validadorId, ValidacionRequestDTO dto);
    ValidacionResponseDTO obtenerPorId(Long id);
    List<ValidacionResponseDTO> listarPorValidador(Long validadorId);
}
