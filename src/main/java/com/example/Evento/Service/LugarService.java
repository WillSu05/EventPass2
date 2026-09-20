package com.example.Evento.Service;

import com.example.Evento.DTO.Request.LugarRequestDTO;
import com.example.Evento.DTO.Response.LugarResponseDTO;
import com.example.Evento.Entity.Lugar;

import java.util.*;

public interface LugarService {
    List<LugarResponseDTO> listarLugares();
    LugarResponseDTO buscarLugarPorId(Long id);
    LugarResponseDTO crearLugar(LugarRequestDTO request);
    LugarResponseDTO actualizarLugar(Long id, LugarRequestDTO request);
    void eliminarLugar(Long id);;
}
