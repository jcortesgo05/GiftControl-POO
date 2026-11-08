package Modelo;

import Productos.Producto;

public class Accesorio extends Producto {
    private String material; 

    public Accesorio(String nombre, double precioBase, int stock, String material) {
        super(nombre, precioBase, stock);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    
    @Override
    public double calcularPrecio() {
        double precio = getPrecioBase();
        if ("Acrílico".equalsIgnoreCase(material) || "Acrilico".equalsIgnoreCase(material)) {
            precio += 2000;
        } else if ("Metal".equalsIgnoreCase(material)) {
            precio += 3000;
        }
        return precio;
    }

    @Override
    public String detalle() {
        return "Accesorio de " + (material != null ? material.toLowerCase() : "material no especificado");
    }

    
    @Override
    public String[] tecnicasDisponibles() {
        return new String[]{"Sublimación", "DTF"};
    }


}
