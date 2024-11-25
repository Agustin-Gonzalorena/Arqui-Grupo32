package com.microservice.viaje.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "viaje")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "usuario_id")
    private Long usuarioId;
    @Column(nullable = false,name = "monopatin_id")
    private Long monopatinId;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private LocalDateTime inicioPausa;
    private LocalDateTime finPausa;
    private Double kilometros;

    public Viaje(Long monopatinId, Long usuarioId){
        this.usuarioId=usuarioId;
        this.monopatinId=monopatinId;
        this.inicio=LocalDateTime.now();
        this.fin=this.inicio;
        this.kilometros=0.0;
        this.inicioPausa=null;
        this.finPausa=null;
    }
}
