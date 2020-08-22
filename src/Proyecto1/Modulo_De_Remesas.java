package Proyecto1;

public class Modulo_De_Remesas {

    private String idRemesa;
    private String idRemitente;
    private String idBeneficiario;
    private String paisDestino;
    private String fechaVenta;
    private String horaVenta;
    private String montoOrigen;
    private String montoDestino;
    private boolean vigente;
    private String usuarioCrea;

    public String getIdRemesa() {
        return idRemesa;
    }

    public void setIdRemesa(String idRemesa) {
        this.idRemesa = idRemesa;
    }

    public String getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(String idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(String idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getHoraVenta() {
        return horaVenta;
    }

    public void setHoraVenta(String horaVenta) {
        this.horaVenta = horaVenta;
    }

    public String getMontoOrigen() {
        return montoOrigen;
    }

    public void setMontoOrigen(String montoOrigen) {
        this.montoOrigen = montoOrigen;
    }

    public String getMontoDestino() {
        return montoDestino;
    }

    public void setMontoDestino(String montoDestino) {
        this.montoDestino = montoDestino;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Modulo_De_Remesas(String idRemesa, String idRemitente, String idBeneficiario, String paisDestino, String fechaVenta, String horaVenta, String montoOrigen, String montoDestino, boolean vigente, String usuarioCrea) {
        this.idRemesa = idRemesa;
        this.idRemitente = idRemitente;
        this.idBeneficiario = idBeneficiario;
        this.paisDestino = paisDestino;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.montoOrigen = montoOrigen;
        this.montoDestino = montoDestino;
        this.vigente = vigente;
        this.usuarioCrea = usuarioCrea;
    }

}
