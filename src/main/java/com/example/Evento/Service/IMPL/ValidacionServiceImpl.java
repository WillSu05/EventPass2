package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.ValidacionRequestDTO;
import com.example.Evento.DTO.Response.ValidacionResponseDTO;
import com.example.Evento.Entity.Entrada;
import com.example.Evento.Entity.Usuario;
import com.example.Evento.Entity.Validacion;
import com.example.Evento.Repository.EntradaRepository;
import com.example.Evento.Repository.UsuarioRepository;
import com.example.Evento.Repository.ValidacionRepository;
import com.example.Evento.Service.ValidacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidacionServiceImpl implements ValidacionService {

    private final ValidacionRepository validacionRepository;
    private final EntradaRepository entradaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ValidacionResponseDTO validarEntrada(Long validadorId, ValidacionRequestDTO dto) {
        Usuario validador = usuarioRepository.findById(validadorId)
                .orElseThrow(() -> new RuntimeException("Validador/Usuario no encontrado con ID: " + validadorId));

        Entrada entrada = entradaRepository.findByCodigo(dto.getCodigoEntrada()).orElse(null);

        Validacion validacion = new Validacion();
        validacion.setFechaHora(LocalDateTime.now());
        validacion.setValidador(validador);
        validacion.setEntrada(entrada);

        boolean yaIngresada = validacionRepository.existsByEntradaCodigoAndResultadoValidacion(dto.getCodigoEntrada(), "EXITOSO");


        if (entrada != null
                && entrada.getOrden() != null
                && "PAGADO".equalsIgnoreCase(entrada.getOrden().getEstadoOrden().getNombre())
                && !yaIngresada) {

            validacion.setResultadoValidacion("EXITOSO");
        } else {
            validacion.setResultadoValidacion("RECHAZADO");
        }

        Validacion guardada = validacionRepository.save(validacion);
        return mapToDTO(guardada);
    }

    @Override
    public ValidacionResponseDTO obtenerPorId(Long id) {
        Validacion v = validacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Validación no encontrada con ID: " + id));
        return mapToDTO(v);
    }

    @Override
    public List<ValidacionResponseDTO> listarPorValidador(Long validadorId) {
        return validacionRepository.findByValidadorId(validadorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ValidacionResponseDTO mapToDTO(Validacion v) {
        ValidacionResponseDTO dto = new ValidacionResponseDTO();
        dto.setId(v.getId());
        dto.setFechaHora(v.getFechaHora());
        dto.setResultadoValidacion(v.getResultadoValidacion());

        if (v.getEntrada() != null) {
            dto.setCodigoEntrada(v.getEntrada().getCodigo());
        }

        if (v.getValidador() != null) {
            dto.setValidadorId(v.getValidador().getId());
            dto.setValidadorNombre(v.getValidador().getNombre());
        }

        return dto;
    }
}
