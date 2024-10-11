package org.example.integrador3.Controller;

import org.example.integrador3.Entidades.Carrera;
import org.example.integrador3.Entidades.Estudiante;
import org.example.integrador3.Entidades.Inscripcion;
import org.example.integrador3.Repository.Carrera_Repository;
import org.example.integrador3.Repository.Estudiante_Repository;
import org.example.integrador3.Repository.Inscripcion_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripciones")
public class Inscripcion_Controller {

    @Autowired
    private Inscripcion_Repository inscripcion_repo;

    @Autowired
    private Carrera_Repository carrera_repo;

    @Autowired
    private Estudiante_Repository estudiante_repo;

    public Inscripcion_Controller(Inscripcion_Repository inscripcion_repo,
                                  Carrera_Repository carrera_repo,
                                  Estudiante_Repository estudiante_repo) {
        this.inscripcion_repo = inscripcion_repo;
        this.carrera_repo = carrera_repo;
        this.estudiante_repo = estudiante_repo;
    }

    @PostMapping("/matricular")
    public void matricular(@PathVariable Inscripcion inscripcion) {
        Estudiante estudiante = inscripcion.getKeyInscripcion().getEstudiante();
        Carrera carrera = inscripcion.getKeyInscripcion().getCarrera();
        if(estudiante_repo.existsById(estudiante.getDni())){
            if(carrera_repo.existsById(carrera.getId())){
                inscripcion_repo.save(inscripcion);
            }else{
                System.out.println("Carrera no encontrada");
            }
        }else{
            System.out.println("Estudiante no encontrado");
        }
    }

}
