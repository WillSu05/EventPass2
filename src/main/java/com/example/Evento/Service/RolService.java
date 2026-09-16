package com.example.Evento.Service;

import com.example.Evento.Entity.Rol;
import java.util.List;

public interface RolService {
    List<Rol> listarTodos();
    Rol buscarPorId(Long id);
    Rol registrarRol(Rol rol);
    Rol actualizarRol(Long id, Rol detalles);
    void eliminarRol(Long id);
}