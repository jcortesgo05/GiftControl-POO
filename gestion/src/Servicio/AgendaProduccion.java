package Servicio;

import Modelo.Cupo;

// Matriz de objetos: filas = turnos de trabajo, columnas = días de la semana.
// Cada posición es un Cupo (L libre, O ocupado, R reservado).
public class AgendaProduccion {
    private static final int FILAS = 3;
    private static final int COLUMNAS = 5;

    private Cupo[][] cupos;
    private String[] turnos;
    private String[] dias;

    public AgendaProduccion() {
        cupos = new Cupo[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                cupos[i][j] = new Cupo();
            }
        }
        turnos = new String[] {"Mañana", "Tarde", "Noche"};
        dias = new String[] {"Lun", "Mar", "Mié", "Jue", "Vie"};
    }

    public int getFilas() { return FILAS; }
    public int getColumnas() { return COLUMNAS; }

    // Fila y columna se numeran desde 1, como las ve el usuario
    public boolean posicionValida(int fila, int columna) {
        return fila >= 1 && fila <= FILAS && columna >= 1 && columna <= COLUMNAS;
    }

    public Cupo obtenerCupo(int fila, int columna) {
        return cupos[fila - 1][columna - 1];
    }

    // Dibuja la cuadrícula en consola
    public String dibujar() {
        String texto = String.format("%-12s", "");
        for (int j = 0; j < COLUMNAS; j++) {
            texto = texto + String.format("%-7s", (j + 1) + "." + dias[j]);
        }
        texto = texto + "\n";
        for (int i = 0; i < FILAS; i++) {
            texto = texto + String.format("%-12s", (i + 1) + ". " + turnos[i]);
            for (int j = 0; j < COLUMNAS; j++) {
                texto = texto + String.format("%-7s", cupos[i][j]);
            }
            texto = texto + "\n";
        }
        texto = texto + "L = libre, O = ocupado, R = reservado";
        return texto;
    }

    public String describirCupo(int fila, int columna) {
        Cupo cupo = obtenerCupo(fila, columna);
        String estado;
        if (cupo.getEstado() == Cupo.LIBRE) {
            estado = "Libre";
        } else if (cupo.getEstado() == Cupo.OCUPADO) {
            estado = "Ocupado";
        } else {
            estado = "Reservado";
        }
        String texto = dias[columna - 1] + ", turno " + turnos[fila - 1].toLowerCase() + ": " + estado;
        if (cupo.getReferencia().length() > 0) {
            texto = texto + " (" + cupo.getReferencia() + ")";
        }
        return texto;
    }

    // Un cupo libre o reservado se puede ocupar; uno ya ocupado no
    public boolean ocupar(int fila, int columna, String referencia) {
        Cupo cupo = obtenerCupo(fila, columna);
        boolean exito = false;
        if (cupo.getEstado() != Cupo.OCUPADO) {
            cupo.ocupar(referencia);
            exito = true;
        }
        return exito;
    }

    // Solo se puede reservar un cupo libre
    public boolean reservar(int fila, int columna, String referencia) {
        Cupo cupo = obtenerCupo(fila, columna);
        boolean exito = false;
        if (cupo.getEstado() == Cupo.LIBRE) {
            cupo.reservar(referencia);
            exito = true;
        }
        return exito;
    }

    public boolean liberar(int fila, int columna) {
        Cupo cupo = obtenerCupo(fila, columna);
        boolean exito = false;
        if (cupo.getEstado() != Cupo.LIBRE) {
            cupo.liberar();
            exito = true;
        }
        return exito;
    }
}
