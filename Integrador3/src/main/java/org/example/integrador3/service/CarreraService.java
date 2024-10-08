package org.example.integrador3.service;

import org.example.integrador3.entity.Carrera;
import org.example.integrador3.entity.dto.CarreraReporteDTO;
import org.example.integrador3.entity.dto.CarreraConInscriptosDTO;
import org.example.integrador3.entity.dto.CarreraSinInscripcionesDTO;
import org.example.integrador3.exception.GlobalExeption;
import org.example.integrador3.repository.CarreraRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarreraService {
    private CarreraRepo carreraRepo;

    public CarreraService(CarreraRepo carreraRepo) {
        this.carreraRepo = carreraRepo;
    }

    @Transactional
    public Carrera getById(long id)throws Exception{
        try {
            return carreraRepo.getById(id);
        }catch (Exception e){
            throw new Exception("Error al obtener la carrera.");
        }
    }

    @Transactional
    public List<CarreraSinInscripcionesDTO> getAll(){
        try {
            return carreraRepo.getAll();
        } catch (Exception e) {
            throw new GlobalExeption("Error al obtener los carreras.");
        }
    }

    @Transactional
    public List<CarreraConInscriptosDTO> getCarrerasInscriptosOrdenada(){
        try {
            return carreraRepo.getCarrerasInscriptosOrdenada();
        }catch (Exception e){
            throw new GlobalExeption("Error al obtener los carreras con inscripciones.");
        }
    }

    @Transactional
    public List<CarreraReporteDTO> getReporte(){
        try {
            return carreraRepo.getReporte();
        } catch (Exception e) {
            throw new GlobalExeption("Error al obtener el reporte de carreras.");
        }
    }
}
