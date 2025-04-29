package model;

public class Producto {
    private int referencia;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int referencia, String nombre, double precio, int cantidad) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}