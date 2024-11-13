package com.microservice.administracion.presentation.controller;

import com.microservice.administracion.presentation.dto.ViajeDTO;
import com.microservice.administracion.presentation.response.ApiResponse;
import com.microservice.administracion.service.implementation.FacturaService;
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
    public ResponseEntity<?> create(@RequestBody ViajeDTO viajeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.OK.value(), facturaService.create(viajeDTO)));
    }

    @GetMapping("/total")
    public ResponseEntity<?> total(@RequestParam int anio, @RequestParam int mesDesde, @RequestParam int mesHasta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), facturaService.totalFacturado(anio, mesDesde, mesHasta)));
    }
}
