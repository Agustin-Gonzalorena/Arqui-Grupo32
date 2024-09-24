package org.example.entitiesDaos;

import org.example.entities.Cliente;
import org.example.entities.dtos.ClienteConFacturacionDTO;

import java.util.List;

public interface ClienteDao {
    void createTable();
    boolean save(Cliente cliente);
    List<ClienteConFacturacionDTO> getClientesPorMayorFacturacion();
    void commit();
    void closeConnection();
}
