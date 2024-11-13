package com.microservice.administracion.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-usuario", path = "/api/usuario")
public interface UsuarioClient {

    @GetMapping("/cobrar")
    ResponseEntity<?> cobrar(@RequestParam("id") Long id,@RequestParam("monto") Double monto);
}
