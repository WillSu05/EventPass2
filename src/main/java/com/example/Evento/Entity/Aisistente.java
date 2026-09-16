package com.example.Evento.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Aisistente extends Usuario{

    @OneToMany(mappedBy = "usuarioComprador")
    private List<Orden> ordenes;
}
