package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.AsistenteRequestDTO;
import com.example.Evento.DTO.Response.AsistenteResponseDTO;
import com.example.Evento.Entity.Extends.Asistente;
import com.example.Evento.Repository.AsistenteRepository;
import com.example.Evento.Service.AsistenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsistenteServiceImpl implements AsistenteService {

    private final AsistenteRepository asistenteRepository;

    @Override
    @Transactional
    public AsistenteResponseDTO registrar(AsistenteRequestDTO dto) {
        if (asistenteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya se encuentra registrado");
        }

        Asistente asistente = new Asistente();
        asistente.setNombre(dto.getNombre());
        asistente.setCorreo(dto.getEmail());
        asistente.setContrasena(dto.getPassword());

        Asistente guardado = asistenteRepository.save(asistente);
        return mapToDTO(guardado);
    }

    @Override
    public AsistenteResponseDTO obtenerPorId(Long id) {
        Asistente asistente = asistenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistente no encontrado con ID: " + id));
        return mapToDTO(asistente);
    }

    @Override
    public List<AsistenteResponseDTO> listarTodos() {
        return asistenteRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AsistenteResponseDTO actualizar(Long id, AsistenteRequestDTO dto) {
        Asistente asistente = asistenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistente no encontrado con ID: " + id));

        asistente.setNombre(dto.getNombre());
        asistente.setCorreo(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            asistente.setContrasena(dto.getPassword());
        }

        return mapToDTO(asistenteRepository.save(asistente));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!asistenteRepository.existsById(id)) {
            throw new RuntimeException("Asistente no encontrado");
        }
        asistenteRepository.deleteById(id);
    }

    private AsistenteResponseDTO mapToDTO(Asistente a) {
        AsistenteResponseDTO dto = new AsistenteResponseDTO();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setEmail(a.getCorreo());
        return dto;
    }
}