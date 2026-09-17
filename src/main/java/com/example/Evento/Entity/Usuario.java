package com.example.Evento.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = READ_ONLY)
    protected Long id;

    @Column(name = "nombre")
    protected String nombre;

    @Email(message = "Debe ser un formato de correo válido")
    @Column(name = "correo", unique = true, nullable = false)
    protected String correo;

    @Column(name = "documento", unique = true, nullable = false)
    protected String documento;

    @Column(name = "fechaNacimiento", nullable = false)
    protected LocalDate fechaNacimiento;

    @Column(name = "contraseña", nullable = false)
    protected String contraseña;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL)
    private List<Evento> eventosOrganizados;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Orden> ordenes;

}
