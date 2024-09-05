package org.example.mysqlDB;

import org.example.entities.Producto;
import org.example.entitiesDaos.ProductoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                + "nombre VARCHAR(45) NOT NULL,"
                + "valor FLOAT NOT NULL," +
                "PRIMARY KEY (idProducto))";

        try {
            conn.prepareStatement(tabla).execute();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Producto> getAll() {
        return List.of();
    }

    @Override
    public Producto getById(int id) {
        String consulta="SELECT * FROM producto WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Producto product=new Producto(rs.getString("nombre"),rs.getFloat("valor"));
                product.setIdProducto(rs.getInt("idProducto"));
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public boolean save(Producto producto) {
        String consulta="INSERT INTO producto VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2,producto.getValor());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void closeConnection() {
        ConnectionManagerMysql.getInstance().closeConnection();
    }

}
