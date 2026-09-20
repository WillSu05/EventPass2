package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.TipoEntradaRequesDTO;
import com.example.Evento.DTO.Response.TipoEntradaResponseDTO;
import com.example.Evento.Entity.Evento;
import com.example.Evento.Entity.TipoEntrada;
import com.example.Evento.Repository.EventoRepository;
import com.example.Evento.Repository.TipoEntradaRepository;
import com.example.Evento.Service.TipoEntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoEntradaServiceImpl implements TipoEntradaService {

    private final TipoEntradaRepository tipoEntradaRepository;
    private final EventoRepository eventoRepository;

    private TipoEntradaResponseDTO convertirADto(TipoEntrada t) {
        return new TipoEntradaResponseDTO(
                t.getNombre(),
                t.getPrecio(),
                t.getCantidadTotal(),
                t.getEvento() != null ? t.getEvento().getNombre() : "Sin evento"
        );
    }

    @Override
    public List<TipoEntradaResponseDTO> listarPorEvento(Long idEvento) {
        return tipoEntradaRepository.findByEventoId(idEvento).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public TipoEntradaResponseDTO buscarPorId(Long id) {
        TipoEntrada t = tipoEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de entrada no encontrado con ID: " + id));
        return convertirADto(t);
    }

    @Override
    public TipoEntradaResponseDTO crearTipoEntrada(TipoEntradaRequesDTO request) {
        Evento evento = eventoRepository.findById(request.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + request.getEventoId()));

        TipoEntrada tipoEntrada = new TipoEntrada();
        tipoEntrada.setNombre(request.getNombre());
        tipoEntrada.setPrecio(request.getPrecio());
        tipoEntrada.setCantidadTotal(request.getCapacidadTotal());
        tipoEntrada.setEvento(evento);

        TipoEntrada guardado = tipoEntradaRepository.save(tipoEntrada);
        return convertirADto(guardado);
    }

    @Override
    public TipoEntradaResponseDTO actualizarTipoEntrada(Long id, TipoEntradaRequesDTO request) {
        TipoEntrada tipoEntrada = tipoEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de entrada no encontrado con ID: " + id));

        tipoEntrada.setNombre(request.getNombre());
        tipoEntrada.setPrecio(request.getPrecio());
        tipoEntrada.setCantidadTotal(request.getCapacidadTotal());

        TipoEntrada actualizado = tipoEntradaRepository.save(tipoEntrada);
        return convertirADto(actualizado);
    }

    @Override
    public void eliminarTipoEntrada(Long id) {
        if (!tipoEntradaRepository.existsById(id)) {
            throw new RuntimeException("Tipo de entrada no encontrado");
        }
        tipoEntradaRepository.deleteById(id);
    }
}
