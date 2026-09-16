package com.example.Evento.Controller;

import com.example.Evento.Entity.Extends.Administrador;
import com.example.Evento.Service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<Administrador>> listarTodos() {
        return ResponseEntity.ok(administradorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Administrador> registrar(@RequestBody Administrador administrador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.registrarAdministrador(administrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizar(@PathVariable Long id, @RequestBody Administrador detalles) {
        return ResponseEntity.ok(administradorService.actualizarAdministrador(id, detalles));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return ResponseEntity.noContent().build();
    }
}