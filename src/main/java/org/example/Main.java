package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entities.Cliente;
import org.example.entities.Factura;
import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.FacturaDao;
import org.example.mysqlDB.ClienteDaoMysql;
import org.example.mysqlDB.CreateTablesMysql;
import org.example.mysqlDB.FactoryMysql;
import org.example.mysqlDB.FacturaDaoMysql;

import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*CreateTablesMysql my = new CreateTablesMysql();
        my.create();*/
        /*
        Cliente c1 = new Cliente("jose","jose@gmai.com");
        ClienteDaoMysql mysql = ClienteDaoMysql.getInstance();
        System.out.println("jose se creo con exito:"+mysql.save(c1));

        Factura f1 = new Factura (1);
        Factura f2 = new Factura (1);

        FacturaDaoMysql fmd = FacturaDaoMysql.getInstance();
        fmd.save(f1);
        fmd.save(f2);*/

        /*DaoFactory df = DaoFactory.getDaoFactory(DaoFactory.MYSQL_JDBC);
        ClienteDao c = df.getClienteDao();

        Cliente c1=c.getById(1);
        System.out.println(c1);
        List<Factura> lf = c1.getFacturas();
        System.out.println("Facturas de "+ c1.getnombre()+"("+lf.size()+")"+": ");
        for (Factura f : lf) {
            System.out.println(f);
        }
        DaoFactory df = DaoFactory.getDaoFactory(DaoFactory.MYSQL_JDBC);
        FacturaDao f1 = df.getFacturaDao();

        Factura f = new Factura(2);
        f1.save(f);*/


        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_JDBC);
        daoFactory.createTable();

        ClienteDao clienteDao = daoFactory.getClienteDao();

        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("./src/main/resources/csvDatos/clientes.csv"));
            for (CSVRecord row : parser) {
                Cliente c1 = new Cliente(row.get("nombre"), row.get("email"));
                clienteDao.save(c1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clienteDao.closeConnection();
    }
}