package Proyecto1;

public class Modulo_De_Ingreso {

    private String tUsuario;
    private String usuario;
    private String Contraseña;

    public String gettUsuario() {
        return tUsuario;
    }

    public void settUsuario(String tUsuario) {
        this.tUsuario = tUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public Modulo_De_Ingreso(String tUsuario, String usuario, String Contraseña) {
        this.tUsuario = tUsuario;
        this.usuario = usuario;
        this.Contraseña = Contraseña;
    }

    public boolean Comparar_Tipo(String tipou) {
        if (tipou.equalsIgnoreCase(tUsuario)) {
            return true;
        }
        return false;
    }

}
