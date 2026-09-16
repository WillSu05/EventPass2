package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Orden;
import com.example.Evento.Entity.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

@AllArgsConstructor
@NoArgsConstructor
public class Asistente extends Usuario {

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
}
