package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.EstadoOrdenRequestDTO;
import com.example.Evento.DTO.Response.EstadoOrdenRespondeDTO;
import com.example.Evento.Entity.EstadoOrden;
import com.example.Evento.Repository.EstadoOrdenRepository;
import com.example.Evento.Service.EstadoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoOrdenServiceIMPL implements EstadoOrdenService {
    private final EstadoOrdenRepository repository;

    @Override
    public List<EstadoOrdenRespondeDTO> listarEstados() {
        return repository.findAll().stream()
                .map(e -> new EstadoOrdenRespondeDTO(e.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public EstadoOrdenRespondeDTO crearEstado(EstadoOrdenRequestDTO request) {
        EstadoOrden estado = new EstadoOrden();
        estado.setNombre(request.getNombre());
        EstadoOrden guardado = repository.save(estado);

        return new EstadoOrdenRespondeDTO(guardado.getNombre());
    }
}
