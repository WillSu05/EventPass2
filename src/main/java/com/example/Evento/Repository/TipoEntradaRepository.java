package com.example.Evento.Repository;

import com.example.Evento.Entity.TipoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoEntradaRepository extends JpaRepository<TipoEntrada, Long> {
    List<TipoEntrada> findByEventoId(Long idEvento);
}
