package org.example.integrador3.service;

import org.example.integrador3.entity.Carrera;
import org.example.integrador3.entity.Estudiante;
import org.example.integrador3.entity.Inscripcion;
import org.example.integrador3.entity.KeyInscripcion;
import org.example.integrador3.entity.dto.InscripcionDTO;
import org.example.integrador3.exception.GlobalExeption;
import org.example.integrador3.repository.InscripcionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InscripcionService {
    private InscripcionRepo inscripcionRepo;
    private EstudianteService estudianteService;
    private CarreraService carreraService;

    public InscripcionService(InscripcionRepo ir, EstudianteService es,CarreraService cs) {
        this.inscripcionRepo = ir;
        this.estudianteService = es;
        this.carreraService = cs;
    }

    @Transactional
    public InscripcionDTO create(InscripcionDTO inscripcionDTO) {
        try {

            Estudiante e = estudianteService.findByDni(inscripcionDTO.getEstudiante_dni());
            if(e==null){
                throw new GlobalExeption("El estudiante que desea inscribir no existe.");
            }
            Carrera c = carreraService.getById(inscripcionDTO.getCarrera_id());
            if(c==null){
                throw new GlobalExeption("La carrera con el id: "+inscripcionDTO.getCarrera_id()+" no existe.");
            }
            KeyInscripcion keyInscripcion = new KeyInscripcion(e,c);

            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setKeyInscripcion(keyInscripcion);
            inscripcion.setFechaInscripcion(LocalDate.now());

            if(inscripcionRepo.getById(keyInscripcion)!=null){
                throw new GlobalExeption("Estudiante ya registrado.");
            }
            inscripcionRepo.save(inscripcion);
            return inscripcionDTO;
        }catch (Exception e){
            throw new GlobalExeption(e.getMessage());
        }
    }
}
