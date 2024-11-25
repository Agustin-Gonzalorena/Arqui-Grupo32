package com.microservice.gateway.service.http;

import com.microservice.gateway.presentation.dto.UsuarioDTO;
import com.microservice.gateway.presentation.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-usuario",path = "/api/usuario")
public interface UsuarioClient {

    @GetMapping("/email/{email}")
    ResponseEntity<ApiResponse<UsuarioDTO>> getUsuarioByEmail(@PathVariable("email") String email);
}
