package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Usuario;
import com.example.Evento.Entity.Validacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("PersonalIngreso")
@Data
public class PersonalIngreso extends Usuario {
    @OneToMany(mappedBy = "validador")
    private List<Validacion> validaciones;

    @Column(name = "turno")
    private String turno;
    @Column(name = "puerta")
    private String puertaAsignada;
}
