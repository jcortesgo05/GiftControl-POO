package Productos;

// En este vamos a crear como tal el inventario, tiene la lista de productos y puede buscar poner o quitar productos
public class RegaloPersonalizado {
    private Producto producto;                // relación: el regalo tiene un producto
    private Personalizacion personalizacion;  // ...una personalización
    private Productos.Empaque empaque;                  // ...y un empaque
    private int cantidad;
    private double precioUnitario;
    private double total;
    private double abono;
    private double saldoPendiente;

    public RegaloPersonalizado(Producto producto, Personalizacion personalizacion,
                               Productos.Empaque empaque, int cantidad) {
        this.producto = producto;
        this.personalizacion = personalizacion;
        this.empaque = empaque;
        this.cantidad = cantidad;
        this.precioUnitario = 0;
        this.total = 0;
        this.abono = 0;
        this.saldoPendiente = 0;
    }

    // Precio por unidad = precio base + personalización + empaque
    public void calcularPrecio() {
        precioUnitario = producto.getPrecioBase()
                + personalizacion.getCostoAdicional()
                + empaque.getCosto();
        total = precioUnitario * cantidad;
    }

    // Regla: el abono mínimo es el 30% del total
    public double calcularAbonoMinimo() {
        return total * 30 / 100;
    }

    public void registrarAbono(double abono) {
        this.abono = abono;
        saldoPendiente = total - abono;
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Personalizacion getPersonalizacion() { return personalizacion; }
    public void setPersonalizacion(Personalizacion personalizacion) { this.personalizacion = personalizacion; }

    public Productos.Empaque getEmpaque() { return empaque; }
    public void setEmpaque(Empaque empaque) { this.empaque = empaque; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public double getTotal() { return total; }
    public double getAbono() { return abono; }
    public double getSaldoPendiente() { return saldoPendiente; }

    public String toString() {
        String texto = "\n===== REGALO PERSONALIZADO =====\n"
                + "Producto: " + producto.getNombre() + " (base $" + producto.getPrecioBase() + ")\n"
                + "Personalización: " + personalizacion + "\n"
                + "Empaque: " + empaque + "\n"
                + "Cantidad: " + cantidad + "\n"
                + "Precio por unidad: $" + precioUnitario + "\n"
                + "TOTAL: $" + total + "\n";
        if (abono > 0) {
            texto = texto + "Abono recibido: $" + abono + "\n"
                    + "Saldo pendiente: $" + saldoPendiente + "\n";
        }
        texto = texto + "================================";
        return texto;
    }
}

