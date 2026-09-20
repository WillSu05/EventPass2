package com.example.Evento.Repository;

import com.example.Evento.Entity.Validacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidacionRepository extends JpaRepository<Validacion, Long> {
    boolean existsByEntradaCodigoAndResultadoValidacion(String codigoEntrada, String resultadoValidacion);
    List<Validacion> findByValidadorId(Long validadorId);
}
