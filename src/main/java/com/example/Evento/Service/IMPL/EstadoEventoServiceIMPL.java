package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.EstadoEventoRequestDTO;
import com.example.Evento.DTO.Response.EstadoEventoResponseDTO;
import com.example.Evento.Entity.EstadoEvento;
import com.example.Evento.Repository.EstadoEventoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EstadoEventoServiceIMPL {
    private final EstadoEventoRepository estadoEventoRepository;

    public List<EstadoEventoResponseDTO> listarEstados() {
        return estadoEventoRepository.findAll().stream()
                .map(e -> new EstadoEventoResponseDTO(e.getNombre()))
                .collect(Collectors.toList());
    }

    public EstadoEventoResponseDTO crearEstado(EstadoEventoRequestDTO request) {
        EstadoEvento estado = new EstadoEvento();
        estado.setId(request.getId());
        estado.setNombre(request.getNombre());
        EstadoEvento guardado = estadoEventoRepository.save(estado);
        EstadoEventoResponseDTO dto = new EstadoEventoResponseDTO();
        dto.setNombre(guardado.getNombre());
        return dto;
    }


}
