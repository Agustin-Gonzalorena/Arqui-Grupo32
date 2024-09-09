package org.example.entitiesDaos;

import org.example.entities.Cliente;

import java.util.List;

public interface ClienteDao {
    void createTable();
    void commit();
    boolean save(Cliente cliente);
    void closeConnection();
    List<String> getClientesPorMayorFacturacion();
}
