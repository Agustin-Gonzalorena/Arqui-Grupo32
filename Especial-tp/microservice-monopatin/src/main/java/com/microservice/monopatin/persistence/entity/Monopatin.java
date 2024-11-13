package com.microservice.monopatin.persistence.entity;

import com.microservice.monopatin.presentation.dto.MonopatinCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monopatin")
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double kilometros;
    @Column(name = "en_mantenimiento")
    private boolean enMantenimiento;
    @Column(name = "tiempo_sin_pausa")
    private double tiempoSinPausa;
    @Column(name = "tiempo_con_pausa")
    private double tiempoConPausa;
    @Column(name = "ultima_parada_id")
    private String ultimaParadaId;

    public Monopatin(MonopatinCreateDTO mDTO) {
        this.kilometros=0;
        this.enMantenimiento=false;
        this.tiempoSinPausa=0;
        this.tiempoConPausa=0;
        this.ultimaParadaId=mDTO.getParadaId();
    }

}
