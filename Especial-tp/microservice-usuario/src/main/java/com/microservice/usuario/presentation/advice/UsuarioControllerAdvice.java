package com.microservice.usuario.presentation.advice;

import com.microservice.usuario.presentation.response.ErrorResponse;
import com.microservice.usuario.service.exception.UsuarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsuarioControllerAdvice {
    @ExceptionHandler(value = UsuarioException.class)
    public ResponseEntity<ErrorResponse> badRequest(UsuarioException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
