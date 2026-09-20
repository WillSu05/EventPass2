package com.example.Evento.Service.IMPL;

import com.example.Evento.Entity.Extends.Administrador;
import com.example.Evento.Repository.AdministradorRepository;
import com.example.Evento.Service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Override
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }

    @Override
    public Administrador buscarPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Administrador registrarAdministrador(Administrador administrador) {
        if (administradorRepository.existsByCorreo(administrador.getCorreo())) {
            throw new RuntimeException("El correo ya se encuentra registrado.");
        }
        if (administradorRepository.existsByDocumento(administrador.getDocumento())) {
            throw new RuntimeException("El documento ya se encuentra registrado.");
        }
        return administradorRepository.save(administrador);
    }

    @Override
    @Transactional
    public Administrador actualizarAdministrador(Long id, Administrador detalles) {
        Administrador admin = buscarPorId(id);
        admin.setNombre(detalles.getNombre());
        admin.setCorreo(detalles.getCorreo());
        admin.setDocumento(detalles.getDocumento());
        admin.setFechaNacimiento(detalles.getFechaNacimiento());


        if (detalles.getContrasena() != null && !detalles.getContrasena().isBlank()) {
            admin.setContrasena(detalles.getContrasena());
        }
        if (detalles.getRol() != null) {
            admin.setRol(detalles.getRol());
        }
        return administradorRepository.save(admin);
    }

    @Override
    @Transactional
    public void eliminarAdministrador(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }
        administradorRepository.deleteById(id);
    }
}
