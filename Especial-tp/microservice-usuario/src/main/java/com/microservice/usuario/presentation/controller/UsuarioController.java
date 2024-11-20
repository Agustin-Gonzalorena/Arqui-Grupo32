package com.microservice.usuario.presentation.controller;

import com.microservice.usuario.presentation.response.ApiResponse;
import com.microservice.usuario.service.implementation.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/ban/{id}")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Permite cambiar el estado de ban del usuario (b)", description = "Requiere token de Administrador")
    public ResponseEntity<?> ban(@Parameter(example = "4") @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), usuarioService.ban(id)));
    }

    @GetMapping("cobrar")
    @Operation(hidden = true)
    public ResponseEntity<?> cobrar(@RequestParam("id") Long id,@RequestParam("monto") Double monto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), usuarioService.cobrar(id,monto)));
    }

    @GetMapping("/email/{email}")
    @Operation(hidden = true)
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), usuarioService.getByEmail(email)));
    }
}
