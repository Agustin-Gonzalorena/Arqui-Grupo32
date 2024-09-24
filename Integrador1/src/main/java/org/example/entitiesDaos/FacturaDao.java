package org.example.entitiesDaos;

import org.example.entities.Factura;

import java.util.List;

public interface FacturaDao {
    void createTable();
    boolean save(Factura factura);
    void commit();
    void closeConnection();
}
