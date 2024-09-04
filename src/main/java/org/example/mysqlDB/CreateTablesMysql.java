package org.example.mysqlDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesMysql {
    private ClienteDaoMysql clienteDaoMysql;
    private FacturaDaoMysql facturaDaoMysql;

    public CreateTablesMysql() {
        clienteDaoMysql = ClienteDaoMysql.getInstance();
        facturaDaoMysql = FacturaDaoMysql.getInstance();
    }

    public void create() {
        clienteDaoMysql.createTable();
        facturaDaoMysql.createTable();

        createForeignKey();
    }

    private void createForeignKey() {
        Connection conn = ConnectionManagerMysql.getInstance().getConnection();
        String facturaCliente = "ALTER TABLE factura ADD CONSTRAINT " +
                "factura_cliente FOREIGN KEY factura_Cliente (idCliente)" +
                "REFERENCES cliente (idCliente);";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(facturaCliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
