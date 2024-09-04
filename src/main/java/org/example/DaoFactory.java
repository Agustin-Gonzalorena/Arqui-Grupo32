package org.example;

import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.FacturaDao;
import org.example.mysqlDB.FactoryMysql;

public abstract class DaoFactory {
    public static final int MYSQL_JDBC=1;
    public static final int DERBY_JDBC=2;
    public static final int HIBERNATE=3;

    public abstract ClienteDao getClienteDao();
    public abstract FacturaDao getFacturaDao();
    public abstract void createTable();

    public static DaoFactory getDaoFactory(int witchFactory) {
        switch (witchFactory) {
            case MYSQL_JDBC: return new FactoryMysql();
            default: return null;
        }
    }
}
