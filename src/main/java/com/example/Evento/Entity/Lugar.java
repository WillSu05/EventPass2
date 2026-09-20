package com.example.Evento.Entity;

import com.example.Evento.Entity.Extends.Organizador;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "lugar")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "id_usuario_creador")
    private Organizador usuarioCreador;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Evento> eventos;

}