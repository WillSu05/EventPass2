package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.OrdenRequestDTO;
import com.example.Evento.DTO.Response.OrdenResponseDTO;
import com.example.Evento.Service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<OrdenResponseDTO> crear(@RequestBody OrdenRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crearOrden(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<OrdenResponseDTO>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ordenService.obtenerPorUsuario(usuarioId));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenResponseDTO> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(ordenService.cambiarEstado(id, nuevoEstado));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<OrdenResponseDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.cancelarOrden(id));
    }
}