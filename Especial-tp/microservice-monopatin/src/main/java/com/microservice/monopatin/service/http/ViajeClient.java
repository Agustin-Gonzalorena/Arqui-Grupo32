package com.microservice.monopatin.service.http;

import com.microservice.monopatin.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-viaje",path = "/api/viaje")
public interface ViajeClient {
    @GetMapping("/idmonopatin")
    ResponseEntity<ApiResponse<List<Long>>> getIdmonopatinPorViajes(@RequestParam int viajes,@RequestParam int anio);
}
