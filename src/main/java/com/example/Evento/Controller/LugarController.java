package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.LugarRequestDTO;
import com.example.Evento.DTO.Response.LugarResponseDTO;
import com.example.Evento.Entity.Lugar;
import com.example.Evento.Service.LugarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lugares")
public class LugarController {
    private final LugarService lugarService;

    @GetMapping("/listarLugares")
    public List<LugarResponseDTO> listar() {
        return lugarService.listarLugares();
    }

    @GetMapping("/buscarLugar/{id}")
    public ResponseEntity<LugarResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lugarService.buscarLugarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crearLugar")
    public ResponseEntity<LugarResponseDTO> crear(@RequestBody LugarRequestDTO request) {
        try {
            return ResponseEntity.ok(lugarService.crearLugar(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/actualizarLugar/{id}")
    public ResponseEntity<LugarResponseDTO> actualizar(@PathVariable Long id, @RequestBody LugarRequestDTO request) {
        try {
            return ResponseEntity.ok(lugarService.actualizarLugar(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarLugar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            lugarService.eliminarLugar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
