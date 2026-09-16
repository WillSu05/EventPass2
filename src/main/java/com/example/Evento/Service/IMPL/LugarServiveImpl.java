package com.example.Evento.Service.IMPL;
import com.example.Evento.Entity.Lugar;
import com.example.Evento.Exceptions.ResourceNotFoundException;
import com.example.Evento.Repository.LugarRepository;
import com.example.Evento.Service.LugarService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LugarServiveImpl implements LugarService {
    private final LugarRepository lugarRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Lugar> listarLugares() {
        return lugarRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lugar> buscarPorId(Long id) {
        return lugarRepository.findById(id);
    }

    @Override
    public Lugar crearLugar(Lugar lugar) {
        return lugarRepository.save(lugar);
    }

    @Override
    public Lugar actualizarLugar(Long id, Lugar lugarDetalles) {
        Lugar lugar = lugarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lugar No Encontrado Con id: "+ id));
        lugar.setNombre(lugarDetalles.getNombre());
        lugar.setDireccion(lugarDetalles.getDireccion());
        lugar.setCapacidad(lugarDetalles.getCapacidad());
        return lugarRepository.save(lugar);
    }

    @Override
    public void eliminarLugar(Long id) {
        lugarRepository.deleteById(id);

    }
}
