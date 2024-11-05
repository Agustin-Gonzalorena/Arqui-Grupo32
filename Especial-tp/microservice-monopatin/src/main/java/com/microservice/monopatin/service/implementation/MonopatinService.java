package com.microservice.monopatin.service.implementation;

import com.microservice.monopatin.persistence.entity.Monopatin;
import com.microservice.monopatin.persistence.repository.MonopatinRepo;
import com.microservice.monopatin.presentation.dto.MonopatinCreateDTO;
import com.microservice.monopatin.presentation.dto.ParadaResponseDTO;
import com.microservice.monopatin.service.exception.MonopatinException;
import com.microservice.monopatin.service.http.ParadaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepo monopatinRepo;
    @Autowired
    private ParadaClient paradaClient;

    public Monopatin create(MonopatinCreateDTO monopatinDTO) {
        try{
            //Arreglar esto TODO
            ResponseEntity<ParadaResponseDTO> response=paradaClient.getParadaById(monopatinDTO.getParadaId());

            Monopatin m = new Monopatin(monopatinDTO);
            return monopatinRepo.save(m);
        }catch (Exception e){
            throw new MonopatinException("Error al crear el monopatin, revise que la parada existe");
        }
    }

    public Monopatin findById(Long id) {
        return monopatinRepo.findById(id)
                .orElseThrow(()-> new MonopatinException("El monopatin con el id " + id + " no existe"));
    }

    public List<Monopatin> findAll() {
        try {
            return monopatinRepo.findAll();
        }catch (Exception e){
            throw new MonopatinException("Error al consultar los monopatin");
        }
    }

    public Monopatin update(Long id,Monopatin m){
        Monopatin m1 = findById(id);
        try{
            m1.setEnMantenimiento(m.isEnMantenimiento());
            m1.setKilometros(m.getKilometros());
            m1.setUltimaParadaId(m.getUltimaParadaId());
            m1.setTiempoConPausa(m.getTiempoConPausa());
            m1.setTiempoSinPausa( m.getTiempoSinPausa());
            return monopatinRepo.save(m1);
        } catch (Exception e) {
            throw new MonopatinException("Error al actualizar el monopatin");
        }
    }

    public Monopatin delete(Long id) {
        Monopatin m = findById(id);
        monopatinRepo.delete(m);
        return m;
    }
}
