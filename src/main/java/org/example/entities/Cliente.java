package org.example.entities;

import java.util.List;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;


    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getnombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email +
                '}';
    }
}
