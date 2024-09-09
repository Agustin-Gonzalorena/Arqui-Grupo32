package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entities.Cliente;
import org.example.entities.Factura;
import org.example.entities.Producto;
import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.FacturaDao;
import org.example.mysqlDB.*;
import org.example.servicios.InsertarCSV;

import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_JDBC);
//        factory.createTable();
//        InsertarCSV insert = new InsertarCSV();
//        insert.insertar();

        ProductoDaoMysql p = ProductoDaoMysql.getInstance();
        ClienteDaoMysql c = ClienteDaoMysql.getInstance();
        System.out.println(p.getProductoMayorRecaudacion());
        System.out.println(c.getClientesPorMayorFacturacion());

    }
}