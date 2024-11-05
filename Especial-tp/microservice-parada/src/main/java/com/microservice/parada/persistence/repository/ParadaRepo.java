package com.microservice.parada.persistence.repository;

import com.microservice.parada.persistence.entity.Parada;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepo extends MongoRepository<Parada, String> {
}
