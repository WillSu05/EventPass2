package com.example.Evento.Service;

import com.example.Evento.Entity.Extends.Administrador;

import java.util.List;

public interface AdministradorService {
        List<Administrador> listarTodos();
        Administrador buscarPorId(Long id);
        Administrador registrarAdministrador(Administrador administrador);
        Administrador actualizarAdministrador(Long id, Administrador detalles);
        void eliminarAdministrador(Long id);
}
