package com.example.Evento.DTO.Request;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoRequestDTO {
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private Long lugarId;
    private Long organizadorId;
    private Long estadoId;
}
