package com.microservice.viaje.service.http;


import com.microservice.viaje.presentation.dto.FacturaDTO;
import com.microservice.viaje.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Tuve que cambiar el nombre porque es igual a tarifa (preguntar) TODO
@FeignClient(name = "msvc-administracion-factura",url = "localhost:8085/api/factura")
public interface FacturaClient {
    @PostMapping("")
    ResponseEntity<ApiResponse<FacturaDTO>> createFactura(@RequestBody FacturaDTO facturaDTO);
}
