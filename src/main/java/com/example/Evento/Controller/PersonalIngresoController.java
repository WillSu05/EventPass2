package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.PersonalIngresoRequestDTO;
import com.example.Evento.DTO.Response.PersonalIngresoResponseDTO;
import com.example.Evento.Service.PersonalIngresoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/PersonalIngreso")
@RequiredArgsConstructor
public class PersonalIngresoController {

    private final PersonalIngresoService personalIngresoService;

    @PostMapping
    public ResponseEntity<PersonalIngresoResponseDTO> registrar(@RequestBody PersonalIngresoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personalIngresoService.registrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalIngresoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personalIngresoService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonalIngresoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(personalIngresoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalIngresoResponseDTO> actualizar(@PathVariable Long id, @RequestBody PersonalIngresoRequestDTO dto) {
        return ResponseEntity.ok(personalIngresoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personalIngresoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}