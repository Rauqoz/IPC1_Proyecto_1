package Proyecto1;

public class Modulo_De_CancelarR {

    private String iD;
    private String fechaCancelar;
    private String horaCancelar;
    private String usuarioCancelar;
    private String motivo;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getFechaCancelar() {
        return fechaCancelar;
    }

    public void setFechaCancelar(String fechaCancelar) {
        this.fechaCancelar = fechaCancelar;
    }

    public String getHoraCancelar() {
        return horaCancelar;
    }

    public void setHoraCancelar(String horaCancelar) {
        this.horaCancelar = horaCancelar;
    }

    public String getUsuarioCancelar() {
        return usuarioCancelar;
    }

    public void setUsuarioCancelar(String usuarioCancelar) {
        this.usuarioCancelar = usuarioCancelar;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Modulo_De_CancelarR(String iD, String fechaCancelar, String horaCancelar, String usuarioCancelar, String motivo) {
        this.iD = iD;
        this.fechaCancelar = fechaCancelar;
        this.horaCancelar = horaCancelar;
        this.usuarioCancelar = usuarioCancelar;
        this.motivo = motivo;
    }

}
