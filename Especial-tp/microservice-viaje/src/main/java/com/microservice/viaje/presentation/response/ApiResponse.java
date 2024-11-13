package com.microservice.viaje.presentation.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private T data;

    public ApiResponse(int status, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.data = data;
    }
}
