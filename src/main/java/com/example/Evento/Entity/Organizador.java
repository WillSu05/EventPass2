package com.example.Evento.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Organizador extends Usuario{

    @OneToMany(mappedBy = "usuarioOrganizador")
    private List<Evento> eventos;

    @OneToMany (mappedBy = "usuarioCreador")
    private List<Lugar> lugares;

}
