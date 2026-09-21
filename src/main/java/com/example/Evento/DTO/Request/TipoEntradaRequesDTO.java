package com.example.Evento.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEntradaRequesDTO {
    private String nombre;
    private Double precio;
    private Integer capacidadTotal;
    private Long eventoId;
}
