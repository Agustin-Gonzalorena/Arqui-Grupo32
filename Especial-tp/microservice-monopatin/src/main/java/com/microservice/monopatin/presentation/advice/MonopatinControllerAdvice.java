package com.microservice.monopatin.presentation.advice;

import com.microservice.monopatin.presentation.response.ErrorResponse;
import com.microservice.monopatin.service.exception.MonopatinException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MonopatinControllerAdvice {

    @ExceptionHandler(value = MonopatinException.class)
    public ResponseEntity<ErrorResponse> badRequest(MonopatinException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

}
