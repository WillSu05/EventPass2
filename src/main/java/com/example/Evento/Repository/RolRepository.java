package com.example.Evento.Repository;

import com.example.Evento.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByNombre(String nombre);
}
