package com.example.Evento.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipoEntrada")
@NoArgsConstructor
@AllArgsConstructor
public class TipoEntrada {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "precio", nullable = false)
    private double precio;
    @Column(name = "cantidadTotal", nullable = false)
    private int cantidadTotal;
    @Column(name = "disponible", nullable = false)
    private int disponible;
}
