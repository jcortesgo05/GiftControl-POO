package modelo;

public class Empaque {
    private String material;  // Caja de cartón, Bolsa de papel, Caja de madera o Sin empaque
    private String tamano;    // Pequeño, Mediano, Grande o N/A
    private double costo;

    public Empaque(String material, String tamano) {
        this.material = material;
        this.tamano = tamano;
        calcularCosto();
    }

    public void calcularCosto() {
        double costoMaterial = 0;
        if (material.equals("Caja de cartón")) {
            costoMaterial = 3000;
        } else if (material.equals("Bolsa de papel")) {
            costoMaterial = 1500;
        } else if (material.equals("Caja de madera")) {
            costoMaterial = 8000;
        }

        double costoTamano = 0;
        if (tamano.equals("Mediano")) {
            costoTamano = 1000;
        } else if (tamano.equals("Grande")) {
            costoTamano = 2000;
        }

        costo = costoMaterial + costoTamano;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) {
        this.material = material;
        calcularCosto();
    }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) {
        this.tamano = tamano;
        calcularCosto();
    }

    public double getCosto() { return costo; }

    public String toString() {
        return material + " (" + tamano + ") - $" + costo;
    }
}
