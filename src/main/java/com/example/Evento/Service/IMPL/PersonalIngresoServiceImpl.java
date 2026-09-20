package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.PersonalIngresoRequestDTO;
import com.example.Evento.DTO.Response.PersonalIngresoResponseDTO;
import com.example.Evento.Entity.Extends.PersonalIngreso;
import com.example.Evento.Repository.PersonalIngresoRepository;
import com.example.Evento.Service.PersonalIngresoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalIngresoServiceImpl implements PersonalIngresoService {

    private final PersonalIngresoRepository personalIngresoRepository;

    @Override
    @Transactional
    public PersonalIngresoResponseDTO registrar(PersonalIngresoRequestDTO dto) {
        if (personalIngresoRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya pertenece a otro usuario");
        }

        PersonalIngreso personal = new PersonalIngreso();
        personal.setNombre(dto.getNombre());
        personal.setCorreo(dto.getEmail());
        personal.setContrasena(dto.getPassword());
        personal.setTurno(dto.getTurno());
        personal.setPuertaAsignada(dto.getPuertaAsignada());

        return mapToDTO(personalIngresoRepository.save(personal));
    }

    @Override
    public PersonalIngresoResponseDTO obtenerPorId(Long id) {
        PersonalIngreso p = personalIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal de ingreso no encontrado con ID: " + id));
        return mapToDTO(p);
    }

    @Override
    public List<PersonalIngresoResponseDTO> listarTodos() {
        return personalIngresoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PersonalIngresoResponseDTO actualizar(Long id, PersonalIngresoRequestDTO dto) {
        PersonalIngreso p = personalIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal de ingreso no encontrado"));

        p.setNombre(dto.getNombre());
        p.setTurno(dto.getTurno());
        p.setPuertaAsignada(dto.getPuertaAsignada());

        return mapToDTO(personalIngresoRepository.save(p));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!personalIngresoRepository.existsById(id)) {
            throw new RuntimeException("Personal de ingreso no encontrado");
        }
        personalIngresoRepository.deleteById(id);
    }

    private PersonalIngresoResponseDTO mapToDTO(PersonalIngreso p) {
        PersonalIngresoResponseDTO dto = new PersonalIngresoResponseDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setEmail(p.getCorreo());
        dto.setTurno(p.getTurno());
        dto.setPuertaAsignada(p.getPuertaAsignada());
        return dto;
    }
}