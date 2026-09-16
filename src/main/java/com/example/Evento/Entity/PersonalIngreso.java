package com.example.Evento.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonalIngreso extends Usuario{
    @OneToMany(mappedBy = "usuarioValidador")
    private List<Validacion> validaciones;

}
