package com.microservice.parada.persistence.repository;

import com.microservice.parada.persistence.entity.Parada;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParadaRepo extends MongoRepository<Parada, String> {

    @Query("{ 'latitud': { $gte: ?0, $lte: ?1 }, 'longitud': { $gte: ?2, $lte: ?3 } }")
    List<Parada> cercanas(double minLat,double maxLat,double minLon,double maxLon);
}
