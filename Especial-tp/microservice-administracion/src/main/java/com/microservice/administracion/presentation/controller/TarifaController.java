package com.microservice.administracion.presentation.controller;

import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.presentation.response.ApiResponse;
import com.microservice.administracion.service.implementation.TarifaService;
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
    public ResponseEntity<?> addTarifa(@RequestBody Tarifa tarifa) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), tarifaService.addTarifa(tarifa)));
    }

    @GetMapping("/vigente")
    public ResponseEntity<?> getTarifaVigente() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), tarifaService.getTarifaVigente()));
    }
}
