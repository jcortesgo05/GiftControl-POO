package modelo;

// Una casilla de la agenda de producción (cada posición de la matriz es un Cupo)
public class Cupo {
    public static final char LIBRE = 'L';
    public static final char OCUPADO = 'O';
    public static final char RESERVADO = 'R';

    private char estado;
    private String referencia;

    public Cupo() {
        estado = LIBRE;
        referencia = "";
    }

    public char getEstado() { return estado; }
    public String getReferencia() { return referencia; }

    public void ocupar(String referencia) {
        this.estado = OCUPADO;
        this.referencia = referencia;
    }

    public void reservar(String referencia) {
        this.estado = RESERVADO;
        this.referencia = referencia;
    }

    public void liberar() {
        this.estado = LIBRE;
        this.referencia = "";
    }

    public String toString() {
        return "[" + estado + "]";
    }
}
