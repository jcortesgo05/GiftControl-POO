package modelo;

// Subclase de Producto: loncheras, cartucheras, monederos, bolsas y otros textiles
public class Textil extends Producto {
    private String tela; // Poliéster, Algodón o Lona

    public Textil(String nombre, double precioBase, int stock, String tela) {
        super(nombre, precioBase, stock);
        this.tela = tela;
    }

    public String getTela() { return tela; }
    public void setTela(String tela) { this.tela = tela; }

    // Regla: el algodón suma $3000 y la lona $5000
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

    // Los textiles admiten las tres técnicas
    @Override
    public String[] tecnicasDisponibles() {
        return new String[] {"Bordado", "Vinilo", "Sublimación"};
    }
}
