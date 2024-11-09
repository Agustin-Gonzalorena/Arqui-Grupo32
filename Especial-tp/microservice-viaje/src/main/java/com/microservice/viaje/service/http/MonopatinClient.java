package com.microservice.viaje.service.http;

import com.microservice.viaje.presentation.dto.MonopatinResponseDTO;
import com.microservice.viaje.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-monopatin",url = "localhost:8081/api/monopatin")
public interface MonopatinClient {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<MonopatinResponseDTO>> getMonopatinById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<MonopatinResponseDTO>> update(@PathVariable ("id") Long id, @RequestBody MonopatinResponseDTO monopatinResponseDTO);
}
