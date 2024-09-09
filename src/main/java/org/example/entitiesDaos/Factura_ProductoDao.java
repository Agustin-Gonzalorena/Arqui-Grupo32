package org.example.entitiesDaos;

import org.example.entities.Factura_Producto;

public interface Factura_ProductoDao {
    void createTable();
    void commit();
    void closeConnection();
    boolean save(Factura_Producto factura_producto);
}
