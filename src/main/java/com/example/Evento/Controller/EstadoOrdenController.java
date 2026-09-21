package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.EstadoOrdenRequestDTO;
import com.example.Evento.DTO.Response.EstadoOrdenRespondeDTO;
import com.example.Evento.Service.EstadoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadoOrden")
@RequiredArgsConstructor
public class EstadoOrdenController {
    private final EstadoOrdenService service;

    @GetMapping("/listarEstadosOrden")
    public List<EstadoOrdenRespondeDTO> listar() {
        return service.listarEstados(); }

    @PostMapping("/crearEstadoOrden")
    public EstadoOrdenRespondeDTO crear(@RequestBody EstadoOrdenRequestDTO request) {
        return service.crearEstado(request); }
}

