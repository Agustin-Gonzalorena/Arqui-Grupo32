package com.microservice.usuario.persistence.repository;

import com.microservice.usuario.persistence.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Long> {
}
