package com.example.Evento.DTO.Response;

import lombok.Data;

@Data
public class EntradaResponseDTO {
    private Long id;
    private String codigo;
    private Double precio;
    private String tipoEntradaNombre;
    private String eventoTitulo;
}