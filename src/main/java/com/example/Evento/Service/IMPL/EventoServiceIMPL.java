package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.EventoRequestDTO;
import com.example.Evento.DTO.Request.LugarRequestDTO;
import com.example.Evento.DTO.Response.EstadoEventoResponseDTO;
import com.example.Evento.DTO.Response.EventoResponseDTO;
import com.example.Evento.DTO.Response.LugarResponseDTO;
import com.example.Evento.Entity.EstadoEvento;
import com.example.Evento.Entity.Evento;
import com.example.Evento.Entity.Extends.Organizador;
import com.example.Evento.Entity.Lugar;
import com.example.Evento.Exceptions.ResourceNotFoundException;
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

        LugarResponseDTO lugarDto = null;
        if (e.getLugar() != null) {
            lugarDto = new LugarResponseDTO();
            lugarDto.setNombre(e.getLugar().getNombre());
            lugarDto.setDireccion(e.getLugar().getDireccion());
            lugarDto.setCapacidad(e.getLugar().getCapacidad());
        }

        EstadoEventoResponseDTO estadoDto;
        if (e.getEstado() != null) {
            estadoDto = new EstadoEventoResponseDTO();
            estadoDto.setNombre(e.getEstado().getNombre());
        } else {
            estadoDto = new EstadoEventoResponseDTO();
            estadoDto.setNombre("Borrador");
        }

        String nombreOrganizador = e.getOrganizador() != null ? e.getOrganizador().getNombre() : "Desconocido";
        return new EventoResponseDTO(
                e.getNombre(),
                e.getDescripcion(),
                e.getFechaHora(),
                lugarDto,
                estadoDto,
                nombreOrganizador
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
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado"));

        Organizador organizador = organizadorRepository.findById(request.getOrganizadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizador no encontrado"));

        EstadoEvento estado = estadoEventoRepository.findById(request.getEstadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de evento no encontrado"));

        Evento evento = new Evento();
        evento.setNombre(request.getNombre());
        evento.setDescripcion(request.getDescripcion());
        evento.setFechaHora(request.getFechaHora());
        evento.setLugar(lugar);
        evento.setOrganizador(organizador);
        evento.setEstado(estado);

        Evento guardado = eventoRepository.save(evento);
        return convertirADto(guardado);
    }

    @Override
    public EventoResponseDTO actualizarEvento(Long id, EventoRequestDTO request) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con ID: " + id));

        Lugar lugar = lugarRepository.findById(request.getLugarId())
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado"));

        Organizador organizador = organizadorRepository.findById(request.getOrganizadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizador no encontrado"));

        EstadoEvento estado = estadoEventoRepository.findById(request.getEstadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        evento.setNombre(request.getNombre());
        evento.setDescripcion(request.getDescripcion());
        evento.setFechaHora(request.getFechaHora());
        evento.setLugar(lugar);
        evento.setOrganizador(organizador);
        evento.setEstado(estado);

        Evento actualizado = eventoRepository.save(evento);
        return convertirADto(actualizado);
    }

    @Override
    public EventoResponseDTO publicarEvento(Long id, EventoRequestDTO requestDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));

        EstadoEvento estadoPublicado = estadoEventoRepository.findById(requestDTO.getEstadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado 'Publicado' no encontrado en el catálogo"));

        evento.setEstado(estadoPublicado);
        Evento publicado = eventoRepository.save(evento);
        return convertirADto(publicado);
    }

    @Override
    public void eliminarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }


}
