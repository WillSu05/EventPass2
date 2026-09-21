package com.example.Evento.DTO.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalIngresoRequestDTO {
    private String nombre;
    private String correo;
    private String contrasena;
    private String turno;
    private String puertaAsignada;
    private String documento;
    private LocalDate fechaNacimiento ;
    private Long rolId;
}