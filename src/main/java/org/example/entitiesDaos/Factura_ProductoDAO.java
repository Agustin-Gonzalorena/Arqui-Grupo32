package org.example.entitiesDaos;

import org.example.entities.Factura;
import org.example.entities.Factura_Producto;

public interface Factura_ProductoDAO {
    void createTable();

    boolean save(Factura_Producto factura_producto);
}
