package org.example.integrador3.controller;

import org.example.integrador3.service.CarreraService;
import org.example.integrador3.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    private CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }


    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), carreraService.getAll()));
    }
    //Ej-2f
    @GetMapping("/inscriptos")
    public ResponseEntity<?> getInscriptos(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), carreraService.getCarrerasInscriptosOrdenada()));
    }
    //Ej-2h
    @GetMapping("/reporte")
    public ResponseEntity<?> getReporte(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), carreraService.getReporte()));
    }

}
