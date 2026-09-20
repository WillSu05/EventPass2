package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.EventoRequestDTO;
import com.example.Evento.DTO.Request.LugarRequestDTO;
import com.example.Evento.DTO.Response.EventoResponseDTO;
import com.example.Evento.DTO.Response.LugarResponseDTO;
import com.example.Evento.Entity.EstadoEvento;
import com.example.Evento.Entity.Evento;
import com.example.Evento.Entity.Extends.Organizador;
import com.example.Evento.Entity.Lugar;
import com.example.Evento.Repository.EstadoEventoRepository;
import com.example.Evento.Repository.EventoRepository;
import com.example.Evento.Repository.LugarRepository;
import com.example.Evento.Repository.OrganizadorRepository;
import com.example.Evento.Service.EventoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EventoServiceIMPL implements EventoService {
    private final EventoRepository eventoRepository;
    private final LugarRepository lugarRepository;
    private final OrganizadorRepository organizadorRepository;
    private final EstadoEventoRepository estadoEventoRepository;

    private EventoResponseDTO convertirADto(Evento e) {
        LugarRequestDTO lugarDto = null;
        if (e.getLugar() != null) {
            lugarDto = new LugarRequestDTO();
            lugarDto.setNombre(e.getLugar().getNombre());
            lugarDto.setDireccion(e.getLugar().getDireccion());
            lugarDto.setCapacidad(e.getLugar().getCapacidad());
        }

        return new EventoResponseDTO(
                e.getNombre(),
                e.getDescripcion(),
                e.getFechaHora(),
                new LugarResponseDTO(),
                e.getEstado() != null ? e.getEstado().getNombre() : "Borrador",
                e.getOrganizador() != null ? e.getOrganizador().getNombre() : "Desconocido"
        );
    }

    @Override
    public List<EventoResponseDTO> listarEventos() {
        return eventoRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public EventoResponseDTO buscarEventoPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
        return convertirADto(evento);
    }

    @Override
    public EventoResponseDTO crearEvento(EventoRequestDTO request) {
        Lugar lugar = lugarRepository.findById(request.getLugarId())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
        Organizador organizador = organizadorRepository.findById(request.getOrganizadorId())
                .orElseThrow(() -> new RuntimeException("Organizador no encontrado"));

        Evento evento = new Evento();
        evento.setNombre(request.getNombre());
        evento.setDescripcion(request.getDescripcion());
        evento.setFechaHora(request.getFechaHora());
        evento.setLugar(lugar);
        evento.setOrganizador(organizador);

        Evento guardado = eventoRepository.save(evento);
        return convertirADto(guardado);
    }

    @Override
    public EventoResponseDTO actualizarEvento(Long id, EventoRequestDTO request) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        Lugar lugar = lugarRepository.findById(request.getLugarId())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        evento.setNombre(request.getNombre());
        evento.setDescripcion(request.getDescripcion());
        evento.setFechaHora(request.getFechaHora());
        evento.setLugar(lugar);

        Evento actualizado = eventoRepository.save(evento);
        return convertirADto(actualizado);
    }

    @Override
    public EventoResponseDTO publicarEvento(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        EstadoEvento estadoPublicado = estadoEventoRepository.findById(Long.valueOf("PUB"))
                .orElseThrow(() -> new RuntimeException("Estado 'Publicado' no encontrado en el catálogo"));

        evento.setEstado(estadoPublicado);
        Evento publicado = eventoRepository.save(evento);
        return convertirADto(publicado);
    }

    @Override
    public void eliminarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }


}
