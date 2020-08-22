package Modulos;

import static Proyecto1.Boot.moduloRemitentes;
import static Proyecto1.Boot.idRContador;
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

public class Gestion_Remitentes_JF extends JFrame {

    JPanel p0, pNuevoRemitente, pModificarRemitente;
    JTabbedPane pestañas;
    JLabel lIdentificador, lidRemitente, lNombreRemitente, lFechaNacimiento, lDireccion, lTelefono, lMail;
    JButton bRegresar, bNR_Crear, bMR_Modificar;
    JScrollPane barraNR = new JScrollPane();
    JScrollPane barraMR = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tDatosNuevoRemitente = new JTable(modelo);
    JTable tDatosModificarRemitentes = new JTable(modelo);
    String[] titulosTabla = {"ID Remitente", "Nombre", "Telefono"};
    JTextField tNR_idRemitente, tNR_Nombre, tNR_Direccion, tNR_Telefono, tNR_Mail, tMR_Nombre, tMR_Direccion, tMR_Telefono, tMR_Mail;
    JComboBox cMR_idRemitente = new JComboBox();
    JComboBox cDiaNR = new JComboBox();
    JComboBox cDiaMR = new JComboBox();
    JComboBox cMesNR = new JComboBox();
    JComboBox cMesMR = new JComboBox();
    JComboBox cAñoNR = new JComboBox();
    JComboBox cAñoMR = new JComboBox();

