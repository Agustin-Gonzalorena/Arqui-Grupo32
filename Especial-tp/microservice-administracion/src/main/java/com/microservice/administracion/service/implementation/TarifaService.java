package com.microservice.administracion.service.implementation;

import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.persistence.repository.TarifaRepo;
import com.microservice.administracion.presentation.dto.TarifaDTO;
import com.microservice.administracion.service.exception.AdministracionException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TarifaService {
    @Autowired
    private TarifaRepo tarifaRepo;

    public TarifaDTO addTarifa(TarifaDTO tarifaDTO) {
        try{
            Tarifa t1 = new Tarifa(tarifaDTO);
            tarifaRepo.save(t1);
            return tarifaDTO;
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
