package Modelo;

import Productos.Producto;


public class Textil extends Producto {
    private String tela; 

    public Textil(String nombre, double precioBase, int stock, String tela) {
        super(nombre, precioBase, stock);
        this.tela = tela;
    }

    public String getTela() { return tela; }
    public void setTela(String tela) { this.tela = tela; }

  
    @Override
    public double calcularPrecio() {
        double precio = getPrecioBase();
        if (tela.equalsIgnoreCase("Algodón")) {
            precio = precio + 3000;
        } else if (tela.equalsIgnoreCase("Lona")) {
            precio = precio + 5000;
        }
        return precio;
    }

    @Override
    public String detalle() {
        return "Textil de " + tela.toLowerCase();
    }

    @Override
    public String[] tecnicasDisponibles() {
        return new String[] {"Serigrafia", "DTF", "Sublimación"};
    }
}
