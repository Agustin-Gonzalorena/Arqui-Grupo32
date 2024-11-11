package com.microservice.administracion.service.implementation;

import com.microservice.administracion.persistence.entity.Factura;
import com.microservice.administracion.persistence.entity.KeyFactura;
import com.microservice.administracion.persistence.repository.FacturaRepo;
import com.microservice.administracion.presentation.dto.FacturaDTO;
import com.microservice.administracion.service.exception.AdministracionException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepo facturacionRepo;

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

    public Factura create(FacturaDTO facturaDTO) {
        try {
            Factura newFactura = new Factura(facturaDTO);
            //cobrar el viaje TODO
            //cobrar(factura);
            return facturacionRepo.save(newFactura);
        } catch (Exception e) {
            throw new AdministracionException("No se pudo facturar");
        }
    }

    public void cobrar(Factura factura) {
        try {
            //pedir al usuario las cuentas TODO
            //descontar saldo de la cuenta
        } catch (Exception e) {
            throw new AdministracionException("No se pudo cobrar");
        }
    }
    public Double totalFacturado(int anio, int mesDesde, int mesHasta) {
        try {
            return facturacionRepo.totalFacturado(anio, mesDesde, mesHasta);
        }catch (Exception e) {
            throw new AdministracionException("Error al obtener el total facturado");
        }
    }
}