    public Gestion_Remitentes_JF() throws HeadlessException {
        setSize(510, 450);
        setTitle("Modulo de Remitentes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        tDatosNuevoRemitente.setEnabled(false);
        tDatosModificarRemitentes.setEnabled(false);

        modelo.setColumnCount(3);
        modelo.setColumnIdentifiers(titulosTabla);
        modelo.setRowCount(moduloRemitentes.length);
        cMR_idRemitente.addItem("");

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

        pModificarRemitente = new JPanel(null);
        pModificarRemitente.setBackground(Color.white);
        pNuevoRemitente = new JPanel(null);
        pNuevoRemitente.setBackground(Color.white);

        pestañas.setBounds(15, 50, 475, 360);

        pestañas.addTab("Nuevo Remitente", pNuevoRemitente);
        pestañas.addTab("Modificar Remitente", pModificarRemitente);

        p0.add(pestañas);

        iniciarPanelNuevoRemitente();
        iniciarPanelModificarRemitente();

    }

    private void iniciarPanelNuevoRemitente() {
        lidRemitente = new JLabel("ID Remitente");
        lNombreRemitente = new JLabel("Nombre");
        lFechaNacimiento = new JLabel("Fecha de Nacimiento");
        lDireccion = new JLabel("Direccion");
        lTelefono = new JLabel("Telefono");
        lMail = new JLabel("Mail");

        tNR_idRemitente = new JTextField();
        tNR_Nombre = new JTextField();
        tNR_Direccion = new JTextField();
        tNR_Telefono = new JTextField();
        tNR_Mail = new JTextField();

        bNR_Crear = new JButton("Crear");

        tNR_idRemitente.setEditable(false);
        tNR_idRemitente.setOpaque(true);
        tNR_idRemitente.setBackground(Color.white);

        for (int i = 1970; i <= 2000; i++) {
            cAñoNR.addItem(String.valueOf(i));

        }
        cMesNR.addItem("ENE");
        cMesNR.addItem("FEB");
        cMesNR.addItem("MAR");
        cMesNR.addItem("ABR");
        cMesNR.addItem("MAY");
        cMesNR.addItem("JUN");
        cMesNR.addItem("JUL");
        cMesNR.addItem("AGO");
        cMesNR.addItem("SEP");
        cMesNR.addItem("OCT");
        cMesNR.addItem("NOV");
        cMesNR.addItem("DIC");

        bNR_Crear.setBounds(340, 15, 100, 30);

        lidRemitente.setBounds(30, 25, 120, 20);
        tNR_idRemitente.setBounds(180, 25, 120, 20);

        lNombreRemitente.setBounds(30, 55, 120, 20);
        tNR_Nombre.setBounds(180, 55, 250, 20);

        lFechaNacimiento.setBounds(30, 85, 120, 20);
        cAñoNR.setBounds(180, 85, 70, 20);
        cMesNR.setBounds(250, 85, 60, 20);
        cDiaNR.setBounds(310, 85, 50, 20);
//        tNR_FechaNacimiento.setBounds(180, 85, 120, 20);

        lDireccion.setBounds(30, 115, 120, 20);
        tNR_Direccion.setBounds(180, 115, 250, 20);

        lTelefono.setBounds(30, 145, 120, 20);
        tNR_Telefono.setBounds(180, 145, 120, 20);

        lMail.setBounds(30, 175, 120, 20);
        tNR_Mail.setBounds(180, 175, 250, 20);

        barraNR.setBounds(10, 210, 450, 115);

        pNuevoRemitente.add(bNR_Crear);

        pNuevoRemitente.add(lidRemitente);
        pNuevoRemitente.add(tNR_idRemitente);

        pNuevoRemitente.add(lNombreRemitente);
        pNuevoRemitente.add(tNR_Nombre);

        pNuevoRemitente.add(lFechaNacimiento);
        pNuevoRemitente.add(cDiaNR);
        pNuevoRemitente.add(cMesNR);
        pNuevoRemitente.add(cAñoNR);
//        pNuevoRemitente.add(tNR_FechaNacimiento);

        pNuevoRemitente.add(lDireccion);
        pNuevoRemitente.add(tNR_Direccion);

        pNuevoRemitente.add(lTelefono);
        pNuevoRemitente.add(tNR_Telefono);

        pNuevoRemitente.add(lMail);
        pNuevoRemitente.add(tNR_Mail);

        pNuevoRemitente.add(barraNR);

        noLetras(tNR_Telefono);

        cMesNR.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cMesNR.getSelectedItem().equals("ENE") || cMesNR.getSelectedItem().equals("MAR") || cMesNR.getSelectedItem().equals("MAY") || cMesNR.getSelectedItem().equals("JUL") || cMesNR.getSelectedItem().equals("AGO") || cMesNR.getSelectedItem().equals("OCT") || cMesNR.getSelectedItem().equals("DIC")) {
                    cDiaNR.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaNR.addItem(i);
                    }
                }
                if (cMesNR.getSelectedItem().equals("ABR") || cMesNR.getSelectedItem().equals("JUN") || cMesNR.getSelectedItem().equals("SEP") || cMesNR.getSelectedItem().equals("NOV")) {
                    cDiaNR.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaNR.addItem(i);
                    }
                }
                if (cMesNR.getSelectedItem().equals("FEB")) {
                    cDiaNR.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cDiaNR.addItem(i);
                    }
                }

            }
        });

        barraNR.setViewportView(tDatosNuevoRemitente);

    }

    private void iniciarPanelModificarRemitente() {
        lidRemitente = new JLabel("ID Remitente");
        lNombreRemitente = new JLabel("Nombre");
        lFechaNacimiento = new JLabel("Fecha de Nacimiento");
        lDireccion = new JLabel("Direccion");
        lTelefono = new JLabel("Telefono");
        lMail = new JLabel("Mail");

        tMR_Nombre = new JTextField();
        tMR_Direccion = new JTextField();
        tMR_Telefono = new JTextField();
        tMR_Mail = new JTextField();

        cMR_idRemitente.setOpaque(true);
        cMR_idRemitente.setBackground(Color.white);

        for (int i = 1970; i <= 2000; i++) {
            cAñoMR.addItem(String.valueOf(i));

        }
        cMesMR.addItem("ENE");
        cMesMR.addItem("FEB");
        cMesMR.addItem("MAR");
        cMesMR.addItem("ABR");
        cMesMR.addItem("MAY");
        cMesMR.addItem("JUN");
        cMesMR.addItem("JUL");
        cMesMR.addItem("AGO");
        cMesMR.addItem("SEP");
        cMesMR.addItem("OCT");
        cMesMR.addItem("NOV");
        cMesMR.addItem("DIC");

        bMR_Modificar = new JButton("Modificar");

        bMR_Modificar.setBounds(340, 15, 100, 30);

        lidRemitente.setBounds(30, 25, 120, 20);
        cMR_idRemitente.setBounds(180, 25, 120, 20);

        lNombreRemitente.setBounds(30, 55, 120, 20);
        tMR_Nombre.setBounds(180, 55, 250, 20);

        lFechaNacimiento.setBounds(30, 85, 120, 20);
        cAñoMR.setBounds(180, 85, 70, 20);
        cMesMR.setBounds(250, 85, 60, 20);
        cDiaMR.setBounds(310, 85, 50, 20);

        lDireccion.setBounds(30, 115, 120, 20);
        tMR_Direccion.setBounds(180, 115, 250, 20);

        lTelefono.setBounds(30, 145, 120, 20);
        tMR_Telefono.setBounds(180, 145, 120, 20);

        lMail.setBounds(30, 175, 120, 20);
        tMR_Mail.setBounds(180, 175, 250, 20);

        barraMR.setBounds(10, 210, 450, 115);

        pModificarRemitente.add(bMR_Modificar);

        pModificarRemitente.add(lidRemitente);
        pModificarRemitente.add(cMR_idRemitente);

        pModificarRemitente.add(lNombreRemitente);
        pModificarRemitente.add(tMR_Nombre);

        pModificarRemitente.add(lFechaNacimiento);
        pModificarRemitente.add(cDiaMR);
        pModificarRemitente.add(cMesMR);
        pModificarRemitente.add(cAñoMR);

        pModificarRemitente.add(lDireccion);
        pModificarRemitente.add(tMR_Direccion);

        pModificarRemitente.add(lTelefono);
        pModificarRemitente.add(tMR_Telefono);

        pModificarRemitente.add(lMail);
        pModificarRemitente.add(tMR_Mail);

        pModificarRemitente.add(barraMR);

        barraMR.setViewportView(tDatosModificarRemitentes);

        noLetras(tMR_Telefono);

        cMesMR.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (cMesMR.getSelectedItem().equals("ENE") || cMesMR.getSelectedItem().equals("MAR") || cMesMR.getSelectedItem().equals("MAY") || cMesMR.getSelectedItem().equals("JUL") || cMesMR.getSelectedItem().equals("AGO") || cMesMR.getSelectedItem().equals("OCT") || cMesMR.getSelectedItem().equals("DIC")) {
                    cDiaMR.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaMR.addItem(i);
                    }
                }
                if (cMesMR.getSelectedItem().equals("ABR") || cMesMR.getSelectedItem().equals("JUN") || cMesMR.getSelectedItem().equals("SEP") || cMesMR.getSelectedItem().equals("NOV")) {
                    cDiaMR.removeAllItems();
                    for (int i = 1; i <= 31; i++) {
                        cDiaMR.addItem(i);
                    }
                }
                if (cMesMR.getSelectedItem().equals("FEB")) {
                    cDiaMR.removeAllItems();
                    for (int i = 1; i <= 28; i++) {
                        cDiaMR.addItem(i);
                    }
                }

            }
        });

        llenarIDs();

        cMR_idRemitente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {

                if (cMR_idRemitente.getSelectedItem().equals("")) {
                    tMR_Nombre.setText("");
                    tMR_Direccion.setText("");
                    tMR_Telefono.setText("");
                    tMR_Mail.setText("");
                    cDiaMR.removeAllItems();

                }

                for (int i = 0; i < moduloRemitentes.length; i++) {

                    if (moduloRemitentes[i].getiD() != null && moduloRemitentes[i].getNombre() != null && moduloRemitentes[i].getFechaNac().getDia() != null && moduloRemitentes[i].getDireccion() != null && moduloRemitentes[i].getTelefono() != null && moduloRemitentes[i].getMail() != null) {
                        if (cMR_idRemitente.getSelectedItem().equals(moduloRemitentes[i].getiD())) {
                            tMR_Nombre.setText(moduloRemitentes[i].getNombre());
                            cAñoMR.setSelectedItem(moduloRemitentes[i].getFechaNac().getAño());
                            cMesMR.setSelectedItem(moduloRemitentes[i].getFechaNac().getMes());
                            cDiaMR.removeAllItems();
                            cDiaMR.addItem(moduloRemitentes[i].getFechaNac().getDia());
                            cDiaMR.setSelectedItem(moduloRemitentes[i].getFechaNac().getDia());
                            tMR_Direccion.setText(moduloRemitentes[i].getDireccion());
                            tMR_Telefono.setText(moduloRemitentes[i].getTelefono());
                            tMR_Mail.setText(moduloRemitentes[i].getMail());
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
                for (int i = 0; i < moduloRemitentes.length; i++) {

                    if (tNR_Nombre.getText().isEmpty() && cDiaNR.getSelectedItem() == null && tNR_Direccion.getText().isEmpty() && tNR_Telefono.getText().isEmpty() && tNR_Mail.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los Campos Vacios");
                        System.out.println("Todo Vacio");
                        break;

                    }
                    if (tNR_Nombre.getText().isEmpty() || cDiaNR.getSelectedItem() == null || tNR_Direccion.getText().isEmpty() || tNR_Telefono.getText().isEmpty() || tNR_Mail.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Faltan Campos por Llenar");
                        System.out.println("Faltan Campos");
                        break;

                    }

                    if (moduloRemitentes[i].getiD() == null) {
                        System.out.println("Encontro Espacio");
                        idRContador += 1;
                        tNR_idRemitente.setText(String.valueOf(idRContador));
                        System.out.println("Aumenta el ID");
                        moduloRemitentes[i] = new Modulo_De_RyB(tNR_idRemitente.getText(), tNR_Nombre.getText(), new Modulo_De_Fecha(cDiaNR.getSelectedItem().toString(), cMesNR.getSelectedItem().toString(), cAñoNR.getSelectedItem().toString()), tNR_Direccion.getText(), tNR_Telefono.getText(), tNR_Mail.getText());

                        System.out.println("Lo Agrego");
                        JOptionPane.showMessageDialog(null, "Nuevo Remitente Agregado");

                        System.out.println("Limpiar Cajas");
                        tNR_idRemitente.setText("");
                        tNR_Nombre.setText("");
                        tNR_Direccion.setText("");
                        tNR_Telefono.setText("");
                        tNR_Mail.setText("");
                        cDiaNR.removeAllItems();

                        llenarIDs();
                        break;

                    }

                    if (moduloRemitentes[i].getiD() != null && moduloRemitentes[i].getNombre().equalsIgnoreCase(tNR_Nombre.getText())) {
                        JOptionPane.showMessageDialog(null, "Este Remitente ya Existe");
                        System.out.println("Remitente ya existe");

                        System.out.println("Limpiar Cajas");
                        tNR_idRemitente.setText("");
                        tNR_Nombre.setText("");
                        tNR_Direccion.setText("");
                        tNR_Telefono.setText("");
                        tNR_Mail.setText("");
                        cDiaNR.setSelectedItem("");
                        break;

                    }

                }
            }

            private void eCrearMostrar() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloRemitentes.length); i++) {

                    if (moduloRemitentes[i].getiD() != null) {
                        tDatosNuevoRemitente.setValueAt(moduloRemitentes[i].getiD(), aux, 0);
                        tDatosNuevoRemitente.setValueAt(moduloRemitentes[i].getNombre(), aux, 1);
                        tDatosNuevoRemitente.setValueAt(moduloRemitentes[i].getTelefono(), aux, 2);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }

            }

        };
        bNR_Crear.addActionListener(eCrear);

        ActionListener eModificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eModificarModificar();
                eModificarMostrar();

            }

            private void eModificarModificar() {

                for (int i = 0; i < moduloRemitentes.length; i++) {

                    if (cMR_idRemitente.getSelectedItem().equals("")) {
                        JOptionPane.showMessageDialog(null, "No Se Puede editar este Remitente");
                        tMR_Direccion.setText("");
                        tMR_Mail.setText("");
                        tMR_Nombre.setText("");
                        tMR_Telefono.setText("");
                        cMR_idRemitente.setSelectedItem("");
                        System.out.println("En Blanco");
                        break;

                    }

                    if (moduloRemitentes[i].getiD() != null && moduloRemitentes[i].getiD().equalsIgnoreCase((String) cMR_idRemitente.getSelectedItem())) {
                        System.out.println("Encontro el ID");

                        if (tMR_Nombre.getText().isEmpty() || cDiaMR.getSelectedItem().equals("") || tMR_Direccion.getText().isEmpty() || tMR_Telefono.getText().isEmpty() || tMR_Mail.getText().isEmpty()) {
                            System.out.println("No Espacios Vacios para Guardar");
                            JOptionPane.showMessageDialog(null, "No Pueden Guardarse Esapcios en Blanco");
                            break;

                        }

                        moduloRemitentes[i].setTelefono(tMR_Telefono.getText());
                        moduloRemitentes[i].setDireccion(tMR_Direccion.getText());
                        moduloRemitentes[i].setFechaNac(new Modulo_De_Fecha(cDiaMR.getSelectedItem().toString(), cMesMR.getSelectedItem().toString(), cAñoMR.getSelectedItem().toString()));
                        moduloRemitentes[i].setNombre(tMR_Nombre.getText());
                        moduloRemitentes[i].setMail(tMR_Mail.getText());
                        System.out.println("Modifico");
                        JOptionPane.showMessageDialog(null, "Modificado");

                        tMR_Direccion.setText("");
                        tMR_Mail.setText("");
                        tMR_Nombre.setText("");
                        tMR_Telefono.setText("");
                        cMR_idRemitente.setSelectedItem("");
                        cDiaMR.removeAllItems();
                        break;

                    }

                }
            }

            private void eModificarMostrar() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloRemitentes.length); i++) {

                    if (moduloRemitentes[i].getiD() != null) {
                        tDatosModificarRemitentes.setValueAt(moduloRemitentes[i].getiD(), aux, 0);
                        tDatosModificarRemitentes.setValueAt(moduloRemitentes[i].getNombre(), aux, 1);
                        tDatosModificarRemitentes.setValueAt(moduloRemitentes[i].getTelefono(), aux, 2);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }
            }
        };
        bMR_Modificar.addActionListener(eModificar);

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
        for (int i = 0; i < moduloRemitentes.length; i++) {
            if (moduloRemitentes[i].getiD() != null) {
                cMR_idRemitente.addItem(moduloRemitentes[i].getiD());
            }

        }
    }

}
