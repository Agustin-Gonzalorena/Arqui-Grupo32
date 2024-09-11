package org.example.entities.dtos;

public class ProductoConRecaudacionDTO {
    private int idProducto;
    private String nombre;
    private float valor;
    private int recaudacion;

    public ProductoConRecaudacionDTO(int idProducto, String nombre, float valor, int recaudacion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
        this.recaudacion = recaudacion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", recaudacion=" + recaudacion +
                '}';
    }
}
