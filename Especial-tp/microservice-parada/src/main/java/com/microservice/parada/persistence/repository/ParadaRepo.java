package com.microservice.parada.persistence.repository;

import com.microservice.parada.persistence.entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepo extends JpaRepository<Parada, Long> {
}
