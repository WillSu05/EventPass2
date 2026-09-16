package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Evento;
import com.example.Evento.Entity.Lugar;
import com.example.Evento.Entity.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Organizador extends Usuario {

    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventos;


}
