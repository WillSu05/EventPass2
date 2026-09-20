package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Orden;
import com.example.Evento.Entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("Asistente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asistente extends Usuario {

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
}
