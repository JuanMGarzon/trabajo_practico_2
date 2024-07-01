package ar.edu.unju.fi.ejercicio5.model;

public class Producto {
    private String codigo;
    private String descripcion;
    private double precioU;
    private OrigenFabricacion origenFabricacion;
    private Categoria categoria;
    private boolean estado; // nuevo atributo

    public Producto(String codigo, String descripcion, double precioU, OrigenFabricacion origenFabricacion, Categoria categoria, boolean estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioU = precioU;
        this.origenFabricacion = origenFabricacion;
        this.categoria = categoria;
        this.estado = estado;
    }

    // Constructor vacío (si se necesita)
    public Producto() {
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(double precioU) {
        this.precioU = precioU;
    }

    public OrigenFabricacion getOrigenFabricacion() {
        return origenFabricacion;
    }

    public void setOrigenFabricacion(OrigenFabricacion origenFabricacion) {
        this.origenFabricacion = origenFabricacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioU=" + precioU +
                ", origenFabricacion=" + origenFabricacion +
                ", categoria=" + categoria +
                ", estado=" + (estado ? "Disponible" : "No disponible") +
                '}';
    }

    public enum OrigenFabricacion {
        ARGENTINA, CHINA, BRASIL, URUGUAY
    }

    public enum Categoria {
        TELEFONIA, INFORMATICA, ELECTROHOGAR, HERRAMIENTAS
    }
}
