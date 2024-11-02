package com.microservice.viaje.persistence.repository;

import com.microservice.viaje.persistence.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepo extends JpaRepository<Viaje, String> {
    @Query("SELECT v FROM Viaje v WHERE v.usuarioId=:idUsuario AND v.inicio=v.fin")
    Viaje findByActivoByUsuarioId(Long idUsuario);
}
