package org.example.entitiesDaos;

import org.example.entities.Producto;
import org.example.entities.dtos.ProductoConRecaudacionDTO;

public interface ProductoDao {
    void createTable();
    boolean save(Producto producto);
    ProductoConRecaudacionDTO getProductoMayorRecaudacion();
    void commit();
    void closeConnection();
}
