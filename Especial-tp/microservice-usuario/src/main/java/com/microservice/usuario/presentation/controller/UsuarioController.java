package com.microservice.usuario.presentation.controller;

import com.microservice.usuario.presentation.response.ApiResponse;
import com.microservice.usuario.service.implementation.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/ban/{id}")
    public ResponseEntity<?> ban(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), usuarioService.ban(id)));
    }
}
