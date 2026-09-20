package com.example.Evento.Service;

import com.example.Evento.DTO.Request.PersonalIngresoRequestDTO;
import com.example.Evento.DTO.Response.PersonalIngresoResponseDTO;

import java.util.List;

public interface PersonalIngresoService {
    PersonalIngresoResponseDTO registrar(PersonalIngresoRequestDTO dto);
    PersonalIngresoResponseDTO obtenerPorId(Long id);
    List<PersonalIngresoResponseDTO> listarTodos();
    PersonalIngresoResponseDTO actualizar(Long id, PersonalIngresoRequestDTO dto);
    void eliminar(Long id);
}