package com.example.Evento.DTO.Request;

import lombok.Data;

@Data
public class OrdenRequestDTO {
    private Long asistenteId;
    private Long tipoEntradaId;
    private Integer cantidad;
}