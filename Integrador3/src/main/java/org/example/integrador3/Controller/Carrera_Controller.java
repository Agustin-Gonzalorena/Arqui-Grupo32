package org.example.integrador3.Controller;

import org.example.integrador3.Entidades.Carrera;
import org.example.integrador3.Entidades.DTO.CarreraConInscriptosYEgresados;
import org.example.integrador3.Repository.Carrera_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carreras")
public class Carrera_Controller {

    @Autowired
    private Carrera_Repository carrera_repo;

    public Carrera_Controller(Carrera_Repository carrera_repo) {
        this.carrera_repo = carrera_repo;
    }

    @PostMapping("/")
    public Carrera addCarrera(@PathVariable Carrera carrera){
        return carrera_repo.save(carrera);
    }

    @GetMapping("/getCarrerasConInscriptosOrdenada")
    public Iterable<Carrera> getCarrerasConInscriptosOrdenada(){
        return carrera_repo.getCarrerasConInscriptosOrdenada();
    }

    @GetMapping("/getCarrerasConCantInscriptosEgresados")
    public Iterable<CarreraConInscriptosYEgresados> getCarrerasConCantInscriptosEgresados(){
        return carrera_repo.getCarrerasConCantInscriptosEgresados();
    }


}
