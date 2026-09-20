package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.AsistenteRequestDTO;
import com.example.Evento.DTO.Response.AsistenteResponseDTO;
import com.example.Evento.Service.AsistenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Asistente")
@RequiredArgsConstructor
public class AsistenteController {

    private final AsistenteService asistenteService;

    @PostMapping
    public ResponseEntity<AsistenteResponseDTO> registrar(@RequestBody AsistenteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenteService.registrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(asistenteService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AsistenteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(asistenteService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenteResponseDTO> actualizar(@PathVariable Long id, @RequestBody AsistenteRequestDTO dto) {
        return ResponseEntity.ok(asistenteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asistenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}