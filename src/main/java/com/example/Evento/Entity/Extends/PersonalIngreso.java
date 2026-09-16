package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Usuario;
import com.example.Evento.Entity.Validacion;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonalIngreso extends Usuario {
    @OneToMany(mappedBy = "validador")
    private List<Validacion> validaciones;

}
