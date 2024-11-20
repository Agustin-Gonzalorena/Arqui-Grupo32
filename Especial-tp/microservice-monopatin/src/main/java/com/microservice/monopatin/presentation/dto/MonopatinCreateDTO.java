package com.microservice.monopatin.presentation.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MonopatinCreateDTO {
    @Schema(example = "4c8c2d0d-e9f2-44f8-9975-cb921c3a3e84")
    private String paradaId;

    public MonopatinCreateDTO() {}
    public MonopatinCreateDTO(String paradaId) {
        this.paradaId = paradaId;
    }
}
