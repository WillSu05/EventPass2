package com.example.Evento.Controller;

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

    @GetMapping("/all")
    public List<Lugar> listar() {
        return lugarService.listarLugares();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Lugar> buscarPorId(@PathVariable Long id) {
        return lugarService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/crear")
    public Lugar crear(@RequestBody Lugar lugar) {
        return lugarService.crearLugar(lugar);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Lugar> actualizar(@PathVariable Long id, @RequestBody Lugar lugarDetalles) {
        try {
            Lugar actualizado = lugarService.actualizarLugar(id, lugarDetalles);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        lugarService.eliminarLugar(id);
        return ResponseEntity.noContent().build();
    }

}
