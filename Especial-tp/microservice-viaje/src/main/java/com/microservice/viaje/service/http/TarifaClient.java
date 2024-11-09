package com.microservice.viaje.service.http;

import com.microservice.viaje.presentation.dto.TarifaResponseDTO;
import com.microservice.viaje.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-administracion",url = "localhost:8085/api/tarifa")
public interface TarifaClient {
    @GetMapping("/vigente")
    ResponseEntity<ApiResponse<TarifaResponseDTO>> getTarifaVigente();
}
