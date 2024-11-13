package com.microservice.viaje.service.http;


import com.microservice.viaje.persistence.entity.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-administracion", path = "/api")
public interface AdministracionClient {
    @PostMapping("/factura")
    ResponseEntity<?> createFactura(@RequestBody Viaje v);
}
