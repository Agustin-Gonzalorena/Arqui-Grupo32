package org.example.entitiesDaos;

import org.example.entities.Producto;

import java.util.List;

public interface ProductoDao {
    void createTable();
    void commit();
    boolean save(Producto producto);
    void closeConnection();
    String getProductoMayorRecaudacion();
}
