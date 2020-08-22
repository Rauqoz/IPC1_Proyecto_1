package Proyecto1;

public class Modulo_Contador_Usuarios {

    private String usuario;
    private int veces;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public Modulo_Contador_Usuarios(String usuario, int veces) {
        this.usuario = usuario;
        this.veces = veces;
    }

}
