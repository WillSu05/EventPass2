package com.example.Evento.Repository;

import com.example.Evento.Entity.Extends.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
    Optional<Organizador> findByEmail(String email);
    boolean existsByEmail(String email);
}