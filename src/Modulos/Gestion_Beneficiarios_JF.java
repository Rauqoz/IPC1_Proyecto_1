package Modulos;

import static Proyecto1.Boot.moduloBeneficiarios;
import static Proyecto1.Boot.idBContador;
import Proyecto1.Modulo_De_Fecha;
import static Proyecto1.Modulo_De_Ingreso_JF.auxTipoUsuario;
import static Proyecto1.Modulo_De_Ingreso_JF.auxUsuario;
import Proyecto1.Modulo_De_RyB;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Gestion_Beneficiarios_JF extends JFrame {

    JPanel p0, pNuevoBeneficiario, pModificarBeneficiario;
    JTabbedPane pestañas;
    JLabel lIdentificador, lidBeneficiario, lNombreBeneficiario, lFechaNacimiento, lDireccion, lTelefono, lMail;
    JButton bRegresar, bNB_Crear, bMB_Modificar;
    JScrollPane barraNB = new JScrollPane();
    JScrollPane barraMB = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tDatosNuevoBeneficiario = new JTable(modelo);
    JTable tDatosModificarBeneficiario = new JTable(modelo);
    String[] titulosTabla = {"ID Beneficiario", "Nombre", "Telefono"};
    JTextField tNB_idBeneficiario, tNB_Nombre, tNB_Direccion, tNB_Telefono, tNB_Mail, tMB_Nombre, tMB_Direccion, tMB_Telefono, tMB_Mail;
    JComboBox cMB_idBeneficiario = new JComboBox();
    JComboBox cDiaNB = new JComboBox();
    JComboBox cDiaMB = new JComboBox();
    JComboBox cMesNB = new JComboBox();
    JComboBox cMesMB = new JComboBox();
    JComboBox cAñoNB = new JComboBox();
    JComboBox cAñoMB = new JComboBox();

    public Gestion_Beneficiarios_JF() throws HeadlessException {
        setSize(510, 450);
        setTitle("Modulo de Beneficiarios");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        tDatosNuevoBeneficiario.setEnabled(false);
        tDatosModificarBeneficiario.setEnabled(false);

        modelo.setColumnCount(3);
        modelo.setColumnIdentifiers(titulosTabla);
        modelo.setRowCount(moduloBeneficiarios.length);
        cMB_idBeneficiario.addItem("");

        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        iniciarPanel();
        iniciarPestañas();
        inicarLabels();
        iniciarBotones();

    }

    private void iniciarPanel() {
        p0 = new JPanel(null);
        this.getContentPane().add(p0);
        p0.setBackground(Color.white);

    }

    private void iniciarPestañas() {
        pestañas = new JTabbedPane();

        pModificarBeneficiario = new JPanel(null);
        pModificarBeneficiario.setBackground(Color.white);
        pNuevoBeneficiario = new JPanel(null);
        pNuevoBeneficiario.setBackground(Color.white);

        pestañas.setBounds(15, 50, 475, 360);

        pestañas.addTab("Nuevo Beneficiario", pNuevoBeneficiario);
        pestañas.addTab("Modificar Beneficiario", pModificarBeneficiario);

        p0.add(pestañas);

        iniciarPanelNuevoBeneficiario();
        iniciarPanelModificarBeneficiario();

    }

    private void iniciarPanelNuevoBeneficiario() {
        lidBeneficiario = new JLabel("ID Beneficiario");
        lNombreBeneficiario = new JLabel("Nombre");
        lFechaNacimiento = new JLabel("Fecha de Nacimiento");
        lDireccion = new JLabel("Direccion");
        lTelefono = new JLabel("Telefono");
        lMail = new JLabel("Mail");

        tNB_idBeneficiario = new JTextField();
        tNB_Nombre = new JTextField();
        tNB_Direccion = new JTextField();
        tNB_Telefono = new JTextField();
        tNB_Mail = new JTextField();

        bNB_Crear = new JButton("Crear");

        tNB_idBeneficiario.setEditable(false);
        tNB_idBeneficiario.setOpaque(true);
        tNB_idBeneficiario.setBackground(Color.white);

        for (int i = 1970; i <= 2000; i++) {
            cAñoNB.addItem(String.valueOf(i));

        }
        cMesNB.addItem("ENE");
        cMesNB.addItem("FEB");
        cMesNB.addItem("MAR");
        cMesNB.addItem("ABR");
        cMesNB.addItem("MAY");
        cMesNB.addItem("JUN");
        cMesNB.addItem("JUL");
        cMesNB.addItem("AGO");
        cMesNB.addItem("SEP");
        cMesNB.addItem("OCT");
        cMesNB.addItem("NOV");
        cMesNB.addItem("DIC");

        bNB_Crear.setBounds(340, 15, 100, 30);

        lidBeneficiario.setBounds(30, 25, 120, 20);
        tNB_idBeneficiario.setBounds(180, 25, 120, 20);

        lNombreBeneficiario.setBounds(30, 55, 120, 20);
        tNB_Nombre.setBounds(180, 55, 250, 20);

        lFechaNacimiento.setBounds(30, 85, 120, 20);
        cAñoNB.setBounds(180, 85, 70, 20);
        cMesNB.setBounds(250, 85, 60, 20);
        cDiaNB.setBounds(310, 85, 50, 20);
        //  tNB_FechaNacimiento.setBounds(180, 85, 120, 20);

        lDireccion.setBounds(30, 115, 120, 20);
        tNB_Direccion.setBounds(180, 115, 250, 20);

        lTelefono.setBounds(30, 145, 120, 20);
        tNB_Telefono.setBounds(180, 145, 120, 20);

        lMail.setBounds(30, 175, 120, 20);
        tNB_Mail.setBounds(180, 175, 250, 20);

        barraNB.setBounds(10, 210, 450, 115);

        pNuevoBeneficiario.add(bNB_Crear);

        pNuevoBeneficiario.add(lidBeneficiario);
        pNuevoBeneficiario.add(tNB_idBeneficiario);

        pNuevoBeneficiario.add(lNombreBeneficiario);
        pNuevoBeneficiario.add(tNB_Nombre);

        pNuevoBeneficiario.add(lFechaNacimiento);
        pNuevoBeneficiario.add(cDiaNB);
        pNuevoBeneficiario.add(cMesNB);
        pNuevoBeneficiario.add(cAñoNB);
        //  pNuevoBeneficiario.add(tNB_FechaNacimiento);

        pNuevoBeneficiario.add(lDireccion);
        pNuevoBeneficiario.add(tNB_Direccion);

        pNuevoBeneficiario.add(lTelefono);
        pNuevoBeneficiario.add(tNB_Telefono);

        pNuevoBeneficiario.add(lMail);
        pNuevoBeneficiario.add(tNB_Mail);

        pNuevoBeneficiario.add(barraNB);

        noLetras(tNB_Telefono);

        cMesNB.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cMesNB.getSelectedItem().equals("ENE") || cMesNB.getSelectedItem().equals("MAR") || cMesNB.getSelectedItem().equals("MAY") || cMesNB.getSelectedItem().equals("JUL") || cMesNB.getSelectedItem().equals("AGO") || cMesNB.getSelectedItem().equals("OCT") || cMesNB.getSelectedItem().equals("DIC")) {
                    cDiaNB.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaNB.addItem(i);
                    }
                }
                if (cMesNB.getSelectedItem().equals("ABR") || cMesNB.getSelectedItem().equals("JUN") || cMesNB.getSelectedItem().equals("SEP") || cMesNB.getSelectedItem().equals("NOV")) {
                    cDiaNB.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaNB.addItem(i);
                    }
                }
                if (cMesNB.getSelectedItem().equals("FEB")) {
                    cDiaNB.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cDiaNB.addItem(i);
                    }
                }

            }
        });

        barraNB.setViewportView(tDatosNuevoBeneficiario);

    }

    private void iniciarPanelModificarBeneficiario() {
        lidBeneficiario = new JLabel("ID Beneficiario");
        lNombreBeneficiario = new JLabel("Nombre");
        lFechaNacimiento = new JLabel("Fecha de Nacimiento");
        lDireccion = new JLabel("Direccion");
        lTelefono = new JLabel("Telefono");
        lMail = new JLabel("Mail");

        tMB_Nombre = new JTextField();
        tMB_Direccion = new JTextField();
        tMB_Telefono = new JTextField();
        tMB_Mail = new JTextField();

        cMB_idBeneficiario.setOpaque(true);
        cMB_idBeneficiario.setBackground(Color.white);

        for (int i = 1970; i <= 2000; i++) {
            cAñoMB.addItem(String.valueOf(i));

        }
        cMesMB.addItem("ENE");
        cMesMB.addItem("FEB");
        cMesMB.addItem("MAR");
        cMesMB.addItem("ABR");
        cMesMB.addItem("MAY");
        cMesMB.addItem("JUN");
        cMesMB.addItem("JUL");
        cMesMB.addItem("AGO");
        cMesMB.addItem("SEP");
        cMesMB.addItem("OCT");
        cMesMB.addItem("NOV");
        cMesMB.addItem("DIC");

        bMB_Modificar = new JButton("Modificar");

        bMB_Modificar.setBounds(340, 15, 100, 30);

        lidBeneficiario.setBounds(30, 25, 120, 20);
        cMB_idBeneficiario.setBounds(180, 25, 120, 20);

        lNombreBeneficiario.setBounds(30, 55, 120, 20);
        tMB_Nombre.setBounds(180, 55, 250, 20);

        lFechaNacimiento.setBounds(30, 85, 120, 20);
        cAñoMB.setBounds(180, 85, 70, 20);
        cMesMB.setBounds(250, 85, 60, 20);
        cDiaMB.setBounds(310, 85, 50, 20);

        lDireccion.setBounds(30, 115, 120, 20);
        tMB_Direccion.setBounds(180, 115, 250, 20);

        lTelefono.setBounds(30, 145, 120, 20);
        tMB_Telefono.setBounds(180, 145, 120, 20);

        lMail.setBounds(30, 175, 120, 20);
        tMB_Mail.setBounds(180, 175, 250, 20);

        barraMB.setBounds(10, 210, 450, 115);

        pModificarBeneficiario.add(bMB_Modificar);

        pModificarBeneficiario.add(lidBeneficiario);
        pModificarBeneficiario.add(cMB_idBeneficiario);

        pModificarBeneficiario.add(lNombreBeneficiario);
        pModificarBeneficiario.add(tMB_Nombre);

        pModificarBeneficiario.add(lFechaNacimiento);
        pModificarBeneficiario.add(cDiaMB);
        pModificarBeneficiario.add(cMesMB);
        pModificarBeneficiario.add(cAñoMB);

        pModificarBeneficiario.add(lDireccion);
        pModificarBeneficiario.add(tMB_Direccion);

        pModificarBeneficiario.add(lTelefono);
        pModificarBeneficiario.add(tMB_Telefono);

        pModificarBeneficiario.add(lMail);
        pModificarBeneficiario.add(tMB_Mail);

        pModificarBeneficiario.add(barraMB);

        barraMB.setViewportView(tDatosModificarBeneficiario);

        noLetras(tMB_Telefono);

        cMesMB.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cMesMB.getSelectedItem().equals("ENE") || cMesMB.getSelectedItem().equals("MAR") || cMesMB.getSelectedItem().equals("MAY") || cMesMB.getSelectedItem().equals("JUL") || cMesMB.getSelectedItem().equals("AGO") || cMesMB.getSelectedItem().equals("OCT") || cMesMB.getSelectedItem().equals("DIC")) {
                    cDiaMB.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaMB.addItem(i);
                    }
                }
                if (cMesMB.getSelectedItem().equals("ABR") || cMesMB.getSelectedItem().equals("JUN") || cMesMB.getSelectedItem().equals("SEP") || cMesMB.getSelectedItem().equals("NOV")) {
                    cDiaMB.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaMB.addItem(i);
                    }
                }
                if (cMesMB.getSelectedItem().equals("FEB")) {
                    cDiaMB.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cDiaMB.addItem(i);
                    }
                }

            }
        });

        llenarIDs();

        cMB_idBeneficiario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {

                if (cMB_idBeneficiario.getSelectedItem().equals("")) {
                    tMB_Nombre.setText("");
                    tMB_Direccion.setText("");
                    tMB_Telefono.setText("");
                    tMB_Mail.setText("");
                    cDiaMB.removeAllItems();

                }

                for (int i = 0; i < moduloBeneficiarios.length; i++) {

                    if (moduloBeneficiarios[i].getiD() != null && moduloBeneficiarios[i].getNombre() != null && moduloBeneficiarios[i].getFechaNac().getDia() != null && moduloBeneficiarios[i].getDireccion() != null && moduloBeneficiarios[i].getTelefono() != null && moduloBeneficiarios[i].getMail() != null) {
                        if (cMB_idBeneficiario.getSelectedItem().equals(moduloBeneficiarios[i].getiD())) {
                            tMB_Nombre.setText(moduloBeneficiarios[i].getNombre());
                            cAñoMB.setSelectedItem(moduloBeneficiarios[i].getFechaNac().getAño());
                            cMesMB.setSelectedItem(moduloBeneficiarios[i].getFechaNac().getMes());
                            cDiaMB.removeAllItems();
                            cDiaMB.addItem(moduloBeneficiarios[i].getFechaNac().getDia());
                            cDiaMB.setSelectedItem(moduloBeneficiarios[i].getFechaNac().getDia());
                            tMB_Direccion.setText(moduloBeneficiarios[i].getDireccion());
                            tMB_Telefono.setText(moduloBeneficiarios[i].getTelefono());
                            tMB_Mail.setText(moduloBeneficiarios[i].getMail());
                            break;
                        }

                    }

                }
            }

        });

    }

    private void inicarLabels() {
        lIdentificador = new JLabel(auxTipoUsuario + " " + auxUsuario);

        lIdentificador.setBounds(68, 15, 200, 25);

        p0.add(lIdentificador);

    }

    private void iniciarBotones() {
        bRegresar = new JButton("Regresar");

        bRegresar.setBounds(380, 15, 100, 30);

        p0.add(bRegresar);

    }

    private void iniciarEventos() {
        ActionListener eRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Proyecto1.Menu_Vendedor_JF menu_Vendedor_JF = new Proyecto1.Menu_Vendedor_JF();
                menu_Vendedor_JF.setVisible(true);
                dispose();

            }
        };
        bRegresar.addActionListener(eRegresar);

        ActionListener eCrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eCrearCrear();
                eCrearMostrar();

            }

            private void eCrearCrear() {
                for (int i = 0; i < moduloBeneficiarios.length; i++) {

                    if (tNB_Nombre.getText().isEmpty() && cDiaNB.getSelectedItem() == null && tNB_Direccion.getText().isEmpty() && tNB_Telefono.getText().isEmpty() && tNB_Mail.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los Campos Vacios");
                        System.out.println("Todo Vacio");
                        break;

                    }
                    if (tNB_Nombre.getText().isEmpty() || cDiaNB.getSelectedItem() == null || tNB_Direccion.getText().isEmpty() || tNB_Telefono.getText().isEmpty() || tNB_Mail.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Faltan Campos por Llenar");
                        System.out.println("Faltan Campos");
                        break;

                    }

                    if (moduloBeneficiarios[i].getiD() == null) {
                        System.out.println("Encontro Espacio");
                        idBContador += 1;
                        tNB_idBeneficiario.setText(String.valueOf(idBContador));
                        System.out.println("Aumenta el ID");
                        moduloBeneficiarios[i] = new Modulo_De_RyB(tNB_idBeneficiario.getText(), tNB_Nombre.getText(), new Modulo_De_Fecha(cDiaNB.getSelectedItem().toString(), cMesNB.getSelectedItem().toString(), cAñoNB.getSelectedItem().toString()), tNB_Direccion.getText(), tNB_Telefono.getText(), tNB_Mail.getText());

                        System.out.println("Lo Agrego");
                        JOptionPane.showMessageDialog(null, "Nuevo Beneficiario Agregado");

                        System.out.println("Limpiar Cajas");
                        tNB_idBeneficiario.setText("");
                        tNB_Nombre.setText("");
                        tNB_Direccion.setText("");
                        tNB_Telefono.setText("");
                        tNB_Mail.setText("");

                        cDiaNB.removeAllItems();
                        llenarIDs();

                        break;

                    }

                    if (moduloBeneficiarios[i].getiD() != null && moduloBeneficiarios[i].getNombre().equalsIgnoreCase(tNB_Nombre.getText())) {
                        JOptionPane.showMessageDialog(null, "Este Beneficiario ya Existe");
                        System.out.println("Beneficiario ya existe");

                        System.out.println("Limpiar Cajas");
                        tNB_idBeneficiario.setText("");
                        tNB_Nombre.setText("");
                        tNB_Direccion.setText("");
                        tNB_Telefono.setText("");
                        tNB_Mail.setText("");
                        cDiaNB.removeAllItems();
                        break;

                    }

                }
            }

            private void eCrearMostrar() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloBeneficiarios.length); i++) {

                    if (moduloBeneficiarios[i].getiD() != null) {
                        tDatosNuevoBeneficiario.setValueAt(moduloBeneficiarios[i].getiD(), aux, 0);
                        tDatosNuevoBeneficiario.setValueAt(moduloBeneficiarios[i].getNombre(), aux, 1);
                        tDatosNuevoBeneficiario.setValueAt(moduloBeneficiarios[i].getTelefono(), aux, 2);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }

            }

        };
        bNB_Crear.addActionListener(eCrear);

        ActionListener eModificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eModificarModificar();
                eModificarMostrar();

            }

            private void eModificarModificar() {

                for (int i = 0; i < moduloBeneficiarios.length; i++) {

                    if (cMB_idBeneficiario.getSelectedItem().equals("")) {
                        JOptionPane.showMessageDialog(null, "No Se Puede editar este Beneficiario");
                        tMB_Direccion.setText("");
                        tMB_Mail.setText("");
                        tMB_Nombre.setText("");
                        tMB_Telefono.setText("");
                        cMB_idBeneficiario.setSelectedItem("");
                        System.out.println("En Blanco");
                        break;

                    }

                    if (moduloBeneficiarios[i].getiD() != null && moduloBeneficiarios[i].getiD().equalsIgnoreCase((String) cMB_idBeneficiario.getSelectedItem())) {
                        System.out.println("Encontro el ID");

                        if (tMB_Nombre.getText().isEmpty() || cDiaMB.getSelectedItem().equals("") || tMB_Direccion.getText().isEmpty() || tMB_Telefono.getText().isEmpty() || tMB_Mail.getText().isEmpty()) {
                            System.out.println("No Espacios Vacios para Guardar");
                            JOptionPane.showMessageDialog(null, "No Pueden Guardarse Esapcios en Blanco");
                            break;

                        }

                        moduloBeneficiarios[i].setTelefono(tMB_Telefono.getText());
                        moduloBeneficiarios[i].setDireccion(tMB_Direccion.getText());
                        moduloBeneficiarios[i].setFechaNac(new Modulo_De_Fecha(cDiaMB.getSelectedItem().toString(), cMesMB.getSelectedItem().toString(), cAñoMB.getSelectedItem().toString()));
                        moduloBeneficiarios[i].setNombre(tMB_Nombre.getText());
                        moduloBeneficiarios[i].setMail(tMB_Mail.getText());
                        System.out.println("Modifico");
                        JOptionPane.showMessageDialog(null, "Modificado");

                        tMB_Direccion.setText("");
                        tMB_Mail.setText("");
                        tMB_Nombre.setText("");
                        tMB_Telefono.setText("");
                        cMB_idBeneficiario.setSelectedItem("");
                        cDiaMB.removeAllItems();
                        break;

                    }

                }
            }

            private void eModificarMostrar() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloBeneficiarios.length); i++) {

                    if (moduloBeneficiarios[i].getiD() != null) {
                        tDatosModificarBeneficiario.setValueAt(moduloBeneficiarios[i].getiD(), aux, 0);
                        tDatosModificarBeneficiario.setValueAt(moduloBeneficiarios[i].getNombre(), aux, 1);
                        tDatosModificarBeneficiario.setValueAt(moduloBeneficiarios[i].getTelefono(), aux, 2);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }
            }
        };
        bMB_Modificar.addActionListener(eModificar);

    }

    public void noLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!Character.isDigit(c) && c != '-') {
                    ke.consume();

                }
            }
        });
    }

    private void llenarIDs() {

        for (int i = 0; i < moduloBeneficiarios.length; i++) {
            if (moduloBeneficiarios[i].getiD() != null) {
                cMB_idBeneficiario.addItem(moduloBeneficiarios[i].getiD());

            }
        }

    }

}
