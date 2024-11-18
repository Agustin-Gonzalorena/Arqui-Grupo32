package com.microservice.viaje.presentation.controller;

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

    @PostMapping("/{monopatinId}")
    public ResponseEntity<?> create(@PathVariable Long monopatinId, HttpServletRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ApiResponse<>(HttpStatus.CREATED.value(), viajeService.create(monopatinId,obetenerUserId(request))));
    }
    @GetMapping("/finalizar")
    public ResponseEntity<?> finalizar(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.finalizar(obetenerUserId(request))));
    }
    @GetMapping("/pausar")
    public ResponseEntity<?> pausar(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.pausar(obetenerUserId(request))));
    }
    @GetMapping("/finpausa")
    public ResponseEntity<?> finpausa(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.finPausa(obetenerUserId(request))));
    }
    @GetMapping("/idmonopatin")
    public ResponseEntity<?> cantidad(@RequestParam int viajes,@RequestParam int anio) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), viajeService.monopatinesIdsPorViajes(viajes,anio)));
    }


    private Long obetenerUserId(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null || !authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return extract(token);
        }
        return null;
    }
    //Recupera el id del token
    private Long extract(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("j7ZookpUTYxclaULynjypGQVKMYXqOXMI+/1sQ2gOV1BF6VOHw6OzYj9RNZY4GcHAE3Igrah3MZ26oLrY/3y4Q==")
                .build()
                .parseClaimsJws(token).getBody();
        return claims.get("userId",Long.class);
    }

}
