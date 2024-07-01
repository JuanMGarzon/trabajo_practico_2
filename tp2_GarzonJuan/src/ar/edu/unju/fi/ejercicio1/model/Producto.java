package ar.edu.unju.fi.ejercicio1.model;

public class Producto {
	
    private String codigo;
    private String descripcion;
    private double precioU;
    private OrigenFabricacion origenFabricacion;
    private Categoria categoria;

    public Producto(String codigo, String descripcion, double precioU, OrigenFabricacion origenFabricacion, Categoria categoria) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioU = precioU;
        this.origenFabricacion = origenFabricacion;
        this.categoria = categoria;
    }

    // Constructor vacío (si se necesita)
    public Producto() {
        // Constructor vacío
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

    @Override
    public String toString() {
        return "Codigo del producto: " + codigo +
                "\nDescripcion del producto: " + descripcion +
                "\nPrecio del producto: " + precioU +
                "\nOrigen de fabricacion: " + origenFabricacion +
                "\nCategoria: " + categoria;
    }

    public enum OrigenFabricacion {
        ARGENTINA, CHINA, BRASIL, URUGUAY
    }

    public enum Categoria {
        TELEFONIA, INFORMATICA, ELECTROHOGAR, HERRAMIENTAS
    }
}
