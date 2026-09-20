package com.example.Evento.Service;

import com.example.Evento.DTO.Request.OrdenRequestDTO;
import com.example.Evento.DTO.Response.OrdenResponseDTO;

import java.util.List;

public interface OrdenService {
    OrdenResponseDTO crearOrden(OrdenRequestDTO dto);
    OrdenResponseDTO obtenerPorId(Long ordenId);
    List<OrdenResponseDTO> obtenerPorUsuario(Long usuarioId);
    OrdenResponseDTO cambiarEstado(Long ordenId, String nuevoEstado);
    OrdenResponseDTO cancelarOrden(Long ordenId);
}