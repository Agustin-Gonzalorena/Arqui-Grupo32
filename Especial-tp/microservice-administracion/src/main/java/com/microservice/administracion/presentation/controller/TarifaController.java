package com.microservice.administracion.presentation.controller;

import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.presentation.dto.TarifaDTO;
import com.microservice.administracion.presentation.response.ApiResponse;
import com.microservice.administracion.service.implementation.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarifa")
public class TarifaController {
    @Autowired
    private TarifaService tarifaService;

    @PostMapping("")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Definir una nueva tarifa (f)", description = "Requiere token de Administrador")
    public ResponseEntity<?> addTarifa(@RequestBody TarifaDTO tarifaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), tarifaService.addTarifa(tarifaDTO)));
    }

    @GetMapping("/vigente")
    @Operation(hidden = true)
    public ResponseEntity<?> getTarifaVigente() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), tarifaService.getTarifaVigente()));
    }
}
