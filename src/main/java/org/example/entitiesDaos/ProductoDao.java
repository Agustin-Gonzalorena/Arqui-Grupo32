package org.example.entitiesDaos;

import org.example.entities.Producto;

import java.util.List;

public interface ProductoDao {
    void createTable();
    boolean save(Producto producto);
    String getProductoMayorRecaudacion();
    void commit();
    void closeConnection();
}
