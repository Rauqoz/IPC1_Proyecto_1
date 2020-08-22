package Modulos;

import static Proyecto1.Boot.moduloBeneficiarios;
import static Proyecto1.Boot.moduloRemitentes;
import static Proyecto1.Boot.moduloCancelar;
import static Proyecto1.Boot.moduloRemesas;
import Proyecto1.Modulo_De_CancelarR;
import static Proyecto1.Modulo_De_Ingreso_JF.auxUsuario;
import static Proyecto1.Modulo_De_Ingreso_JF.auxTipoUsuario;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gestion_Cancelar_JF extends JFrame {

    JPanel p0 = new JPanel(null);
    JLabel lidRemesa, lFechaCancelacion, lHoraCancelacion, lUsuarioCancela, lMotivo, lIdentificador;
    JComboBox cidRemesa;
    JTextField tFechaCancelacion, tHoraCancelacion, tUsuarioCancela;
    JTextArea tMotivo;
    JScrollPane sMotivo;
    JButton bCancelarRemesa, bRegresar;
    Date fecha = new Date();

    public Gestion_Cancelar_JF() throws HeadlessException {
        setTitle("Cancelar Remesas");
        setSize(510, 303);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        iniciaPanel();
        iniciarLabels();
        iniciarCombobox();
        iniciarText();
        iniciarTextArea();
        iniciarBotones();

    }

    private void iniciaPanel() {
        this.getContentPane().add(p0);
        p0.setBackground(Color.white);

    }

    private void iniciarLabels() {
        lIdentificador = new JLabel(auxTipoUsuario + " " + auxUsuario);
        lidRemesa = new JLabel("ID Remesa");
        lFechaCancelacion = new JLabel("Fecha Cancelacion");
        lHoraCancelacion = new JLabel("Hora Cancelacion");
        lUsuarioCancela = new JLabel("Usuario Cancela");
        lMotivo = new JLabel("Motivo");

        lIdentificador.setBounds(68, 20, 200, 20);
        lidRemesa.setBounds(34, 50, 110, 20);
        lFechaCancelacion.setBounds(34, 80, 110, 20);
        lHoraCancelacion.setBounds(34, 110, 110, 20);
        lUsuarioCancela.setBounds(34, 140, 110, 20);
        lMotivo.setBounds(34, 170, 110, 20);

        p0.add(lIdentificador);
        p0.add(lidRemesa);
        p0.add(lFechaCancelacion);
        p0.add(lHoraCancelacion);
        p0.add(lUsuarioCancela);
        p0.add(lMotivo);

    }

    private void iniciarCombobox() {
        cidRemesa = new JComboBox();

        cidRemesa.setBounds(180, 50, 160, 20);
        cidRemesa.setOpaque(true);
        cidRemesa.setBackground(Color.white);

        agregarIDs();

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
                        JOptionPane.showMessageDialog(null, "La Remesa la Envio " + laEnvia + "\nPara " + laRecibe + "\nPor " + moduloRemesas[i].getMontoDestino() + " " + moneda);
                        break;

                    }
                }

            }

        });

        p0.add(cidRemesa);

    }

    private void iniciarText() {
        tFechaCancelacion = new JTextField();
        tHoraCancelacion = new JTextField();
        tUsuarioCancela = new JTextField();

        tFechaCancelacion.setBounds(180, 80, 160, 20);
        tHoraCancelacion.setBounds(180, 110, 160, 20);
        tUsuarioCancela.setBounds(180, 140, 160, 20);

        tFechaCancelacion.setEditable(false);
        tHoraCancelacion.setEditable(false);
        tUsuarioCancela.setEditable(false);
        tFechaCancelacion.setOpaque(true);
        tFechaCancelacion.setBackground(Color.white);
        tHoraCancelacion.setOpaque(true);
        tHoraCancelacion.setBackground(Color.white);
        tUsuarioCancela.setOpaque(true);
        tUsuarioCancela.setBackground(Color.white);

        p0.add(tFechaCancelacion);
        p0.add(tHoraCancelacion);
        p0.add(tUsuarioCancela);

    }

    private void iniciarTextArea() {
        sMotivo = new JScrollPane();
        tMotivo = new JTextArea();
        tMotivo.setLineWrap(true);
        tMotivo.setWrapStyleWord(true);

        sMotivo.setBounds(180, 170, 160, 85);
        sMotivo.setViewportView(tMotivo);

        p0.add(sMotivo);

    }

    private void iniciarBotones() {
        bCancelarRemesa = new JButton("Cancelar Remesa");
        bRegresar = new JButton("Regresar");

        bCancelarRemesa.setBounds(355, 100, 135, 40);
        bRegresar.setBounds(355, 160, 135, 30);

        p0.add(bCancelarRemesa);
        p0.add(bRegresar);

    }

    private void iniciarEventos() {
        ActionListener eRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Ventas_JF gestion_Ventas_JF = new Modulos.Gestion_Ventas_JF();
                gestion_Ventas_JF.setVisible(true);
                dispose();

            }
        };
        bRegresar.addActionListener(eRegresar);

        ActionListener eCancelarRemesa = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eCancelarCancelar();

            }

            private void eCancelarCancelar() {
                for (int i = 0; i < moduloRemesas.length; i++) {

                    if (cidRemesa.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Esta Remesa no Existe");
                        break;
                    }
                    if (cidRemesa.getSelectedItem().equals(moduloRemesas[i].getIdRemesa()) && moduloRemesas[i].isVigente() == false) {
                        JOptionPane.showMessageDialog(null, "No esta Vigente");
                        break;

                    }

                    if (moduloRemesas[i].isVigente() == true) {
                        System.out.println("Busca las Vigentes");

                        if (cidRemesa.getSelectedItem().equals(moduloRemesas[i].getIdRemesa())) {
                            tFechaCancelacion.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
                            tHoraCancelacion.setText(new SimpleDateFormat("hh:mm").format(fecha));
                            tUsuarioCancela.setText(auxUsuario);
                            System.out.println("Cancelo la Remesa");
                            moduloRemesas[i].setVigente(false);
                            for (int j = 0; j < moduloCancelar.length; j++) {
                                if (moduloCancelar[j].getiD() == null) {
                                    moduloCancelar[j] = new Modulo_De_CancelarR(moduloRemesas[i].getIdRemesa(), tFechaCancelacion.getText(), tHoraCancelacion.getText(), tUsuarioCancela.getText(), tMotivo.getText());
                                    System.out.println(moduloCancelar[j].getFechaCancelar());
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Se Cancelo la Remesa");
                            tMotivo.setEditable(false);
                            break;
                        }
                    }

                }
                cidRemesa.setSelectedItem(null);
                agregarIDs();

            }
        };
        bCancelarRemesa.addActionListener(eCancelarRemesa);

    }

    private void agregarIDs() {
        cidRemesa.removeAllItems();
        for (int i = 0; i < moduloRemesas.length; i++) {
            if (moduloRemesas[i].getIdRemesa() != null && moduloRemesas[i].isVigente() == true) {
                cidRemesa.addItem(moduloRemesas[i].getIdRemesa());
            }
        }

    }

}
