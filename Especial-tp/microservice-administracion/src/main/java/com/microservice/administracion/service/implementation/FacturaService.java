package com.microservice.administracion.service.implementation;

import com.microservice.administracion.persistence.entity.Factura;
import com.microservice.administracion.persistence.entity.KeyFactura;
import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.persistence.repository.FacturaRepo;
import com.microservice.administracion.presentation.dto.ViajeDTO;
import com.microservice.administracion.service.exception.AdministracionException;
import com.microservice.administracion.service.http.UsuarioClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepo facturacionRepo;
    @Autowired
    private TarifaService tarifaService;
    @Autowired
    private UsuarioClient usuarioClient;

    @PostConstruct
    public void init() {
        KeyFactura keyFactura = new KeyFactura(3L,22L);
        Factura factura = Factura.builder()
                .idFactura(keyFactura)
                .fecha(LocalDate.now())
                .montoCobrado(300.0)
                .build();
        facturacionRepo.save(factura);
    }

    public Factura create(ViajeDTO viajeDTO) {
        try {
            double cobrado = calcularPrecio(viajeDTO);
            Factura newFactura = new Factura(viajeDTO,cobrado);
            usuarioClient.cobrar(viajeDTO.getUsuarioId(), cobrado);
            return facturacionRepo.save(newFactura);
        } catch (Exception e) {
            throw new AdministracionException("No se pudo facturar");
        }
    }

    public Double totalFacturado(int anio, int mesDesde, int mesHasta) {
        try {
            return facturacionRepo.totalFacturado(anio, mesDesde, mesHasta);
        }catch (Exception e) {
            throw new AdministracionException("Error al obtener el total facturado");
        }
    }

    //en realidad deberia estar calculado en minutos pero por cuestines de prueba los segundos serian minutos
    private double calcularPrecio(ViajeDTO v){
        Tarifa tarifa = tarifaService.getTarifaVigente();
        double precioNormal= tarifa.getPrecio();
        double precioExtra=tarifa.getExtra();

        double minutosTotales= Duration.between(v.getInicio(), v.getFin()).toSeconds();
        double total= minutosTotales*precioNormal;

        if(v.getInicioPausa()!=null && v.getFinPausa()==null){ //se paso de la pausa
            double minutosExtras = Duration.between(v.getInicioPausa(),v.getFin()).toSeconds();
            double totalExtra = minutosExtras*precioExtra;
            total=total+totalExtra;
        }
        return total;
    }
}
