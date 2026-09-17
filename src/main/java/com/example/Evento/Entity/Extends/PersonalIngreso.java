package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Usuario;
import com.example.Evento.Entity.Validacion;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalIngreso extends Usuario {
    @OneToMany(mappedBy = "validador")
    private List<Validacion> validaciones;

}
