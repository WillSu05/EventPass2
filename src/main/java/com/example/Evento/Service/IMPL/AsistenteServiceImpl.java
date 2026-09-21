package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.AsistenteRequestDTO;
import com.example.Evento.DTO.Response.AsistenteResponseDTO;
import com.example.Evento.Entity.Extends.Asistente;
import com.example.Evento.Entity.Rol;
import com.example.Evento.Repository.AsistenteRepository;
import com.example.Evento.Repository.OrdenRepository;
import com.example.Evento.Repository.RolRepository;
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
    private final RolRepository rolRepository;
    private final OrdenRepository ordenRepository;

    @Override
    @Transactional
    public AsistenteResponseDTO registrar(AsistenteRequestDTO dto) {
        if (asistenteRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado");
        }

        // Asignación segura de Rol: Busca por ID enviado, por nombre "ASISTENTE" o crea el rol si no existe en BD
        Rol rol = null;
        if (dto.getRolId() != null) {
            rol = rolRepository.findById(dto.getRolId()).orElse(null);
        }

        if (rol == null) {
            rol = rolRepository.findByNombreIgnoreCase("ASISTENTE")
                    .orElseGet(() -> rolRepository.findById(2L)
                            .orElseGet(() -> {
                                Rol nuevoRol = new Rol();
                                nuevoRol.setNombre("ASISTENTE");
                                return rolRepository.save(nuevoRol);
                            }));
        }

        Asistente asistente = new Asistente();
        asistente.setNombre(dto.getNombre());
        asistente.setCorreo(dto.getCorreo());
        asistente.setDocumento(dto.getDocumento());
        asistente.setFechaNacimiento(dto.getFechaNacimiento());
        asistente.setContrasena(dto.getContrasena());
        asistente.setRol(rol); // Garantiza que rol_id NUNCA sea null

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
    public List<AsistenteResponseDTO> listartodos() {
        return asistenteRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean tieneOrdenesPendientes(Long asistenteId) {
        return ordenRepository.findByUsuarioId(asistenteId).stream()
                .anyMatch(o -> o.getEstadoOrden() != null && "PENDIENTE".equalsIgnoreCase(o.getEstadoOrden().getNombre()));
    }

    private AsistenteResponseDTO mapToDTO(Asistente a) {
        AsistenteResponseDTO dto = new AsistenteResponseDTO();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setCorreo(a.getCorreo());
        dto.setDocumento(a.getDocumento());
        dto.setFechaNacimiento(a.getFechaNacimiento());
        dto.setRolNombre(a.getRol() != null ? a.getRol().getNombre() : null);
        return dto;
    }
}