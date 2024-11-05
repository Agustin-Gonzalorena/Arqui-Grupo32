package com.microservice.viaje.service.implementation;

import com.microservice.viaje.persistence.entity.Viaje;
import com.microservice.viaje.persistence.repository.ViajeRepo;
import com.microservice.viaje.presentation.dto.ViajeCreateDTO;
import com.microservice.viaje.service.exception.ViajeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
public class ViajeService {
    @Autowired
    private ViajeRepo viajeRepo;

    public Viaje create(ViajeCreateDTO viaje) {
        try{
            //chequear que el monopatin exista y no este en mantenimiento{peticion monopatin} TODO
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
            //Pedir monopatin y sumarle kilometros y minutos TODO
            //Mandar a facturarion para que hagan el cobro TODO
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
        double precioNormal=100; //pedir a la api de cuentas TODO
        double precioExtra=20;

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

}
