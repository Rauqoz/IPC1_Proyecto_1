package Proyecto1;

public class Boot {

    public static Modulo_De_Ingreso moduloIngreso[] = new Modulo_De_Ingreso[10];
    public static Modulo_De_RyB moduloRemitentes[] = new Modulo_De_RyB[10];
    public static Modulo_De_RyB moduloBeneficiarios[] = new Modulo_De_RyB[10];
    public static Modulo_De_Remesas moduloRemesas[] = new Modulo_De_Remesas[10];
    public static Modulo_De_CancelarR moduloCancelar[] = new Modulo_De_CancelarR[10];
    public static Modulo_De_Pago moduloPagos[] = new Modulo_De_Pago[10];
    public static int idRContador = 0;
    public static int idBContador = 0;
    public static int idRemesaContador = 0;

    public Boot() {
        inicio();

        Modulo_De_Ingreso_JF main = new Modulo_De_Ingreso_JF();
        main.setVisible(true);

    }

    private void inicio() {
        moduloIngreso[0] = new Modulo_De_Ingreso("Administrador", "2015", "2015");
        for (int i = 1; i < moduloIngreso.length; i++) {
            if (moduloIngreso[i] == null) {
                moduloIngreso[i] = new Modulo_De_Ingreso(null, null, null);
                //llenar los campos con nulos
            }
        }

        for (int i = 0; i < moduloRemitentes.length; i++) {
            if (moduloRemitentes[i] == null) {
                moduloRemitentes[i] = new Modulo_De_RyB(null, null, new Modulo_De_Fecha(null, null, null), null, null, null);

            }
        }

        for (int i = 0; i < moduloBeneficiarios.length; i++) {
            if (moduloBeneficiarios[i] == null) {
                moduloBeneficiarios[i] = new Modulo_De_RyB(null, null, new Modulo_De_Fecha(null, null, null), null, null, null);

            }
        }

        for (int i = 0; i < moduloRemesas.length; i++) {
            if (moduloRemesas[i] == null) {
                moduloRemesas[i] = new Modulo_De_Remesas(null, null, null, null, null, null, null, null, false, null);

            }
        }

        for (int i = 0; i < moduloCancelar.length; i++) {
            if (moduloCancelar[i] == null) {
                moduloCancelar[i] = new Modulo_De_CancelarR(null, null, null, null, null);

            }
        }

        for (int i = 0; i < moduloPagos.length; i++) {
            if (moduloPagos[i] == null) {
                moduloPagos[i] = new Modulo_De_Pago(null, null, null, null, null, null);

            }
        }
    }

}
