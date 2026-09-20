package com.example.Evento.DTO.Request;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String correo;
    private String fechaNacimiento;
    private String documento;
    private String contrasena;
    private Long rolId;
    private String tipo;

}
