package org.example;

import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.FacturaDao;
import org.example.entitiesDaos.Factura_ProductoDao;
import org.example.entitiesDaos.ProductoDao;

public abstract class DaoFactory {
    public static final int MYSQL_JDBC=1;
    public static final int DERBY_JDBC=2;
    public static final int HIBERNATE=3;

    public abstract ClienteDao getClienteDao();
    public abstract FacturaDao getFacturaDao();
    public abstract Factura_ProductoDao getFactura_ProductoDao();
    public abstract ProductoDao getProductoDao();
    public abstract void createTable();

    public static DaoFactory getDaoFactory(int witchFactory) {
        switch (witchFactory) {
            case MYSQL_JDBC: return new FactoryMysql();
            default: return null;
        }
    }
}
