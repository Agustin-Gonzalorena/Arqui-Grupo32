package org.example.entities.dtos;

public class ClienteConFacturacionDTO {
    private int idCliente;
    private String nombre;
    private String email;
    private int facturacion;

    public ClienteConFacturacionDTO(int idCliente, String nombre, String email, int facturacion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.facturacion = facturacion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", facturacion=" + facturacion +
                '}';
    }
}
