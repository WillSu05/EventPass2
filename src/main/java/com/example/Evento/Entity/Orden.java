package com.example.Evento.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario_comprador")
    private Long idUsuario_Comprador;

    @Column(name = "id_estado_orden")
    private Long idEstadoOrden;

    @Column(name = "total")
    private Double total;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}