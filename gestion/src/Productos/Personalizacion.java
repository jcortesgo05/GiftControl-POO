package Productos;

public class Personalizacion {
    private String tecnica;        
    private String tamanoDiseno;   
    private String tipoContenido;  
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

   
    public static double costoTecnica(String tecnica) {
        double costo = 0;
        if (tecnica.equals("Sublimación")) {
            costo = 4000;
        } else if (tecnica.equals("DTF")) {
            costo = 5000;
        } else if (tecnica.equals("Serigrafia")) {
            costo = 2500;
        }
        return costo;
    }

    
    public void calcularCosto() {
        double costo = costoTecnica(tecnica);

        if (tamanoDiseno.equals("Mediano")) {
            costo = costo + 2000;
        } else if (tamanoDiseno.equals("Grande")) {
            costo = costo + 3000;
        }

        if (tipoContenido.equals("Imagen")) {
            costo = costo + 2000;
        } else if (tipoContenido.equals("Texto e imagen")) {
            costo = costo + 3000;
        }

        if (servicioDiseno) {
            costo = costo + 35000;
        }

        costoAdicional = costo;
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

    public String toString() {
        String servicio;
        if (servicioDiseno) {
            servicio = "Sí";
        } else {
            servicio = "No";
        }
        return "Técnica: " + tecnica + ", tamaño: " + tamanoDiseno
             + ", contenido: " + tipoContenido + ", servicio de diseño: " + servicio
             + "\n   Descripción: " + descripcion
             + "\n   Costo adicional: $" + costoAdicional;
    }
}
