package com.example.Evento.DTO.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String correo;
    private LocalDate fechaNacimiento;
    private String documento;
    private String contrasena;
    @JsonProperty("rol_id")
    private Long rolId;

}
