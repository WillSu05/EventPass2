package com.example.Evento.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Email(message = "Debe ser un formato de correo válido")
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "documento", unique = true, nullable = false)
    private String documento;

    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

}
