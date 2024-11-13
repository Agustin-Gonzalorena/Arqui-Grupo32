package com.microservice.viaje.presentation.advice;

import com.microservice.viaje.presentation.response.ErrorResponse;
import com.microservice.viaje.service.exception.ViajeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ViajeControllerAdvice {

    @ExceptionHandler(value = ViajeException.class)
    public ResponseEntity<ErrorResponse> badRequest(ViajeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
