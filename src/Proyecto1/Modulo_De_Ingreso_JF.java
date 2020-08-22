package Proyecto1;

import static Proyecto1.Boot.moduloIngreso;
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
import javax.swing.JTextField;

public class Modulo_De_Ingreso_JF extends JFrame {

//    public static Modulo_De_Ingreso moduloIngreso[] = new Modulo_De_Ingreso[10];
    JPanel panel;
    JLabel labelTUsuario, labelUsuario, labelContraseña, labelId;
    JComboBox comboTUsuario;
    String TUsuarios[] = {"Administrador", "Vendedor", "Pagador"};
    JTextField textUsuario, textContraseña;
    JButton botonAceptar, botonCancelar;
    public static String auxUsuario, auxTipoUsuario;

    public Modulo_De_Ingreso_JF() throws HeadlessException {
        setSize(560, 315);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Modulo de Igreso");
        setResizable(false);
//        moduloIngreso[0] = new Modulo_De_Ingreso("Administrador", "2015", "2015");

        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        iniciarPanel();
        compLabels();
        compCombobox();
        compText();
        compBotones();

    }

    private void iniciarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        this.getContentPane().add(panel);

    }

    private void compLabels() {
        labelTUsuario = new JLabel("Tipo de Usuario");
        labelUsuario = new JLabel("Usuario");
        labelContraseña = new JLabel("Contraseña");
        labelId = new JLabel("Raul Quiñonez :v");
        labelId.setBounds(400, 260, 110, 25);
        labelTUsuario.setBounds(48, 58, 150, 27);
        labelUsuario.setBounds(48, 106, 75, 27);
        labelContraseña.setBounds(48, 154, 110, 27);
        panel.setFocusable(true);
        labelId.setVisible(false);
        panel.add(labelId);
        panel.add(labelTUsuario);
        panel.add(labelUsuario);
        panel.add(labelContraseña);

    }

    private void compCombobox() {
        comboTUsuario = new JComboBox(TUsuarios);

        comboTUsuario.setBounds(224, 58, 301, 27);
        comboTUsuario.setOpaque(true);
        comboTUsuario.setBackground(Color.white);

        panel.add(comboTUsuario);

    }

    private void compText() {
        textUsuario = new JTextField();
        textContraseña = new JTextField();
        panel.add(textUsuario);
        panel.add(textContraseña);
        textUsuario.setBounds(198, 106, 327, 27);
        textContraseña.setBounds(198, 154, 327, 27);

    }

    private void compBotones() {
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        panel.add(botonAceptar);
        panel.add(botonCancelar);
        botonAceptar.setBounds(70, 207, 169, 40);
        botonCancelar.setBounds(285, 207, 169, 40);
        botonAceptar.setEnabled(true);
        botonCancelar.setEnabled(true);
        botonAceptar.setMnemonic('a');
        botonCancelar.setMnemonic('c');

    }

    private void iniciarEventos() {
        ActionListener aceptar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean entro = false;
                for (int i = 0; i < moduloIngreso.length && !entro; i++) {

                    if (moduloIngreso[i].gettUsuario() != null && moduloIngreso[i].getUsuario() != null && moduloIngreso[i].getContraseña() != null) {

                        if (comboTUsuario.getSelectedItem().equals(moduloIngreso[i].gettUsuario()) && textUsuario.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && textContraseña.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) {
                            System.out.println("entro");

                            if (comboTUsuario.getSelectedItem().equals(comboTUsuario.getItemAt(0))) {
                                Menu_Administrador_JF menu_Administrador = new Menu_Administrador_JF();
                                JOptionPane.showMessageDialog(null, "Bienvenido " + moduloIngreso[i].getUsuario() + " Como vas Prro" + " \nIniciando Session Como Administrador...");
                                auxUsuario = textUsuario.getText();
                                auxTipoUsuario = (String) comboTUsuario.getSelectedItem();
                                menu_Administrador.setVisible(true);
                                entro = true;
                                dispose();
                                break;

                            } else if (comboTUsuario.getSelectedItem().equals(comboTUsuario.getItemAt(1))) {
                                Menu_Vendedor_JF menu_Vendedor = new Menu_Vendedor_JF();
                                JOptionPane.showMessageDialog(null, "Bienvenido " + moduloIngreso[i].getUsuario() + " Como vas Prro" + " \nIniciando Session Como Vendedor...");
                                auxUsuario = textUsuario.getText();
                                auxTipoUsuario = (String) comboTUsuario.getSelectedItem();
                                menu_Vendedor.setVisible(true);
                                entro = true;
                                dispose();
                                break;
                            } else if (comboTUsuario.getSelectedItem().equals(comboTUsuario.getItemAt(2))) {
                                Menu_Pagador_JF menu_Pagador = new Menu_Pagador_JF();
                                JOptionPane.showMessageDialog(null, "Bienvenido " + moduloIngreso[i].getUsuario() + " Como vas Prro" + " \nIniciando Session Como Pagador...");
                                auxUsuario = textUsuario.getText();
                                auxTipoUsuario = (String) comboTUsuario.getSelectedItem();
                                menu_Pagador.setVisible(true);
                                entro = true;
                                dispose();
                                break;
                            }

                        }

                    }

                }
                if (!entro) {
                    JOptionPane.showMessageDialog(null, "Nel");
                    textUsuario.setText("");
                    textContraseña.setText("");

                }
            }
        };
        botonAceptar.addActionListener(aceptar);

        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Orale Manco");
                dispose();
            }
        };
        botonCancelar.addActionListener(cancelar);

        comboTUsuario.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                labelId.setVisible(false);
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyCode() == KeyEvent.VK_F12)) {
                    labelId.setVisible(true);
                }
            }

        });

    }

}
