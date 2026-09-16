package com.example.Evento.Service;

import com.example.Evento.Entity.Lugar;

import java.util.*;

public interface LugarService {
    List<Lugar> listarLugares();
    Optional<Lugar> buscarPorId(Long id);
    Lugar crearLugar(Lugar lugar);
    Lugar actualizarLugar(Long id, Lugar lugarDetalles);
    void eliminarLugar(Long id);
}
