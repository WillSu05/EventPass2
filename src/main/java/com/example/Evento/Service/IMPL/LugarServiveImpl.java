package com.example.Evento.Service.IMPL;
import com.example.Evento.DTO.Request.LugarRequestDTO;
import com.example.Evento.DTO.Response.LugarResponseDTO;
import com.example.Evento.Entity.Extends.Organizador;
import com.example.Evento.Entity.Lugar;
import com.example.Evento.Entity.Usuario;
import com.example.Evento.Exceptions.ResourceNotFoundException;
import com.example.Evento.Repository.LugarRepository;
import com.example.Evento.Repository.UsuarioRepository;
import com.example.Evento.Service.LugarService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LugarServiveImpl implements LugarService {
    private final LugarRepository lugarRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<LugarResponseDTO> listarLugares() {
        return lugarRepository.findAll().stream()
                .map(l -> new LugarResponseDTO(
                        l.getNombre(),
                        l.getDireccion(),
                        l.getCapacidad(),
                        l.getUsuarioCreador() != null ? l.getUsuarioCreador().getNombre() : "Desconocido"
                ))
                .collect(Collectors.toList());
    }

    @Override
    public LugarResponseDTO buscarLugarPorId(Long id) {
        return lugarRepository.findById(id)
                .map(l -> new LugarResponseDTO(
                        l.getNombre(),
                        l.getDireccion(),
                        l.getCapacidad(),
                        l.getUsuarioCreador() != null ? l.getUsuarioCreador().getNombre() : "Desconocido"
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado con ID: " + id));
    }

    @Override
    public LugarResponseDTO crearLugar(LugarRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuarioCreador())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario creador no encontrado"));

        if (!(usuario instanceof Organizador)) {
            throw new ResourceNotFoundException("Solo los usuarios con rol de Organizador pueden registrar lugares");
        }

        Lugar lugar = new Lugar();
        lugar.setNombre(request.getNombre());
        lugar.setDireccion(request.getDireccion());
        lugar.setCapacidad(request.getCapacidad());
        lugar.setUsuarioCreador((Organizador) usuario);

        Lugar guardado = lugarRepository.save(lugar);

        return new LugarResponseDTO(
                guardado.getNombre(),
                guardado.getDireccion(),
                guardado.getCapacidad(),
                guardado.getUsuarioCreador().getNombre()
        );
    }

    @Override
    public LugarResponseDTO actualizarLugar(Long id, LugarRequestDTO request) {
        Lugar lugar = lugarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado con ID: " + id));

        lugar.setNombre(request.getNombre());
        lugar.setDireccion(request.getDireccion());
        lugar.setCapacidad(request.getCapacidad());

        Lugar actualizado = lugarRepository.save(lugar);

        return new LugarResponseDTO(
                actualizado.getNombre(),
                actualizado.getDireccion(),
                actualizado.getCapacidad(),
                actualizado.getUsuarioCreador() != null ? actualizado.getUsuarioCreador().getNombre() : "Desconocido"
        );
    }

    @Override
    public void eliminarLugar(Long id) {
        if (!lugarRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lugar no encontrado");
        }
        lugarRepository.deleteById(id);
    }
}
