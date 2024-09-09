package org.example.entitiesDaos;

import org.example.entities.Factura;

import java.util.List;

public interface FacturaDao {
    void createTable();
    void commit();
    void closeConnection();
    boolean save(Factura factura);
}
