package Servicio;

import Productos.RegaloPersonalizado;

public class Cierre {
    private int totalRegalos;
    private int totalUnidades;
    private double totalVentas;
    private double totalAbonos;
    private double totalPendiente;
    private double ventaMayor;

    public Cierre() {
        totalRegalos = 0;
        totalUnidades = 0;
        totalVentas = 0;
        totalAbonos = 0;
        totalPendiente = 0;
        ventaMayor = 0;
    }


    public void registrar(RegaloPersonalizado regalo) {
        totalRegalos++;
        totalUnidades = totalUnidades + regalo.getCantidad();
        totalVentas = totalVentas + regalo.getTotal();
        totalAbonos = totalAbonos + regalo.getAbono();
        totalPendiente = totalPendiente + regalo.getSaldoPendiente();
        if (regalo.getTotal() > ventaMayor) {
            ventaMayor = regalo.getTotal();
        }
    }

    public int getTotalRegalos() { return totalRegalos; }
    public int getTotalUnidades() { return totalUnidades; }
    public double getTotalVentas() { return totalVentas; }
    public double getTotalAbonos() { return totalAbonos; }
    public double getTotalPendiente() { return totalPendiente; }
    public double getVentaMayor() { return ventaMayor; }

    public String toString() {
        String resumen = "\n======= CIERRE DEL DÍA =======\n"
            + "Regalos vendidos: " + totalRegalos + "\n"
            + "Unidades vendidas: " + totalUnidades + "\n"
            + "Total en ventas: $" + totalVentas + "\n"
            + "Abonos recibidos: $" + totalAbonos + "\n"
            + "Saldo pendiente por cobrar: $" + totalPendiente + "\n"
            + "Venta más grande: $" + ventaMayor + "\n";
        if (totalRegalos > 0) {
            resumen = resumen + "Promedio por venta: $" + (totalVentas / totalRegalos) + "\n";
        }
        resumen = resumen + "==============================";
        return resumen;
    }
}
