package org.example.mysqlDB;

import org.example.entities.Cliente;
import org.example.entitiesDaos.ClienteDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoMysql implements ClienteDao {
    private static ClienteDaoMysql instance;
    private Connection conn;

    private ClienteDaoMysql() {
        this.conn = ConnectionManagerMysql.getInstance().getConnection();
    }

    public static ClienteDaoMysql getInstance() {
        if (instance == null) {
            instance = new ClienteDaoMysql();
        }
        return instance;
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE cliente ("
                + "idCliente INT NOT NULL AUTO_INCREMENT, "
                + "nombre VARCHAR(500) NOT NULL, "
                + "email VARCHAR(120) NOT NULL, "
                + "CONSTRAINT cliente_pk PRIMARY KEY (idCliente)"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, email) VALUES (?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getnombre());
            ps.setString(2, cliente.getEmail());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cliente> getClientesPorMayorFacturacion() {
        String query = """
            SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS totalFacturado
            FROM cliente c
            JOIN factura f ON c.idCliente = f.idCliente
            JOIN factura_producto fp ON f.idFactura = fp.factura_idFactura
            JOIN producto p ON fp.producto_idProducto = p.idProducto
            GROUP BY c.idCliente, c.nombre, c.email
            ORDER BY totalFacturado DESC;
        """;

        List<Cliente> clientesFacturados = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                Cliente cliente = new Cliente( nombre, email);
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientesFacturados.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientesFacturados;
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

