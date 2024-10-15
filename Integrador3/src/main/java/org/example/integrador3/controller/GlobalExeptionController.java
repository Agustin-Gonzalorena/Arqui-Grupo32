package org.example.integrador3.controller;

import org.example.integrador3.exception.GlobalExeption;
import org.example.integrador3.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExeptionController {

    @ExceptionHandler(value = GlobalExeption.class)
    public ResponseEntity<ErrorResponse> badRequest(GlobalExeption ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new  ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }
}
