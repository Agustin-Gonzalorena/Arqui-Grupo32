package com.microservice.administracion.persistence.repository;

import com.microservice.administracion.persistence.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepo extends JpaRepository<Factura, Long> {
    @Query("SELECT sum(f.montoCobrado) FROM Factura f " +
            "WHERE  YEAR(f.fecha) = :anio " +
            "AND MONTH(f.fecha) BETWEEN :mesDesde AND :mesHasta")
    Double totalFacturado(int anio,int mesDesde,int mesHasta);
}
