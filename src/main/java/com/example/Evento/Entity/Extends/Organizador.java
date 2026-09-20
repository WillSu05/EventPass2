package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Evento;
import com.example.Evento.Entity.Lugar;
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
@NoArgsConstructor
@AllArgsConstructor
public class Organizador extends Usuario {

    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventos;


}
