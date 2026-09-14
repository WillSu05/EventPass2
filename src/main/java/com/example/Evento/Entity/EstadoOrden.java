package com.example.Evento.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_orden")
public class EstadoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}