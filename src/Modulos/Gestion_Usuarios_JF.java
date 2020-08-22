package Modulos;

import Proyecto1.Modulo_De_Ingreso;
import static Proyecto1.Modulo_De_Ingreso_JF.auxUsuario;
import static Proyecto1.Boot.moduloIngreso;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Gestion_Usuarios_JF extends JFrame {

    JTabbedPane pestañas;
    JPanel pNuevoUsuario, pModificarUsuario, pEliminarUsuario, pPanel0;
    JLabel lTUsuarios, lUsuario, lContraseña, lIdentificador;
    JButton bRegresar, bAccionesUsuario;
    JTextField tUsuarioC, tContraseñaC, tUsuarioM, tContraseñaM, tUsuarioE, tContraseñaE;
    JComboBox cTUsuarioC, cTUsuarioM, cTUsuarioE;
    String TiposDeUsuario[] = {"Administrador", "Vendedor", "Pagador"};
    Object titulosTabla[] = {"Tipo Usuario", "Usuario"};
    JScrollPane barraC = new JScrollPane();
    JScrollPane barraM = new JScrollPane();
    JScrollPane barraE = new JScrollPane();
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tablaDatosC = new JTable(modelo);
    JTable tablaDatosM = new JTable(modelo);
    JTable tablaDatosE = new JTable(modelo);

    public Gestion_Usuarios_JF() throws HeadlessException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Gestion de Usuarios");
        setSize(600, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        tablaDatosC.setEnabled(false);
        tablaDatosM.setEnabled(false);
        tablaDatosE.setEnabled(false);

        modelo.setRowCount(moduloIngreso.length);
        modelo.setColumnCount(2);
        modelo.setColumnIdentifiers(titulosTabla);

        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        pPanel0 = new JPanel(null);
        this.getContentPane().add(pPanel0);
        pPanel0.setBackground(Color.white);

        pNuevoUsuario = new JPanel(null);
        pModificarUsuario = new JPanel(null);
        pEliminarUsuario = new JPanel(null);
        iniciarPaneles();
        pestañas = new JTabbedPane();
        bRegresar = new JButton("Regresar");
        bRegresar.setMnemonic('r');
        lIdentificador = new JLabel("Bienvenido " + auxUsuario);

        pNuevoUsuario.setBackground(Color.white);
        pModificarUsuario.setBackground(Color.white);
        pEliminarUsuario.setBackground(Color.white);

        bRegresar.setBounds(450, 10, 100, 30);
        lIdentificador.setBounds(80, 5, 200, 25);

        pestañas.setBounds(10, 35, 570, 365);
        pestañas.addTab("Nuevo Usuario", pNuevoUsuario);
        pestañas.addTab("Modificar Usuario", pModificarUsuario);
        pestañas.addTab("Eliminar Usuario", pEliminarUsuario);

        pPanel0.add(lIdentificador);
        pPanel0.add(bRegresar);
        pPanel0.add(pestañas);

    }

    private void iniciarPaneles() {

        iniciarPNUsuario();
        iniciarPMUsuario();
        iniciarPEUsuario();

    }

    private void iniciarPNUsuario() {
        lTUsuarios = new JLabel("Tipo de Usuario");
        lUsuario = new JLabel("Usuario");
        lContraseña = new JLabel("Contraseña");
        cTUsuarioC = new JComboBox(TiposDeUsuario);
        tUsuarioC = new JTextField();
        tContraseñaC = new JTextField();
        bAccionesUsuario = new JButton("Crear");
        bAccionesUsuario.setMnemonic('c');

        lTUsuarios.setBounds(50, 50, 100, 25);
        cTUsuarioC.setBounds(190, 50, 301, 25);
        lUsuario.setBounds(50, 100, 100, 25);
        tUsuarioC.setBounds(190, 100, 150, 25);
        lContraseña.setBounds(50, 150, 100, 25);
        tContraseñaC.setBounds(190, 150, 150, 25);
        bAccionesUsuario.setBounds(400, 125, 100, 25);
        barraC.setBounds(20, 200, 525, 125);

        cTUsuarioC.setOpaque(true);
        cTUsuarioC.setBackground(Color.white);

        pNuevoUsuario.add(lTUsuarios);
        pNuevoUsuario.add(cTUsuarioC);
        pNuevoUsuario.add(lUsuario);
        pNuevoUsuario.add(tUsuarioC);
        pNuevoUsuario.add(lContraseña);
        pNuevoUsuario.add(tContraseñaC);
        pNuevoUsuario.add(bAccionesUsuario);
        pNuevoUsuario.add(barraC);

        ActionListener ecrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                eCrearCrear();

                tUsuarioC.setText("");
                tContraseñaC.setText("");

                ordenarDatos();
                mostrarTablaC();
                barraC.setViewportView(tablaDatosC);

            }

            private void mostrarTablaC() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloIngreso.length); i++) {

                    if (moduloIngreso[i].gettUsuario() != null && moduloIngreso[i].getUsuario() != null && moduloIngreso[i].getContraseña() != null) {
                        tablaDatosC.setValueAt(moduloIngreso[i].gettUsuario(), aux, 0);
                        tablaDatosC.setValueAt(moduloIngreso[i].getUsuario(), aux, 1);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }
            }

            private void eCrearCrear() {

                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (tUsuarioC.getText().isEmpty() && tContraseñaC.getText().isEmpty()) {
                        System.out.println("Espacios Vacios");
                        JOptionPane.showMessageDialog(null, "No hay datos");
                        break;
                    } else if (tUsuarioC.getText().isEmpty() || tContraseñaC.getText().isEmpty()) {
                        System.out.println("Faltan Datos");
                        JOptionPane.showMessageDialog(null, "Hay Campos Vacios");
                        break;
                    }

                    if (moduloIngreso[i].gettUsuario() == null && moduloIngreso[i].getUsuario() == null && moduloIngreso[i].getContraseña() == null) {
                        System.out.println("Encontro Espacio " + cTUsuarioC.getSelectedItem() + " - " + tUsuarioC.getText() + " - " + tContraseñaC.getText());

                        moduloIngreso[i] = new Modulo_De_Ingreso((String) cTUsuarioC.getSelectedItem(), tUsuarioC.getText(), tContraseñaC.getText());
                        JOptionPane.showMessageDialog(null, "Usuario Agregado");

                        System.out.println("Lo Agrego");
                        break;
                    }

                    if (moduloIngreso[i].gettUsuario() != null && moduloIngreso[i].getUsuario() != null && moduloIngreso[i].getContraseña() != null && tUsuarioC.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario())) {
                        System.out.println("No lo agrega porque ya existe el usuario");
                        JOptionPane.showMessageDialog(null, "El Usuario ya Existe \n No Puede Duplicarse");
                        break;
                    }

                }
            }

        };
        bAccionesUsuario.addActionListener(ecrear);

    }

    private void iniciarPMUsuario() {
        lTUsuarios = new JLabel("Cambiar a ");
        lUsuario = new JLabel("Usuario");
        lContraseña = new JLabel("Contraseña");
        cTUsuarioM = new JComboBox(TiposDeUsuario);
        tUsuarioM = new JTextField();
        tContraseñaM = new JTextField();
        bAccionesUsuario = new JButton("Modificar");
        bAccionesUsuario.setMnemonic('m');

        lTUsuarios.setBounds(50, 50, 100, 25);
        cTUsuarioM.setBounds(190, 50, 301, 25);
        lUsuario.setBounds(50, 100, 100, 25);
        tUsuarioM.setBounds(190, 100, 150, 25);
        lContraseña.setBounds(50, 150, 100, 25);
        tContraseñaM.setBounds(190, 150, 150, 25);
        bAccionesUsuario.setBounds(400, 125, 100, 25);
        barraM.setBounds(20, 200, 525, 125);

        cTUsuarioM.setOpaque(true);
        cTUsuarioM.setBackground(Color.white);

        pModificarUsuario.add(lTUsuarios);
        pModificarUsuario.add(cTUsuarioM);
        pModificarUsuario.add(lUsuario);
        pModificarUsuario.add(tUsuarioM);
        pModificarUsuario.add(lContraseña);
        pModificarUsuario.add(tContraseñaM);
        pModificarUsuario.add(bAccionesUsuario);
        pModificarUsuario.add(barraM);

        ActionListener emodificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eMoficicarModificar();

                tUsuarioM.setText("");
                tContraseñaM.setText("");

                ordenarDatos();
                mostrarTablaM();
                barraM.setViewportView(tablaDatosM);

            }

            private void mostrarTablaM() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloIngreso.length); i++) {

                    if (moduloIngreso[i] != null) {
                        tablaDatosM.setValueAt(moduloIngreso[i].gettUsuario(), aux, 0);
                        tablaDatosM.setValueAt(moduloIngreso[i].getUsuario(), aux, 1);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }
            }

            private void eMoficicarModificar() {

                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (moduloIngreso[i].gettUsuario() != null && moduloIngreso[i].getUsuario() != null && moduloIngreso[i].getContraseña() != null) {
                        System.out.println("Entro a buscar " + tUsuarioM.getText() + " - " + tContraseñaM.getText());

                        if (tUsuarioM.getText().isEmpty() && tContraseñaM.getText().isEmpty()) {
                            System.out.println("Espacios Vacios");
                            JOptionPane.showMessageDialog(null, "No hay datos");
                            break;
                        }

                        if (cTUsuarioM.getSelectedItem().equals(moduloIngreso[i].gettUsuario()) && tUsuarioM.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && tContraseñaM.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) {
                            System.out.println("El del mismo tipo");
                            JOptionPane.showMessageDialog(null, "Ya es Tipo " + cTUsuarioM.getSelectedItem());
                            break;

                        }

                        if (tUsuarioM.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && tContraseñaM.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) {
                            System.out.println("Lo Encontro");

                            moduloIngreso[i].settUsuario((String) cTUsuarioM.getSelectedItem());
                            JOptionPane.showMessageDialog(null, "Usuario Modificado");
                            System.out.println("Lo Modifico");
                            break;

                        }

                        if ((!tUsuarioM.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && tContraseñaM.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) || (tUsuarioM.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && !tContraseñaM.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña()))) {
                            System.out.println("Mal los Datos");
                            JOptionPane.showMessageDialog(null, "Los Datos No Coinciden");
                            break;
                        }

                    }

                }
            }

        };
        bAccionesUsuario.addActionListener(emodificar);

    }

    private void iniciarPEUsuario() {
        lTUsuarios = new JLabel("Tipo de Usuario");
        lUsuario = new JLabel("Usuario");
        lContraseña = new JLabel("Contraseña");
        cTUsuarioE = new JComboBox(TiposDeUsuario);
        tUsuarioE = new JTextField();
        tContraseñaE = new JTextField();
        bAccionesUsuario = new JButton("Eliminar");
        bAccionesUsuario.setMnemonic('e');

        lTUsuarios.setBounds(50, 50, 100, 25);
        cTUsuarioE.setBounds(190, 50, 301, 25);
        lUsuario.setBounds(50, 100, 100, 25);
        tUsuarioE.setBounds(190, 100, 150, 25);
        lContraseña.setBounds(50, 150, 100, 25);
        tContraseñaE.setBounds(190, 150, 150, 25);
        bAccionesUsuario.setBounds(400, 125, 100, 25);
        barraE.setBounds(20, 200, 525, 125);

        cTUsuarioE.setOpaque(true);
        cTUsuarioE.setBackground(Color.white);

        pEliminarUsuario.add(lTUsuarios);
        pEliminarUsuario.add(cTUsuarioE);
        pEliminarUsuario.add(lUsuario);
        pEliminarUsuario.add(tUsuarioE);
        pEliminarUsuario.add(lContraseña);
        pEliminarUsuario.add(tContraseñaE);
        pEliminarUsuario.add(bAccionesUsuario);
        pEliminarUsuario.add(barraE);

        ActionListener eelimiar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eEliminarEliminar();

                tUsuarioE.setText("");
                tContraseñaE.setText("");

                ordenarDatos();
                mostrarTablaE();
                barraE.setViewportView(tablaDatosE);

            }

            private void mostrarTablaE() {
                System.out.println("Muestra la Tabla");
                int aux = 0;

                for (int i = 0; i < (moduloIngreso.length); i++) {

                    if (moduloIngreso[i] != null) {
                        tablaDatosE.setValueAt(moduloIngreso[i].gettUsuario(), aux, 0);
                        tablaDatosE.setValueAt(moduloIngreso[i].getUsuario(), aux, 1);
                        aux += 1;

                    } else {
                        aux = i;
                    }

                }
            }

            private void eEliminarEliminar() {

                for (int i = 0; i < moduloIngreso.length; i++) {

                    if (tUsuarioE.getText().isEmpty() && tContraseñaE.getText().isEmpty()) {
                        System.out.println("Espacios Vacios");
                        JOptionPane.showMessageDialog(null, "No hay datos");
                        break;
                    }

                    if (moduloIngreso[i] != null) {
                        System.out.println("Entro a buscar " + cTUsuarioE.getSelectedItem() + " - " + tUsuarioE.getText() + " - " + tContraseñaE.getText());

                        if (cTUsuarioE.getSelectedItem().equals(moduloIngreso[i].gettUsuario()) && tUsuarioE.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && tContraseñaE.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) {
                            System.out.println("Lo encontro");

                            moduloIngreso[i] = new Modulo_De_Ingreso(null, null, null);
                            JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                            System.out.println("Lo Elimino");
                            break;

                        }

                        if ((!cTUsuarioE.getSelectedItem().equals(moduloIngreso[i].gettUsuario()) && tUsuarioE.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && tContraseñaE.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) || (cTUsuarioE.getSelectedItem().equals(moduloIngreso[i].gettUsuario()) && !tUsuarioE.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && tContraseñaE.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña())) || (cTUsuarioE.getSelectedItem().equals(moduloIngreso[i].gettUsuario()) && tUsuarioE.getText().equalsIgnoreCase(moduloIngreso[i].getUsuario()) && !tContraseñaE.getText().equalsIgnoreCase(moduloIngreso[i].getContraseña()))) {
                            System.out.println("Mal los Datos");
                            JOptionPane.showMessageDialog(null, "Los Datos No Coinciden");
                            break;

                        }
                        if (tUsuarioE.getText().equalsIgnoreCase(moduloIngreso[0].getUsuario())) {
                            System.out.println("No el Raiz");
                            JOptionPane.showMessageDialog(null, "No Puedes Eliminar el Usuario Principal");
                            break;

                        }

                    }

                }
            }

        };
        bAccionesUsuario.addActionListener(eelimiar);

    }

    private void iniciarEventos() {
        ActionListener eRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Proyecto1.Menu_Administrador_JF menu_Administrador = new Proyecto1.Menu_Administrador_JF();
                menu_Administrador.setVisible(true);
                dispose();

            }
        };
        bRegresar.addActionListener(eRegresar);

    }

    private void ordenarDatos() {
        for (int i = 0; i < moduloIngreso.length - 1; i++) {
            for (int j = 0; j < moduloIngreso.length - 1; j++) {
                if (moduloIngreso[j].gettUsuario() == null && moduloIngreso[j].getUsuario() == null && moduloIngreso[j].getContraseña() == null) {
                    moduloIngreso[j] = moduloIngreso[j + 1];
                    moduloIngreso[j + 1] = new Modulo_De_Ingreso(null, null, null);

                }

            }

        }

    }

}
