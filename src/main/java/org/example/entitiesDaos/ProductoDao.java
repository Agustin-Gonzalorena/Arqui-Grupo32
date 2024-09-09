package org.example.entitiesDaos;

import org.example.entities.Producto;

import java.util.List;

public interface ProductoDao {
    void createTable();

    List<Producto> getAll();

    Producto getById(int id);

    boolean save(Producto producto);

    void closeConnection();
}
