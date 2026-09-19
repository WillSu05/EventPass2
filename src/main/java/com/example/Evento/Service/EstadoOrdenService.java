package com.example.Evento.Service;

import com.example.Evento.DTO.Request.EstadoOrdenRequestDTO;
import com.example.Evento.DTO.Response.EstadoOrdenRespondeDTO;
import com.example.Evento.Repository.EstadoOrdenRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EstadoOrdenService{
    List<EstadoOrdenRespondeDTO> listarEstados();
    EstadoOrdenRespondeDTO crearEstado(EstadoOrdenRequestDTO request);
}
