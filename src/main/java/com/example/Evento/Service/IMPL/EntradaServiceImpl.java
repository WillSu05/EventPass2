package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Response.EntradaResponseDTO;
import com.example.Evento.Entity.Entrada;
import com.example.Evento.Repository.EntradaRepository;
import com.example.Evento.Service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntradaServiceImpl implements EntradaService {

    private final EntradaRepository entradaRepository;

    @Override
    public EntradaResponseDTO obtenerPorId(Long id) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));
        return mapToDTO(entrada);
    }

    @Override
    public EntradaResponseDTO obtenerPorCodigo(String codigo) {
        Entrada entrada = entradaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con código: " + codigo));
        return mapToDTO(entrada);
    }

    private EntradaResponseDTO mapToDTO(Entrada e) {
        EntradaResponseDTO dto = new EntradaResponseDTO();
        dto.setId(e.getId());
        dto.setCodigo(e.getCodigo());

        if (e.getTipoEntrada() != null) {
            dto.setPrecio(e.getTipoEntrada().getPrecio());
            dto.setTipoEntradaNombre(e.getTipoEntrada().getNombre());

            if (e.getTipoEntrada().getEvento() != null) {
                dto.setEventoTitulo(e.getTipoEntrada().getEvento().getNombre());
            }
        }
        return dto;
    }
}