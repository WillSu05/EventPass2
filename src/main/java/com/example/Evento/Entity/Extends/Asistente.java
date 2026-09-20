package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Orden;
import com.example.Evento.Entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asistente extends Usuario {

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
}
