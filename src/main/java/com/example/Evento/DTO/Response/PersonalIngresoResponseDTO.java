package com.example.Evento.DTO.Response;

import lombok.Data;

@Data
public class PersonalIngresoResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String turno;
    private String puertaAsignada;
}