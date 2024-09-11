package org.example;

import org.example.entities.dtos.ClienteConFacturacionDTO;
import org.example.entitiesDaos.ClienteDao;
import org.example.entitiesDaos.ProductoDao;
import org.example.servicios.InsertarCSV;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_JDBC);

        //Creacion de tablas -ejercicio 1
        /*daoFactory.createTable();
        System.out.println("-----------------------------");
        System.out.println("Tablas creadas");

        //Insencion de datos en la tablas desde los csv -ejercicio 2
        InsertarCSV insertService = new InsertarCSV();
        insertService.insertar();
        System.out.println("-----------------------------");
        System.out.println("Se insertaron los datos");*/

        // Se recupera el producto que mas recaudo -ejercicio 3
        ProductoDao p = daoFactory.getProductoDao();
        System.out.println("-----------------------------");
        System.out.println("El producto que mas recaudo fue:");
        System.out.println(p.getProductoMayorRecaudacion());

        // Lista de clientes ordenada de mayor a menor segun su facturacion -ejercicio 4
        ClienteDao c = daoFactory.getClienteDao();
        System.out.println("-----------------------------");
        System.out.println("Lista de clientes ordenados segun quien facturo mas, de mayor a menor:");
        List<ClienteConFacturacionDTO> clientes = c.getClientesPorMayorFacturacion();
        for (ClienteConFacturacionDTO cliente : clientes) {
            System.out.println(cliente);
        }

        System.out.println("-----------------------------");
        System.out.println("Fin del programa");

    }
}