package Proyecto1;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu_Administrador_JF extends JFrame {

    private JPanel panel;
    private JButton bGestionUsuarios, bModuloVentas, bModuloPagos, bModuloEspecial, bCerrarSesion;
    private JLabel lMenus;

    public Menu_Administrador_JF() throws HeadlessException {
        setSize(385, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Menu del Administrador");
        setResizable(false);
        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes() {
        inicarPanel();
        compBotones();
        compTextos();

    }

    private void inicarPanel() {
        panel = new JPanel(null);
        panel.setBackground(Color.white);
        this.getContentPane().add(panel);

    }

    private void compTextos() {
        lMenus = new JLabel("Menus", SwingConstants.CENTER);
        lMenus.setBounds(160, 20, 66, 27);
        lMenus.setFont(new Font("Arial", 0, 18));
        panel.add(lMenus);

    }

    private void compBotones() {
        bGestionUsuarios = new JButton("Gestion de Usuarios");
        bModuloVentas = new JButton("Modulo Ventas");
        bModuloPagos = new JButton("Modulo Pagos");
        bModuloEspecial = new JButton("Modulo Especial");
        bCerrarSesion = new JButton("Cerrar Sesion");

        bGestionUsuarios.setMnemonic('g');
        bModuloVentas.setMnemonic('v');
        bModuloPagos.setMnemonic('p');
        bModuloEspecial.setMnemonic('e');
        bCerrarSesion.setMnemonic('c');

        bGestionUsuarios.setBounds(20, 62, 160, 50);
        bModuloVentas.setBounds(200, 62, 160, 50);
        bModuloPagos.setBounds(20, 132, 160, 50);
        bModuloEspecial.setBounds(200, 132, 160, 50);
        bCerrarSesion.setBounds(115, 202, 160, 40);

        panel.add(bGestionUsuarios);
        panel.add(bModuloVentas);
        panel.add(bModuloPagos);
        panel.add(bModuloEspecial);
        panel.add(bCerrarSesion);

    }

    private void iniciarEventos() {
        ActionListener eCerarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulo_De_Ingreso_JF modulo_De_Ingreso_JF = new Modulo_De_Ingreso_JF();
                JOptionPane.showMessageDialog(null, "Cerrando Sesion de Administrador...");
                modulo_De_Ingreso_JF.setVisible(true);
                dispose();

            }
        };
        bCerrarSesion.addActionListener(eCerarSesion);

        ActionListener eGestionUsuarios = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Usuarios_JF gestion_Usuarios = new Modulos.Gestion_Usuarios_JF();
                gestion_Usuarios.setVisible(true);
                dispose();

            }
        };
        bGestionUsuarios.addActionListener(eGestionUsuarios);

        ActionListener eGestionVentas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Ventas_JF gestion_Ventas_JF = new Modulos.Gestion_Ventas_JF();
                gestion_Ventas_JF.setVisible(true);
                dispose();

            }
        };
        bModuloVentas.addActionListener(eGestionVentas);

        ActionListener eGestionPagos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Pagos_JF gestion_Pagos_JF = new Modulos.Gestion_Pagos_JF();
                gestion_Pagos_JF.setVisible(true);
                dispose();

            }
        };
        bModuloPagos.addActionListener(eGestionPagos);

        ActionListener eGestionEstadistica = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                JOptionPane.showMessageDialog(null, "Te Falle :'v ");
                Modulos.Gestion_Especial_Menu_JF gestion_Especial_Menu = new Modulos.Gestion_Especial_Menu_JF();
                gestion_Especial_Menu.setVisible(true);
                dispose();

            }
        };
        bModuloEspecial.addActionListener(eGestionEstadistica);

    }

}
