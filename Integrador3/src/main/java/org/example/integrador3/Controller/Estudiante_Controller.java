package org.example.integrador3.Controller;

import org.example.integrador3.Entidades.Estudiante;
import org.example.integrador3.Repository.Estudiante_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class Estudiante_Controller {

    @Autowired
    private Estudiante_Repository estudiante_repo;

    public Estudiante_Controller(Estudiante_Repository estudiante_repo) {
        this.estudiante_repo = estudiante_repo;
    }

    @PostMapping("/")
    public Estudiante altaEstudiante(@RequestBody Estudiante estudiante) {
        return estudiante_repo.save(estudiante);
    }

    @GetMapping("/getEstudiantesOrdenados/{orden}")
    public Iterable<Estudiante> getEstudiantesOrdenados(@PathVariable String orden) {
        return estudiante_repo.getEstudiantesOrdenados(orden);
    }

    @GetMapping("/getEstudiantePorNroLibreta/{libreta}")
    public Estudiante getEstudiantesOrdenados(@PathVariable int libreta) {
        return estudiante_repo.getEstudiantePorNroLibreta(libreta);
    }

    @GetMapping("/getEstudiantesPorGenero/{genero}")
    public Iterable<Estudiante> getEstudiantesPorGenero(@PathVariable String genero) {
        return estudiante_repo.getEstudiantesPorGenero(genero);
    }

    @GetMapping("/getEstudiantesPorCarreraCiudad/{carrera}/{ciudad}")
    public Iterable<Estudiante> getEstudiantesPorCarreraCiudad(@PathVariable String carrera, @PathVariable String ciudad) {
        return estudiante_repo.getEstudiantesPorCarreraCiudad(carrera, ciudad);
    }

}
