package com.microservice.parada.presentation.controller;

import com.microservice.parada.presentation.dto.ParadaCreateDTO;
import com.microservice.parada.presentation.response.ApiResponse;
import com.microservice.parada.service.implementation.ParadaService;
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
    public ResponseEntity<?> create(@RequestBody ParadaCreateDTO paradaCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(),paradaService.create(paradaCreateDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(),paradaService.findById(id)));
    }
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(),paradaService.findAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ParadaCreateDTO paradaCreateDTO, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), paradaService.update(paradaCreateDTO,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), paradaService.delete(id)));
    }
}
