package org.example.mysqlDB;

import org.example.DaoFactory;
import org.example.entities.Factura;
import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.FacturaDao;

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

}
    