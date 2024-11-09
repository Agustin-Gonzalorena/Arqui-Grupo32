package com.microservice.administracion.presentation.controller;

import com.microservice.administracion.presentation.dto.FacturaDTO;
import com.microservice.administracion.presentation.response.ApiResponse;
import com.microservice.administracion.service.implementation.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody FacturaDTO facturaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.OK.value(), facturaService.create(facturaDTO)));
    }
}
