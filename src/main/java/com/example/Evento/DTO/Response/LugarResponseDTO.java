package com.example.Evento.DTO.Response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LugarResponseDTO {
    private String nombre;
    private String direccion;
    private int capacidad;
    private String nombreCreador;
}
