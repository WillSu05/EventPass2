package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.UsuarioRequestDTO;
import com.example.Evento.DTO.Response.UsuarioResponseDTO;
import com.example.Evento.Entity.Usuario;
import com.example.Evento.Exceptions.ResourceNotFoundException;
import com.example.Evento.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @GetMapping("/listarUsuarios")
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/buscarUsuario/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrarUsuario")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRequestDTO request) {
        try {
            return ResponseEntity.ok(usuarioService.registrarUsuario(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/loginUsuario")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestParam String correo, @RequestParam String contrasena) {
        try {
            return ResponseEntity.ok(usuarioService.login(correo, contrasena));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build(); // No autorizado
        }
    }

    @PutMapping("/actualizarUsuario/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        try {
            return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/modificarDatosUsuario/{id}")
    public ResponseEntity<UsuarioResponseDTO> modificarDatos(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        try {
            return ResponseEntity.ok(usuarioService.modificarDatos(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==========================================
    // ENDPOINTS ADMINISTRADOR
    // ==========================================
    @PostMapping("/usuarioAdmin/{id}/Otorgarpermisos")
    public ResponseEntity<Void> otorgarPermisos(@PathVariable Long id, @RequestParam String permisos) {
        usuarioService.otorgarPermisos(id, permisos);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/usuarioAdmin/{id}/Actualizarpermisos")
    public ResponseEntity<Void> actualizarPermisos(@PathVariable Long id, @RequestParam String permisos) {
        usuarioService.actualizarPermisos(id, permisos);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuarioAdmin/cancelar-evento/{idEvento}")
    public ResponseEntity<Void> cancelarEvento(@PathVariable Long idEvento) {
        usuarioService.cancelarEventos(idEvento);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuarioAdmin/{idAdmin}/consultar-eventos")
    public ResponseEntity<Void> consultarEventos(@PathVariable Long idAdmin) {
        usuarioService.consultarEventos(idAdmin);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuarioAdmin/{id}/reportes")
    public ResponseEntity<Void> consultarReportes(@PathVariable Long id) {
        usuarioService.consultarReportes(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/usuarioAdmin/eliminar-reporte/{idReporte}")
    public ResponseEntity<Void> eliminarReportes(@PathVariable Long idReporte) {
        usuarioService.eliminarReportes(idReporte);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuarioAdmin/{idAdmin}/generar-reporte")
    public ResponseEntity<Void> generarReportes(@PathVariable Long idAdmin) {
        usuarioService.generarReportes(idAdmin);
        return ResponseEntity.ok().build();
    }

    // ==========================================
    // ENDPOINTS ORGANIZADOR
    // ==========================================
    @PostMapping("/usuarioOrganizador/{idOrganizador}/crear-evento")
    public ResponseEntity<Void> crearEvento(@PathVariable Long idOrganizador, @RequestBody Object eventoDto) {
        usuarioService.crearEvento(idOrganizador, eventoDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/usuarioOrganizador/actualizar-evento/{idEvento}")
    public ResponseEntity<Void> actualizarEvento(@PathVariable Long idEvento, @RequestBody Object eventoDto) {
        usuarioService.actualizarEvento(idEvento, eventoDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/usuarioOrganizador/{id}/datos-personales")
    public ResponseEntity<UsuarioResponseDTO> actualizarDatosOrganizador(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.actualizarDatosPersonales(id, request));
    }

    @PostMapping("/usuarioOrganizador/configurar-entradas/{idEvento}")
    public ResponseEntity<Void> configurarTiposEntrada(@PathVariable Long idEvento, @RequestBody Object configuracion) {
        usuarioService.configurarTiposEntrada(idEvento, configuracion);
        return ResponseEntity.ok().build();
    }

    // ==========================================
    // ENDPOINTS ASISTENTE
    // ==========================================
    @GetMapping("/usuarioAsistente/buscar-eventos")
    public ResponseEntity<Void> buscarEventos(@RequestParam String parametros) {
        usuarioService.buscarEventos(parametros);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuarioAsistente/seleccionar-entradas/{idEvento}")
    public ResponseEntity<Void> seleccionarEntradas(@PathVariable Long idEvento, @RequestParam int cantidad) {
        usuarioService.seleccionarEntradas(idEvento, cantidad);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuarioAsistente/procesar-pago/{idOrden}")
    public ResponseEntity<Void> procesarPago(@PathVariable Long idOrden) {
        usuarioService.procesarPago(idOrden);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuarioAsistente/consultar-entrada/{codigo}")
    public ResponseEntity<Void> consultarEntrada(@PathVariable String codigo) {
        usuarioService.consultarEntrada(codigo);
        return ResponseEntity.ok().build();
    }

    // ==========================================
    // ENDPOINTS PERSONAL INGRESO
    // ==========================================
    @PostMapping("/usuarioPersonal/iniciar-sesion")
    public ResponseEntity<Void> iniciarSecionPersonal(@RequestParam String credenciales) {
        usuarioService.iniciarSecion(credenciales);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuarioPersonal/validar-entrada/{codigo}")
    public ResponseEntity<Void> validarEntrada(@PathVariable String codigo) {
        usuarioService.validarEntrada(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuarioPersonal/consulta-evento/{idEvento}")
    public ResponseEntity<Void> consultaEvento(@PathVariable Long idEvento) {
        usuarioService.consultaEvento(idEvento);
        return ResponseEntity.ok().build();
    }
}
