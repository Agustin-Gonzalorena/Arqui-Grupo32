package com.microservice.parada.persistence.entity;

import com.microservice.parada.presentation.dto.ParadaCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "parada")
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitud;
    private Double longitud;

    public Parada(ParadaCreateDTO paradaCreateDTO) {
        this.latitud = paradaCreateDTO.getLatitud();
        this.longitud = paradaCreateDTO.getLongitud();
    }
}
