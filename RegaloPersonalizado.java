package modelo;

public class RegaloPersonalizado {
    private Producto producto;                // relación: el regalo tiene un producto
    private Personalizacion personalizacion;  // ...una personalización
    private Empaque empaque;                  // ...y un empaque
    private int cantidad;
    private double precioUnitario;
    private double total;
    private double abono;
    private double saldoPendiente;

    public RegaloPersonalizado(Producto producto, Personalizacion personalizacion,
                               Empaque empaque, int cantidad) {
        this.producto = producto;
        this.personalizacion = personalizacion;
        this.empaque = empaque;
        this.cantidad = cantidad;
        this.abono = 0;
        calcularPrecio(); // Calcula de inmediato el precio unitario y el total
    }

    // Precio por unidad = precio del producto + personalización + empaque.
    // producto.calcularPrecio() es polimórfico: cada tipo de producto aplica su propia regla.
   public void calcularPrecio() {
        if (producto != null && personalizacion != null && empaque != null) {
            this.precioUnitario = producto.calcularPrecio()
                                + personalizacion.getCostoAdicional()
                                + empaque.getCosto();
            this.total = this.precioUnitario * this.cantidad;
            this.saldoPendiente = this.total - this.abono;
        }
    }

    // Regla: el abono mínimo es el 30% del total
    public double calcularAbonoMinimo() {
       return total * 0.30;
    }

    public void registrarAbono(double abono) {
        this.abono = abono;
        saldoPendiente = total - abono;
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { 
        this.producto = producto; 
        calcularPrecio();
    }

    public Personalizacion getPersonalizacion() { return personalizacion; }
    public void setPersonalizacion(Personalizacion personalizacion) { 
        this.personalizacion = personalizacion; 
        calcularPrecio();
    }

    public Empaque getEmpaque() { return empaque; }
    public void setEmpaque(Empaque empaque) { 
        this.empaque = empaque; 
        calcularPrecio();
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
        calcularPrecio();
    }

    public double getPrecioUnitario() { return precioUnitario; }
    public double getTotal() { return total; }
    public double getAbono() { return abono; }
    public double getSaldoPendiente() { return saldoPendiente; }

    @Override
    public String toString() {
        String texto = "\n===== REGALO PERSONALIZADO =====\n"
             + "Producto: " + (producto != null ? producto.detalle() : "N/A") 
             + " ($" + (producto != null ? String.format("%,.0f", producto.calcularPrecio()) : 0) + ")\n"
             + "Personalización: " + personalizacion + "\n"
             + "Empaque: " + empaque + "\n"
             + "Cantidad: " + cantidad + "\n"
             + "Precio por unidad: $" + String.format("%,.0f", precioUnitario) + "\n"
             + "TOTAL: $" + String.format("%,.0f", total) + "\n";
        if (abono > 0) {
            texto += "Abono recibido: $" + String.format("%,.0f", abono) + "\n"
                  + "Saldo pendiente: $" + String.format("%,.0f", saldoPendiente) + "\n";
        }
        texto += "================================";
        return texto;
    }
}
