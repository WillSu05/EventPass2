package com.example.Evento.Repository;

import com.example.Evento.Entity.Extends.PersonalIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalIngresoRepository extends JpaRepository<PersonalIngreso, Long> {
    Optional<PersonalIngreso> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}