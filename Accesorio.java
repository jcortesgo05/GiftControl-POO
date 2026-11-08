package modelo;

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
        if ("Acrílico".equalsIgnoreCase(material) || "Acrilico".equalsIgnoreCase(material)) {
            precio += 2000;
        } else if ("Metal".equalsIgnoreCase(material)) {
            precio += 4000;
        }
        return precio;
    }

    @Override
    public String detalle() {
        return "Accesorio de " + material.toLowerCasereturn "Accesorio de " + (material != null ? material.toLowerCase() : "material no especificado");
    }

    // Los accesorios se personalizan con sublimación o vinilo
    @Override
    ublic String toString() {
        return super.toString() + " | Material: " + material + " | Total: $" + String.format("%,.0f", calcularPrecio());
    }
    }
}
