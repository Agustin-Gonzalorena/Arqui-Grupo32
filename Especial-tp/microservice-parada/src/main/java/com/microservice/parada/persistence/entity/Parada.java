package com.microservice.parada.persistence.entity;

import com.microservice.parada.presentation.dto.ParadaCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parada {
    @Id
    private String id;
    private Double latitud;
    private Double longitud;//Haversine

    public Parada(ParadaCreateDTO paradaCreateDTO) {
        //generar uuID random y setearlo
        this.id = UUID.randomUUID().toString();
        this.latitud = paradaCreateDTO.getLatitud();
        this.longitud = paradaCreateDTO.getLongitud();
    }
}
