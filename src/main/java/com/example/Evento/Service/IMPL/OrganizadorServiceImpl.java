package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.OrganizadorRequestDTO;
import com.example.Evento.DTO.Response.OrganizadorResponseDTO;
import com.example.Evento.Entity.Extends.Organizador;
import com.example.Evento.Entity.Rol;
import com.example.Evento.Repository.OrganizadorRepository;
import com.example.Evento.Repository.RolRepository;
import com.example.Evento.Service.OrganizadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizadorServiceImpl implements OrganizadorService {

    private final OrganizadorRepository organizadorRepository;
    private final RolRepository rolRepository;

    @Override
    @Transactional
    public OrganizadorResponseDTO registrar(OrganizadorRequestDTO dto) {
        if (organizadorRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado");
        }

        Rol rol = null;
        if (dto.getRolId() != null) {
            rol = rolRepository.findById(dto.getRolId()).orElse(null);
        }

        if (rol == null) {
            rol = rolRepository.findByNombreIgnoreCase("ORGANIZADOR")
                    .orElseGet(() -> rolRepository.findById(2L)
                            .orElseGet(() -> {
                                Rol nuevoRol = new Rol();
                                nuevoRol.setNombre("ORGANIZADOR");
                                return rolRepository.save(nuevoRol);
                            }));
        }

        Organizador org = new Organizador();
        org.setNombre(dto.getNombre());
        org.setCorreo(dto.getCorreo());
        org.setDocumento(dto.getDocumento());
        org.setFechaNacimiento(dto.getFechaNacimiento());
        org.setContrasena(dto.getContrasena());
        org.setRol(rol);

        return mapToDTO(organizadorRepository.save(org));
    }

    @Override
    public OrganizadorResponseDTO obtenerPorId(Long id) {
        Organizador org = organizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizador no encontrado con ID: " + id));
        return mapToDTO(org);
    }

    @Override
    public List<OrganizadorResponseDTO> listarTodos() {
        return organizadorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private OrganizadorResponseDTO mapToDTO(Organizador o) {
        OrganizadorResponseDTO dto = new OrganizadorResponseDTO();
        dto.setId(o.getId());
        dto.setNombre(o.getNombre());
        dto.setCorreo(o.getCorreo());
        dto.setDocumento(o.getDocumento());
        dto.setFechaNacimiento(o.getFechaNacimiento());
        dto.setRolNombre(o.getRol() != null ? o.getRol().getNombre() : null);
        return dto;
    }
}