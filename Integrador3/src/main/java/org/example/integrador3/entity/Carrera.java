package org.example.integrador3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data //genera @getter @setter @toSting @requiredArgsConstructor @equalsAndHashcode
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    //cascade = CascadeType.ALL
    @OneToMany(mappedBy = "keyInscripcion.carrera")
    private List<Inscripcion> inscripciones;

}
