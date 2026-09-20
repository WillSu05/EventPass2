package com.example.Evento.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ValidacionResponseDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String resultadoValidacion;
    private String codigoEntrada;
    private Long validadorId;
    private String validadorNombre;
}