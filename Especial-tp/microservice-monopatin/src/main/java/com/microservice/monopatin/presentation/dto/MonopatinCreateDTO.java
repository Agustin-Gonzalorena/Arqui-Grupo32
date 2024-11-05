package com.microservice.monopatin.presentation.dto;


import lombok.Data;

@Data
public class MonopatinCreateDTO {
    private String paradaId;

    public MonopatinCreateDTO() {}
    public MonopatinCreateDTO(String paradaId) {
        this.paradaId = paradaId;
    }
}
