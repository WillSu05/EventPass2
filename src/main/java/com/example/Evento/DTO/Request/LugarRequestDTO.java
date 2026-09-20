package com.example.Evento.DTO.Request;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LugarRequestDTO {
    private String nombre;
    private String direccion;
    private int capacidad;
    private Long idUsuarioCreador;
}
