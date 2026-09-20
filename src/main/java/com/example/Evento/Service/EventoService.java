package com.example.Evento.Service;

import com.example.Evento.DTO.Request.EventoRequestDTO;
import com.example.Evento.DTO.Response.EventoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {
    List<EventoResponseDTO> listarEventos();
    EventoResponseDTO buscarEventoPorId(Long id);
    EventoResponseDTO crearEvento(EventoRequestDTO request);
    EventoResponseDTO actualizarEvento(Long id, EventoRequestDTO request);
    EventoResponseDTO publicarEvento(Long id);
    void eliminarEvento(Long id);

}

