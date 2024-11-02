package com.microservice.monopatin.presentation.dto;


import lombok.Data;

@Data
public class MonopatinCreateDTO {
    private Long paradaId;

    public MonopatinCreateDTO() {}
    public MonopatinCreateDTO(Long paradaId) {
        this.paradaId = paradaId;
    }
}
