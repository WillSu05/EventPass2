package com.example.Evento.Service;

import com.example.Evento.Entity.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario buscarPorId(Long id);
    Usuario registrarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Long id, Usuario detalles);
    void eliminarUsuario(Long id);
}