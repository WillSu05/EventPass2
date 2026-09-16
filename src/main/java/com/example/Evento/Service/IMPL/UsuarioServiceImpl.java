package com.example.Evento.Service.IMPL;

import com.example.Evento.Entity.Usuario;
import com.example.Evento.Repository.UsuarioRepository;
import com.example.Evento.Service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya se encuentra registrado.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario detalles) {
        Usuario usuario = buscarPorId(id);
        usuario.setNombre(detalles.getNombre());
        usuario.setCorreo(detalles.getCorreo());
        usuario.setDocumento(detalles.getDocumento());
        usuario.setFechaNacimiento(detalles.getFechaNacimiento());
        if (detalles.getContraseña() != null && !detalles.getContraseña().isBlank()) {
            usuario.setContraseña(detalles.getContraseña());
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}