package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.EstadoEventoRequestDTO;
import com.example.Evento.DTO.Response.EstadoEventoResponseDTO;
import com.example.Evento.Service.EstadoEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadoEvento")
@RequiredArgsConstructor
public class EstadoEventoController {

    private final EstadoEventoService estadoEventoService;

    @GetMapping("/listarEstadosEvento")
    public List<EstadoEventoResponseDTO> listar() {
        return estadoEventoService.listarEstadoEvento(); }

    @PostMapping("/crearEstadoEvento")
    public EstadoEventoResponseDTO crear(@RequestBody EstadoEventoRequestDTO request) {
        return estadoEventoService.crearEstadoEvento(request); }
}
