package com.microservice.parada.presentation.advice;

import com.microservice.parada.presentation.response.ErrorResponse;
import com.microservice.parada.service.exception.ParadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParadaControllerAdvice {

    @ExceptionHandler(value = ParadaException.class)
    public ResponseEntity<ErrorResponse> badRequest(ParadaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
