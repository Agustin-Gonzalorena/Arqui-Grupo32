package org.example.entitiesDaos;

import org.example.entities.Cliente;

import java.util.List;

public interface ClienteDao {
    void createTable();
    boolean save(Cliente cliente);
    List<String> getClientesPorMayorFacturacion();
    void commit();
    void closeConnection();
}
