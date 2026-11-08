package modelo;

// Clase padre abstracta: define lo común a todos los productos del catálogo.
// Cada subclase sobrescribe calcularPrecio() y detalle() a su manera.
public abstract class Producto {
    private String nombre;
    private double precioBase;
    private int stock;

    public Producto(String nombre, double precioBase, int stock) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public boolean estaDisponible() {
        return stock > 0;
    }

    public boolean hayStock(int cantidad) {
        return cantidad <= stock;
    }

    public void descontarStock(int cantidad) {
        stock = stock - cantidad;
    }

    // Cada tipo de producto calcula su precio con su propia regla
    public abstract double calcularPrecio();

    // Cada tipo de producto describe sus características propias
    public abstract String detalle();

    // Cada tipo de producto ofrece solo las técnicas de personalización que admite
    public abstract String[] tecnicasDisponibles();

    public String toString() {
        String disponibilidad;
        if (stock > 0) {
            disponibilidad = stock + " uds";
        } else {
            disponibilidad = "Agotado";
        }
        return String.format("%-14s %-30s $%,9.0f  %s", nombre, detalle(), calcularPrecio(), disponibilidad);
    }
}
