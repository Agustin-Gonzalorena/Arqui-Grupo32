package org.example.entitiesDaos;

import org.example.entities.Factura;

import java.util.List;

public interface FacturaDao {
    void createTable();

    List<Factura> getAll();

    Factura getById(int id);

    boolean save(Factura factura);
}
