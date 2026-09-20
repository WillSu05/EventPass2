package com.example.Evento.Service;

import com.example.Evento.DTO.Request.UsuarioRequestDTO;
import com.example.Evento.DTO.Response.UsuarioResponseDTO;
import com.example.Evento.Entity.Usuario;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface UsuarioService {

    // MÉTODOS COMUNES DE USUARIO (Padre)

    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO buscarUsuarioPorId(Long id);
    UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request);
    UsuarioResponseDTO login(String correo, String contrasena);
    UsuarioResponseDTO modificarDatos(Long id, UsuarioRequestDTO request);
    void eliminarUsuario(Long id);
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO request);

    // MÉTODOS DEL USUARIO  ADMINISTRADOR

    void otorgarPermisos(Long idUsuario, String nuevosPermisos);
    void actualizarPermisos(Long idUsuario, String permisosActualizados);
    void cancelarEventos(Long idEvento);
    void consultarEventos(Long idAdmin);
    void consultarReportes(Long idAdmin);
    void eliminarReportes(Long idReporte);
    void generarReportes(Long idAdmin);

    // MÉTODOS DEL USUARIO ORGANIZADOR
    void crearEvento(Long idOrganizador, Object eventoDto); // Usaremos Object hasta llegar a la Fase 3
    void actualizarEvento(Long idEvento, Object eventoDto);
    UsuarioResponseDTO actualizarDatosPersonales(Long idOrganizador, UsuarioRequestDTO request);
    void configurarTiposEntrada(Long idEvento, Object configuracion);

    // MÉTODOS DEL USUARIO ASISTENTE

    void buscarEventos(String parametrosBusqueda);
    void seleccionarEntradas(Long idEvento, int cantidad);
    void procesarPago(Long idOrden);
    void consultarEntrada(String codigo);

    // MÉTODOS DEL USUARIO PERSONAL INGRESO

    void iniciarSecion(String credenciales);
    void validarEntrada(String codigo);
    void consultaEvento(Long idEvento);


}