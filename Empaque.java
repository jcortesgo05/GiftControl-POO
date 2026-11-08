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
        if (material == null || tamano == null || "Sin empaque".equalsIgnoreCase(material)) {
            this.costo = 0.0;
            return;
        }

        double costoMaterial = 0;
        if ("Caja de cartón".equalsIgnoreCase(material) || "Caja de carton".equalsIgnoreCase(material)) {
            costoMaterial = 3000;
        } else if ("Bolsa de papel".equalsIgnoreCase(material)) {
            costoMaterial = 1500;
        } else if ("Caja de madera".equalsIgnoreCase(material)) {
            costoMaterial = 8000;
        }

        double costoTamano = 0;
        if ("Mediano".equalsIgnoreCase(tamano)) {
            costoTamano = 1000;
        } else if ("Grande".equalsIgnoreCase(tamano)) {
            costoTamano = 2000;
        }

        this.costo = costoMaterial + costoTamano;
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

    @Override
    public String toString() {
        if ("Sin empaque".equalsIgnoreCase(material)) {
            return "Sin empaque - $0.0";
        }
        return material + " (" + tamano + ") - $" + costo;
    }
}