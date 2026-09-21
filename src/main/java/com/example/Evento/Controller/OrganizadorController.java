package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.OrganizadorRequestDTO;
import com.example.Evento.DTO.Response.OrganizadorResponseDTO;
import com.example.Evento.Service.OrganizadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Organizador")
@RequiredArgsConstructor
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    @PostMapping
    public ResponseEntity<OrganizadorResponseDTO> registrar(@RequestBody OrganizadorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizadorService.registrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(organizadorService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(organizadorService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorResponseDTO> actualizar(@PathVariable Long id, @RequestBody OrganizadorRequestDTO dto) {
        return ResponseEntity.ok(organizadorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        organizadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}