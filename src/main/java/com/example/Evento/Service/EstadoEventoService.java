package com.example.Evento.Service;

import com.example.Evento.DTO.Request.EstadoEventoRequestDTO;
import com.example.Evento.DTO.Response.EstadoEventoResponseDTO;

import java.util.List;

public interface EstadoEventoService {
    List<EstadoEventoResponseDTO> listarEstadoEvento();
    EstadoEventoResponseDTO crearEstadoEvento(EstadoEventoRequestDTO request);
}
