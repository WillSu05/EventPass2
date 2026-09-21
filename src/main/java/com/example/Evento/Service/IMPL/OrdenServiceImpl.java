package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.OrdenRequestDTO;
import com.example.Evento.DTO.Response.EntradaResponseDTO;
import com.example.Evento.DTO.Response.OrdenResponseDTO;
import com.example.Evento.Entity.*;
import com.example.Evento.Entity.Extends.Asistente;
import com.example.Evento.Repository.*;
import com.example.Evento.Service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final AsistenteRepository asistenteRepository;
    private final TipoEntradaRepository tipoEntradaRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;

    @Override
    @Transactional
    public OrdenResponseDTO crearOrden(OrdenRequestDTO dto) {
        Asistente asistente = asistenteRepository.findById(dto.getAsistenteId())
                .orElseThrow(() -> new RuntimeException("Asistente no encontrado con ID: " + dto.getAsistenteId()));

        TipoEntrada tipoEntrada = tipoEntradaRepository.findById(dto.getTipoEntradaId())
                .orElseThrow(() -> new RuntimeException("Tipo de entrada no encontrado con ID: " + dto.getTipoEntradaId()));

        if (tipoEntrada.getDisponible() < dto.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente. Disponibles: " + tipoEntrada.getDisponible());
        }

        // Descontar inventario disponible
        tipoEntrada.setDisponible(tipoEntrada.getDisponible() - dto.getCantidad());
        tipoEntradaRepository.save(tipoEntrada);

        EstadoOrden estadoPendiente = estadoOrdenRepository.findByNombreIgnoreCase("PENDIENTE")
                .orElseThrow(() -> new RuntimeException("Estado 'PENDIENTE' no parametrizado en el sistema"));

        Orden orden = new Orden();
        orden.setFecha(LocalDateTime.now());
        orden.setTotal(tipoEntrada.getPrecio() * dto.getCantidad());
        orden.setUsuario(asistente);
        orden.setIdUsuario_Comprador(asistente.getId());
        orden.setEstadoOrden(estadoPendiente);

        List<Entrada> entradas = new ArrayList<>();
        for (int i = 0; i < dto.getCantidad(); i++) {
            Entrada entrada = new Entrada();
            entrada.setCodigo(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            entrada.setTipoEntrada(tipoEntrada);
            entrada.setOrden(orden);
            entradas.add(entrada);
        }

        orden.setEntradas(entradas);
        return mapToDTO(ordenRepository.save(orden));
    }

    @Override
    public OrdenResponseDTO obtenerPorId(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + ordenId));
        return mapToDTO(orden);
    }

    @Override
    public List<OrdenResponseDTO> obtenerPorAsistente(Long asistenteId) {
        return ordenRepository.findByUsuarioId(asistenteId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrdenResponseDTO procesarPago(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + ordenId));

        if (!"PENDIENTE".equalsIgnoreCase(orden.getEstadoOrden().getNombre())) {
            throw new IllegalStateException("Solo se pueden pagar órdenes que se encuentren en estado PENDIENTE");
        }

        EstadoOrden estadoPagado = estadoOrdenRepository.findByNombreIgnoreCase("PAGADO")
                .orElseThrow(() -> new RuntimeException("Estado 'PAGADO' no parametrizado en el sistema"));

        orden.setEstadoOrden(estadoPagado);
        return mapToDTO(ordenRepository.save(orden));
    }

    @Override
    @Transactional
    public OrdenResponseDTO cancelarOrden(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + ordenId));

        if ("CANCELADO".equalsIgnoreCase(orden.getEstadoOrden().getNombre())) {
            throw new IllegalStateException("La orden ya se encuentra cancelada");
        }

        // Devolución de stock al inventario
        if (orden.getEntradas() != null) {
            for (Entrada entrada : orden.getEntradas()) {
                TipoEntrada tipo = entrada.getTipoEntrada();
                if (tipo != null) {
                    tipo.setDisponible(tipo.getDisponible() + 1);
                    tipoEntradaRepository.save(tipo);
                }
            }
        }

        EstadoOrden estadoCancelado = estadoOrdenRepository.findByNombreIgnoreCase("CANCELADO")
                .orElseThrow(() -> new RuntimeException("Estado 'CANCELADO' no parametrizado"));

        orden.setEstadoOrden(estadoCancelado);
        return mapToDTO(ordenRepository.save(orden));
    }

    @Override
    public Double calcularTotalCompradoPorAsistente(Long asistenteId) {
        return ordenRepository.findByUsuarioId(asistenteId).stream()
                .filter(o -> o.getEstadoOrden() != null && "PAGADO".equalsIgnoreCase(o.getEstadoOrden().getNombre()))
                .mapToDouble(Orden::getTotal)
                .sum();
    }

    private OrdenResponseDTO mapToDTO(Orden o) {
        OrdenResponseDTO dto = new OrdenResponseDTO();
        dto.setId(o.getId());
        dto.setIdUsuarioComprador(o.getIdUsuario_Comprador());
        dto.setEstadoOrdenNombre(o.getEstadoOrden() != null ? o.getEstadoOrden().getNombre() : null);
        dto.setTotal(o.getTotal());
        dto.setFecha(o.getFecha());

        if (o.getUsuario() != null) {
            dto.setUsuarioId(o.getUsuario().getId());
            dto.setUsuarioNombre(o.getUsuario().getNombre());
        }

        if (o.getEntradas() != null) {
            dto.setEntradas(o.getEntradas().stream().map(e -> {
                EntradaResponseDTO edto = new EntradaResponseDTO();
                edto.setId(e.getId());
                edto.setCodigo(e.getCodigo());
                if (e.getTipoEntrada() != null) {
                    edto.setPrecio(e.getTipoEntrada().getPrecio());
                    edto.setTipoEntradaNombre(e.getTipoEntrada().getNombre());
                    if (e.getTipoEntrada().getEvento() != null) {
                        edto.setEventoTitulo(e.getTipoEntrada().getEvento().getNombre());
                    }
                }
                return edto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }
}