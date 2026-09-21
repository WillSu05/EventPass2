package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.EventoRequestDTO;
import com.example.Evento.DTO.Response.EventoResponseDTO;
import com.example.Evento.Service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
@RequiredArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @GetMapping("/listarEventos")
    public List<EventoResponseDTO> listar() {
        return eventoService.listarEventos();
    }

    @GetMapping("/listarEvento/{id}")
    public ResponseEntity<EventoResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(eventoService.buscarEventoPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crearEvento")
    public ResponseEntity<EventoResponseDTO> crear(@RequestBody EventoRequestDTO request) {
        try {
            return ResponseEntity.ok(eventoService.crearEvento(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/actualizarEvento/{id}")
    public ResponseEntity<EventoResponseDTO> actualizar(@PathVariable Long id, @RequestBody EventoRequestDTO request) {
        try {
            return ResponseEntity.ok(eventoService.actualizarEvento(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/publicarEvento")
    public ResponseEntity<EventoResponseDTO> publicar(@PathVariable Long id, EventoRequestDTO requestDTO) {
        try {
            return ResponseEntity.ok(eventoService.publicarEvento(id, requestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminarEvento/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
