package Modulos;

import static Proyecto1.Boot.moduloPagos;
import static Proyecto1.Boot.moduloRemitentes;
import static Proyecto1.Boot.moduloBeneficiarios;
import static Proyecto1.Boot.idRemesaContador;
import static Proyecto1.Boot.moduloCancelar;
import static Proyecto1.Boot.moduloRemesas;
import static Proyecto1.Modulo_De_Ingreso_JF.auxUsuario;
import static Proyecto1.Modulo_De_Ingreso_JF.auxTipoUsuario;
import Proyecto1.Modulo_De_Remesas;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Gestion_Ventas_JF extends JFrame {

    JPanel p0 = new JPanel(null);
    JLabel lidRemesa, lidRemitente, lidBeneficiario, lPais, lFechaV, lHoraV, lMontoOrigen, lIdentificador;
    JScrollPane barra = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tDatosVentas = new JTable(modelo);
    JTextField tidRemesa, tFechaVenta, tHoraVenta, tMontoOrigen;
    JComboBox cidRemitente, cidBeneficiario, cPais;
    JButton bNuevaRemesa, bGuardarRemesa, bRegresar, bCancelarRemesa;
    String[] titulosTabla = {"ID Remitente", "ID Beneficiario", "Pais Destino", "Fecha", "Monto", "Vigente"};
    String[] paises = {"Estados Unidos", "España"};
    Date fecha = new Date();
    int cambioMoneda;

    public Gestion_Ventas_JF() throws HeadlessException {
        setSize(500, 430);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Venta de Remesas");
        setResizable(false);
        tDatosVentas.setEnabled(false);

        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        iniciarPaneles();
        iniciarLabels();
        iniciarText();
        iniciarCombobox();
        iniciarBotones();
        inicarTabla();

    }

    private void iniciarPaneles() {
        this.getContentPane().add(p0);
        p0.setBackground(Color.white);

    }

    private void iniciarLabels() {
        lidRemesa = new JLabel("ID Remesa");
        lidRemitente = new JLabel("ID Remitente");
        lidBeneficiario = new JLabel("ID Beneficiario");
        lPais = new JLabel("Pais Destino");
        lFechaV = new JLabel("Fecha Venta");
        lHoraV = new JLabel("Hora Venta");
        lMontoOrigen = new JLabel("Monto Origen");
        lIdentificador = new JLabel(auxTipoUsuario + " " + auxUsuario);

        lidRemesa.setBounds(32, 31, 62, 14);
        lidRemitente.setBounds(32, 64, 73, 14);
        lidBeneficiario.setBounds(32, 95, 89, 14);
        lPais.setBounds(32, 126, 78, 14);
        lFechaV.setBounds(32, 157, 70, 14);
        lHoraV.setBounds(32, 188, 64, 14);
        lMontoOrigen.setBounds(32, 219, 80, 14);
        lIdentificador.setBounds(10, 5, 200, 14);

        p0.add(lidRemesa);
        p0.add(lidRemitente);
        p0.add(lidBeneficiario);
        p0.add(lPais);
        p0.add(lFechaV);
        p0.add(lHoraV);
        p0.add(lMontoOrigen);
        p0.add(lIdentificador);

    }

    private void iniciarText() {
        tidRemesa = new JTextField();
        tFechaVenta = new JTextField();
        tHoraVenta = new JTextField();
        tMontoOrigen = new JTextField();

        tidRemesa.setBounds(140, 31, 131, 20);
        tFechaVenta.setBounds(140, 157, 131, 20);
        tHoraVenta.setBounds(140, 188, 131, 20);
        tMontoOrigen.setBounds(140, 219, 131, 20);

        tFechaVenta.setEditable(false);
        tFechaVenta.setOpaque(true);
        tFechaVenta.setBackground(Color.white);
        tHoraVenta.setEditable(false);
        tHoraVenta.setOpaque(true);
        tHoraVenta.setBackground(Color.white);
        tidRemesa.setEditable(false);
        tidRemesa.setOpaque(true);
        tidRemesa.setBackground(Color.white);

        noLetras(tMontoOrigen);

        p0.add(tidRemesa);
        p0.add(tFechaVenta);
        p0.add(tHoraVenta);
        p0.add(tMontoOrigen);

    }

    private void iniciarCombobox() {
        cidRemitente = new JComboBox();
        cidBeneficiario = new JComboBox();
        cPais = new JComboBox(paises);

        cidRemitente.setBounds(140, 64, 131, 20);
        cidBeneficiario.setBounds(140, 95, 131, 20);
        cPais.setBounds(140, 126, 131, 20);

        cidRemitente.setOpaque(true);
        cidRemitente.setBackground(Color.white);
        cidBeneficiario.setOpaque(true);
        cidBeneficiario.setBackground(Color.white);
        cPais.setOpaque(true);
        cPais.setBackground(Color.white);

        cidRemitente.addItem("");
        cidBeneficiario.addItem("");

        agregarIDs();

        cidRemitente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {

                for (int i = 0; i < moduloRemitentes.length; i++) {

                    if (moduloRemitentes[i].getiD() != null) {
                        if (cidRemitente.getSelectedItem().equals(moduloRemitentes[i].getiD())) {
                            JOptionPane.showMessageDialog(null, "Envia la Remesa " + moduloRemitentes[i].getNombre());
                            break;

                        }

                    }

                }
            }
        });

        cidBeneficiario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {

                for (int i = 0; i < moduloRemitentes.length; i++) {

                    if (moduloBeneficiarios[i].getiD() != null) {
                        if (cidBeneficiario.getSelectedItem().equals(moduloBeneficiarios[i].getiD())) {
                            JOptionPane.showMessageDialog(null, "Recibe la Remesa " + moduloBeneficiarios[i].getNombre());
                            break;

                        }

                    }

                }

            }
        });

        p0.add(cidRemitente);
        p0.add(cidBeneficiario);
        p0.add(cPais);

    }

    private void iniciarBotones() {
        bNuevaRemesa = new JButton("Nueva Remesa");
        bGuardarRemesa = new JButton("Guardar Remesa");
        bCancelarRemesa = new JButton("Cancelar Remesa");
        bRegresar = new JButton("Regresar");

        bNuevaRemesa.setMnemonic('n');
        bGuardarRemesa.setMnemonic('g');
        bCancelarRemesa.setMnemonic('c');
        bRegresar.setMnemonic('r');

        bNuevaRemesa.setBounds(290, 50, 135, 33);
        bGuardarRemesa.setBounds(290, 100, 135, 33);
        bCancelarRemesa.setBounds(290, 150, 135, 33);
        bRegresar.setBounds(290, 200, 135, 33);

        p0.add(bNuevaRemesa);
        p0.add(bGuardarRemesa);
        p0.add(bCancelarRemesa);
        p0.add(bRegresar);

    }

    private void inicarTabla() {
        modelo.setColumnCount(5);
        modelo.setColumnIdentifiers(titulosTabla);
        modelo.setRowCount(moduloRemesas.length);

        barra.setBounds(10, 260, 475, 130);

        barra.setViewportView(tDatosVentas);

        p0.add(barra);

    }

    private void iniciarEventos() {

        ActionListener eRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (auxTipoUsuario.equalsIgnoreCase("Administrador")) {
                    Proyecto1.Menu_Administrador_JF menu_Administrador_JF = new Proyecto1.Menu_Administrador_JF();
                    menu_Administrador_JF.setVisible(true);
                    dispose();

                } else if (auxTipoUsuario.equalsIgnoreCase("Vendedor")) {
                    Proyecto1.Menu_Vendedor_JF menu_Vendedor_JF = new Proyecto1.Menu_Vendedor_JF();
                    menu_Vendedor_JF.setVisible(true);
                    dispose();

                }

            }
        };
        bRegresar.addActionListener(eRegresar);

        ActionListener eGuardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eGuardarGuardar();
                eGuardarMostrar();

            }

            private void eGuardarGuardar() {
                for (int i = 0; i < moduloRemesas.length; i++) {

                    if (tMontoOrigen.getText().isEmpty()) {
                        System.out.println("No Envio Nada");
                        JOptionPane.showMessageDialog(null, "Ingrese un Monto");
                        break;
                    }

                    if (cidRemitente.getSelectedItem().equals("")) {
                        JOptionPane.showMessageDialog(null, "No Existe Remitente");
                        break;

                    }

                    if (cidBeneficiario.getSelectedItem().equals("")) {
                        JOptionPane.showMessageDialog(null, "No Existe Beneficiario");
                        break;

                    }

                    if (moduloRemesas[i].getIdRemesa() == null) {
                        System.out.println("Encontro Lugar");
                        idRemesaContador += 1;
                        tidRemesa.setText(String.valueOf(idRemesaContador));
                        tFechaVenta.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
                        tHoraVenta.setText(new SimpleDateFormat("hh:mm").format(fecha));
                        if (cPais.getSelectedItem().equals("Estados Unidos")) {
                            cambioMoneda = (Integer.parseInt(tMontoOrigen.getText())) / 8;
                            System.out.println("Cambio moneda a Dolares");
                        } else if (cPais.getSelectedItem().equals("España")) {
                            cambioMoneda = (Integer.parseInt(tMontoOrigen.getText())) / 10;
                            System.out.println("Cambio moneta a Euros");
                        }

                        moduloRemesas[i] = new Modulo_De_Remesas(tidRemesa.getText(), (String) cidRemitente.getSelectedItem(), (String) cidBeneficiario.getSelectedItem(), (String) cPais.getSelectedItem(), tFechaVenta.getText(), tHoraVenta.getText(), tMontoOrigen.getText(), String.valueOf(cambioMoneda), true, auxUsuario);
                        System.out.println("Lo agrego");
                        JOptionPane.showMessageDialog(null, "Nueva Remesa Agregada");

                        tidRemesa.setText("");
                        tMontoOrigen.setText("");
                        break;

                    }

                }
            }

        };
        bGuardarRemesa.addActionListener(eGuardar);

        ActionListener eNueva = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tMontoOrigen.setText("");
                tHoraVenta.setText("");
                tFechaVenta.setText("");
                cidRemitente.setSelectedItem("");
                cidBeneficiario.setSelectedItem("");

                eGuardarMostrar();

            }
        };
        bNuevaRemesa.addActionListener(eNueva);

        ActionListener eCancelarRemesa = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Cancelar_JF gestion_Cancelar_JF = new Modulos.Gestion_Cancelar_JF();
                gestion_Cancelar_JF.setVisible(true);
                dispose();

            }
        };
        bCancelarRemesa.addActionListener(eCancelarRemesa);

    }

    private void agregarIDs() {

        for (int i = 0; i < moduloRemitentes.length; i++) {
            if (moduloRemitentes[i].getiD() != null) {
                cidRemitente.addItem(moduloRemitentes[i].getiD());
            }
        }

        for (int i = 0; i < moduloBeneficiarios.length; i++) {
            if (moduloBeneficiarios[i].getiD() != null) {
                cidBeneficiario.addItem(moduloBeneficiarios[i].getiD());
            }
        }
    }

    private void eGuardarMostrar() {
        System.out.println("Muestra la Tabla");
        int aux = 0;
        String vigencia = "";

        for (int i = 0; i < (moduloRemesas.length); i++) {

            if (moduloRemesas[i].isVigente() == true) {
                vigencia = "Si";
            }

            if (moduloRemesas[i].getIdRemesa() != null) {

                for (int j = 0; j < moduloCancelar.length; j++) {
                    if (moduloRemesas[i].getIdRemesa().equals(moduloCancelar[j].getiD())) {
                        vigencia = "Cancelada";
                    }
                }
                for (int j = 0; j < moduloPagos.length; j++) {
                    if (moduloRemesas[i].getIdRemesa().equalsIgnoreCase(moduloPagos[j].getiD())) {
                        vigencia = "Pagada";
                    }
                }
                tDatosVentas.setValueAt(moduloRemesas[i].getIdRemitente(), aux, 0);
                tDatosVentas.setValueAt(moduloRemesas[i].getIdBeneficiario(), aux, 1);
                tDatosVentas.setValueAt(moduloRemesas[i].getPaisDestino(), aux, 2);
                tDatosVentas.setValueAt(moduloRemesas[i].getFechaVenta(), aux, 3);
                tDatosVentas.setValueAt(moduloRemesas[i].getMontoDestino(), aux, 4);
                tDatosVentas.setValueAt(vigencia, aux, 5);
                aux += 1;

            } else {
                aux = i;
            }

        }

    }

    public void noLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!Character.isDigit(c) && c != ',') {
                    ke.consume();

                }
            }
        });
    }

    public void noSignos(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (Character.isDigit(c)) {
                    ke.consume();
                }
            }
        });
    }

}
