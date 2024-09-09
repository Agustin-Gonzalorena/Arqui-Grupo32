package org.example.mysqlDB;

import org.example.entities.Factura_Producto;
import org.example.entitiesDaos.Factura_ProductoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Factura_ProductoDaoMysql implements Factura_ProductoDao {
    private static Factura_ProductoDaoMysql instance;
    private Connection conn;

    private Factura_ProductoDaoMysql() {
        this.conn = ConnectionManagerMysql.getInstance().getConnection();
    }

    public static Factura_ProductoDaoMysql getInstance() {
        if (instance == null) {
            instance = new Factura_ProductoDaoMysql();
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
        String sql = "CREATE TABLE IF NOT EXISTS factura_producto (" +
                "factura_idFactura INT NOT NULL, " +
                "producto_idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "PRIMARY KEY (factura_idFactura, producto_idProducto)" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla Factura_Producto creada sin claves foráneas.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al crear la tabla Factura_Producto.");
        }
    }

    @Override
    public boolean save(Factura_Producto facturaProducto) {
        String sql = "INSERT INTO factura_producto (factura_idFactura, producto_idProducto, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, facturaProducto.getIdFactura());
            pstmt.setInt(2, facturaProducto.getIdProducto());
            pstmt.setInt(3, facturaProducto.getCantidad());
            pstmt.executeUpdate();

            //System.out.println("Producto asociado a la factura guardado exitosamente.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el producto asociado a la factura.");
            return false;
        }
    }
}
