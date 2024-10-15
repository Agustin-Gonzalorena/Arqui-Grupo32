package org.example.integrador3.controller;

import jakarta.validation.Valid;
import org.example.integrador3.entity.dto.InscripcionDTO;
import org.example.integrador3.service.InscripcionService;
import org.example.integrador3.utils.ApiResponse;
import org.example.integrador3.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {

    private InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    //Ej-2-b
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid InscripcionDTO inscripcionDTO, BindingResult bindingResult) {
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getFieldError().getDefaultMessage()));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(HttpStatus.CREATED.value(), inscripcionService.create(inscripcionDTO)));
    }
}
