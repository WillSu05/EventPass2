package com.example.Evento.DTO.Response;
import com.example.Evento.DTO.Request.LugarRequestDTO;
import jakarta.validation.constraints.NotNull;
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
    private EstadoEventoResponseDTO estado;
    private String nombreOrganizador;
}
