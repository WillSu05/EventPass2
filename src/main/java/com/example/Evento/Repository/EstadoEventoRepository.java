package com.example.Evento.Repository;

import com.example.Evento.Entity.EstadoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoEventoRepository extends JpaRepository<EstadoEvento, Long> {
}
