package com.example.Evento.DTO.Response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrganizadorResponseDTO {
    private Long id;
    private String nombre;
    private String Correo;
    private String documento;
    private LocalDate fechaNacimiento;
    private String rolNombre;
}