package com.microservice.monopatin.presentation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParadaResponseDTO {
    private LocalDateTime timestamp;
    private int status;
}
