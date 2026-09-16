package com.example.Evento.Repository;

import com.example.Evento.Entity.Extends.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento);
}
