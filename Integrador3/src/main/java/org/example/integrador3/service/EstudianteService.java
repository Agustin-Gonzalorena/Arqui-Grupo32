package org.example.integrador3.service;

import org.example.integrador3.entity.Estudiante;
import org.example.integrador3.entity.dto.EstudianteConCarreraDTO;
import org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO;
import org.example.integrador3.exception.GlobalExeption;
import org.example.integrador3.repository.EstudianteRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteService {
    private EstudianteRepo estudianteRepo;

    public EstudianteService(EstudianteRepo estudianteRepo) {
        this.estudianteRepo = estudianteRepo;
    }

    @Transactional
    public EstudianteSinInscripcionesDTO create(EstudianteSinInscripcionesDTO estudianteDto)  {
        try {
            if(estudianteRepo.existsByDni(estudianteDto.getDNI())){
                throw new GlobalExeption("El dni ya se encuentra registrado.");
            } else if (estudianteRepo.existByNroLibreta(estudianteDto.getNroLibretaUniversitaria())) {
                throw new GlobalExeption("El nro libreta ya existe.");
            }

            Estudiante e = new Estudiante(estudianteDto);
            estudianteRepo.save(e);
            return estudianteDto;
        }
        catch (Exception e) {
            throw new GlobalExeption(e.getMessage());
        }
    }

    @Transactional
    public EstudianteSinInscripcionesDTO getByNroLibreta(int nroLibreta){
        try{
            if(!estudianteRepo.existByNroLibreta(nroLibreta)){
                throw new Exception("No existe un estudiante con el nro libreta: " + nroLibreta+".");
            }
            return estudianteRepo.getByNroLibreta(nroLibreta);
        }catch (Exception e){
            throw new GlobalExeption(e.getMessage());
        }
    }

    public Page<EstudianteSinInscripcionesDTO> search( EstudianteConCarreraDTO estudiante,
                                                      Pageable pageable ) {
        String ciudad=estudiante.getCiudad();
            if(estudiante.getCiudad()!=null)
                 ciudad=estudiante.getCiudad().replace("_"," ");
        try {
            return estudianteRepo.search(estudiante.getNombre(), estudiante.getApellido(),
                    estudiante.getDni(),estudiante.getGenero(), ciudad,
                    estudiante.getNroLibretaUniversitaria(),estudiante.getCarrera_id(), pageable);

        }catch (Exception e) {
            throw new GlobalExeption(e.getMessage());
        }
    }

    //lo usa inscripcion
    @Transactional
    public Estudiante findByDni(int dni) throws Exception{
        try {
            return estudianteRepo.findByDni(dni);
        }catch (Exception e) {
            throw new Exception("Error al obtener el estudiante.");
        }
    }
}
