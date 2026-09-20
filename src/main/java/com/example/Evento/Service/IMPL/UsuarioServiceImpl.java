package com.example.Evento.Service.IMPL;

import com.example.Evento.DTO.Request.UsuarioRequestDTO;
import com.example.Evento.DTO.Response.UsuarioResponseDTO;
import com.example.Evento.Entity.Extends.Administrador;
import com.example.Evento.Entity.Extends.Asistente;
import com.example.Evento.Entity.Extends.Organizador;
import com.example.Evento.Entity.Extends.PersonalIngreso;
import com.example.Evento.Entity.Rol;
import com.example.Evento.Entity.Usuario;
import com.example.Evento.Exceptions.ResourceNotFoundException;
import com.example.Evento.Repository.RolRepository;
import com.example.Evento.Repository.UsuarioRepository;
import com.example.Evento.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponseDTO(
                        u.getNombre(),
                        u.getCorreo(),
                        u.getRol().getNombre(),
                        u.getClass().getSimpleName() // Devuelve dinámicamente "Asistente", "Organizador", etc.
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(u -> new UsuarioResponseDTO(
                        u.getNombre(),
                        u.getCorreo(),
                        u.getRol().getNombre(),
                        u.getClass().getSimpleName()
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {
        Rol rol = rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + request.getRolId()));

        Usuario nuevoUsuario;
        String nombreRol = rol.getNombre().toUpperCase();
        if (nombreRol.contains("ASISTENTE")) {
            nuevoUsuario = new Asistente();
        } else if (nombreRol.contains("ORGANIZADOR")) {
            Organizador org = new Organizador();
            nuevoUsuario = org;
        } else if (nombreRol.contains("ADMIN")) {
            Administrador admin = new Administrador();
            nuevoUsuario = admin;
        } else if (nombreRol.contains("INGRESO") || nombreRol.contains("PERSONAL")) {
            nuevoUsuario = new PersonalIngreso();
        } else {
            throw new RuntimeException("Tipo de rol no reconocido para instanciar una clase");
        }

        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setCorreo(request.getCorreo());
        nuevoUsuario.setFechaNacimiento(LocalDate.parse(request.getFechaNacimiento()));
        nuevoUsuario.setDocumento(request.getDocumento());
        nuevoUsuario.setContrasena(request.getContrasena());
        nuevoUsuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(nuevoUsuario);

        return new UsuarioResponseDTO(
                guardado.getNombre(),
                guardado.getCorreo(),
                guardado.getRol().getNombre(),
                guardado.getClass().getSimpleName()
        );
    }

    @Override
    public UsuarioResponseDTO login(String correo, String contrasena) {
        return null;
    }
    @Override
    public UsuarioResponseDTO modificarDatos(Long id, UsuarioRequestDTO request) {
        return null;
    }
    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO request) {
        return actualizarUsuario(id, request);
    }

    // MÉTODOS DEL ADMINISTRADOR

    @Override public void otorgarPermisos(Long idUsuario, String nuevosPermisos) {}
    @Override public void actualizarPermisos(Long idUsuario, String permisosActualizados) {}
    @Override public void cancelarEventos(Long idEvento) {}
    @Override public void consultarEventos(Long idAdmin) {}
    @Override public void consultarReportes(Long idAdmin) {}
    @Override public void eliminarReportes(Long idReporte) {}
    @Override public void generarReportes(Long idAdmin) {}


    // MÉTODOS DEL ORGANIZADOR (Del Diagrama)
    @Override public void crearEvento(Long idOrganizador, Object eventoDto) {}
    @Override public void actualizarEvento(Long idEvento, Object eventoDto) {}
    @Override public UsuarioResponseDTO actualizarDatosPersonales(Long idOrganizador, UsuarioRequestDTO request) {
        return actualizarUsuario(idOrganizador, request);
    }
    @Override public void configurarTiposEntrada(Long idEvento, Object configuracion) {}


    // MÉTODOS DEL ASISTENTE (Del Diagrama)
    @Override public void buscarEventos(String parametrosBusqueda) {}
    @Override public void seleccionarEntradas(Long idEvento, int cantidad) {}
    @Override public void procesarPago(Long idOrden) {}
    @Override public void consultarEntrada(String codigo) {}

    // MÉTODOS DEL PERSONAL INGRESO
    @Override public void iniciarSecion(String credenciales) {}
    @Override public void validarEntrada(String codigo) {}
    @Override public void consultaEvento(Long idEvento) {}
}