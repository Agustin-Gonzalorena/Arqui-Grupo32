package org.example.mysqlDB;

import org.example.DaoFactory;
import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.FacturaDao;
import org.example.entitiesDaos.Factura_ProductoDao;
import org.example.entitiesDaos.ProductoDao;

public class FactoryMysql extends DaoFactory {
    public void createTable() {
        CreateTablesMysql createTablesMysql = new CreateTablesMysql();
        createTablesMysql.create();
    }

    public ClienteDao getClienteDao() {
        return ClienteDaoMysql.getInstance();
    }

    public FacturaDao getFacturaDao() {
        return FacturaDaoMysql.getInstance();
    }

    @Override
    public Factura_ProductoDao getFactura_ProductoDao() {
        return Factura_ProductoDaoMysql.getInstance();
    }

    @Override
    public ProductoDao getProductoDao() {
        return ProductoDaoMysql.getInstance();
    }

}
    