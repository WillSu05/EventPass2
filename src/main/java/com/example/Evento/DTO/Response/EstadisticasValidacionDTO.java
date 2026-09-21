package com.example.Evento.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstadisticasValidacionDTO {
    private Long totalEscaneos;
    private Long exitosos;
    private Long rechazados;
}