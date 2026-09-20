package com.example.Evento.DTO.Request;

import lombok.Data;

@Data
public class PersonalIngresoRequestDTO {
    private String nombre;
    private String email;
    private String password;
    private String turno;
    private String puertaAsignada;
}