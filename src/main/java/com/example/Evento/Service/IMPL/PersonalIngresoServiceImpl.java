package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.PersonalIngresoRequestDTO;
import com.example.Evento.DTO.Response.PersonalIngresoResponseDTO;
import com.example.Evento.Entity.Extends.PersonalIngreso;
import com.example.Evento.Entity.Rol;
import com.example.Evento.Repository.PersonalIngresoRepository;
import com.example.Evento.Repository.RolRepository;
import com.example.Evento.Service.PersonalIngresoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalIngresoServiceImpl implements PersonalIngresoService {

    private final PersonalIngresoRepository personalIngresoRepository;
    private final RolRepository rolRepository;

    @Override
    @Transactional
    public PersonalIngresoResponseDTO registrar(PersonalIngresoRequestDTO dto) {
        if (personalIngresoRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado");
        }

        Rol rol = null;
        if (dto.getRolId() != null) {
            rol = rolRepository.findById(dto.getRolId()).orElse(null);
        }

        if (rol == null) {
            rol = rolRepository.findByNombreIgnoreCase("PERSONAL_INGRESO")
                    .orElseGet(() -> rolRepository.findById(4L)
                            .orElseGet(() -> {
                                Rol nuevoRol = new Rol();
                                nuevoRol.setNombre("PERSONAL_INGRESO");
                                return rolRepository.save(nuevoRol);
                            }));
        }

        PersonalIngreso personal = new PersonalIngreso();
        personal.setNombre(dto.getNombre());
        personal.setCorreo(dto.getCorreo());
        personal.setDocumento(dto.getDocumento());
        personal.setFechaNacimiento(dto.getFechaNacimiento());
        personal.setContrasena(dto.getContrasena());
        personal.setRol(rol);

        return mapToDTO(personalIngresoRepository.save(personal));
    }

    @Override
    public PersonalIngresoResponseDTO obtenerPorId(Long id) {
        PersonalIngreso personal = personalIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal de ingreso no encontrado con ID: " + id));
        return mapToDTO(personal);
    }

    @Override
    public List<PersonalIngresoResponseDTO> listarTodos() {
        return personalIngresoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PersonalIngresoResponseDTO mapToDTO(PersonalIngreso p) {
        PersonalIngresoResponseDTO dto = new PersonalIngresoResponseDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setCorreo(p.getCorreo());
        dto.setDocumento(p.getDocumento());
        dto.setFechaNacimiento(p.getFechaNacimiento());
        return dto;
    }
}