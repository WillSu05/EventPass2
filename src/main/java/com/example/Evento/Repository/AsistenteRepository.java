package com.example.Evento.Repository;

import com.example.Evento.Entity.Extends.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    Optional<Asistente> findByEmail(String email);
    boolean existsByEmail(String email);
}