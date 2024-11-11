package com.microservice.viaje.service.implementation;

import com.microservice.viaje.persistence.entity.Viaje;
import com.microservice.viaje.persistence.repository.ViajeRepo;
import com.microservice.viaje.presentation.dto.FacturaDTO;
import com.microservice.viaje.presentation.dto.MonopatinResponseDTO;
import com.microservice.viaje.presentation.dto.TarifaResponseDTO;
import com.microservice.viaje.presentation.dto.ViajeCreateDTO;
import com.microservice.viaje.presentation.response.ApiResponse;
import com.microservice.viaje.service.exception.ViajeException;
import com.microservice.viaje.service.http.FacturaClient;
import com.microservice.viaje.service.http.MonopatinClient;
import com.microservice.viaje.service.http.TarifaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ViajeService {
    @Autowired
    private ViajeRepo viajeRepo;
    @Autowired
    private MonopatinClient monopatinClient;
    @Autowired
    private TarifaClient tarifaClient;
    @Autowired
    private FacturaClient facturaClient;

    public Viaje create(ViajeCreateDTO viaje) {
        try{
            //si no existe va al catch
            ApiResponse<MonopatinResponseDTO> response = monopatinClient.getMonopatinById(viaje.getMonopatinId()).getBody();
            MonopatinResponseDTO monopatinDTO = response.getData();
            if(monopatinDTO.isEnMantenimiento()) throw new ViajeException("El monopatin esta en mantenimiento");
            //chequear que el usuario exista{peticion usuario} TODO
            //chequear que el monopatin no este en uso TODO
            if(viajeActivo(viaje.getUsuarioId())){//chequear que no tenga un viaje activo
                throw new Exception("El usuario no existe o tiene un viaje activo");
            }
            Viaje v = new Viaje(viaje);
            return viajeRepo.save(v);
        }catch(Exception e){
            throw new ViajeException("Error al crear el viaje");
        }
    }

    public Viaje finalizar(Long idUsuario){
        //chequear que este en una parada valida TODO
        try{
            if(!viajeActivo(idUsuario)){
                throw new ViajeException("El usuario no existe o no tiene un viaje activo");
            }
            Viaje v = viajeRepo.findByActivoByUsuarioId(idUsuario);
            v.setFin(LocalDateTime.now());
            calcularPrecio(v);
            estimarKilometros(v);
            viajeRepo.save(v);
            actualizarMonopatin(v);
            enviarAFacuturacion(v);
            return v;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Viaje pausar(Long idUsuario){
        //solo puede hacer una pausa, chequear que no esten en null TODO
        try {
            if(!viajeActivo(idUsuario)){
                throw new ViajeException("El usuario no tiene un viaje activo");
            }
            Viaje v = viajeRepo.findByActivoByUsuarioId(idUsuario);
            v.setInicioPausa(LocalDateTime.now());
            viajeRepo.save(v);
            return v;
        }catch (Exception e){
            throw new ViajeException("Error al pausar el viaje");
        }
    }

    public Viaje finPausa(Long idUsuario){
       try{
           if(!viajeActivo(idUsuario)){
               throw new ViajeException("El usuario no tiene un viaje activo");
           }
           Viaje v = viajeRepo.findByActivoByUsuarioId(idUsuario);
           double tiempoTranscurrido = Duration.between(v.getInicioPausa(),LocalDateTime.now()).toSeconds();
           if(tiempoTranscurrido > 15){
               throw new ViajeException("Tiempo de pausa excedido");
           }
           v.setFinPausa(LocalDateTime.now());
           viajeRepo.save(v);
           return v;
       }catch (Exception e){
           throw new ViajeException("Error al pausar el viaje");
       }
    }

    public List<Long> monopatinesIdsPorViajes(int viajes,int anio){
        try {
            return viajeRepo.monopatinIdsPorViajes(viajes,anio);
        }catch (Exception e){
            throw new ViajeException("Error al consultar los monopatines por viaje");
        }
    }

    /*---------AUXILIARES------------*/

    private Boolean viajeActivo(Long idUsuario){
        try {
            Viaje v = viajeRepo.findByActivoByUsuarioId(idUsuario);
            return v != null;
        } catch (Exception e) {
            throw new ViajeException("Error al obtener el viaje");
        }

    }

    //en realidad deberia estar calculado en minutos pero por cuestines de prueba los segundos serian minutos
    private void calcularPrecio(Viaje v){
        ApiResponse<TarifaResponseDTO> response = tarifaClient.getTarifaVigente().getBody();
        TarifaResponseDTO tarifaDTO = response.getData();

        double precioNormal=tarifaDTO.getPrecio();
        double precioExtra=tarifaDTO.getExtra();

        double minutosTotales= Duration.between(v.getInicio(), v.getFin()).toSeconds();
        double total= minutosTotales*precioNormal;

        if(v.getInicioPausa()!=null && v.getFinPausa()==null){ //se paso de la pausa
            double minutosExtras = Duration.between(v.getInicioPausa(),v.getFin()).toSeconds();
            double totalExtra = minutosExtras*precioExtra;
            total=total+totalExtra;
        }
        v.setPrecio(total);
    }

    private void estimarKilometros(Viaje v){
        double tiempoTotal=Duration.between(v.getInicio(), v.getFin()).toSeconds();
        if(tiempoTotal>0 && tiempoTotal<10){
            v.setKilometros(10.0);
        }else if(tiempoTotal>=10 && tiempoTotal<25){
            v.setKilometros(25.0);
        } else if (tiempoTotal>=25 && tiempoTotal<50) {
            v.setKilometros(50.0);
        }
    }

    private void actualizarMonopatin(Viaje v){
        ApiResponse<MonopatinResponseDTO> response = monopatinClient.getMonopatinById(v.getMonopatinId()).getBody();
        MonopatinResponseDTO monopatin = response.getData();
        monopatin.setKilometros(monopatin.getKilometros()+v.getKilometros());
        monopatin.setTiempoSinPausa(monopatin.getTiempoSinPausa() + Duration.between(v.getInicio(), v.getFin()).toSeconds());

        //si arranco la pausa y se paso de 15m
        if(v.getInicioPausa()!=null && v.getFinPausa()==null){
            monopatin.setTiempoConPausa(monopatin.getTiempoConPausa() + Duration.between(v.getInicioPausa(),v.getFin()).toSeconds());
            //si arranco la pausa y la pauso a tiempo
        } else if (v.getInicioPausa()!=null && v.getFinPausa()!=null) {
            monopatin.setTiempoConPausa(Duration.between(v.getInicioPausa(),v.getFinPausa()).toSeconds());
            //si no puso pausa
        }
        /*else if (v.getInicioPausa()!=null && v.getFinPausa()!=null) {}*/
        monopatinClient.update(v.getMonopatinId(), monopatin);
    }
    private void enviarAFacuturacion(Viaje v){
        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setViajeId(v.getId());
        facturaDTO.setUsuarioId(v.getUsuarioId());
        facturaDTO.setMontoCobrado(v.getPrecio());
        facturaClient.createFactura(facturaDTO);
    }

}
