package Modulos;

import static Proyecto1.Boot.moduloIngreso;
import static Proyecto1.Boot.moduloBeneficiarios;
import static Proyecto1.Boot.moduloRemitentes;
import static Proyecto1.Boot.moduloCancelar;
import static Proyecto1.Boot.moduloPagos;
import static Proyecto1.Boot.moduloRemesas;
import static Proyecto1.Modulo_De_Ingreso_JF.auxUsuario;
import static Proyecto1.Modulo_De_Ingreso_JF.auxTipoUsuario;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Gestion_Especial_Menu_JF extends JFrame {

    JPanel panel, pRemesas, pEstadosRemesas, pRemitentes, pBeneficiarios, pContabilidad;
    JButton bCerrarSesion, bRemesasCargar, bRemitentesCargar, bBeneficiariosCargar, bCMasRCanceladas, bCMenosRCanceladas, bCMasRPagadas, bCMenosRPagadas;
    JLabel lIdentificador, lRHasta, lERidRemitente, lERNombreRemitente, lRDe, lRPara, lRidentificadorDe, lRidentificadorPara, lBPara, lBidentificador, lBHasta, lCUsuario;
    JTabbedPane pPrincipal, pRemesasVPC;
    JScrollPane sRVendidas, sRPagadas, sRCanceladas, sERemesas, sRRemitentes, sBBeneficiarios, sCContabilidad;
    JTable tRVendidas, tRPagadas, tRCanceladas, tERemesas, tRRemitentes, tBBeneficiarios, tCContabilidad;
    JComboBox cMesRemesas1, cDiaRemesas1, cMesRemesas2, cDiaRemesas2, cERidRemitente, cRRemitentes, cRBeneficiarios, cRMesDesde, cRMesHasta, cRDiaDesde, cRDiaHasta, cBBeneficiarios, cBMesDesde, cBMesHasta, cBDiaDesde, cBDiaHasta;
    int mesDesde = 0, mesHasta = 0, mesDesdeRemitentes = 0, mesHastaRemitentes = 0, mesDesdeBeneficiarios = 0, mesHastaBeneficiarios = 0;
    Proyecto1.Modulo_Contador_Usuarios verificar[] = new Proyecto1.Modulo_Contador_Usuarios[moduloIngreso.length];
    int posicion;

    public Gestion_Especial_Menu_JF() throws HeadlessException {
        setSize(570, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Menu Especial Contabilidad");
        setResizable(false);
        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        inicarPanel();
        compBotones();
        compTextos();
        iniciarPestañas();

    }

    private void inicarPanel() {
        panel = new JPanel(null);
        panel.setBackground(Color.white);
        this.getContentPane().add(panel);

    }

    private void compTextos() {
        lIdentificador = new JLabel(auxTipoUsuario + " " + auxUsuario);
        lIdentificador.setBounds(50, 10, 120, 25);
        panel.add(lIdentificador);

    }

    private void compBotones() {
        bCerrarSesion = new JButton("Regresar");

        bCerrarSesion.setMnemonic('r');

        bCerrarSesion.setBounds(430, 10, 100, 30);

        panel.add(bCerrarSesion);

    }

    private void iniciarEventos() {
        ActionListener eCerarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Proyecto1.Menu_Administrador_JF menu_Administrador_JF = new Proyecto1.Menu_Administrador_JF();
                menu_Administrador_JF.setVisible(true);
                dispose();

            }
        };
        bCerrarSesion.addActionListener(eCerarSesion);

        bRemesasCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                llenarMesDesdeParaRemesas();
                llenarMesHastaParaRemesas();
                verificarFechasParaRemesas();
                llenartRVendidas();
                llenarRPagadas();
                llenarRCanceladas();

            }

            private void llenartRVendidas() {
                int aux = 0;

                for (int i = 0; i < moduloRemesas.length; i++) {

                    if (mesDesde <= mesHasta) {
                        System.out.println("Entra Mes Vendidas");
                        if (moduloRemesas[i].getFechaVenta() != null && cDiaRemesas1.getSelectedItem() != null && cDiaRemesas2.getSelectedItem() != null) {

                            System.out.println(moduloRemesas[i].getFechaVenta());
                            String fechas[] = moduloRemesas[i].getFechaVenta().split("/");
                            int dia = Integer.parseInt(fechas[0]);
                            int mes = Integer.parseInt(fechas[1]);
                            int diaDesde = Integer.parseInt(cDiaRemesas1.getSelectedItem().toString());
                            int diaHasta = Integer.parseInt(cDiaRemesas2.getSelectedItem().toString());
                            System.out.println("composicion Vendidas");
                            System.out.println(dia + " desl mes " + mes + " desde " + diaDesde + " hasta " + diaHasta + " del mes " + mesDesde + " hasta " + mesHasta);

                            if (mesDesde == mesHasta) {
                                System.out.println("Mes Igual Vendidas");

                                if (diaDesde <= dia && dia < diaHasta) {
                                    System.out.println("Muestra Tabla Vendidas");

                                    tRVendidas.setValueAt(moduloRemesas[i].getIdRemesa(), aux, 0);
                                    String idRemitente = "";
                                    for (int j = 0; j < moduloRemitentes.length; j++) {
                                        if (moduloRemesas[i].getIdRemitente().equalsIgnoreCase(moduloRemitentes[j].getiD())) {
                                            idRemitente = moduloRemitentes[j].getNombre();
                                        }
                                    }
                                    tRVendidas.setValueAt(idRemitente, aux, 1);

                                    String idBeneficiario = "";
                                    for (int j = 0; j < moduloBeneficiarios.length; j++) {
                                        if (moduloRemesas[i].getIdBeneficiario().equalsIgnoreCase(moduloBeneficiarios[j].getiD())) {
                                            idBeneficiario = moduloBeneficiarios[j].getNombre();
                                        }
                                    }
                                    tRVendidas.setValueAt(idBeneficiario, aux, 2);
                                    tRVendidas.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 3);
                                    tRVendidas.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 4);
                                    tRVendidas.setValueAt(moduloRemesas[i].getFechaVenta(), aux, 5);
                                    aux += 1;

                                } else {
                                    aux = i;
                                }

                            } else {
                                System.out.println("Limpiar Tabla Vendidas");
                                tRVendidas.setValueAt(null, i, 0);
                                tRVendidas.setValueAt(null, i, 1);
                                tRVendidas.setValueAt(null, i, 2);
                                tRVendidas.setValueAt(null, i, 3);
                                tRVendidas.setValueAt(null, i, 4);
                                tRVendidas.setValueAt(null, i, 5);
                            }

                            if (mesDesde < mes && mesHasta >= mes) {

                                System.out.println("Mes Diferente Vendidas");

                                if (diaDesde < dia || dia <= diaHasta) {
                                    System.out.println("Muestra Tabla Vendidas");
                                    tRVendidas.setValueAt(moduloRemesas[i].getIdRemesa(), aux, 0);

                                    String idRemitente = "";
                                    for (int j = 0; j < moduloRemitentes.length; j++) {
                                        if (moduloRemesas[i].getIdRemitente().equalsIgnoreCase(moduloRemitentes[j].getiD())) {
                                            idRemitente = moduloRemitentes[j].getNombre();
                                        }
                                    }
                                    tRVendidas.setValueAt(idRemitente, aux, 1);

                                    String idBeneficiario = "";
                                    for (int j = 0; j < moduloBeneficiarios.length; j++) {
                                        if (moduloRemesas[i].getIdBeneficiario().equalsIgnoreCase(moduloBeneficiarios[j].getiD())) {
                                            idBeneficiario = moduloBeneficiarios[j].getNombre();
                                        }
                                    }
                                    tRVendidas.setValueAt(idBeneficiario, aux, 2);
                                    tRVendidas.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 3);
                                    tRVendidas.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 4);
                                    tRVendidas.setValueAt(moduloRemesas[i].getFechaVenta(), aux, 5);
                                    aux += 1;

                                } else {
                                    aux = i;
                                }
                            } else {
                                System.out.println("Limpiar Tabla Vendidas");
                                tRVendidas.setValueAt(null, i, 0);
                                tRVendidas.setValueAt(null, i, 1);
                                tRVendidas.setValueAt(null, i, 2);
                                tRVendidas.setValueAt(null, i, 3);
                                tRVendidas.setValueAt(null, i, 4);
                                tRVendidas.setValueAt(null, i, 5);

                            }

                        }

                    }

                }
            }

            private void llenarRPagadas() {
                int aux = 0;
                for (int i = 0; i < moduloPagos.length; i++) {

                    if (mesDesde <= mesHasta) {
                        System.out.println("Entra Mes Pagadas");
                        if (moduloPagos[i].getiD() != null) {

                            System.out.println(moduloPagos[i].getFechaPago());
                            String fechas[] = moduloPagos[i].getFechaPago().split("/");
                            int dia = Integer.parseInt(fechas[0]);
                            int mes = Integer.parseInt(fechas[1]);
                            int diaDesde = Integer.parseInt(cDiaRemesas1.getSelectedItem().toString());
                            int diaHasta = Integer.parseInt(cDiaRemesas2.getSelectedItem().toString());
                            System.out.println("composicion Pagadas");
                            System.out.println(dia + " desl mes " + mes + " desde " + diaDesde + " hasta " + diaHasta + " del mes " + mesDesde + " hasta " + mesHasta);

                            if (mesDesde == mesHasta) {
                                System.out.println("Mes Igual Pagadas");

                                if (diaDesde <= dia && dia < diaHasta) {
                                    System.out.println("Muestra Tabla Pagadas");

                                    tRPagadas.setValueAt(moduloPagos[i].getiD(), aux, 0);
                                    tRPagadas.setValueAt(moduloPagos[i].getTipoCambio(), aux, 1);
                                    tRPagadas.setValueAt(moduloPagos[i].getMontoDestino(), aux, 2);
                                    tRPagadas.setValueAt(moduloPagos[i].getHoraPago(), aux, 3);
                                    tRPagadas.setValueAt(moduloPagos[i].getUsuarioPagador(), aux, 4);
                                    tRPagadas.setValueAt(moduloPagos[i].getFechaPago(), aux, 5);
                                    aux += 1;

                                } else {
                                    aux = i;
                                }

                            } else {
                                System.out.println("Limpiar Tabla para Pagadas");
                                tRPagadas.setValueAt(null, i, 0);
                                tRPagadas.setValueAt(null, i, 1);
                                tRPagadas.setValueAt(null, i, 2);
                                tRPagadas.setValueAt(null, i, 3);
                                tRPagadas.setValueAt(null, i, 4);
                                tRPagadas.setValueAt(null, i, 5);
                            }

                            if (mesDesde < mes && mesHasta >= mes) {

                                System.out.println("Mes Diferente Pagadas");

                                if (diaDesde < dia || dia <= diaHasta) {
                                    System.out.println("Muestra Tabla Pagadas");
                                    tRPagadas.setValueAt(moduloPagos[i].getiD(), aux, 0);
                                    tRPagadas.setValueAt(moduloPagos[i].getTipoCambio(), aux, 1);
                                    tRPagadas.setValueAt(moduloPagos[i].getMontoDestino(), aux, 2);
                                    tRPagadas.setValueAt(moduloPagos[i].getHoraPago(), aux, 3);
                                    tRPagadas.setValueAt(moduloPagos[i].getUsuarioPagador(), aux, 4);
                                    tRPagadas.setValueAt(moduloPagos[i].getFechaPago(), aux, 5);
                                    aux += 1;

                                } else {
                                    aux = i;
                                }
                            } else {
                                System.out.println("Limpiar Tabla Pagadas");
                                tRPagadas.setValueAt(null, i, 0);
                                tRPagadas.setValueAt(null, i, 1);
                                tRPagadas.setValueAt(null, i, 2);
                                tRPagadas.setValueAt(null, i, 3);
                                tRPagadas.setValueAt(null, i, 4);
                                tRPagadas.setValueAt(null, i, 5);

                            }

                        }
                    }

                }

            }

            private void llenarRCanceladas() {
                int aux = 0;
                for (int i = 0; i < moduloCancelar.length; i++) {

                    if (mesDesde <= mesHasta) {
                        System.out.println("Entra Mes Canceladas");
                        if (moduloCancelar[i].getFechaCancelar() != null && cDiaRemesas1.getSelectedItem() != null && cDiaRemesas2.getSelectedItem() != null) {

                            System.out.println(moduloCancelar[i].getFechaCancelar());
                            String fechas[] = moduloCancelar[i].getFechaCancelar().split("/");
                            int dia = Integer.parseInt(fechas[0]);
                            int mes = Integer.parseInt(fechas[1]);
                            int diaDesde = Integer.parseInt(cDiaRemesas1.getSelectedItem().toString());
                            int diaHasta = Integer.parseInt(cDiaRemesas2.getSelectedItem().toString());
                            System.out.println("composicion Canceladas");
                            System.out.println(dia + " desl mes " + mes + " desde " + diaDesde + " hasta " + diaHasta + " del mes " + mesDesde + " hasta " + mesHasta);

                            if (mesDesde == mesHasta) {
                                System.out.println("Mes Igual Canceladas");

                                if (diaDesde <= dia && dia < diaHasta) {
                                    System.out.println("Muestra Tabla Canceladas");

                                    tRCanceladas.setValueAt(moduloCancelar[i].getiD(), aux, 0);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getHoraCancelar(), aux, 1);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getUsuarioCancelar(), aux, 2);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getFechaCancelar(), aux, 3);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getMotivo(), aux, 4);
                                    aux += 1;

                                } else {
                                    aux = i;
                                }

                            } else {
                                System.out.println("Limpiar Tabla para Canceladas");
                                tRCanceladas.setValueAt(null, i, 0);
                                tRCanceladas.setValueAt(null, i, 1);
                                tRCanceladas.setValueAt(null, i, 2);
                                tRCanceladas.setValueAt(null, i, 3);
                                tRCanceladas.setValueAt(null, i, 4);
                            }

                            if (mesDesde < mes && mesHasta >= mes) {

                                System.out.println("Mes Diferente Cancelar");

                                if (diaDesde < dia || dia <= diaHasta) {
                                    System.out.println("Muestra Tabla Canceladas");
                                    tRCanceladas.setValueAt(moduloCancelar[i].getiD(), aux, 0);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getHoraCancelar(), aux, 1);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getUsuarioCancelar(), aux, 2);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getFechaCancelar(), aux, 3);
                                    tRCanceladas.setValueAt(moduloCancelar[i].getMotivo(), aux, 4);
                                    aux += 1;

                                } else {
                                    aux = i;
                                }
                            } else {
                                System.out.println("Limpiar Tabla para Canceladas");
                                tRCanceladas.setValueAt(null, i, 0);
                                tRCanceladas.setValueAt(null, i, 1);
                                tRCanceladas.setValueAt(null, i, 2);
                                tRCanceladas.setValueAt(null, i, 3);
                                tRCanceladas.setValueAt(null, i, 4);

                            }

                        }
                    }

                }
            }

            private void verificarFechasParaRemesas() {

                if (cDiaRemesas1.getSelectedItem() == null || cDiaRemesas2.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona la Fecha");
                    System.out.println("No hay Fecha");

                }
                if (mesDesde > mesHasta) {
                    JOptionPane.showMessageDialog(null, "No se puede ver en ese Rango de Meses");

                }
            }

        });

        bRemitentesCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                llenarMesDesdeRemitentes();
                llenarMesHastaRemitentes();
                verificarFechasParaRemitentes();
                llenarRRemitentes();

            }

            private void llenarMesDesdeRemitentes() {
                if (cRMesDesde.getSelectedItem() == "ENE") {
                    mesDesdeRemitentes = 1;
                }
                if (cRMesDesde.getSelectedItem() == "FEB") {
                    mesDesdeRemitentes = 2;
                }
                if (cRMesDesde.getSelectedItem() == "MAR") {
                    mesDesdeRemitentes = 3;
                }
                if (cRMesDesde.getSelectedItem() == "ABR") {
                    mesDesdeRemitentes = 4;
                }
                if (cRMesDesde.getSelectedItem() == "MAY") {
                    mesDesdeRemitentes = 5;
                }
                if (cRMesDesde.getSelectedItem() == "JUN") {
                    mesDesdeRemitentes = 6;
                }
                if (cRMesDesde.getSelectedItem() == "JUL") {
                    mesDesdeRemitentes = 7;
                }
                if (cRMesDesde.getSelectedItem() == "AGO") {
                    mesDesdeRemitentes = 8;
                }
                if (cRMesDesde.getSelectedItem() == "SEP") {
                    mesDesdeRemitentes = 9;
                }
                if (cRMesDesde.getSelectedItem() == "OCT") {
                    mesDesdeRemitentes = 10;
                }
                if (cRMesDesde.getSelectedItem() == "NOV") {
                    mesDesdeRemitentes = 11;
                }
                if (cRMesDesde.getSelectedItem() == "DIC") {
                    mesDesdeRemitentes = 12;
                }

            }

            private void llenarMesHastaRemitentes() {
                if (cRMesHasta.getSelectedItem() == "ENE") {
                    mesHastaRemitentes = 1;
                }
                if (cRMesHasta.getSelectedItem() == "FEB") {
                    mesHastaRemitentes = 2;
                }
                if (cRMesHasta.getSelectedItem() == "MAR") {
                    mesHastaRemitentes = 3;
                }
                if (cRMesHasta.getSelectedItem() == "ABR") {
                    mesHastaRemitentes = 4;
                }
                if (cRMesHasta.getSelectedItem() == "MAY") {
                    mesHastaRemitentes = 5;
                }
                if (cRMesHasta.getSelectedItem() == "JUN") {
                    mesHastaRemitentes = 6;
                }
                if (cRMesHasta.getSelectedItem() == "JUL") {
                    mesHastaRemitentes = 7;
                }
                if (cRMesHasta.getSelectedItem() == "AGO") {
                    mesHastaRemitentes = 8;
                }
                if (cRMesHasta.getSelectedItem() == "SEP") {
                    mesHastaRemitentes = 9;
                }
                if (cRMesHasta.getSelectedItem() == "OCT") {
                    mesHastaRemitentes = 10;
                }
                if (cRMesHasta.getSelectedItem() == "NOV") {
                    mesHastaRemitentes = 11;
                }
                if (cRMesHasta.getSelectedItem() == "DIC") {
                    mesHastaRemitentes = 12;
                }

            }

            private void llenarRRemitentes() {
                int aux = 0;
                limpiarTablaRemitentes();

                for (int i = 0; i < moduloRemesas.length; i++) {

                    if (mesDesdeRemitentes <= mesHastaRemitentes) {
                        System.out.println("Entra Mes Remitentes");
                        if (moduloRemesas[i].getIdRemesa() != null && cRDiaDesde.getSelectedItem() != null && cRDiaHasta.getSelectedItem() != null) {
                            if (cRRemitentes.getSelectedItem().equals(moduloRemesas[i].getIdRemitente()) && cRBeneficiarios.getSelectedItem().equals(moduloRemesas[i].getIdBeneficiario())) {
                                System.out.println("Es el mismo Remitente y Beneficiario");

                                String idRemitente = "", idBeneficiario = "";
                                for (int j = 0; j < moduloRemitentes.length; j++) {
                                    if (moduloRemesas[i].getIdRemitente().equalsIgnoreCase(moduloRemitentes[j].getiD())) {
                                        idRemitente = moduloRemitentes[j].getNombre();
                                    }
                                }
                                for (int j = 0; j < moduloBeneficiarios.length; j++) {
                                    if (moduloRemesas[i].getIdBeneficiario().equalsIgnoreCase(moduloBeneficiarios[j].getiD())) {
                                        idBeneficiario = moduloBeneficiarios[j].getNombre();
                                    }
                                }
                                System.out.println(moduloRemesas[i].getFechaVenta() + " Remitente " + idRemitente + " Beneficiario " + idBeneficiario);

                                String fechas[] = moduloRemesas[i].getFechaVenta().split("/");
                                int dia = Integer.parseInt(fechas[0]);
                                int mes = Integer.parseInt(fechas[1]);
                                int diaDesde = Integer.parseInt(cRDiaDesde.getSelectedItem().toString());
                                int diaHasta = Integer.parseInt(cRDiaHasta.getSelectedItem().toString());
                                System.out.println("composicion Remitentes");
                                System.out.println(dia + " desl mes " + mes + " desde " + diaDesde + " hasta " + diaHasta + " del mes " + mesDesdeRemitentes + " hasta " + mesHastaRemitentes);

                                if (mesDesdeRemitentes == mesHastaRemitentes) {
                                    System.out.println("Mes Igual Remitentes");

                                    if (diaDesde <= dia && dia < diaHasta) {
                                        System.out.println("Muestra Tabla Remitentes");

                                        tRRemitentes.setValueAt(moduloRemesas[i].getIdRemesa(), aux, 0);
                                        tRRemitentes.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 1);
                                        tRRemitentes.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 2);
                                        tRRemitentes.setValueAt(moduloRemesas[i].getUsuarioCrea(), aux, 3);
                                        String pagada;

                                        if (moduloRemesas[i].isVigente() == true) {
                                            pagada = "No";

                                        } else {
                                            pagada = "Si";
                                        }
                                        for (int j = 0; j < moduloCancelar.length; j++) {
                                            if (moduloRemesas[i].getIdRemesa().equals(moduloCancelar[j].getiD())) {
                                                pagada = "Cancelada";
                                            }
                                        }
                                        tRRemitentes.setValueAt(pagada, aux, 4);
                                        aux += 1;

                                    } else {
                                        aux = i;
                                    }

                                } else {
                                }

                                if (mesDesdeRemitentes < mes && mesHastaRemitentes >= mes) {

                                    System.out.println("Mes Diferente Remitentes");

                                    if (diaDesde < dia || dia <= diaHasta) {
                                        System.out.println("Muestra Tabla Remitentes");

                                        tRRemitentes.setValueAt(moduloRemesas[i].getIdRemesa(), aux, 0);
                                        tRRemitentes.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 1);
                                        tRRemitentes.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 2);
                                        tRRemitentes.setValueAt(moduloRemesas[i].getUsuarioCrea(), aux, 3);
                                        String pagada;

                                        if (moduloRemesas[i].isVigente() == true) {
                                            pagada = "No";

                                        } else {
                                            pagada = "Si";
                                        }
                                        for (int j = 0; j < moduloCancelar.length; j++) {
                                            if (moduloRemesas[i].getIdRemesa().equals(moduloCancelar[j].getiD())) {
                                                pagada = "Cancelada";
                                            }
                                        }
                                        tRRemitentes.setValueAt(pagada, aux, 4);
                                        aux += 1;

                                    } else {
                                        aux = i;
                                    }
                                } else {
                                }

                            }

                        }

                    }

                }
            }

            private void limpiarTablaRemitentes() {
                for (int i = 0; i < moduloRemesas.length; i++) {
                    tRRemitentes.setValueAt(null, i, 0);
                    tRRemitentes.setValueAt(null, i, 1);
                    tRRemitentes.setValueAt(null, i, 2);
                    tRRemitentes.setValueAt(null, i, 3);
                    tRRemitentes.setValueAt(null, i, 4);
                }
            }

            private void verificarFechasParaRemitentes() {

                if (cRRemitentes.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "No hay Remitentes Aun");
                    System.out.println("No hay Remitente");
                }
                if (cRBeneficiarios.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "No hay Beneficiario Aun");
                    System.out.println("No hay Beneficiario");
                }
                if (cRDiaDesde.getSelectedItem() == null || cRDiaHasta.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona la Fecha");
                    System.out.println("No hay Fecha");

                }
                if (mesDesdeRemitentes > mesHastaRemitentes) {
                    JOptionPane.showMessageDialog(null, "No se puede ver en ese Rango de Meses");

                }
            }

        });

        bBeneficiariosCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                llenarMesDesdeBeneficiarios();
                llenarMesHastaBeneficiarios();
                verificarFechasBeneficiarios();
                llenarBBeneficiarios();

            }

            private void llenarMesDesdeBeneficiarios() {
                if (cBMesDesde.getSelectedItem() == "ENE") {
                    mesDesdeBeneficiarios = 1;
                }
                if (cBMesDesde.getSelectedItem() == "FEB") {
                    mesDesdeBeneficiarios = 2;
                }
                if (cBMesDesde.getSelectedItem() == "MAR") {
                    mesDesdeBeneficiarios = 3;
                }
                if (cBMesDesde.getSelectedItem() == "ABR") {
                    mesDesdeBeneficiarios = 4;
                }
                if (cBMesDesde.getSelectedItem() == "MAY") {
                    mesDesdeBeneficiarios = 5;
                }
                if (cBMesDesde.getSelectedItem() == "JUN") {
                    mesDesdeBeneficiarios = 6;
                }
                if (cBMesDesde.getSelectedItem() == "JUL") {
                    mesDesdeBeneficiarios = 7;
                }
                if (cBMesDesde.getSelectedItem() == "AGO") {
                    mesDesdeBeneficiarios = 8;
                }
                if (cBMesDesde.getSelectedItem() == "SEP") {
                    mesDesdeBeneficiarios = 9;
                }
                if (cBMesDesde.getSelectedItem() == "OCT") {
                    mesDesdeBeneficiarios = 10;
                }
                if (cBMesDesde.getSelectedItem() == "NOV") {
                    mesDesdeBeneficiarios = 11;
                }
                if (cBMesDesde.getSelectedItem() == "DIC") {
                    mesDesdeBeneficiarios = 12;
                }
            }

            private void llenarMesHastaBeneficiarios() {
                if (cBMesHasta.getSelectedItem() == "ENE") {
                    mesHastaBeneficiarios = 1;
                }
                if (cBMesHasta.getSelectedItem() == "FEB") {
                    mesHastaBeneficiarios = 2;
                }
                if (cBMesHasta.getSelectedItem() == "MAR") {
                    mesHastaBeneficiarios = 3;
                }
                if (cBMesHasta.getSelectedItem() == "ABR") {
                    mesHastaBeneficiarios = 4;
                }
                if (cBMesHasta.getSelectedItem() == "MAY") {
                    mesHastaBeneficiarios = 5;
                }
                if (cBMesHasta.getSelectedItem() == "JUN") {
                    mesHastaBeneficiarios = 6;
                }
                if (cBMesHasta.getSelectedItem() == "JUL") {
                    mesHastaBeneficiarios = 7;
                }
                if (cBMesHasta.getSelectedItem() == "AGO") {
                    mesHastaBeneficiarios = 8;
                }
                if (cBMesHasta.getSelectedItem() == "SEP") {
                    mesHastaBeneficiarios = 9;
                }
                if (cBMesHasta.getSelectedItem() == "OCT") {
                    mesHastaBeneficiarios = 10;
                }
                if (cBMesHasta.getSelectedItem() == "NOV") {
                    mesHastaBeneficiarios = 11;
                }
                if (cBMesHasta.getSelectedItem() == "DIC") {
                    mesHastaBeneficiarios = 12;
                }
            }

            private void verificarFechasBeneficiarios() {

                if (cBBeneficiarios.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "No hay Beneficiario Aun");
                    System.out.println("No hay Beneficiario");
                }
                if (cBDiaDesde.getSelectedItem() == null || cBDiaHasta.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona la Fecha");
                    System.out.println("No hay Fecha");
                }
                if (mesDesdeBeneficiarios > mesHastaBeneficiarios) {
                    JOptionPane.showMessageDialog(null, "No se puede ver en ese Rango de Meses");

                }
            }

            private void llenarBBeneficiarios() {
                int aux = 0;
                limpiarTablaBeneficiarios();

                for (int i = 0; i < moduloRemesas.length; i++) {

                    if (mesDesdeBeneficiarios <= mesHastaBeneficiarios) {
                        System.out.println("Entra Mes Beneficiarios");
                        if (moduloRemesas[i].getIdRemesa() != null && cBDiaDesde.getSelectedItem() != null && cBDiaHasta.getSelectedItem() != null) {
                            if (cBBeneficiarios.getSelectedItem().equals(moduloRemesas[i].getIdBeneficiario())) {
                                System.out.println("Es el mismo Beneficiario");

                                String idRemitente = "";
                                for (int j = 0; j < moduloRemitentes.length; j++) {
                                    if (moduloRemesas[i].getIdRemitente().equalsIgnoreCase(moduloRemitentes[j].getiD())) {
                                        idRemitente = moduloRemitentes[j].getNombre();
                                    }
                                }
                                System.out.println(moduloRemesas[i].getFechaVenta() + " Remitente " + idRemitente);

                                String fechas[] = moduloRemesas[i].getFechaVenta().split("/");
                                int dia = Integer.parseInt(fechas[0]);
                                int mes = Integer.parseInt(fechas[1]);
                                int diaDesde = Integer.parseInt(cBDiaDesde.getSelectedItem().toString());
                                int diaHasta = Integer.parseInt(cBDiaHasta.getSelectedItem().toString());
                                System.out.println("composicion Beneficiarios");
                                System.out.println(dia + " desl mes " + mes + " desde " + diaDesde + " hasta " + diaHasta + " del mes " + mesDesdeBeneficiarios + " hasta " + mesHastaBeneficiarios);

                                if (mesDesdeBeneficiarios == mesHastaBeneficiarios) {
                                    System.out.println("Mes Igual Beneficiarios");

                                    if (diaDesde <= dia && dia < diaHasta) {
                                        System.out.println("Muestra Tabla Beneficiarios");

                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getIdRemesa(), aux, 0);
                                        tBBeneficiarios.setValueAt(idRemitente, aux, 1);
                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 2);
                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 3);
                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getUsuarioCrea(), aux, 4);
                                        String pagada;

                                        if (moduloRemesas[i].isVigente() == true) {
                                            pagada = "No";

                                        } else {
                                            pagada = "Si";
                                        }
                                        for (int j = 0; j < moduloCancelar.length; j++) {
                                            if (moduloRemesas[i].getIdRemesa().equals(moduloCancelar[j].getiD())) {
                                                pagada = "Cancelada";
                                            }
                                        }
                                        tBBeneficiarios.setValueAt(pagada, aux, 5);
                                        aux += 1;

                                    } else {
                                        aux = i;
                                    }

                                } else {
                                }

                                if (mesDesdeBeneficiarios < mes && mesHastaBeneficiarios >= mes) {

                                    System.out.println("Mes Diferente Beneficiarios");

                                    if (diaDesde < dia || dia <= diaHasta) {
                                        System.out.println("Muestra Tabla Beneficiarios");

                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getIdRemesa(), aux, 0);
                                        tBBeneficiarios.setValueAt(idRemitente, aux, 1);
                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 2);
                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 3);
                                        tBBeneficiarios.setValueAt(moduloRemesas[i].getUsuarioCrea(), aux, 4);
                                        String pagada;

                                        if (moduloRemesas[i].isVigente() == true) {
                                            pagada = "No";

                                        } else {
                                            pagada = "Si";
                                        }
                                        for (int j = 0; j < moduloCancelar.length; j++) {
                                            if (moduloRemesas[i].getIdRemesa().equals(moduloCancelar[j].getiD())) {
                                                pagada = "Cancelada";
                                            }
                                        }
                                        tBBeneficiarios.setValueAt(pagada, aux, 5);
                                        aux += 1;

                                    } else {
                                        aux = i;
                                    }
                                } else {
                                }

                            }

                        }

                    }

                }
            }

            private void limpiarTablaBeneficiarios() {
                for (int i = 0; i < moduloRemesas.length; i++) {
                    tBBeneficiarios.setValueAt(null, i, 0);
                    tBBeneficiarios.setValueAt(null, i, 1);
                    tBBeneficiarios.setValueAt(null, i, 2);
                    tBBeneficiarios.setValueAt(null, i, 3);
                    tBBeneficiarios.setValueAt(null, i, 4);
                    tBBeneficiarios.setValueAt(null, i, 5);
                }
            }

        });

        bCMasRCanceladas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarTablaContabilidad();
                verificarUsuarioMasC();
                lCUsuario.setText(verificar[0].getUsuario());
                mostrarVerificar();
                mostrarTablaUsuarioMasC();

            }

            private void verificarUsuarioMasC() {
                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (moduloIngreso[0].getUsuario().equalsIgnoreCase("2015") && moduloIngreso[1].getUsuario() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Mas Usuarios");
                        break;
                    }

                    if (moduloIngreso[i].getUsuario() != null) {
                        verificar[i] = new Proyecto1.Modulo_Contador_Usuarios(moduloIngreso[i].getUsuario(), 0);
                        System.out.println("Se Creo " + verificar[i].getUsuario() + " con " + verificar[i].getVeces());

                        for (int j = 0; j < moduloCancelar.length; j++) {
                            if (moduloCancelar[j].getiD() != null && verificar[i].getUsuario().equalsIgnoreCase(moduloCancelar[j].getUsuarioCancelar())) {
                                verificar[i].setVeces(verificar[i].getVeces() + 1);
                                System.out.println("Ese usuario se suma en 1 sus veces");
                            }

                        }
                    }

                    for (int k = 0; k < verificar.length - 1; k++) {
                        for (int j = 0; j < verificar.length - 1; j++) {
                            System.out.println("Entro a ordenar");
                            Proyecto1.Modulo_Contador_Usuarios aux;
                            if (verificar[j].getUsuario() != null) {

                                if (verificar[j].getVeces() < verificar[j + 1].getVeces()) {

                                    aux = verificar[j];
                                    verificar[j] = verificar[j + 1];
                                    verificar[j + 1] = aux;
                                }

                            }
                        }
                    }
                    System.out.println("Ordenado");

                }

            }

            private void mostrarTablaUsuarioMasC() {
                int aux = 0;
                for (int i = 0; i < moduloCancelar.length; i++) {
                    if (moduloCancelar[i].getiD() != null) {
                        if (moduloCancelar[i].getUsuarioCancelar().equalsIgnoreCase(verificar[0].getUsuario())) {
                            System.out.println("Muestra la Tabla");
                            tCContabilidad.setValueAt(moduloCancelar[i].getiD(), aux, 0);
                            String pais = "", monto = "";
                            for (int j = 0; j < moduloRemesas.length; j++) {
                                if (moduloCancelar[i].getiD().equalsIgnoreCase(moduloRemesas[j].getIdRemesa())) {
                                    pais = moduloRemesas[j].getPaisDestino();
                                    monto = moduloRemesas[j].getMontoDestino();
                                }
                            }
                            tCContabilidad.setValueAt(pais, aux, 1);
                            tCContabilidad.setValueAt(monto, aux, 2);
                            tCContabilidad.setValueAt(moduloCancelar[i].getFechaCancelar(), aux, 3);
                            tCContabilidad.setValueAt(moduloCancelar[i].getHoraCancelar(), aux, 4);
                            tCContabilidad.setValueAt(moduloCancelar[i].getMotivo(), aux, 5);
                            aux += 1;

                        } else {
                            aux = aux;

                        }
                    } else {

                    }

                }
            }

        });

        bCMenosRCanceladas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarTablaContabilidad();
                verificarUsuarioMenosC();
                mostrarVerificar();
                mostrarTablaUsuarioMenosC();
                lCUsuario.setText(verificar[posicion].getUsuario());
            }

            private void verificarUsuarioMenosC() {
                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (moduloIngreso[0].getUsuario().equalsIgnoreCase("2015") && moduloIngreso[1].getUsuario() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Mas Usuarios");
                        break;
                    }

                    if (moduloIngreso[i].getUsuario() != null) {
                        verificar[i] = new Proyecto1.Modulo_Contador_Usuarios(moduloIngreso[i].getUsuario(), 0);
                        System.out.println("Se Creo " + verificar[i].getUsuario() + " con " + verificar[i].getVeces());

                        for (int j = 0; j < moduloCancelar.length; j++) {
                            if (moduloCancelar[j].getiD() != null && verificar[i].getUsuario().equalsIgnoreCase(moduloCancelar[j].getUsuarioCancelar())) {
                                verificar[i].setVeces(verificar[i].getVeces() + 1);
                                System.out.println("Ese usuario se suma en 1 sus veces");
                            }

                        }
                    }

                    for (int k = 0; k < verificar.length - 1; k++) {
                        for (int j = 0; j < verificar.length - 1; j++) {
                            System.out.println("Entro a ordenar");
                            Proyecto1.Modulo_Contador_Usuarios aux;
                            if (verificar[j].getUsuario() != null) {

                                if (verificar[j].getVeces() > verificar[j + 1].getVeces() && verificar[j].getVeces() != 0) {

                                    aux = verificar[j];
                                    verificar[j] = verificar[j + 1];
                                    verificar[j + 1] = aux;
                                }

                            }
                        }
                    }
                    System.out.println("Ordenado");

                }

                for (int j = 0; j < verificar.length; j++) {
                    if (verificar[j].getVeces() != 0) {
                        posicion = j;
                        break;
                    }
                }
            }

            private void mostrarTablaUsuarioMenosC() {
                int aux = 0;
                for (int i = 0; i < moduloCancelar.length; i++) {
                    if (moduloCancelar[i].getiD() != null) {
                        if (moduloCancelar[i].getUsuarioCancelar().equalsIgnoreCase(verificar[posicion].getUsuario())) {
                            System.out.println("Muestra la Tabla");
                            tCContabilidad.setValueAt(moduloCancelar[i].getiD(), aux, 0);
                            String pais = "", monto = "";
                            for (int j = 0; j < moduloRemesas.length; j++) {
                                if (moduloCancelar[i].getiD().equalsIgnoreCase(moduloRemesas[j].getIdRemesa())) {
                                    pais = moduloRemesas[j].getPaisDestino();
                                    monto = moduloRemesas[j].getMontoDestino();
                                }
                            }
                            tCContabilidad.setValueAt(pais, aux, 1);
                            tCContabilidad.setValueAt(monto, aux, 2);
                            tCContabilidad.setValueAt(moduloCancelar[i].getFechaCancelar(), aux, 3);
                            tCContabilidad.setValueAt(moduloCancelar[i].getHoraCancelar(), aux, 4);
                            tCContabilidad.setValueAt(moduloCancelar[i].getMotivo(), aux, 5);
                            aux += 1;

                        } else {
                            aux = aux;

                        }
                    } else {

                    }

                }
            }

        });

        bCMasRPagadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarTablaContabilidad();
                verificarUsuarioMasP();
                lCUsuario.setText(verificar[0].getUsuario());
                mostrarVerificar();
                mostrarTablaUsuarioMasP();

            }

            private void verificarUsuarioMasP() {
                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (moduloIngreso[0].getUsuario().equalsIgnoreCase("2015") && moduloIngreso[1].getUsuario() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Mas Usuarios");
                        break;
                    }

                    if (moduloIngreso[i].getUsuario() != null) {
                        verificar[i] = new Proyecto1.Modulo_Contador_Usuarios(moduloIngreso[i].getUsuario(), 0);
                        System.out.println("Se Creo " + verificar[i].getUsuario() + " con " + verificar[i].getVeces());

                        for (int j = 0; j < moduloPagos.length; j++) {
                            if (moduloPagos[j].getiD() != null && verificar[i].getUsuario().equalsIgnoreCase(moduloPagos[j].getUsuarioPagador())) {
                                verificar[i].setVeces(verificar[i].getVeces() + 1);
                                System.out.println("Ese usuario se suma en 1 sus veces");
                            }

                        }
                    }

                    for (int k = 0; k < verificar.length - 1; k++) {
                        for (int j = 0; j < verificar.length - 1; j++) {
                            System.out.println("Entro a ordenar");
                            Proyecto1.Modulo_Contador_Usuarios aux;
                            if (verificar[j].getUsuario() != null) {

                                if (verificar[j].getVeces() < verificar[j + 1].getVeces()) {

                                    aux = verificar[j];
                                    verificar[j] = verificar[j + 1];
                                    verificar[j + 1] = aux;
                                }

                            }
                        }
                    }
                    System.out.println("Ordenado");

                }
            }

            private void mostrarTablaUsuarioMasP() {
                int aux = 0;
                for (int i = 0; i < moduloPagos.length; i++) {
                    if (moduloPagos[i].getiD() != null) {
                        if (moduloPagos[i].getUsuarioPagador().equalsIgnoreCase(verificar[0].getUsuario())) {
                            System.out.println("Muestra la Tabla");
                            tCContabilidad.setValueAt(moduloPagos[i].getiD(), aux, 0);
                            String pais = "", monto = "";
                            for (int j = 0; j < moduloRemesas.length; j++) {
                                if (moduloPagos[i].getiD().equalsIgnoreCase(moduloRemesas[j].getIdRemesa())) {
                                    pais = moduloRemesas[j].getPaisDestino();
                                    monto = moduloRemesas[j].getMontoDestino();
                                }
                            }
                            tCContabilidad.setValueAt(pais, aux, 1);
                            tCContabilidad.setValueAt(monto, aux, 2);
                            tCContabilidad.setValueAt(moduloPagos[i].getFechaPago(), aux, 3);
                            tCContabilidad.setValueAt(moduloPagos[i].getHoraPago(), aux, 4);
                            tCContabilidad.setValueAt("Pagada", aux, 5);
                            aux += 1;

                        } else {
                            aux = aux;

                        }
                    } else {

                    }

                }
            }

        });

        bCMenosRPagadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarTablaContabilidad();
                verificarUsuarioMenosP();
                mostrarVerificar();
                mostrarTablaUsuarioMenosP();
                lCUsuario.setText(verificar[posicion].getUsuario());
            }

            private void verificarUsuarioMenosP() {
                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (moduloIngreso[0].getUsuario().equalsIgnoreCase("2015") && moduloIngreso[1].getUsuario() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Mas Usuarios");
                        break;
                    }

                    if (moduloIngreso[i].getUsuario() != null) {
                        verificar[i] = new Proyecto1.Modulo_Contador_Usuarios(moduloIngreso[i].getUsuario(), 0);
                        System.out.println("Se Creo " + verificar[i].getUsuario() + " con " + verificar[i].getVeces());

                        for (int j = 0; j < moduloPagos.length; j++) {
                            if (moduloPagos[j].getiD() != null && verificar[i].getUsuario().equalsIgnoreCase(moduloPagos[j].getUsuarioPagador())) {
                                verificar[i].setVeces(verificar[i].getVeces() + 1);
                                System.out.println("Ese usuario se suma en 1 sus veces");
                            }

                        }
                    }

                    for (int k = 0; k < verificar.length - 1; k++) {
                        for (int j = 0; j < verificar.length - 1; j++) {
                            System.out.println("Entro a ordenar");
                            Proyecto1.Modulo_Contador_Usuarios aux;
                            if (verificar[j].getUsuario() != null) {

                                if (verificar[j].getVeces() > verificar[j + 1].getVeces() && verificar[j].getVeces() != 0) {

                                    aux = verificar[j];
                                    verificar[j] = verificar[j + 1];
                                    verificar[j + 1] = aux;
                                }

                            }
                        }
                    }
                    System.out.println("Ordenado");

                }

                for (int j = 0; j < verificar.length; j++) {
                    if (verificar[j].getVeces() != 0) {
                        posicion = j;
                        break;
                    }
                }
            }

            private void mostrarTablaUsuarioMenosP() {
                int aux = 0;
                for (int i = 0; i < moduloPagos.length; i++) {
                    if (moduloPagos[i].getiD() != null) {
                        if (moduloPagos[i].getUsuarioPagador().equalsIgnoreCase(verificar[posicion].getUsuario())) {
                            System.out.println("Muestra la Tabla");
                            tCContabilidad.setValueAt(moduloPagos[i].getiD(), aux, 0);
                            String pais = "", monto = "";
                            for (int j = 0; j < moduloRemesas.length; j++) {
                                if (moduloPagos[i].getiD().equalsIgnoreCase(moduloRemesas[j].getIdRemesa())) {
                                    pais = moduloRemesas[j].getPaisDestino();
                                    monto = moduloRemesas[j].getMontoDestino();
                                }
                            }
                            tCContabilidad.setValueAt(pais, aux, 1);
                            tCContabilidad.setValueAt(monto, aux, 2);
                            tCContabilidad.setValueAt(moduloPagos[i].getUsuarioPagador(), aux, 3);
                            tCContabilidad.setValueAt(moduloPagos[i].getHoraPago(), aux, 4);
                            tCContabilidad.setValueAt("Pagada", aux, 5);
                            aux += 1;

                        } else {
                            aux = aux;

                        }
                    } else {

                    }

                }
            }
        });

    }

    private void iniciarPestañas() {
        pPrincipal = new JTabbedPane();
        pRemesas = new JPanel(null);
        pEstadosRemesas = new JPanel(null);
        pRemitentes = new JPanel(null);
        pBeneficiarios = new JPanel(null);
        pContabilidad = new JPanel(null);

        iniciarPRemesas();
        iniciarPEstadoRemesas();
        iniciarPRemitentes();
        iniciarPBeneficiarios();
        iniciarPContabilidad();

        pPrincipal.setBounds(10, 50, 545, 365);
        pPrincipal.addTab("Remesas", pRemesas);
        pPrincipal.addTab("Estado Remesas", pEstadosRemesas);
        pPrincipal.addTab("Remitentes", pRemitentes);
        pPrincipal.addTab("Beneficiarios", pBeneficiarios);
        pPrincipal.addTab("Contabilidad", pContabilidad);

        panel.add(pPrincipal);

    }

    private void iniciarPRemesas() {
        pRemesas.setBackground(Color.white);

        cMesRemesas1 = new JComboBox();
        cMesRemesas2 = new JComboBox();
        cDiaRemesas1 = new JComboBox();
        cDiaRemesas2 = new JComboBox();
        bRemesasCargar = new JButton("Cargar");

        lRHasta = new JLabel("Hasta ->");

        llenarMesRemesas();

        cMesRemesas1.setBounds(20, 30, 60, 20);
        cMesRemesas2.setBounds(380, 30, 60, 20);
        cDiaRemesas1.setBounds(100, 30, 50, 20);
        cDiaRemesas2.setBounds(460, 30, 50, 20);
        lRHasta.setBounds(250, 30, 60, 20);
        bRemesasCargar.setBounds(230, 60, 80, 20);

        pRemesas.add(cMesRemesas1);
        pRemesas.add(cMesRemesas2);
        pRemesas.add(cDiaRemesas1);
        pRemesas.add(cDiaRemesas2);
        pRemesas.add(lRHasta);
        pRemesas.add(bRemesasCargar);

        pRemesasVPC = new JTabbedPane();
        sRVendidas = new JScrollPane();
        sRPagadas = new JScrollPane();
        sRCanceladas = new JScrollPane();

        pRemesasVPC.setBounds(15, 100, 510, 230);

        iniciarTablaRVendidas();
        iniciarTablaRPagadas();
        iniciarTablaRCanceladas();

        pRemesas.add(pRemesasVPC);

        cMesRemesas1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cMesRemesas1.getSelectedItem().equals("ENE") || cMesRemesas1.getSelectedItem().equals("MAR") || cMesRemesas1.getSelectedItem().equals("MAY") || cMesRemesas1.getSelectedItem().equals("JUL") || cMesRemesas1.getSelectedItem().equals("AGO") || cMesRemesas1.getSelectedItem().equals("OCT") || cMesRemesas1.getSelectedItem().equals("DIC")) {
                    cDiaRemesas1.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaRemesas1.addItem(i);
                    }
                }
                if (cMesRemesas1.getSelectedItem().equals("ABR") || cMesRemesas1.getSelectedItem().equals("JUN") || cMesRemesas1.getSelectedItem().equals("SEP") || cMesRemesas1.getSelectedItem().equals("NOV")) {
                    cDiaRemesas1.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaRemesas1.addItem(i);
                    }
                }
                if (cMesRemesas1.getSelectedItem().equals("FEB")) {
                    cDiaRemesas1.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cDiaRemesas1.addItem(i);
                    }
                }

            }
        });

        cMesRemesas2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cMesRemesas2.getSelectedItem().equals("ENE") || cMesRemesas2.getSelectedItem().equals("MAR") || cMesRemesas2.getSelectedItem().equals("MAY") || cMesRemesas2.getSelectedItem().equals("JUL") || cMesRemesas2.getSelectedItem().equals("AGO") || cMesRemesas2.getSelectedItem().equals("OCT") || cMesRemesas2.getSelectedItem().equals("DIC")) {
                    cDiaRemesas2.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaRemesas2.addItem(i);
                    }
                }
                if (cMesRemesas2.getSelectedItem().equals("ABR") || cMesRemesas2.getSelectedItem().equals("JUN") || cMesRemesas2.getSelectedItem().equals("SEP") || cMesRemesas2.getSelectedItem().equals("NOV")) {
                    cDiaRemesas2.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaRemesas2.addItem(i);
                    }
                }
                if (cMesRemesas2.getSelectedItem().equals("FEB")) {
                    cDiaRemesas2.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cDiaRemesas2.addItem(i);
                    }
                }

            }
        });

    }

    private void iniciarPEstadoRemesas() {
        pEstadosRemesas.setBackground(Color.white);

        lERidRemitente = new JLabel("ID Remitente");
        cERidRemitente = new JComboBox();
        lERNombreRemitente = new JLabel("");

        lERidRemitente.setBounds(20, 30, 100, 20);
        cERidRemitente.setBounds(130, 30, 80, 20);
        lERNombreRemitente.setBounds(350, 30, 100, 20);

        llenarIDsRemitente();

        sERemesas = new JScrollPane();
        sERemesas.setBounds(15, 100, 510, 230);
        DefaultTableModel mERemesas = new DefaultTableModel();
        String titulosRC[] = {"ID", "Beneficiario", "Monto", "Pagada"};
        mERemesas.setColumnCount(titulosRC.length);
        mERemesas.setColumnIdentifiers(titulosRC);
        mERemesas.setRowCount(moduloRemesas.length);
        tERemesas = new JTable(mERemesas);
        tERemesas.setEnabled(false);
        sERemesas.setViewportView(tERemesas);

        pEstadosRemesas.add(lERidRemitente);
        pEstadosRemesas.add(cERidRemitente);
        pEstadosRemesas.add(lERNombreRemitente);
        pEstadosRemesas.add(sERemesas);

        cERidRemitente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int auxER = 0;
                limpiarTablaER();
                for (int i = 0; i < moduloRemesas.length; i++) {
                    if (cERidRemitente.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Remitentes Aun");
                        break;
                    }

                    if (cERidRemitente.getSelectedItem().equals(moduloRemesas[i].getIdRemitente())) {
                        System.out.println("Imprime nombre Remitente");
                        String nombre = "";
                        for (int j = 0; j < moduloRemitentes.length; j++) {
                            if (moduloRemesas[i].getIdRemitente().equalsIgnoreCase(moduloRemitentes[j].getiD())) {
                                nombre = moduloRemitentes[j].getNombre();
                            }
                        }

                        lERNombreRemitente.setText(nombre);

                        System.out.println("Muestra Tabla Estado Remesas");
                        tERemesas.setValueAt(moduloRemesas[i].getIdRemesa(), auxER, 0);
                        String beneficiario = "";
                        for (int j = 0; j < moduloBeneficiarios.length; j++) {
                            if (moduloBeneficiarios[j].getiD() != null) {
                                if (moduloBeneficiarios[j].getiD().equalsIgnoreCase(moduloRemesas[i].getIdBeneficiario())) {
                                    beneficiario = moduloBeneficiarios[j].getNombre();
                                }
                            }
                        }

                        tERemesas.setValueAt(beneficiario, auxER, 1);
                        String moneda = "";
                        if (moduloRemesas[i].getPaisDestino().equalsIgnoreCase("Estados Unidos")) {
                            moneda = "Dolares";
                        } else if (moduloRemesas[i].getPaisDestino().equalsIgnoreCase("España")) {
                            moneda = "Euros";
                        }
                        tERemesas.setValueAt(moduloRemesas[i].getMontoDestino() + "  " + moneda, auxER, 2);
                        String pagada;

                        if (moduloRemesas[i].isVigente() == true) {
                            pagada = "No";

                        } else {
                            pagada = "Si";
                        }
                        for (int j = 0; j < moduloCancelar.length; j++) {
                            if (moduloRemesas[i].getIdRemesa().equals(moduloCancelar[j].getiD())) {
                                pagada = "Cancelada";
                            }
                        }
                        tERemesas.setValueAt(pagada, auxER, 3);

                        auxER += 1;

                    } else {
                        String nombre = "";
                        for (int j = 0; j < moduloRemitentes.length; j++) {
                            if (cERidRemitente.getSelectedItem().equals(moduloRemitentes[j].getiD())) {
                                nombre = moduloRemitentes[j].getNombre();
                            }
                        }

                        lERNombreRemitente.setText(nombre);
                        auxER = auxER;
                    }

                }

            }

            private void limpiarTablaER() {
                for (int i = 0; i < moduloRemesas.length; i++) {
                    tERemesas.setValueAt(null, i, 0);
                    tERemesas.setValueAt(null, i, 1);
                    tERemesas.setValueAt(null, i, 2);
                    tERemesas.setValueAt(null, i, 3);
                }

            }
        });

    }

    private void iniciarPRemitentes() {
        pRemitentes.setBackground(Color.white);

        cRRemitentes = new JComboBox();
        lRDe = new JLabel("De");
        lRPara = new JLabel("Para");
        cRBeneficiarios = new JComboBox();
        cRMesDesde = new JComboBox();
        cRMesHasta = new JComboBox();
        cRDiaDesde = new JComboBox();
        cRDiaHasta = new JComboBox();
        lRHasta = new JLabel("Hasta ->");
        bRemitentesCargar = new JButton("Cargar");
        lRidentificadorDe = new JLabel("");
        lRidentificadorPara = new JLabel("");
        sRRemitentes = new JScrollPane();

        lRDe.setBounds(20, 10, 50, 20);
        cRRemitentes.setBounds(20, 40, 100, 20);
        lRidentificadorDe.setBounds(150, 40, 100, 20);
        lRPara.setBounds(300, 10, 50, 20);
        cRBeneficiarios.setBounds(300, 40, 100, 20);
        lRidentificadorPara.setBounds(430, 40, 100, 20);
        cRMesDesde.setBounds(20, 80, 60, 20);
        cRMesHasta.setBounds(380, 80, 60, 20);
        cRDiaDesde.setBounds(100, 80, 50, 20);
        cRDiaHasta.setBounds(460, 80, 50, 20);
        lRHasta.setBounds(250, 80, 60, 20);
        bRemitentesCargar.setBounds(230, 110, 80, 20);

        llenarRMesDesde();
        llenarIDsRemitente2();

        sRRemitentes.setBounds(15, 150, 510, 180);
        DefaultTableModel mRRemitentes = new DefaultTableModel();
        String titulosRC[] = {"ID", "Pais", "Monto", "Usuario", "Pagada"};
        mRRemitentes.setColumnCount(titulosRC.length);
        mRRemitentes.setColumnIdentifiers(titulosRC);
        mRRemitentes.setRowCount(moduloRemesas.length);
        tRRemitentes = new JTable(mRRemitentes);
        tRRemitentes.setEnabled(false);
        sRRemitentes.setViewportView(tRRemitentes);

        pRemitentes.add(cRRemitentes);
        pRemitentes.add(lRDe);
        pRemitentes.add(lRPara);
        pRemitentes.add(cRBeneficiarios);
        pRemitentes.add(cRMesDesde);
        pRemitentes.add(cRMesHasta);
        pRemitentes.add(cRDiaDesde);
        pRemitentes.add(cRDiaHasta);
        pRemitentes.add(lRHasta);
        pRemitentes.add(bRemitentesCargar);
        pRemitentes.add(lRidentificadorDe);
        pRemitentes.add(lRidentificadorPara);
        pRemitentes.add(sRRemitentes);

        cRRemitentes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                for (int i = 0; i < moduloRemitentes.length; i++) {
                    String nombreDe = "";
                    if (cRRemitentes.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Remitentes Aun");
                        break;
                    }

                    if (cRRemitentes.getSelectedItem() != null) {

                        if (cRRemitentes.getSelectedItem().equals(moduloRemitentes[i].getiD())) {
                            nombreDe = moduloRemitentes[i].getNombre();

                            lRidentificadorDe.setText(nombreDe);
                        }
                    }

                }

            }
        });

        cRBeneficiarios.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {

                for (int i = 0; i < moduloBeneficiarios.length; i++) {
                    String nombrePara = "";
                    if (cRBeneficiarios.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Beneficiarios Aun");
                        break;
                    }
                    if (cRBeneficiarios.getSelectedItem() != null) {
                        if (cRBeneficiarios.getSelectedItem().equals(moduloBeneficiarios[i].getiD())) {
                            nombrePara = moduloBeneficiarios[i].getNombre();

                            lRidentificadorPara.setText(nombrePara);
                        }
                    }
                }
            }
        });

        cRMesDesde.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cRMesDesde.getSelectedItem().equals("ENE") || cRMesDesde.getSelectedItem().equals("MAR") || cRMesDesde.getSelectedItem().equals("MAY") || cRMesDesde.getSelectedItem().equals("JUL") || cRMesDesde.getSelectedItem().equals("AGO") || cRMesDesde.getSelectedItem().equals("OCT") || cRMesDesde.getSelectedItem().equals("DIC")) {
                    cRDiaDesde.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cRDiaDesde.addItem(i);
                    }
                }
                if (cRMesDesde.getSelectedItem().equals("ABR") || cRMesDesde.getSelectedItem().equals("JUN") || cRMesDesde.getSelectedItem().equals("SEP") || cRMesDesde.getSelectedItem().equals("NOV")) {
                    cRDiaDesde.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cRDiaDesde.addItem(i);
                    }
                }
                if (cRMesDesde.getSelectedItem().equals("FEB")) {
                    cRDiaDesde.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cRDiaDesde.addItem(i);
                    }
                }

            }
        });

        cRMesHasta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cRMesHasta.getSelectedItem().equals("ENE") || cRMesHasta.getSelectedItem().equals("MAR") || cRMesHasta.getSelectedItem().equals("MAY") || cRMesHasta.getSelectedItem().equals("JUL") || cRMesHasta.getSelectedItem().equals("AGO") || cRMesHasta.getSelectedItem().equals("OCT") || cRMesHasta.getSelectedItem().equals("DIC")) {
                    cRDiaHasta.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cRDiaHasta.addItem(i);
                    }
                }
                if (cRMesHasta.getSelectedItem().equals("ABR") || cRMesHasta.getSelectedItem().equals("JUN") || cRMesHasta.getSelectedItem().equals("SEP") || cRMesHasta.getSelectedItem().equals("NOV")) {
                    cRDiaHasta.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cRDiaHasta.addItem(i);
                    }
                }
                if (cRMesHasta.getSelectedItem().equals("FEB")) {
                    cRDiaHasta.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cRDiaHasta.addItem(i);
                    }
                }

            }
        });

    }

    private void iniciarPBeneficiarios() {
        pBeneficiarios.setBackground(Color.white);

        lBPara = new JLabel("Para");
        lBidentificador = new JLabel("");
        cBBeneficiarios = new JComboBox();
        cBMesDesde = new JComboBox();
        cBMesHasta = new JComboBox();
        cBDiaDesde = new JComboBox();
        cBDiaHasta = new JComboBox();
        lBHasta = new JLabel("Hasta ->");
        cBMesHasta = new JComboBox();
        cBDiaHasta = new JComboBox();
        bBeneficiariosCargar = new JButton("Cargar");
        sBBeneficiarios = new JScrollPane();

        lBPara.setBounds(20, 30, 50, 20);
        cBBeneficiarios.setBounds(90, 30, 80, 20);
        lBidentificador.setBounds(230, 30, 100, 20);
        cBMesDesde.setBounds(20, 80, 60, 20);
        cBDiaDesde.setBounds(100, 80, 50, 20);
        lBHasta.setBounds(250, 80, 60, 20);
        cBMesHasta.setBounds(380, 80, 60, 20);
        cBDiaHasta.setBounds(460, 80, 50, 20);
        bBeneficiariosCargar.setBounds(230, 110, 80, 20);

        llenarMesBeneficiarios();
        llenarBidsBeneficiarios();

        sBBeneficiarios.setBounds(15, 150, 510, 180);
        DefaultTableModel mBBeneficiarios = new DefaultTableModel();
        String titulosBB[] = {"ID", "Remitente", "Pais", "Monto", "Usuario", "Pagada"};
        mBBeneficiarios.setColumnCount(titulosBB.length);
        mBBeneficiarios.setColumnIdentifiers(titulosBB);
        mBBeneficiarios.setRowCount(moduloRemesas.length);
        tBBeneficiarios = new JTable(mBBeneficiarios);
        tBBeneficiarios.setEnabled(false);
        sBBeneficiarios.setViewportView(tBBeneficiarios);

        pBeneficiarios.add(lBPara);
        pBeneficiarios.add(cBBeneficiarios);
        pBeneficiarios.add(lBidentificador);
        pBeneficiarios.add(cBMesDesde);
        pBeneficiarios.add(cBDiaDesde);
        pBeneficiarios.add(lBHasta);
        pBeneficiarios.add(cBMesHasta);
        pBeneficiarios.add(cBDiaHasta);
        pBeneficiarios.add(bBeneficiariosCargar);
        pBeneficiarios.add(sBBeneficiarios);

        cBMesDesde.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cBMesDesde.getSelectedItem().equals("ENE") || cBMesDesde.getSelectedItem().equals("MAR") || cBMesDesde.getSelectedItem().equals("MAY") || cBMesDesde.getSelectedItem().equals("JUL") || cBMesDesde.getSelectedItem().equals("AGO") || cBMesDesde.getSelectedItem().equals("OCT") || cBMesDesde.getSelectedItem().equals("DIC")) {
                    cBDiaDesde.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cBDiaDesde.addItem(i);
                    }
                }
                if (cBMesDesde.getSelectedItem().equals("ABR") || cBMesDesde.getSelectedItem().equals("JUN") || cBMesDesde.getSelectedItem().equals("SEP") || cBMesDesde.getSelectedItem().equals("NOV")) {
                    cBDiaDesde.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cBDiaDesde.addItem(i);
                    }
                }
                if (cBMesDesde.getSelectedItem().equals("FEB")) {
                    cBDiaDesde.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cBDiaDesde.addItem(i);
                    }
                }

            }
        });

        cBMesHasta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cBMesHasta.getSelectedItem().equals("ENE") || cBMesHasta.getSelectedItem().equals("MAR") || cBMesHasta.getSelectedItem().equals("MAY") || cBMesHasta.getSelectedItem().equals("JUL") || cBMesHasta.getSelectedItem().equals("AGO") || cBMesHasta.getSelectedItem().equals("OCT") || cBMesHasta.getSelectedItem().equals("DIC")) {
                    cBDiaHasta.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cBDiaHasta.addItem(i);
                    }
                }
                if (cBMesHasta.getSelectedItem().equals("ABR") || cBMesHasta.getSelectedItem().equals("JUN") || cBMesHasta.getSelectedItem().equals("SEP") || cBMesHasta.getSelectedItem().equals("NOV")) {
                    cBDiaHasta.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cBDiaHasta.addItem(i);
                    }
                }
                if (cBMesHasta.getSelectedItem().equals("FEB")) {
                    cBDiaHasta.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cBDiaHasta.addItem(i);
                    }
                }

            }
        });

        cBBeneficiarios.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {

                for (int i = 0; i < moduloBeneficiarios.length; i++) {
                    String nombreParaBeneficiario = "";
                    if (cBBeneficiarios.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "No hay Beneficiarios Aun");
                        break;
                    }
                    if (cBBeneficiarios.getSelectedItem() != null) {
                        if (cBBeneficiarios.getSelectedItem().equals(moduloBeneficiarios[i].getiD())) {
                            nombreParaBeneficiario = moduloBeneficiarios[i].getNombre();

                            lBidentificador.setText(nombreParaBeneficiario);
                        }
                    }
                }
            }
        });

    }

    private void iniciarPContabilidad() {
        pContabilidad.setBackground(Color.white);

        bCMasRCanceladas = new JButton("+ Remesas Canceladas");
        bCMenosRCanceladas = new JButton("- Remesas Canceladas");
        bCMasRPagadas = new JButton("+ Remesas Pagadas");
        bCMenosRPagadas = new JButton("- Remesas Pagadas");
        lCUsuario = new JLabel("");
        sCContabilidad = new JScrollPane();

        bCMasRCanceladas.setBounds(35, 30, 180, 25);
        bCMenosRCanceladas.setBounds(35, 70, 180, 25);
        bCMasRPagadas.setBounds(320, 30, 180, 25);
        bCMenosRPagadas.setBounds(320, 70, 180, 25);
        lCUsuario.setBounds(240, 110, 80, 20);

        sCContabilidad.setBounds(15, 150, 510, 180);
        DefaultTableModel mRContabilidad = new DefaultTableModel();
        String titulosRC[] = {"ID", "Pais", "Monto", "Fecha", "Hora", "Motivo"};
        mRContabilidad.setColumnCount(titulosRC.length);
        mRContabilidad.setColumnIdentifiers(titulosRC);
        mRContabilidad.setRowCount(moduloRemesas.length);
        tCContabilidad = new JTable(mRContabilidad);
        tCContabilidad.setEnabled(false);
        sCContabilidad.setViewportView(tCContabilidad);

        pContabilidad.add(bCMasRCanceladas);
        pContabilidad.add(bCMenosRCanceladas);
        pContabilidad.add(bCMasRPagadas);
        pContabilidad.add(bCMenosRPagadas);
        pContabilidad.add(sCContabilidad);
        pContabilidad.add(lCUsuario);

    }

    private void iniciarTablaRVendidas() {
        pRemesasVPC.addTab("Remesas Vendidas", sRVendidas);
        DefaultTableModel mRVendidas = new DefaultTableModel();
        String titulosRV[] = {"ID", "Remitente", "Beneficiario", "Pais", "Monto", "Fecha"};
        mRVendidas.setColumnCount(titulosRV.length);
        mRVendidas.setColumnIdentifiers(titulosRV);
        mRVendidas.setRowCount(moduloRemesas.length);
        tRVendidas = new JTable(mRVendidas);
        tRVendidas.setEnabled(false);
        sRVendidas.setViewportView(tRVendidas);
    }

    private void iniciarTablaRPagadas() {
        pRemesasVPC.addTab("Remesas Pagadas", sRPagadas);
        DefaultTableModel mRPagadas = new DefaultTableModel();
        String titulosRP[] = {"ID", "Cambio", "Monto", "Hora", "Usuario", "Fecha"};
        mRPagadas.setColumnCount(titulosRP.length);
        mRPagadas.setColumnIdentifiers(titulosRP);
        mRPagadas.setRowCount(moduloPagos.length);
        tRPagadas = new JTable(mRPagadas);
        tRPagadas.setEnabled(false);
        sRPagadas.setViewportView(tRPagadas);
    }

    private void iniciarTablaRCanceladas() {
        pRemesasVPC.addTab("Remesas Canceladas", sRCanceladas);
        DefaultTableModel mRCanceladas = new DefaultTableModel();
        String titulosRC[] = {"ID", "Hora", "Usuario", "Fecha", "Motivo"};
        mRCanceladas.setColumnCount(titulosRC.length);
        mRCanceladas.setColumnIdentifiers(titulosRC);
        mRCanceladas.setRowCount(moduloCancelar.length);
        tRCanceladas = new JTable(mRCanceladas);
        tRCanceladas.setEnabled(false);
        sRCanceladas.setViewportView(tRCanceladas);

    }

    private void llenarMesDesdeParaRemesas() {
        if (cMesRemesas1.getSelectedItem() == "ENE") {
            mesDesde = 1;
        }
        if (cMesRemesas1.getSelectedItem() == "FEB") {
            mesDesde = 2;
        }
        if (cMesRemesas1.getSelectedItem() == "MAR") {
            mesDesde = 3;
        }
        if (cMesRemesas1.getSelectedItem() == "ABR") {
            mesDesde = 4;
        }
        if (cMesRemesas1.getSelectedItem() == "MAY") {
            mesDesde = 5;
        }
        if (cMesRemesas1.getSelectedItem() == "JUN") {
            mesDesde = 6;
        }
        if (cMesRemesas1.getSelectedItem() == "JUL") {
            mesDesde = 7;
        }
        if (cMesRemesas1.getSelectedItem() == "AGO") {
            mesDesde = 8;
        }
        if (cMesRemesas1.getSelectedItem() == "SEP") {
            mesDesde = 9;
        }
        if (cMesRemesas1.getSelectedItem() == "OCT") {
            mesDesde = 10;
        }
        if (cMesRemesas1.getSelectedItem() == "NOV") {
            mesDesde = 11;
        }
        if (cMesRemesas1.getSelectedItem() == "DIC") {
            mesDesde = 12;
        }

    }

    private void llenarMesHastaParaRemesas() {
        if (cMesRemesas2.getSelectedItem() == "ENE") {
            mesHasta = 1;
        }
        if (cMesRemesas2.getSelectedItem() == "FEB") {
            mesHasta = 2;
        }
        if (cMesRemesas2.getSelectedItem() == "MAR") {
            mesHasta = 3;
        }
        if (cMesRemesas2.getSelectedItem() == "ABR") {
            mesHasta = 4;
        }
        if (cMesRemesas2.getSelectedItem() == "MAY") {
            mesHasta = 5;
        }
        if (cMesRemesas2.getSelectedItem() == "JUN") {
            mesHasta = 6;
        }
        if (cMesRemesas2.getSelectedItem() == "JUL") {
            mesHasta = 7;
        }
        if (cMesRemesas2.getSelectedItem() == "AGO") {
            mesHasta = 8;
        }
        if (cMesRemesas2.getSelectedItem() == "SEP") {
            mesHasta = 9;
        }
        if (cMesRemesas2.getSelectedItem() == "OCT") {
            mesHasta = 10;
        }
        if (cMesRemesas2.getSelectedItem() == "NOV") {
            mesHasta = 11;
        }
        if (cMesRemesas2.getSelectedItem() == "DIC") {
            mesHasta = 12;
        }
    }

    private void llenarIDsRemitente() {
        for (int i = 0; i < moduloRemitentes.length; i++) {
            if (moduloRemitentes[i].getiD() != null) {
                cERidRemitente.addItem(moduloRemitentes[i].getiD());
            } else {

            }

        }

    }

    private void llenarRMesDesde() {
        cRMesDesde.addItem("ENE");
        cRMesDesde.addItem("FEB");
        cRMesDesde.addItem("MAR");
        cRMesDesde.addItem("ABR");
        cRMesDesde.addItem("MAY");
        cRMesDesde.addItem("JUN");
        cRMesDesde.addItem("JUL");
        cRMesDesde.addItem("AGO");
        cRMesDesde.addItem("SEP");
        cRMesDesde.addItem("OCT");
        cRMesDesde.addItem("NOV");
        cRMesDesde.addItem("DIC");
        cRMesHasta.addItem("ENE");
        cRMesHasta.addItem("FEB");
        cRMesHasta.addItem("MAR");
        cRMesHasta.addItem("ABR");
        cRMesHasta.addItem("MAY");
        cRMesHasta.addItem("JUN");
        cRMesHasta.addItem("JUL");
        cRMesHasta.addItem("AGO");
        cRMesHasta.addItem("SEP");
        cRMesHasta.addItem("OCT");
        cRMesHasta.addItem("NOV");
        cRMesHasta.addItem("DIC");
    }

    private void llenarMesRemesas() {
        cMesRemesas1.addItem("ENE");
        cMesRemesas1.addItem("FEB");
        cMesRemesas1.addItem("MAR");
        cMesRemesas1.addItem("ABR");
        cMesRemesas1.addItem("MAY");
        cMesRemesas1.addItem("JUN");
        cMesRemesas1.addItem("JUL");
        cMesRemesas1.addItem("AGO");
        cMesRemesas1.addItem("SEP");
        cMesRemesas1.addItem("OCT");
        cMesRemesas1.addItem("NOV");
        cMesRemesas1.addItem("DIC");
        cMesRemesas2.addItem("ENE");
        cMesRemesas2.addItem("FEB");
        cMesRemesas2.addItem("MAR");
        cMesRemesas2.addItem("ABR");
        cMesRemesas2.addItem("MAY");
        cMesRemesas2.addItem("JUN");
        cMesRemesas2.addItem("JUL");
        cMesRemesas2.addItem("AGO");
        cMesRemesas2.addItem("SEP");
        cMesRemesas2.addItem("OCT");
        cMesRemesas2.addItem("NOV");
        cMesRemesas2.addItem("DIC");
    }

    private void llenarIDsRemitente2() {
        for (int i = 0; i < moduloRemitentes.length; i++) {
            if (moduloRemitentes[i].getiD() != null) {
                cRRemitentes.addItem(moduloRemitentes[i].getiD());
            } else {

            }

        }
        for (int i = 0; i < moduloBeneficiarios.length; i++) {
            if (moduloBeneficiarios[i].getiD() != null) {
                cRBeneficiarios.addItem(moduloBeneficiarios[i].getiD());
            } else {

            }
        }
    }

    private void llenarMesBeneficiarios() {
        cBMesDesde.addItem("ENE");
        cBMesDesde.addItem("FEB");
        cBMesDesde.addItem("MAR");
        cBMesDesde.addItem("ABR");
        cBMesDesde.addItem("MAY");
        cBMesDesde.addItem("JUN");
        cBMesDesde.addItem("JUL");
        cBMesDesde.addItem("AGO");
        cBMesDesde.addItem("SEP");
        cBMesDesde.addItem("OCT");
        cBMesDesde.addItem("NOV");
        cBMesDesde.addItem("DIC");
        cBMesHasta.addItem("ENE");
        cBMesHasta.addItem("FEB");
        cBMesHasta.addItem("MAR");
        cBMesHasta.addItem("ABR");
        cBMesHasta.addItem("MAY");
        cBMesHasta.addItem("JUN");
        cBMesHasta.addItem("JUL");
        cBMesHasta.addItem("AGO");
        cBMesHasta.addItem("SEP");
        cBMesHasta.addItem("OCT");
        cBMesHasta.addItem("NOV");
        cBMesHasta.addItem("DIC");
    }

    private void llenarBidsBeneficiarios() {

        for (int i = 0; i < moduloBeneficiarios.length; i++) {
            if (moduloBeneficiarios[i].getiD() != null) {
                cBBeneficiarios.addItem(moduloBeneficiarios[i].getiD());
            } else {

            }
        }
    }

    private void limpiarTablaContabilidad() {
        for (int i = 0; i < moduloRemesas.length; i++) {
            tCContabilidad.setValueAt(null, i, 0);
            tCContabilidad.setValueAt(null, i, 1);
            tCContabilidad.setValueAt(null, i, 2);
            tCContabilidad.setValueAt(null, i, 3);
            tCContabilidad.setValueAt(null, i, 4);
            tCContabilidad.setValueAt(null, i, 5);
        }
        for (int i = 0; i < verificar.length; i++) {
            verificar[i] = new Proyecto1.Modulo_Contador_Usuarios(null, 0);
        }
    }

    private void mostrarVerificar() {
        for (int i = 0; i < verificar.length; i++) {
            System.out.println(verificar[i].getUsuario() + " " + verificar[i].getVeces());
        }
    }

}
