package org.example.entitiesDaos;

import org.example.entities.Producto;

import java.util.List;

public interface ProductoDao {
    void createTable();
    boolean save(Producto producto);
    Producto getProductoMayorRecaudacion();
    void commit();
    void closeConnection();
}
