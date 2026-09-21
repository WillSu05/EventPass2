package com.example.Evento.DTO.Response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEntradaResponseDTO {
    private String nombre;
    private Double precio;
    private Integer capacidadTotal;
    private String nombreEvento;
}
