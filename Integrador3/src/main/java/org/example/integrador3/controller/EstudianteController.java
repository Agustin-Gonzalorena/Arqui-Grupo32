package org.example.integrador3.controller;

import jakarta.validation.Valid;
import org.example.integrador3.entity.dto.EstudianteConCarreraDTO;
import org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO;
import org.example.integrador3.service.EstudianteService;
import org.example.integrador3.utils.ApiResponse;
import org.example.integrador3.utils.ErrorResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    private EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    //Ej-2-a
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid EstudianteSinInscripcionesDTO estudiante, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getFieldError().getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), estudianteService.create(estudiante)));
    }

    //Ej-2-c-e-g
    @GetMapping("")
    public ResponseEntity<?> getAll(EstudianteConCarreraDTO estudiante, Pageable pageable) {
        return  ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), estudianteService.search(estudiante,pageable)));
    }

    //Ej-2-d
    @GetMapping("/{nroLibreta}")
    public ResponseEntity<?> getByNroLibreta(@PathVariable int nroLibreta){
        return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(HttpStatus.OK.value(),estudianteService.getByNroLibreta(nroLibreta)));
    }
}
