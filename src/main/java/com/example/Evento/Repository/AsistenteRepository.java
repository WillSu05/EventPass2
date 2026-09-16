package com.example.Evento.Repository;

import com.example.Evento.Entity.Extends.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {

}
