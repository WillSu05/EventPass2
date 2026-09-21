package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.TipoEntradaRequesDTO;
import com.example.Evento.DTO.Response.TipoEntradaResponseDTO;
import com.example.Evento.Service.TipoEntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoEntrada")
@RequiredArgsConstructor
public class TipoEntradaController {

    private final TipoEntradaService tipoEntradaService;

    @GetMapping("/evento/{idEvento}")
    public List<TipoEntradaResponseDTO> listarPorEvento(@PathVariable Long idEvento) {
        return tipoEntradaService.listarPorEvento(idEvento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEntradaResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tipoEntradaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoEntradaResponseDTO> crear(@RequestBody TipoEntradaRequesDTO request) {
        try {
            return ResponseEntity.ok(tipoEntradaService.crearTipoEntrada(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEntradaResponseDTO> actualizar(@PathVariable Long id, @RequestBody TipoEntradaRequesDTO request) {
        try {
            return ResponseEntity.ok(tipoEntradaService.actualizarTipoEntrada(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            tipoEntradaService.eliminarTipoEntrada(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

