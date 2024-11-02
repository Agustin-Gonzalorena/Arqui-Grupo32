package com.microservice.monopatin.persistence.repository;

import com.microservice.monopatin.persistence.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonopatinRepo extends JpaRepository<Monopatin, Long> {
}
