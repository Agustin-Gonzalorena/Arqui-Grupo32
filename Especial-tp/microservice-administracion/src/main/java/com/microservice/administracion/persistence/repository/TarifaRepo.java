package com.microservice.administracion.persistence.repository;

import com.microservice.administracion.persistence.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TarifaRepo extends JpaRepository<Tarifa, Long> {

    @Query("SELECT t FROM Tarifa t where t.fechaVigencia <= :fecha order by t.fechaVigencia DESC limit 1")
    Tarifa findByFechaVigencia(LocalDate fecha);
}
