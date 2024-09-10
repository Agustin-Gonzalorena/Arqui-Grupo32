package org.example.mysqlDB;

import org.example.entities.Producto;
import org.example.entitiesDaos.ProductoDao;

import java.sql.*;
import java.util.List;

public class ProductoDaoMysql implements ProductoDao {
    private static ProductoDaoMysql instance;
    private Connection conn;

    private ProductoDaoMysql() {
        this.conn = ConnectionManagerMysql.getInstance().getConnection();
    }

    public static ProductoDaoMysql getInstance() {
        if (instance == null) {
            instance = new ProductoDaoMysql();
        }
        return instance;
    }

    @Override
    public void createTable() {
        String tabla="CREATE TABLE producto("
                + "idProducto INT NOT NULL AUTO_INCREMENT,"
                + "nombre VARCHAR(500) NOT NULL,"
                + "valor FLOAT(8,2) NOT NULL," +
                "PRIMARY KEY (idProducto))";

        try {
            conn.prepareStatement(tabla).execute();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Producto producto) {
        String consulta="INSERT INTO producto (nombre, valor) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2,producto.getValor());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Producto getProductoMayorRecaudacion() {
        String query = """
            SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS recaudacion
            FROM Producto p
            JOIN Factura_Producto fp ON p.idProducto = fp.idProducto
            GROUP BY p.idProducto, p.nombre, p.valor
            ORDER BY recaudacion DESC
            LIMIT 1;
        """;

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                float valor = rs.getFloat("valor");

                return new Producto(nombre, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Si no se encuentra ningún producto
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

}
