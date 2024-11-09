package com.microservice.monopatin.service.http;


import com.microservice.monopatin.presentation.dto.ParadaResponseDTO;
import com.microservice.monopatin.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-parada",url = "localhost:8082/api/parada")
public interface ParadaClient {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<ParadaResponseDTO>> getParadaById(@PathVariable("id") String id);
}
