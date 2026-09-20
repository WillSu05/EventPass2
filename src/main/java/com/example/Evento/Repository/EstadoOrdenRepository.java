package com.example.Evento.Repository;

import com.example.Evento.Entity.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Long> {
    Optional<EstadoOrden> findByNombreIgnoreCase(String nombre);
}
