package com.example.Evento.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "validacion")
public class Validacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "resultadoValidacion", nullable = false)
    private String resultadoValidacion;


}
