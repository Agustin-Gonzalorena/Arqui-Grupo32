package com.microservice.monopatin.service.implementation;

import com.microservice.monopatin.persistence.entity.Monopatin;
import com.microservice.monopatin.persistence.repository.MonopatinRepo;
import com.microservice.monopatin.presentation.dto.EnOperacionDTO;
import com.microservice.monopatin.presentation.dto.MonopatinCreateDTO;
import com.microservice.monopatin.presentation.dto.ReporteMonopatinDTO;
import com.microservice.monopatin.presentation.response.ApiResponse;
import com.microservice.monopatin.service.exception.MonopatinException;
import com.microservice.monopatin.service.http.ParadaClient;
import com.microservice.monopatin.service.http.ViajeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepo monopatinRepo;
    @Autowired
    private ParadaClient paradaClient;
    @Autowired
    private ViajeClient viajeClient;

    public Monopatin create(MonopatinCreateDTO monopatinDTO) {
        try{
            //si no existe la parada rompe y va al throw
            paradaClient.getParadaById(monopatinDTO.getParadaId());

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

    public Monopatin ponerEnMantenimiento(Long id) {
        try{
            Monopatin m = findById(id);
            m.setEnMantenimiento(!m.isEnMantenimiento());
            return monopatinRepo.save(m);
        }catch (Exception e){
            throw new MonopatinException("Error al actualizar el monopatin");
        }
    }
    public List<Monopatin> porCantidadViajes(int viajes,int anio) {
        //pedir a los viajes los ids de los monopatines con mas de x viajes
        try {
            ApiResponse<List<Long>> response = viajeClient.getIdmonopatinPorViajes(viajes, anio).getBody();
            List<Long> monopatinIds = response.getData();
            List<Monopatin> out = new ArrayList<>();
            for (Long monopatinId : monopatinIds) {
                out.add(findById(monopatinId));
            }
            return out;
        }catch (Exception e){
            throw new MonopatinException("Error al consultar los monopatin");
        }

    }
    public EnOperacionDTO contarPorEstado(){
        try {
            return monopatinRepo.contarPorEstado();
        }catch (Exception e){
            throw new MonopatinException("Error al consultar los monopatin");
        }
    }

    public List<ReporteMonopatinDTO> reporte(Boolean conPausa ){
        try{
            return monopatinRepo.reporte(conPausa);
        } catch (Exception e) {
            throw new MonopatinException("Error al generar el reporte");
        }
    }
}
