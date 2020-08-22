package Modulos;

import static Proyecto1.Boot.moduloBeneficiarios;
import static Proyecto1.Boot.moduloPagos;
import static Proyecto1.Boot.moduloRemesas;
import static Proyecto1.Boot.moduloRemitentes;
import static Proyecto1.Modulo_De_Ingreso_JF.auxUsuario;
import static Proyecto1.Modulo_De_Ingreso_JF.auxTipoUsuario;
import Proyecto1.Modulo_De_Pago;
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
import javax.swing.JTextField;

public class Gestion_Pagos_JF extends JFrame {

    JPanel p0 = new JPanel(null);
    JLabel lidRemesa, lFechaPago, lHoraPago, lUsuarioPagador, lTipoCambio, lMontoDestino, lIdentificador;
    JTextField tFechaPago, tHoraPago, tUsuarioPagador, tTipoCambio, tMontoDestino;
    JComboBox cidRemesa;
    JButton bPagoRemesa, bCancelar;
    Date fecha = new Date();

    public Gestion_Pagos_JF() throws HeadlessException {
        setSize(475, 250);
        setTitle("Pago de Remesas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        iniciarPanel();
        iniciarLabels();
        iniciarText();
        iniciarCombobox();
        iniciarBotones();

    }

    private void iniciarPanel() {
        this.getContentPane().add(p0);
        p0.setBackground(Color.white);

    }

    private void iniciarLabels() {
        lidRemesa = new JLabel("ID Remesa");
        lFechaPago = new JLabel("Fecha Pago");
        lHoraPago = new JLabel("Hora Pago");
        lUsuarioPagador = new JLabel("Usuario Pagador");
        lTipoCambio = new JLabel("Tipo de Cambio");
        lMontoDestino = new JLabel("Monto Destino");
        lIdentificador = new JLabel(auxTipoUsuario + " " + auxUsuario);

        lidRemesa.setBounds(45, 50, 95, 14);
        lFechaPago.setBounds(45, 75, 95, 14);
        lHoraPago.setBounds(45, 100, 95, 14);
        lUsuarioPagador.setBounds(45, 125, 95, 14);
        lTipoCambio.setBounds(45, 150, 95, 14);
        lMontoDestino.setBounds(45, 175, 95, 14);
        lIdentificador.setBounds(70, 5, 200, 25);

        p0.add(lidRemesa);
        p0.add(lFechaPago);
        p0.add(lHoraPago);
        p0.add(lUsuarioPagador);
        p0.add(lTipoCambio);
        p0.add(lMontoDestino);
        p0.add(lIdentificador);

    }

    private void iniciarText() {
        tFechaPago = new JTextField();
        tHoraPago = new JTextField();
        tUsuarioPagador = new JTextField();
        tTipoCambio = new JTextField();
        tMontoDestino = new JTextField();

        tFechaPago.setEditable(false);
        tFechaPago.setOpaque(true);
        tFechaPago.setBackground(Color.white);
        tHoraPago.setEditable(false);
        tHoraPago.setOpaque(true);
        tHoraPago.setBackground(Color.white);
        tTipoCambio.setEditable(false);
        tTipoCambio.setOpaque(true);
        tTipoCambio.setBackground(Color.white);
        tUsuarioPagador.setEditable(false);
        tUsuarioPagador.setOpaque(true);
        tUsuarioPagador.setBackground(Color.white);
        tMontoDestino.setEditable(false);
        tMontoDestino.setOpaque(true);
        tMontoDestino.setBackground(Color.white);

        tFechaPago.setBounds(170, 78, 130, 20);
        tHoraPago.setBounds(170, 100, 130, 20);
        tUsuarioPagador.setBounds(170, 125, 130, 20);
        tTipoCambio.setBounds(170, 150, 130, 20);
        tMontoDestino.setBounds(170, 175, 130, 20);

        p0.add(tFechaPago);
        p0.add(tHoraPago);
        p0.add(tUsuarioPagador);
        p0.add(tTipoCambio);
        p0.add(tMontoDestino);

    }

    private void iniciarCombobox() {
        cidRemesa = new JComboBox();

        cidRemesa.setBounds(170, 50, 130, 20);

        llenarIDs();

        cidRemesa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                String moneda = "";
                String laEnvia = "";
                String laRecibe = "";
                for (int i = 0; i < moduloRemesas.length; i++) {
                    System.out.println("Busca la Remesa");
                    if (moduloRemesas[i].getIdRemesa() != null && cidRemesa.getSelectedItem().equals(moduloRemesas[i].getIdRemesa())) {
                        System.out.println("Encuentra la Remesa");

                        if (moduloRemesas[i].getPaisDestino().equalsIgnoreCase("Estados Unidos")) {
                            moneda = "Dolares";
                            System.out.println("Fue en Dolares");
                        } else if (moduloRemesas[i].getPaisDestino().equalsIgnoreCase("España")) {
                            moneda = "Euros";
                            System.out.println("Fue en Euros");
                        }

                        for (int j = 0; j < moduloBeneficiarios.length; j++) {
                            if (moduloRemesas[i].getIdBeneficiario().equals(moduloBeneficiarios[j].getiD())) {
                                laRecibe = moduloBeneficiarios[j].getNombre();
                            }
                        }
                        for (int j = 0; j < moduloRemitentes.length; j++) {
                            if (moduloRemesas[i].getIdRemitente().equalsIgnoreCase(moduloRemitentes[j].getiD())) {
                                laEnvia = moduloRemitentes[j].getNombre();
                            }
                        }
                        JOptionPane.showMessageDialog(null, "La Remesa la Envio " + laEnvia + "\nPara " + laRecibe + "\nPor " + moduloRemesas[i].getMontoDestino() + " " + moneda + "\nNo ha Sido Pagada");
                        break;

                    }
                }

            }
        });

        p0.add(cidRemesa);

    }

    private void iniciarBotones() {
        bPagoRemesa = new JButton("Pago Remesa");
        bCancelar = new JButton("Regresar");

        bPagoRemesa.setMnemonic('p');
        bCancelar.setMnemonic('r');

        bPagoRemesa.setBounds(325, 80, 120, 40);
        bCancelar.setBounds(325, 135, 120, 40);

        p0.add(bPagoRemesa);
        p0.add(bCancelar);

    }

    private void iniciarEventos() {

        ActionListener eCancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (auxTipoUsuario.equalsIgnoreCase("Administrador")) {
                    Proyecto1.Menu_Administrador_JF menu_Administrador_JF = new Proyecto1.Menu_Administrador_JF();
                    menu_Administrador_JF.setVisible(true);
                    dispose();

                } else if (auxTipoUsuario.equalsIgnoreCase("Pagador")) {
                    Proyecto1.Menu_Pagador_JF menu_Pagador_JF = new Proyecto1.Menu_Pagador_JF();
                    menu_Pagador_JF.setVisible(true);
                    dispose();

                }

            }
        };
        bCancelar.addActionListener(eCancelar);

        ActionListener ePago = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                ePagoPago();

            }

            private void ePagoPago() {
                for (int i = 0; i < moduloRemesas.length; i++) {

                    if (moduloRemesas[i].isVigente() == true) {
                        System.out.println("Busca las Vigentes");

                        if (cidRemesa.getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(null, "Esta Remesa no Existe");
                            break;
                        }
                        if (cidRemesa.getSelectedItem().equals(moduloRemesas[i].getIdRemesa())) {
                            tFechaPago.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
                            tHoraPago.setText(new SimpleDateFormat("hh:mm").format(fecha));
                            tUsuarioPagador.setText(auxUsuario);
                            tMontoDestino.setText(moduloRemesas[i].getMontoDestino());
                            moduloRemesas[i].setVigente(false);
                            if (moduloRemesas[i].getPaisDestino().equalsIgnoreCase("Estados Unidos")) {
                                tTipoCambio.setText("8,00");
                            }
                            if (moduloRemesas[i].getPaisDestino().equalsIgnoreCase("España")) {
                                tTipoCambio.setText("10,00");
                            }
                            System.out.println("Pago la Remesa");
                            for (int j = 0; j < moduloPagos.length; j++) {
                                if (moduloPagos[j].getiD() == null) {
                                    moduloPagos[j] = new Modulo_De_Pago(moduloRemesas[i].getIdRemesa(), tFechaPago.getText(), tHoraPago.getText(), tUsuarioPagador.getText(), tTipoCambio.getText(), tMontoDestino.getText());
                                    System.out.println(moduloPagos[j].getFechaPago());
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Se Pago la Remesa");
                            break;
                        }
                    }

                }
                cidRemesa.setSelectedItem(null);
                llenarIDs();
            }
        };
        bPagoRemesa.addActionListener(ePago);

    }

    public void noLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (Character.isLetter(c)) {
                    ke.consume();

                }
            }
        });
    }

    private void llenarIDs() {
        cidRemesa.removeAllItems();
        for (int i = 0; i < moduloRemesas.length; i++) {
            if (moduloRemesas[i].getIdRemesa() != null && moduloRemesas[i].isVigente() == true) {
                cidRemesa.addItem(moduloRemesas[i].getIdRemesa());
            }
        }

    }

}
