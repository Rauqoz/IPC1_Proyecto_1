package Proyecto1;

public class Modulo_De_Fecha {
    
    private String dia;
    private String mes;
    private String año;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public Modulo_De_Fecha(String dia, String mes, String año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }
    
    

}
