package modelo;

public class Proveedor {
    private String nombre;
    private String telefono;
    private String correo;
    private String suministra;

    public Proveedor(String nombre, String telefono, String correo, String suministra) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.suministra = suministra;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getSuministra() { return suministra; }
    public void setSuministra(String suministra) { this.suministra = suministra; }

    public String toString() {
        return "Proveedor: " + nombre
             + "\nTeléfono: " + telefono
             + "\nCorreo: " + correo
             + "\nSuministra: " + suministra;
    }
}
