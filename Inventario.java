package servicio;

import modelo.Producto;
import modelo.Proveedor;

public class Inventario {
    private static final int MAXIMO = 8;

    // Un solo arreglo guarda productos de distintas subclases (Accesorio, Papeleria, Textil)
    private Producto[] productos;
    private int cantidadProductos;
    private Proveedor proveedor;

    public Inventario() {
        productos = new Producto[MAXIMO];
        cantidadProductos = 0;
        proveedor = null;
    }

    public boolean hayEspacio() {
        return cantidadProductos < MAXIMO;
    }

    public int getMaximo() { return MAXIMO; }

    // Guarda el producto en la primera posición libre del arreglo
    public void agregarProducto(Producto producto) {
        productos[cantidadProductos] = producto;
        cantidadProductos++;
    }

    // El número va de 1 a cantidadProductos
    public Producto obtenerProducto(int numero) {
        Producto resultado = null;
        if (numero >= 1 && numero <= cantidadProductos) {
            resultado = productos[numero - 1];
        }
        return resultado;
    }

    public boolean hayDisponibilidad(int numero, int cantidad) {
        boolean disponible = false;
        Producto p = obtenerProducto(numero);
        if (p != null) {
            disponible = p.hayStock(cantidad);
        }
        return disponible;
    }

    public void actualizarExistencias(int numero, int cantidad) {
        Producto p = obtenerProducto(numero);
        if (p != null) {
            p.descontarStock(cantidad);
        }
    }

    // Compara nombres sin importar mayúsculas (para no repetir productos)
    public boolean existeNombre(String nombre) {
        boolean existe = false;
        for (int i = 0; i < cantidadProductos; i++) {
            if (productos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                existe = true;
            }
        }
        return existe;
    }

    // Buscador: devuelve los productos cuyo nombre o descripción contienen el texto,
    // sin importar mayúsculas. Si no hay coincidencias devuelve "".
    public String buscar(String texto) {
        String buscado = texto.trim().toLowerCase();
        String resultado = "";
        for (int i = 0; i < cantidadProductos; i++) {
            Producto p = productos[i];
            if (p.getNombre().toLowerCase().contains(buscado)
                    || p.detalle().toLowerCase().contains(buscado)) {
                resultado = resultado + (i + 1) + ". " + p + "\n";
            }
        }
        return resultado;
    }

    // Recorre el arreglo y cada producto responde con su propio calcularPrecio()
    public double calcularValorInventario() {
        double total = 0;
        for (int i = 0; i < cantidadProductos; i++) {
            total = total + productos[i].calcularPrecio() * productos[i].getStock();
        }
        return total;
    }

    public int getCantidadProductos() { return cantidadProductos; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

    public String toString() {
        String texto = "";
        for (int i = 0; i < cantidadProductos; i++) {
            texto = texto + (i + 1) + ". " + productos[i] + "\n";
        }
        return texto;
    }
}
