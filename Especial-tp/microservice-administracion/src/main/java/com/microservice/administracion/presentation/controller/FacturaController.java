package com.microservice.administracion.presentation.controller;

import com.microservice.administracion.presentation.dto.ViajeDTO;
import com.microservice.administracion.presentation.response.ApiResponse;
import com.microservice.administracion.service.implementation.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping("")
    @Operation(hidden = true)
    public ResponseEntity<?> create(@RequestBody ViajeDTO viajeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.OK.value(), facturaService.create(viajeDTO)));
    }

    @GetMapping("/total")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Obtener el total facturado en un determinado tiempo (d)", description = "Requiere token de Administrador")
    public ResponseEntity<?> total(@Parameter(example = "2024") @RequestParam int anio,
                                   @Parameter(example = "1") @RequestParam int mesDesde,
                                   @Parameter(example = "2") @RequestParam int mesHasta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), facturaService.totalFacturado(anio, mesDesde, mesHasta)));
    }
}
