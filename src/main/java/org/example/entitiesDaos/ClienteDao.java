package org.example.entitiesDaos;

import org.example.entities.Cliente;

import java.util.List;

public interface ClienteDao {
    void createTable();


    List<Cliente> getAll();

    Cliente getById(int id);

    boolean save(Cliente cliente);

    void closeConnection();
    List<String> getClientesPorMayorFacturacion();
}
