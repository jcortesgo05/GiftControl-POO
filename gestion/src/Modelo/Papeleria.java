package Modelo;

import Productos.Producto;

public class Papeleria extends Producto {
    private String tamano; 

    public Papeleria(String nombre, double precioBase, int stock, String tamano) {
        super(nombre, precioBase, stock);
        this.tamano = tamano;
    }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    
    @Override
    public double calcularPrecio() {
        double precio = getPrecioBase();
        if ("Mediano".equalsIgnoreCase(tamano)) {
            precio += 3000;
        } else if ("Grande".equalsIgnoreCase(tamano)) {
            precio += 6000;
        }
        return precio;
    }

    @Override
    public String detalle() {
        return "Papelería tamaño " + (tamano != null ? tamano.toLowerCase() : "no especificado");
    }
    @Override
    public String[] tecnicasDisponibles() {
        return new String[]{"DTF", "Serigrafia"};
    }
   
    @Override
    public String toString() {
        return super.toString() + " | Tamaño: " + tamano + " | Total: $" + String.format("%,.0f", calcularPrecio());
    }
}
