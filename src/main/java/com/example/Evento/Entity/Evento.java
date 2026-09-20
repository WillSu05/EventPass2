package com.example.Evento.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column (unique = true, nullable = false)
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id", nullable = false)
    private Usuario organizador;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<TipoEntrada> tiposEntrada;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lugar_id", nullable = false)
    private Lugar lugar;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEvento estado;

}
