package Entidades;

import java.util.Scanner;
import Modelo.Accesorio;
import Productos.Empaque;
import Modelo.Papeleria;
import Productos.Producto;
import Productos.RegaloPersonalizado;
import Modelo.Textil;
import Productos.Personalizacion;
import Servicio.AgendaProduccion;
import Servicio.Cierre;
import Inventario.Inventario;

public class Tienda {
    private Scanner sc;
    private Inventario inventario;
    private RegaloPersonalizado ultimoRegalo;
    private Cierre cierre;
    private AgendaProduccion agenda;

    public Tienda() {
        sc = new Scanner(System.in);
        inventario = new Inventario();
        ultimoRegalo = null;
        cierre = new Cierre();
        agenda = new AgendaProduccion();
        // Productos de ejemplo: cada uno es de una subclase distinta de Producto
        inventario.agregarProducto(new Accesorio("Taza", 18000, 40, "Cerámica"));
        inventario.agregarProducto(new Papeleria("Agenda", 35000, 20, "Pequeño"));
        inventario.agregarProducto(new Textil("Cartuchera", 22000, 15, "Poliéster"));
        inventario.agregarProducto(new Textil("Monedero", 15000, 25, "Algodón"));
    }

    public void iniciar() {
        int opcion = 0;
        while (opcion != 9) {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    consultarProductos();
                    break;
                case 3:
                    registrarProveedor();
                    break;
                case 4:
                    consultarProveedor();
                    break;
                case 5:
                    hacerRegalo();
                    break;
                case 6:
                    verUltimoRegalo();
                    break;
                case 7:
                    buscarProductos();
                    break;
                case 8:
                    menuAgenda();
                    break;
                case 9:
                    System.out.println("Cerrando la jornada...");
                    break;
                default:
                    System.out.println("Opción no válida. Elige un número del 1 al 9.");
            }
        }
        System.out.println(cierre);
    }

    private void mostrarMenu() {
        System.out.println("\n======= REGALO DE GRACIA =======");
        System.out.println("1. Registrar producto");
        System.out.println("2. Consultar productos");
        System.out.println("3. Registrar proveedor");
        System.out.println("4. Consultar proveedor");
        System.out.println("5. Crear regalo personalizado");
        System.out.println("6. Ver último regalo");
        System.out.println("7. Buscar producto");
        System.out.println("8. Agenda de producción");
        System.out.println("9. Salir y cerrar el día");
        System.out.println("================================");
    }

    // ---- Productos ----
    private void registrarProducto() {
        if (inventario.hayEspacio()) {
            String nombre = leerTexto("Nombre del producto: ");
            while (inventario.existeNombre(nombre)) {
                System.out.println("Ya existe un producto con ese nombre.");
                nombre = leerTexto("Nombre del producto: ");
            }
            int precio = leerEntero("Precio base (pesos, sin decimales): ");
            while (precio <= 0) {
                System.out.println("El precio debe ser mayor que 0.");
                precio = leerEntero("Precio base (pesos, sin decimales): ");
            }
            int stock = leerEntero("Unidades disponibles: ");
            while (stock < 0) {
                System.out.println("Las unidades no pueden ser negativas.");
                stock = leerEntero("Unidades disponibles: ");
            }

            // Se crea la subclase según la categoría; el inventario la guarda como Producto
            System.out.println("\nCategoría del producto:");
            System.out.println("1. Accesorio (tazas, llaveros, pocillos...)");
            System.out.println("2. Papelería (agendas, esferos...)");
            System.out.println("3. Textil (loncheras, cartucheras, monederos, bolsas...)");
            int tipo = leerOpcion("Elige (1-3): ", 1, 3);

            Producto nuevo = null;
            switch (tipo) {
                case 1:
                    System.out.println("\nMaterial: 1. Cerámica   2. Acrílico (+$2000)   3. Metal (+$4000)");
                    int mat = leerOpcion("Elige (1-3): ", 1, 3);
                    if (mat == 1) {
                        nuevo = new Accesorio(nombre, precio, stock, "Cerámica");
                    } else if (mat == 2) {
                        nuevo = new Accesorio(nombre, precio, stock, "Acrílico");
                    } else {
                        nuevo = new Accesorio(nombre, precio, stock, "Metal");
                    }
                    break;
                case 2:
                    System.out.println("\nTamaño: 1. Pequeño   2. Mediano (+$3000)   3. Grande (+$6000)");
                    int tam = leerOpcion("Elige (1-3): ", 1, 3);
                    if (tam == 1) {
                        nuevo = new Papeleria(nombre, precio, stock, "Pequeño");
                    } else if (tam == 2) {
                        nuevo = new Papeleria(nombre, precio, stock, "Mediano");
                    } else {
                        nuevo = new Papeleria(nombre, precio, stock, "Grande");
                    }
                    break;
                case 3:
                    System.out.println("\nTela: 1. Poliéster   2. Algodón (+$3000)   3. Lona (+$5000)");
                    int tela = leerOpcion("Elige (1-3): ", 1, 3);
                    if (tela == 1) {
                        nuevo = new Textil(nombre, precio, stock, "Poliéster");
                    } else if (tela == 2) {
                        nuevo = new Textil(nombre, precio, stock, "Algodón");
                    } else {
                        nuevo = new Textil(nombre, precio, stock, "Lona");
                    }
                    break;
            }
            inventario.agregarProducto(nuevo);
            System.out.println("Producto registrado: " + nuevo);
        } else {
            System.out.println("El inventario está lleno (máximo " + inventario.getMaximo() + " productos).");
        }
    }

    private void consultarProductos() {
        if (inventario.getCantidadProductos() == 0) {
            System.out.println("Todavía no hay productos registrados.");
        } else {
            System.out.println("\n--- PRODUCTOS ---");
            System.out.println(inventario);
            System.out.println(String.format("Valor total del inventario: $%,.0f", inventario.calcularValorInventario()));
        }
    }

    // Buscador: sin importar mayúsculas, busca en el nombre y la descripción
    private void buscarProductos() {
        if (inventario.getCantidadProductos() == 0) {
            System.out.println("Todavía no hay productos registrados.");
        } else {
            String texto = leerTexto("Escribe parte del nombre o la descripción: ");
            String resultado = inventario.buscar(texto);
            if (resultado.length() == 0) {
                System.out.println("No se encontraron productos con \"" + texto.trim() + "\".");
            } else {
                System.out.println("\n--- RESULTADOS ---");
                System.out.print(resultado);
            }
        }
    }

    // ---- Proveedor ----
    private void registrarProveedor() {
        String nombre = leerTexto("Nombre del proveedor: ");
        String telefono = leerTexto("Teléfono: ");
        while (telefono.length() < 7) {
            System.out.println("El teléfono debe tener al menos 7 caracteres.");
            telefono = leerTexto("Teléfono: ");
        }
        String correo = leerTexto("Correo: ");
        String suministra = leerTexto("¿Qué suministra? ");
        inventario.setProveedor(new Proveedor(nombre, telefono, correo, suministra));
        System.out.println("Proveedor registrado.");
    }

    private void consultarProveedor() {
        if (inventario.getProveedor() == null) {
            System.out.println("Todavía no hay un proveedor registrado.");
        } else {
            System.out.println("\n--- PROVEEDOR ---");
            System.out.println(inventario.getProveedor());
        }
    }

    // ---- Regalo personalizado ----
    private void hacerRegalo() {
        if (inventario.getCantidadProductos() == 0) {
            System.out.println("Primero registra un producto (opción 1).");
        } else {
            int numero = elegirNumeroProducto();
            Producto elegido = inventario.obtenerProducto(numero);
            if (elegido.estaDisponible()) {
                procesarRegalo(numero, elegido);
            } else {
                System.out.println("Ese producto está agotado.");
            }
        }
    }

    private int elegirNumeroProducto() {
        System.out.println("\n--- PRODUCTOS ---");
        System.out.println(inventario);
        int numero = leerEntero("Número del producto: ");
        while (numero < 1 || numero > inventario.getCantidadProductos()) {
            System.out.println("Producto no válido. Elige un número de la lista.");
            numero = leerEntero("Número del producto: ");
        }
        return numero;
    }

    private void procesarRegalo(int numero, Producto elegido) {
        // Validación de disponibilidad: no se puede pedir más de lo que hay
        int cantidad = leerEntero("Cantidad (disponibles: " + elegido.getStock() + "): ");
        while (cantidad < 1 || cantidad > elegido.getStock()) {
            System.out.println("La cantidad debe estar entre 1 y " + elegido.getStock() + ".");
            cantidad = leerEntero("Cantidad: ");
        }

        Personalizacion pers = crearPersonalizacion(elegido);
        Empaque empaque = crearEmpaque();

        RegaloPersonalizado regalo = new RegaloPersonalizado(elegido, pers, empaque, cantidad);
        regalo.calcularPrecio();
        System.out.println(regalo);

        if (leerSiNo("¿Desea continuar con el pedido? (s/n): ")) {
            // Regla: se pide un abono antes de registrar la venta
            double minimo = regalo.calcularAbonoMinimo();
            System.out.println("Para registrar la venta se requiere un abono mínimo del 30%: $" + minimo);
            int abono = leerEntero("Valor del abono (pesos): ");
            while (abono < minimo || abono > regalo.getTotal()) {
                System.out.println("El abono debe estar entre $" + minimo + " y $" + regalo.getTotal() + ".");
                abono = leerEntero("Valor del abono (pesos): ");
            }

            if (inventario.hayDisponibilidad(numero, cantidad)) {
                regalo.registrarAbono(abono);
                inventario.actualizarExistencias(numero, cantidad);
                ultimoRegalo = regalo;
                cierre.registrar(regalo);
                System.out.println("Venta registrada. Abono recibido: $" + abono);
                System.out.println("Saldo pendiente: $" + regalo.getSaldoPendiente());
            } else {
                System.out.println("Ya no hay unidades suficientes. Pedido cancelado.");
            }
        } else {
            System.out.println("Pedido cancelado.");
        }
    }

    private Personalizacion crearPersonalizacion(Producto elegido) {
        String tecnica = elegirTecnica(elegido);
        String tamano = elegirTamanoDiseno();
        String contenido = elegirContenido();
        boolean servicio = leerSiNo("¿Requiere servicio de diseño? (+$10000) (s/n): ");

        String descripcion = leerTexto("Descripción o texto del diseño (máx. 40 caracteres): ");
        while (descripcion.length() > 40) {
            System.out.println("La descripción no puede superar los 40 caracteres.");
            descripcion = leerTexto("Descripción o texto del diseño (máx. 40 caracteres): ");
        }

        return new Personalizacion(tecnica, tamano, contenido, servicio, descripcion);
    }

    private Empaque crearEmpaque() {
        String material = elegirMaterial();
        String tamano = "N/A";
        if (!material.equals("Sin empaque")) {
            tamano = elegirTamanoEmpaque();
        }
        return new Empaque(material, tamano);
    }

    // Las técnicas que se ofrecen dependen del producto elegido (cada subclase las define)
    private String elegirTecnica(Producto elegido) {
        String[] tecnicas = elegido.tecnicasDisponibles();
        System.out.println("\nTécnicas de estampado disponibles para " + elegido.getNombre() + ":");
        for (int i = 0; i < tecnicas.length; i++) {
            System.out.println((i + 1) + ". " + tecnicas[i] + " (+$" + (int) Personalizacion.costoTecnica(tecnicas[i]) + ")");
        }
        int op = leerOpcion("Elige (1-" + tecnicas.length + "): ", 1, tecnicas.length);
        return tecnicas[op - 1];
    }

    private String elegirTamanoDiseno() {
        System.out.println("\nTamaño del diseño:");
        System.out.println("1. Pequeño (+$0)");
        System.out.println("2. Mediano (+$3000)");
        System.out.println("3. Grande (+$6000)");
        String tamano = "";
        while (tamano.length() == 0) {
            int op = leerEntero("Elige (1-3): ");
            switch (op) {
                case 1: tamano = "Pequeño"; break;
                case 2: tamano = "Mediano"; break;
                case 3: tamano = "Grande"; break;
                default: System.out.println("Opción no válida.");
            }
        }
        return tamano;
    }

    private String elegirContenido() {
        System.out.println("\nTipo de contenido:");
        System.out.println("1. Texto (+$0)");
        System.out.println("2. Imagen (+$4000)");
        System.out.println("3. Texto e imagen (+$6000)");
        String contenido = "";
        while (contenido.length() == 0) {
            int op = leerEntero("Elige (1-3): ");
            switch (op) {
                case 1: contenido = "Texto"; break;
                case 2: contenido = "Imagen"; break;
                case 3: contenido = "Texto e imagen"; break;
                default: System.out.println("Opción no válida.");
            }
        }
        return contenido;
    }

    private String elegirMaterial() {
        System.out.println("\nMaterial del empaque:");
        System.out.println("1. Caja de cartón (+$3000)");
        System.out.println("2. Bolsa de papel (+$1500)");
        System.out.println("3. Caja de madera (+$8000)");
        System.out.println("4. Sin empaque (+$0)");
        String material = "";
        while (material.length() == 0) {
            int op = leerEntero("Elige (1-4): ");
            switch (op) {
                case 1: material = "Caja de cartón"; break;
                case 2: material = "Bolsa de papel"; break;
                case 3: material = "Caja de madera"; break;
                case 4: material = "Sin empaque"; break;
                default: System.out.println("Opción no válida.");
            }
        }
        return material;
    }

    private String elegirTamanoEmpaque() {
        System.out.println("\nTamaño del empaque:");
        System.out.println("1. Pequeño (+$0)");
        System.out.println("2. Mediano (+$1000)");
        System.out.println("3. Grande (+$2000)");
        String tamano = "";
        while (tamano.length() == 0) {
            int op = leerEntero("Elige (1-3): ");
            switch (op) {
                case 1: tamano = "Pequeño"; break;
                case 2: tamano = "Mediano"; break;
                case 3: tamano = "Grande"; break;
                default: System.out.println("Opción no válida.");
            }
        }
        return tamano;
    }

    // ---- Agenda de producción (matriz) ----
    private void menuAgenda() {
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n--- AGENDA DE PRODUCCIÓN ---");
            System.out.println(agenda.dibujar());
            System.out.println("\n1. Consultar un cupo");
            System.out.println("2. Ocupar un cupo");
            System.out.println("3. Reservar un cupo");
            System.out.println("4. Liberar un cupo");
            System.out.println("5. Ver la agenda otra vez");
            System.out.println("6. Volver al menú principal");
            opcion = leerOpcion("Elige una opción: ", 1, 6);
            if (opcion >= 1 && opcion <= 4) {
                int fila = leerOpcion("Fila (1-" + agenda.getFilas() + ", turno): ", 1, agenda.getFilas());
                int columna = leerOpcion("Columna (1-" + agenda.getColumnas() + ", día): ", 1, agenda.getColumnas());
                switch (opcion) {
                    case 1:
                        System.out.println(agenda.describirCupo(fila, columna));
                        break;
                    case 2:
                        String refOcupar = leerTexto("Referencia del pedido (ej. nombre del cliente): ");
                        if (agenda.ocupar(fila, columna, refOcupar)) {
                            System.out.println("Cupo ocupado.");
                        } else {
                            System.out.println("Ese cupo ya está ocupado.");
                        }
                        break;
                    case 3:
                        String refReservar = leerTexto("Referencia de la reserva: ");
                        if (agenda.reservar(fila, columna, refReservar)) {
                            System.out.println("Cupo reservado.");
                        } else {
                            System.out.println("Solo se puede reservar un cupo libre.");
                        }
                        break;
                    case 4:
                        if (agenda.liberar(fila, columna)) {
                            System.out.println("Cupo liberado.");
                        } else {
                            System.out.println("Ese cupo ya está libre.");
                        }
                        break;
                }
            }
        }
    }

    private void verUltimoRegalo() {
        if (ultimoRegalo == null) {
            System.out.println("Todavía no hay regalos confirmados.");
        } else {
            System.out.println(ultimoRegalo);
        }
    }

    // ---- Lectura de datos ----
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Escribe un número entero: ");
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    // Pide un entero hasta que esté entre min y max
    private int leerOpcion(String mensaje, int min, int max) {
        int n = leerEntero(mensaje);
        while (n < min || n > max) {
            System.out.println("Opción no válida. Debe estar entre " + min + " y " + max + ".");
            n = leerEntero(mensaje);
        }
        return n;
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String texto = sc.nextLine();
        while (texto.length() == 0) {
            System.out.println("Este dato no puede estar vacío.");
            System.out.print(mensaje);
            texto = sc.nextLine();
        }
        return texto;
    }

    private boolean leerSiNo(String mensaje) {
        boolean valido = false;
        boolean respuesta = false;
        while (!valido) {
            System.out.print(mensaje);
            String r = sc.nextLine();
            if (r.equals("s") || r.equals("S")) {
                respuesta = true;
                valido = true;
            } else if (r.equals("n") || r.equals("N")) {
                respuesta = false;
                valido = true;
            } else {
                System.out.println("Responde con s o n.");
            }
        }
        return respuesta;
    }
}
