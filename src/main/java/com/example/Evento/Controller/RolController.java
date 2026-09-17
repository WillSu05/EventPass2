package com.example.Evento.Controller;

import com.example.Evento.DTO.Request.RolRequestDTO;
import com.example.Evento.DTO.Response.RolResponseDTO;
import com.example.Evento.Entity.Rol;
import com.example.Evento.Service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;
    @GetMapping("/listarRoles")
    public List<RolResponseDTO> listar() {
        return rolService.listarRoles();
    }
    @PostMapping("/crearRol")
    public RolResponseDTO crear(@RequestBody RolRequestDTO request) {
        return rolService.crearRol(request);
    }

}
