package com.example.Evento.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdenResponseDTO {
    private Long id;
    private Long idUsuarioComprador;
    private String estadoOrdenNombre;
    private Double total;
    private LocalDateTime fecha;
    private Long usuarioId;
    private String usuarioNombre;
    private List<EntradaResponseDTO> entradas;
}