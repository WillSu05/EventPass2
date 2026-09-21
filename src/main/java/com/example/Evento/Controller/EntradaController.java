package com.example.Evento.Controller;

import com.example.Evento.DTO.Response.EntradaResponseDTO;
import com.example.Evento.Service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrada")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    @GetMapping("/{id}")
    public ResponseEntity<EntradaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entradaService.obtenerPorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<EntradaResponseDTO> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(entradaService.obtenerPorCodigo(codigo));
    }
}
