package org.example.integrador3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO;

import java.util.List;

@Entity
@Data
public class Estudiante {

    private String nombre;
    private String apellido;
    private String genero;
    @Id
    private Integer DNI;
    private String ciudadResidencia;
    @Column(unique = true)
    private Integer nroLibretaUniversitaria;
    @OneToMany(mappedBy = "keyInscripcion.estudiante")
    /*@JsonIgnore*/
    private List<Inscripcion> inscripciones;

    public Estudiante(){}
    public Estudiante(EstudianteSinInscripcionesDTO eDTO){
        this.nombre = eDTO.getNombre();
        this.apellido = eDTO.getApellido();
        this.genero = eDTO.getGenero();
        this.DNI = eDTO.getDNI();
        this.ciudadResidencia = eDTO.getCiudadResidencia();
        this.nroLibretaUniversitaria = eDTO.getNroLibretaUniversitaria();
    }



}
