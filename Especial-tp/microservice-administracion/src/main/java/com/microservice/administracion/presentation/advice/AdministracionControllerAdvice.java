package com.microservice.administracion.presentation.advice;

import com.microservice.administracion.presentation.response.ErrorResponse;
import com.microservice.administracion.service.exception.AdministracionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdministracionControllerAdvice {
    @ExceptionHandler(value = AdministracionException.class)
    public ResponseEntity<ErrorResponse> badRequest(AdministracionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
