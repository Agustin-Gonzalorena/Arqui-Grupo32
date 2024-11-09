package com.microservice.administracion.persistence.repository;

import com.microservice.administracion.persistence.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepo extends JpaRepository<Factura, Long> {
}
