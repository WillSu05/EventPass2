package com.example.Evento.DTO.Response;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String nombre;
    private String correo;
    private String rolNombre;
    private String tipoUsuarioSistema;
}
