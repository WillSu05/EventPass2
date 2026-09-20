package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.ValidacionRequestDTO;
import com.example.Evento.DTO.Response.ValidacionResponseDTO;
import com.example.Evento.Service.ValidacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/validacion")
@RequiredArgsConstructor
public class ValidacionController {

    private final ValidacionService validacionService;

    @PostMapping("/validador/{validadorId}")
    public ResponseEntity<ValidacionResponseDTO> validarEntrada(
            @PathVariable Long validadorId,
            @RequestBody ValidacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(validacionService.validarEntrada(validadorId, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(validacionService.obtenerPorId(id));
    }

    @GetMapping("/validador/{validadorId}")
    public ResponseEntity<List<ValidacionResponseDTO>> listarPorValidador(@PathVariable Long validadorId) {
        return ResponseEntity.ok(validacionService.listarPorValidador(validadorId));
    }
}
