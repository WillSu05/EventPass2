package com.example.Evento.Repository;

import com.example.Evento.Entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradoRepository extends JpaRepository<Administrador, Long> {
}
