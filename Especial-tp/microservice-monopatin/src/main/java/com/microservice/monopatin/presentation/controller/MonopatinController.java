package com.microservice.monopatin.presentation.controller;

import com.microservice.monopatin.persistence.entity.Monopatin;
import com.microservice.monopatin.presentation.dto.MonopatinCreateDTO;
import com.microservice.monopatin.presentation.response.ApiResponse;
import com.microservice.monopatin.service.implementation.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monopatin")
public class MonopatinController {
    @Autowired
    private MonopatinService monopatinService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody MonopatinCreateDTO monopatinCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), monopatinService.create(monopatinCreateDTO)));
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Monopatin m) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.update(id,m)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.delete(id)));
    }

    @GetMapping("/mantenimiento/{id}")
    public ResponseEntity<?> mantenimiento(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.ponerEnMantenimiento(id)));
    }
    @GetMapping("/viajes")
    public ResponseEntity<?> viajes(@RequestParam int cantidad,@RequestParam int anio){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.porCantidadViajes(cantidad,anio)));
    }
    @GetMapping("/estado")
    public ResponseEntity<?> contarPorEstado(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), monopatinService.contarPorEstado()));
    }
}
