package org.example.mysqlDB;

import org.example.entities.Factura;
import org.example.entitiesDaos.FacturaDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDaoMysql implements FacturaDao {
    private static FacturaDaoMysql instance;
    private Connection conn;

    private FacturaDaoMysql() {
        this.conn = ConnectionManagerMysql.getInstance().getConnection();
    }

    public static FacturaDaoMysql getInstance() {
        if (instance == null) {
            instance = new FacturaDaoMysql();
        }
        return instance;
    }

    @Override
    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void closeConnection() {
        ConnectionManagerMysql.getInstance().closeConnection();
    }
    @Override
    public void createTable() {
        String sql = "CREATE TABLE factura ("
                + "idFactura INT NOT NULL AUTO_INCREMENT,"
                + "idCliente INT NOT NULL,"
                + "CONSTRAINT factura_pk PRIMARY KEY (idFactura)"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean save(Factura factura) {
        String sql = "INSERT INTO factura (idCliente) VALUES (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, factura.getIdCliente());
            ps.execute();
            ps.close();
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
