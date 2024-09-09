package org.example.mysqlDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesMysql {
    private ClienteDaoMysql clienteDaoMysql;
    private FacturaDaoMysql facturaDaoMysql;
    private Factura_ProductoDaoMysql factura_productoDaoMysql;
    private ProductoDaoMysql productoDaoMysql;

    public CreateTablesMysql() {
        clienteDaoMysql = ClienteDaoMysql.getInstance();
        facturaDaoMysql = FacturaDaoMysql.getInstance();
        factura_productoDaoMysql = Factura_ProductoDaoMysql.getInstance();
        productoDaoMysql = ProductoDaoMysql.getInstance();
    }

    public void create() {

        productoDaoMysql.createTable();
        clienteDaoMysql.createTable();
        factura_productoDaoMysql.createTable();
        facturaDaoMysql.createTable();

        createForeignKey();
    }

    private void createForeignKey() {
        Connection conn = ConnectionManagerMysql.getInstance().getConnection();

        // Clave foránea para la relación Factura -> Cliente
        String facturaCliente = "ALTER TABLE factura ADD CONSTRAINT " +
                "factura_cliente FOREIGN KEY (idCliente) " +
                "REFERENCES cliente (idCliente);";

        // Clave foránea para la relación Factura_Producto -> Factura
        String facturaProductoFactura = "ALTER TABLE factura_producto ADD CONSTRAINT " +
                "factura_producto_factura FOREIGN KEY factura_producto_factura (factura_idFactura) " +
                "REFERENCES factura (idFactura);";

        // Clave foránea para la relación Factura_Producto -> Producto
        String facturaProductoProducto = "ALTER TABLE factura_producto ADD CONSTRAINT " +
                "factura_producto_producto FOREIGN KEY factura_producto_producto (producto_idProducto) " +
                "REFERENCES producto (idProducto);";

        try (Statement stmt = conn.createStatement()) {
            // Ejecuta la creación de la clave foránea Factura -> Cliente
            stmt.execute(facturaCliente);

            //Ejecuta la creación de la clave foránea Factura_Producto -> Factura
            stmt.execute(facturaProductoFactura);

            // Ejecuta la creación de la clave foránea Factura_Producto -> Producto
            stmt.execute(facturaProductoProducto);

            //System.out.println("Claves foráneas creadas exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al crear las claves foráneas.");
        }
    }

}
