package Modelo;

import Productos.Producto;

// Subclase de Producto: agendas, esferos y otros artículos de papelería
public class Papeleria extends Producto {
    private String tamano; // Pequeño, Mediano o Grande

    public Papeleria(String nombre, double precioBase, int stock, String tamano) {
        super(nombre, precioBase, stock);
        this.tamano = tamano;
    }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    // Regla: el tamaño mediano suma $3000 y el grande $6000
    @Override
    public double calcularPrecio() {
        double precio = getPrecioBase();
        if (tamano.equalsIgnoreCase("Mediano")) {
            precio = precio + 3000;
        } else if (tamano.equalsIgnoreCase("Grande")) {
            precio = precio + 6000;
        }
        return precio;
    }

    @Override
    public String detalle() {
        return "Papelería tamaño " + tamano.toLowerCase();
    }

    // La papelería se personaliza con vinilo o bordado
    @Override
    public String[] tecnicasDisponibles() {
        return new String[] {"Vinilo", "Bordado"};
    }
}
