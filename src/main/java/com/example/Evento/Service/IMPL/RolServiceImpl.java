package com.example.Evento.Service.IMPL;

import com.example.Evento.Entity.Rol;
import com.example.Evento.Repository.RolRepository;
import com.example.Evento.Service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Rol registrarRol(Rol rol) {
        if (rol.getNombre() != null && rolRepository.existsByNombre(rol.getNombre())) {
            throw new RuntimeException("El rol ya existe: " + rol.getNombre());
        }
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public Rol actualizarRol(Long id, Rol detalles) {
        Rol rol = buscarPorId(id);
        rol.setNombre(detalles.getNombre());
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado con ID: " + id);
        }
        rolRepository.deleteById(id);
    }
}