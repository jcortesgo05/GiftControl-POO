package modelo;

public class Personalizacion {
    private String tecnica;        // Sublimación, Vinilo o Bordado
    private String tamanoDiseno;   // Pequeño, Mediano o Grande
    private String tipoContenido;  // Texto, Imagen o Texto e imagen
    private boolean servicioDiseno;
    private String descripcion;
    private double costoAdicional;

    public Personalizacion(String tecnica, String tamanoDiseno, String tipoContenido,
                           boolean servicioDiseno, String descripcion) {
        this.tecnica = tecnica;
        this.tamanoDiseno = tamanoDiseno;
        this.tipoContenido = tipoContenido;
        this.servicioDiseno = servicioDiseno;
        this.descripcion = descripcion;
        calcularCosto();
    }

    // Suma el costo de cada opción elegida
    public void calcularCosto() {
        double costo = 0;

        // Validación de Técnica
        if ("Sublimación".equalsIgnoreCase(tecnica) || "Sublimacion".equalsIgnoreCase(tecnica)) {
            costo += 5000;
        } else if ("Vinilo".equalsIgnoreCase(tecnica)) {
            costo += 7000;
        } else if ("Bordado".equalsIgnoreCase(tecnica)) {
            costo += 12000;
        }

        // Validación de Tamaño
        if ("Mediano".equalsIgnoreCase(tamanoDiseno)) {
            costo += 3000;
        } else if ("Grande".equalsIgnoreCase(tamanoDiseno)) {
            costo += 6000;
        }

        // Validación de Tipo de Contenido
        if ("Imagen".equalsIgnoreCase(tipoContenido)) {
            costo += 4000;
        } else if ("Texto e imagen".equalsIgnoreCase(tipoContenido) || "Texto e Imagen".equalsIgnoreCase(tipoContenido)) {
            costo += 6000;
        }

        // Servicio adicional de diseño
        if (servicioDiseno) {
            costo += 10000;
        }

        this.costoAdicional = costo;
    }

    public String getTecnica() { return tecnica; }
    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
        calcularCosto();
    }

    public String getTamanoDiseno() { return tamanoDiseno; }
    public void setTamanoDiseno(String tamanoDiseno) {
        this.tamanoDiseno = tamanoDiseno;
        calcularCosto();
    }

    public String getTipoContenido() { return tipoContenido; }
    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
        calcularCosto();
    }

    public boolean isServicioDiseno() { return servicioDiseno; }
    public void setServicioDiseno(boolean servicioDiseno) {
        this.servicioDiseno = servicioDiseno;
        calcularCosto();
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getCostoAdicional() { return costoAdicional; }

    @Override
    public String toString() {
        String servicio = servicioDiseno ? "Sí" : "No";
        return "Técnica: " + tecnica + ", tamaño: " + tamanoDiseno
             + ", contenido: " + tipoContenido + ", servicio de diseño: " + servicio
             + "\n   Descripción: " + descripcion
             + "\n   Costo adicional: $" + String.format("%,.0f", costoAdicional);
    }
}
  