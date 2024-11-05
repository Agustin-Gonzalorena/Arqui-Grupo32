package com.microservice.parada.service.implementation;

import com.microservice.parada.persistence.entity.Parada;
import com.microservice.parada.persistence.repository.ParadaRepo;
import com.microservice.parada.presentation.dto.ParadaCreateDTO;
import com.microservice.parada.service.exception.ParadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepo paradaRepo;

    public Parada create(ParadaCreateDTO pDto) {
        try{
            Parada parada = new Parada(pDto);
            return paradaRepo.save(parada);
        }catch(Exception e){
            throw new ParadaException("Error al crear la parada");
        }
    }

    public Parada findById(String id) {
        return paradaRepo.findById(id)
                .orElseThrow(()->new ParadaException("La parada con el id: " + id + " no existe"));
    }
    public List<Parada> findAll() {
        try {
            return paradaRepo.findAll();
        }catch(Exception e){
            throw new ParadaException("Error al consultar las paradas");
        }
    }

    public Parada update(ParadaCreateDTO parada, String id) {
        Parada oldParada = findById(id);
        try{
            oldParada.setLongitud(parada.getLongitud());
            oldParada.setLatitud(parada.getLatitud());
            return paradaRepo.save(oldParada);
        } catch (Exception e) {
            throw new ParadaException("Error al actualizar la parada");
        }
    }

    public Parada delete(String id) {
        Parada parada = findById(id);
        try{
            paradaRepo.delete(parada);
            return parada;
        }catch(Exception e){
            throw new ParadaException("Error al eliminar la parada");
        }
    }


}
