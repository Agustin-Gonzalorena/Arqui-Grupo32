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
    public List<Cliente> getAll() {
        return List.of();
    }

    @Override
    public Cliente getById(int id) {
        String sql = "SELECT * FROM cliente c WHERE c.idCliente =?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente c = new Cliente(rs.getString(2), rs.getString(3));
                c.setIdCliente(id);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    @Override
    public List<String> getClientesPorMayorFacturacion() {
        String query = """
            SELECT c.nombre, SUM(fp.cantidad * p.valor) AS totalFacturado
            FROM Cliente c
            JOIN Factura f ON c.idCliente = f.idCliente
            JOIN Factura_Producto fp ON f.idFactura = fp.idFactura
            JOIN Producto p ON fp.idProducto = p.idProducto
            GROUP BY c.idCliente, c.nombre
            ORDER BY totalFacturado DESC;
        """;

        List<String> clientesFacturados = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String clienteNombre = rs.getString("nombre");
                double totalFacturado = rs.getDouble("totalFacturado");
                clientesFacturados.add("Cliente: " + clienteNombre + " - Total Facturado: " + totalFacturado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientesFacturados;
    }



}

