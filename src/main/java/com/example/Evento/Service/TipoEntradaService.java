package com.example.Evento.Service;

import com.example.Evento.DTO.Request.TipoEntradaRequesDTO;
import com.example.Evento.DTO.Response.TipoEntradaResponseDTO;

import java.util.List;

public interface TipoEntradaService {
    List<TipoEntradaResponseDTO> listarPorEvento(Long idEvento);
    TipoEntradaResponseDTO buscarPorId(Long id);
    TipoEntradaResponseDTO crearTipoEntrada(TipoEntradaRequesDTO request);
    TipoEntradaResponseDTO actualizarTipoEntrada(Long id, TipoEntradaRequesDTO request);
    void eliminarTipoEntrada(Long id);
}
