package com.example.Evento.Entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "lugar")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "capacidad")
    private int capacidad;

    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Evento> eventos;

}