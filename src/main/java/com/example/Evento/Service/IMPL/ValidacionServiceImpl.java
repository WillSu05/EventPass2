package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.ValidacionRequestDTO;
import com.example.Evento.DTO.Response.EstadisticasValidacionDTO;
import com.example.Evento.DTO.Response.ValidacionResponseDTO;
import com.example.Evento.Entity.Entrada;
import com.example.Evento.Entity.Extends.PersonalIngreso;
import com.example.Evento.Entity.Validacion;
import com.example.Evento.Repository.EntradaRepository;
import com.example.Evento.Repository.PersonalIngresoRepository;
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
    private final PersonalIngresoRepository personalIngresoRepository;

    @Override
    @Transactional
    public ValidacionResponseDTO validarEntrada(Long validadorId, ValidacionRequestDTO dto) {
        PersonalIngreso validador = personalIngresoRepository.findById(validadorId)
                .orElseThrow(() -> new RuntimeException("Personal de ingreso no encontrado con ID: " + validadorId));

        Entrada entrada = entradaRepository.findByCodigo(dto.getCodigoEntrada()).orElse(null);

        Validacion validacion = new Validacion();
        validacion.setFechaHora(LocalDateTime.now());
        validacion.setValidador(validador);
        validacion.setEntrada(entrada);

        boolean yaIngresada = validacionRepository.existsByEntradaCodigoAndResultadoValidacion(
                dto.getCodigoEntrada(), "EXITOSO"
        );

        boolean esValida = entrada != null
                && entrada.getOrden() != null
                && entrada.getOrden().getEstadoOrden() != null
                && "PAGADO".equalsIgnoreCase(entrada.getOrden().getEstadoOrden().getNombre())
                && !yaIngresada;

        validacion.setResultadoValidacion(esValida ? "EXITOSO" : "RECHAZADO");
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

    @Override
    public EstadisticasValidacionDTO obtenerEstadisticasValidador(Long validadorId) {
        List<Validacion> validaciones = validacionRepository.findByValidadorId(validadorId);

        long total = validaciones.size();
        long exitosos = validaciones.stream()
                .filter(v -> "EXITOSO".equalsIgnoreCase(v.getResultadoValidacion()))
                .count();
        long rechazados = total - exitosos;

        return new EstadisticasValidacionDTO(total, exitosos, rechazados);
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