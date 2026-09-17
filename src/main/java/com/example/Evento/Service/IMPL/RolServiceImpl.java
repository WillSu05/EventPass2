package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.RolRequestDTO;
import com.example.Evento.DTO.Response.RolResponseDTO;
import com.example.Evento.Entity.Rol;
import com.example.Evento.Repository.RolRepository;
import com.example.Evento.Service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    private RolResponseDTO convertirADto(Rol rol) {
        RolResponseDTO dto = new RolResponseDTO();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    @Override
    public List<RolResponseDTO> listarRoles() {
        return rolRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public RolResponseDTO crearRol(RolRequestDTO requestDTO) {
        Rol rol = new Rol();
        rol.setNombre(requestDTO.getNombre());
        return convertirADto(rolRepository.save(rol));
    }
}