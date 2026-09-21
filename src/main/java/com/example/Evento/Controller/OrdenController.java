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
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<OrdenResponseDTO> crearOrden(@RequestBody OrdenRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crearOrden(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerPorId(id));
    }

    @GetMapping("/asistente/{asistenteId}")
    public ResponseEntity<List<OrdenResponseDTO>> obtenerPorAsistente(@PathVariable Long asistenteId) {
        return ResponseEntity.ok(ordenService.obtenerPorAsistente(asistenteId));
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<OrdenResponseDTO> procesarPago(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.procesarPago(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<OrdenResponseDTO> cancelarOrden(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.cancelarOrden(id));
    }

    @GetMapping("/asistente/{asistenteId}/total-comprado")
    public ResponseEntity<Double> obtenerTotalComprado(@PathVariable Long asistenteId) {
        return ResponseEntity.ok(ordenService.calcularTotalCompradoPorAsistente(asistenteId));
    }
}