package com.microservice.monopatin.persistence.repository;

import com.microservice.monopatin.persistence.entity.Monopatin;
import com.microservice.monopatin.presentation.dto.EnOperacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepo extends JpaRepository<Monopatin, Long> {
    @Query("SELECT new com.microservice.monopatin.presentation.dto.EnOperacionDTO( " +
            "SUM(case when m.enMantenimiento = false THEN 1 else 0 end), " +
            "SUM(case when m.enMantenimiento = true THEN 1 else 0 end ))" +
            "FROM Monopatin m")
    EnOperacionDTO contarPorEstado();

}
