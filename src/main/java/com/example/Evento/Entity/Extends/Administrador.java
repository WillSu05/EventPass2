package com.example.Evento.Entity.Extends;

import com.example.Evento.Entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Administrador")
@Data
public class Administrador extends Usuario {

}
