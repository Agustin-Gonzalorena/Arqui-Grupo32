package com.microservice.administracion.service.implementation;

import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.persistence.repository.TarifaRepo;
import com.microservice.administracion.service.exception.AdministracionException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TarifaService {
    @Autowired
    private TarifaRepo tarifaRepo;

    public Tarifa addTarifa(Tarifa tarifa) {
        try{
            Tarifa t1 = Tarifa.builder()
                    .precio(tarifa.getPrecio())
                    .extra(tarifa.getExtra())
                    .fechaVigencia(tarifa.getFechaVigencia()).build();
            tarifaRepo.save(t1);
            return t1;
        } catch (Exception e) {
            throw new AdministracionException("Error al actualizar la tarifa");
        }
    }

    public Tarifa getTarifaVigente(){
        try{
            return tarifaRepo.findByFechaVigencia(LocalDate.now());
        } catch (Exception e) {
            throw new AdministracionException("Error al obtener la tarifa vigente");
        }
    }
}
