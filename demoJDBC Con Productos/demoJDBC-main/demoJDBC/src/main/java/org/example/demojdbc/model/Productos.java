package org.example.demojdbc.model;

public class Productos {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public Productos() {
    }

/*    public Productos(int id, String nombre, String apellidos, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }*/

    public Productos(String nombre, double precio, int stock) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public Productos(String nombre) {
        this.nombre=nombre;
    }

}
