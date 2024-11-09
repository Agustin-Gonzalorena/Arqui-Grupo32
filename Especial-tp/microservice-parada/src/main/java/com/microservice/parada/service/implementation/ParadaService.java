package com.microservice.parada.service.implementation;

import com.microservice.parada.persistence.entity.Parada;
import com.microservice.parada.persistence.repository.ParadaRepo;
import com.microservice.parada.presentation.dto.ParadaCreateDTO;
import com.microservice.parada.service.exception.ParadaException;
import com.microservice.parada.service.utils.Gps;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepo paradaRepo;

    @PostConstruct
    public void initData(){
        Parada p1 = Parada.builder()
                .id("9d2c1824-91db-49ba-b3c0-63f2f15ba0ea")
                .latitud(-37.329617)
                .longitud(-59.137109).build();
        Parada p2=Parada.builder()
                .id("4c8c2d0d-e9f2-44f8-9975-cb921c3a3e84")
                .latitud(-37.341537)
                .longitud(-59.130793).build();
        paradaRepo.save(p1);
        paradaRepo.save(p2);
    }

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
            if(oldParada.getLatitud()!=null && oldParada.getLongitud()!=null){
                throw new ParadaException("Debe actualizar los dos valores");
            }
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

    public List<Parada> cerca(Double userLat, Double userLong) {
        try {
            double[] limites = Gps.calcularCuadradoLimite(userLat, userLong, 1);
            return paradaRepo.cercanas(limites[0], limites[1], limites[2], limites[3]);
        } catch (Exception e) {
            throw new ParadaException("Error al cercar las paradas");
        }
    }


}
