package com.microservice.parada.presentation.controller;

import com.microservice.parada.presentation.dto.ParadaCreateDTO;
import com.microservice.parada.presentation.response.ApiResponse;
import com.microservice.parada.service.implementation.ParadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parada")
public class ParadaController {
    @Autowired
    private ParadaService paradaService;

    @PostMapping("")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
            summary = "Crear una nueva parada", description = "Requiere token de Administrador")
    public ResponseEntity<?> create(@RequestBody ParadaCreateDTO paradaCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(),paradaService.create(paradaCreateDTO)));
    }

    @GetMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(),paradaService.findById(id)));
    }
    @GetMapping("")
    @Operation(summary = "Obetener todas las paradas existentes")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(),paradaService.findAll()));
    }

    @PutMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<?> update(@RequestBody ParadaCreateDTO paradaCreateDTO, @PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), paradaService.update(paradaCreateDTO,id)));
    }

    @DeleteMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), paradaService.delete(id)));
    }

    @GetMapping("/cerca")
    @Operation(security = @SecurityRequirement(name = "Security Token" ),
                summary = "Obtener las paradas cerca de la ubicacion del usuario (g)", description = "Requiere token de Usuario")
    public ResponseEntity<?> paradasCerca(@Parameter(example = "-37.340534") @RequestParam (name = "latitud") Double latitud,
                                          @Parameter(example = "-59.119879") @RequestParam (name = "longitud") Double longitud) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), paradaService.cerca(latitud, longitud)));
    }
}
