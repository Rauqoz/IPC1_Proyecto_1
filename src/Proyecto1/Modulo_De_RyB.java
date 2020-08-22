package Proyecto1;

public class Modulo_De_RyB {

    private String iD;
    private String nombre;
    private Modulo_De_Fecha fechaNac;
    private String direccion;
    private String telefono;
    private String mail;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo_De_Fecha getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Modulo_De_Fecha fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Modulo_De_RyB(String iD, String nombre, Modulo_De_Fecha fechaNac, String direccion, String telefono, String mail) {
        this.iD = iD;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
    }
    
    

    

}
