package org.example.servicios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entities.Cliente;
import org.example.entities.Factura;
import org.example.entities.Factura_Producto;
import org.example.entities.Producto;
import org.example.mysqlDB.ClienteDaoMysql;
import org.example.mysqlDB.FacturaDaoMysql;
import org.example.mysqlDB.Factura_ProductoDaoMysql;
import org.example.mysqlDB.ProductoDaoMysql;

import java.io.FileReader;
import java.io.IOException;

public class InsertarCSV {
    private static final String FILE_NAME_CLIENTE = "./src/main/resources/csvDatos/clientes.csv";
    private static final String FILE_NAME_FACTURA = "./src/main/resources/csvDatos/facturas.csv";
    private static final String FILE_NAME_FACTURA_PRODUCTO = "./src/main/resources/csvDatos/facturas-productos.csv";
    private static final String FILE_NAME_PRODUCTO = "./src/main/resources/csvDatos/productos.csv";

    private CSVParser parserCliente;
    private CSVParser parserFactura;
    private CSVParser parserFacturaProducto;
    private CSVParser parserProducto;

    private ClienteDaoMysql clienteDaoMysql;
    private FacturaDaoMysql facturaDaoMysql;
    private ProductoDaoMysql productoDaoMysql;
    private Factura_ProductoDaoMysql facturaProductoDaoMysql;

    public InsertarCSV(){
        //se leen los csv
        parserCliente = lector(FILE_NAME_CLIENTE);
        parserFactura = lector(FILE_NAME_FACTURA);
        parserFacturaProducto = lector(FILE_NAME_FACTURA_PRODUCTO);
        parserProducto = lector(FILE_NAME_PRODUCTO);

        clienteDaoMysql = ClienteDaoMysql.getInstance();
        facturaDaoMysql = FacturaDaoMysql.getInstance();
        productoDaoMysql = ProductoDaoMysql.getInstance();
        facturaProductoDaoMysql = Factura_ProductoDaoMysql.getInstance();

    }
    public void insertar(){
        try {
            //clientes
            for(CSVRecord c : parserCliente){
                Cliente c1 = new Cliente(c.get("nombre"), c.get("email"));
                clienteDaoMysql.save(c1);
            }
            //facturas
            for(CSVRecord f : parserFactura){
                Factura f1 = new Factura(Integer.parseInt(f.get("idCliente")));
                facturaDaoMysql.save(f1);
            }
            //productos
            for (CSVRecord p : parserProducto){
                Producto p1 = new Producto(p.get("nombre"), Float.parseFloat(p.get("valor")));
                productoDaoMysql.save(p1);
            }
            //factura_productos
            for(CSVRecord fp : parserFacturaProducto){
                Factura_Producto fp1 = new Factura_Producto(Integer.parseInt(fp.get("idFactura")), Integer.parseInt(fp.get("idProducto")), Integer.parseInt(fp.get("cantidad")));
                facturaProductoDaoMysql.save(fp1);

            }

            clienteDaoMysql.commit();
            facturaDaoMysql.commit();
            productoDaoMysql.commit();
            facturaProductoDaoMysql.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CSVParser lector(String archivo){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader(archivo));
            return parser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
