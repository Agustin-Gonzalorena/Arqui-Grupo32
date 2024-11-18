package com.microservice.viaje.presentation.controller;

import com.microservice.viaje.persistence.entity.Viaje;
import com.microservice.viaje.persistence.repository.ViajeRepo;
import com.microservice.viaje.presentation.dto.ViajeCreateDTO;
import com.microservice.viaje.presentation.response.ApiResponse;
import com.microservice.viaje.service.implementation.ViajeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viaje")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ViajeCreateDTO viaje, HttpServletRequest request) {
        //Deberia crear el viaje opteniendo el usuario mediante el token que manda TODO
        //Pero tengo que cambiar todos los endpoints de viaje, basicamente hacerlo de nuevo el msvc
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null || !authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String userId = extract(token);
            System.out.println(userId);
        }

       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ApiResponse<>(HttpStatus.CREATED.value(), viajeService.create(viaje)));
    }
    @GetMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.finalizar(id)));
    }
    @GetMapping("/pausar/{id}")
    public ResponseEntity<?> pausar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.pausar(id)));
    }
    @GetMapping("/finpausa/{id}")
    public ResponseEntity<?> finpausa(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.finPausa(id)));
    }
    @GetMapping("/idmonopatin")
    public ResponseEntity<?> cantidad(@RequestParam int viajes,@RequestParam int anio) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.monopatinesIdsPorViajes(viajes,anio)));
    }


    //Recupera el email del token
    private String extract(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("j7ZookpUTYxclaULynjypGQVKMYXqOXMI+/1sQ2gOV1BF6VOHw6OzYj9RNZY4GcHAE3Igrah3MZ26oLrY/3y4Q==")
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
