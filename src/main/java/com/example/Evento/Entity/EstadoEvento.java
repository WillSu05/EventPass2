package com.example.Evento.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_evento")
public class EstadoEvento {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}