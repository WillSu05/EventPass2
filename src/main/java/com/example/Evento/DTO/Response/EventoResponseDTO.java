package com.example.Evento.DTO.Response;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoResponseDTO {
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private LugarResponseDTO lugar;
    private String estadoEvento;
    private String nombreOrganizador;
}
