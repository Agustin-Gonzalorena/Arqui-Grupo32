package com.microservice.monopatin.presentation.controller;

import com.microservice.monopatin.persistence.entity.Monopatin;
import com.microservice.monopatin.presentation.dto.MonopatinCreateDTO;
import com.microservice.monopatin.presentation.response.ApiResponse;
import com.microservice.monopatin.service.implementation.MonopatinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/monopatin")
public class MonopatinController {
    @Autowired
    private MonopatinService monopatinService;

    @PostMapping("")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Crear nuevo Monopatin",description = "Requiere token de Administrador")
    public ResponseEntity<?> save(@RequestBody MonopatinCreateDTO monopatinCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), monopatinService.create(monopatinCreateDTO)));
    }

    @GetMapping("")
    @Operation(hidden = true)
    //@Operation(summary = "Secure endpoint",description = "requiere autorizacion",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.findById(id)));
    }

    @PutMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Monopatin m) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.update(id,m)));
    }

    @DeleteMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.delete(id)));
    }

    @PutMapping("/mantenimiento/{id}")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Poner o sacar monopatin de mantenimiento", description = "Requiere token de Mantenimiento")
    public ResponseEntity<?> mantenimiento(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.ponerEnMantenimiento(id)));
    }
    @GetMapping("/viajes")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
            summary = "Obtener monopatines por la cantidad de viajes en un año (c)", description = "Requiere token de Administrador")
    public ResponseEntity<?> viajes(@Parameter(example = "2") @RequestParam int cantidad,
                                    @Parameter(example = "2024") @RequestParam int anio){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.porCantidadViajes(cantidad,anio)));
    }
    @GetMapping("/estado")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Obtener reporte de monopatines en mantenimiento vs disponibles (e)", description = "Requiere token de Administrador")
    public ResponseEntity<?> contarPorEstado(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.contarPorEstado()));
    }
    @GetMapping("/reporte")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Obtener reporte de mantenimiento (a)", description = "Requiere token de Mantenimiento")
    public ResponseEntity<?> reporteUso(@RequestParam(value = "conPausa", required = false, defaultValue = "false")boolean conPausa) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.reporte(conPausa)));
    }
}
