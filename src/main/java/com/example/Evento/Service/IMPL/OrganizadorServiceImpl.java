package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.OrganizadorRequestDTO;
import com.example.Evento.DTO.Response.OrganizadorResponseDTO;
import com.example.Evento.Entity.Extends.Organizador;
import com.example.Evento.Repository.OrganizadorRepository;
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

    @Override
    @Transactional
    public OrganizadorResponseDTO registrar(OrganizadorRequestDTO dto) {
        if (organizadorRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya se encuentra registrado");
        }

        Organizador org = new Organizador();
        org.setNombre(dto.getNombre());
        org.setCorreo(dto.getEmail());
        org.setContrasena(dto.getPassword());

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

    @Override
    @Transactional
    public OrganizadorResponseDTO actualizar(Long id, OrganizadorRequestDTO dto) {
        Organizador org = organizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizador no encontrado con ID: " + id));

        org.setNombre(dto.getNombre());
        org.setCorreo(dto.getEmail());

        return mapToDTO(organizadorRepository.save(org));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!organizadorRepository.existsById(id)) {
            throw new RuntimeException("Organizador no encontrado");
        }
        organizadorRepository.deleteById(id);
    }

    private OrganizadorResponseDTO mapToDTO(Organizador org) {
        OrganizadorResponseDTO dto = new OrganizadorResponseDTO();
        dto.setId(org.getId());
        dto.setNombre(org.getNombre());
        dto.setEmail(org.getCorreo());

        return dto;
    }
}