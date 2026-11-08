package Modelo;

import Productos.Producto;

// Subclase de Producto: tazas, llaveros, pocillos y otros accesorios
public class Accesorio extends Producto {
    private String material; // Cerámica, Acrílico o Metal

    public Accesorio(String nombre, double precioBase, int stock, String material) {
        super(nombre, precioBase, stock);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    // Regla: el acrílico suma $2000 y el metal $4000 al precio base
    @Override
    public double calcularPrecio() {
        double precio = getPrecioBase();
        if (material.equalsIgnoreCase("Acrílico")) {
            precio = precio + 2000;
        } else if (material.equalsIgnoreCase("Metal")) {
            precio = precio + 4000;
        }
        return precio;
    }

    @Override
    public String detalle() {
        return "Accesorio de " + material.toLowerCase();
    }

    // Los accesorios se personalizan con sublimación o vinilo
    @Override
    public String[] tecnicasDisponibles() {
        return new String[] {"Sublimación", "Vinilo"};
    }
}
