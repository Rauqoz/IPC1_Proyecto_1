package Proyecto1;

public class Modulo_De_Pago {
    
    private String iD;
    private String fechaPago;
    private String horaPago;
    private String usuarioPagador;
    private String tipoCambio;
    private String montoDestino;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getHoraPago() {
        return horaPago;
    }

    public void setHoraPago(String horaPago) {
        this.horaPago = horaPago;
    }

    public String getUsuarioPagador() {
        return usuarioPagador;
    }

    public void setUsuarioPagador(String usuarioPagador) {
        this.usuarioPagador = usuarioPagador;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getMontoDestino() {
        return montoDestino;
    }

    public void setMontoDestino(String montoDestino) {
        this.montoDestino = montoDestino;
    }

    public Modulo_De_Pago(String iD, String fechaPago, String horaPago, String usuarioPagador, String tipoCambio, String montoDestino) {
        this.iD = iD;
        this.fechaPago = fechaPago;
        this.horaPago = horaPago;
        this.usuarioPagador = usuarioPagador;
        this.tipoCambio = tipoCambio;
        this.montoDestino = montoDestino;
    }
    
    

}
