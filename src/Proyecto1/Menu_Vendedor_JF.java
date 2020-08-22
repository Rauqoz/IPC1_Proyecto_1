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

public class Menu_Vendedor_JF extends JFrame {

    private JPanel panel;
    private JButton bModuloVentas, bModuloRemitentes, bModuloBeneficiarios, bCerrarSesion;
    private JLabel lMenus;

    public Menu_Vendedor_JF() throws HeadlessException {
        setSize(385, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Menu del Vendedor");
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
        bModuloVentas = new JButton("Modulo Ventas");
        bModuloRemitentes = new JButton("Modulo Remitentes");
        bModuloBeneficiarios = new JButton("Modulo Beneficiarios");
        bCerrarSesion = new JButton("Cerrar Sesion");

        bModuloVentas.setMnemonic('v');
        bModuloRemitentes.setMnemonic('r');
        bModuloBeneficiarios.setMnemonic('b');
        bCerrarSesion.setMnemonic('c');

        bModuloVentas.setBounds(20, 62, 160, 50);
        bModuloRemitentes.setBounds(200, 62, 160, 50);
        bModuloBeneficiarios.setBounds(20, 130, 160, 50);
        bCerrarSesion.setBounds(200, 135, 160, 40);

        panel.add(bModuloVentas);
        panel.add(bModuloRemitentes);
        panel.add(bModuloBeneficiarios);
        panel.add(bCerrarSesion);

    }

    private void iniciarEventos() {
        ActionListener eCerarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulo_De_Ingreso_JF modulo_De_Ingreso_JF = new Modulo_De_Ingreso_JF();
                JOptionPane.showMessageDialog(null, "Cerrando Sesion de Vendedor...");
                modulo_De_Ingreso_JF.setVisible(true);
                dispose();

            }
        };
        bCerrarSesion.addActionListener(eCerarSesion);

        ActionListener eGestionVentas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Ventas_JF gestion_Ventas_JF = new Modulos.Gestion_Ventas_JF();
                gestion_Ventas_JF.setVisible(true);
                dispose();

            }
        };
        bModuloVentas.addActionListener(eGestionVentas);

        ActionListener eGestionRemitentes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Remitentes_JF gestion_Remitentes_JF = new Modulos.Gestion_Remitentes_JF();
                gestion_Remitentes_JF.setVisible(true);
                dispose();

            }
        };
        bModuloRemitentes.addActionListener(eGestionRemitentes);

        ActionListener eGestionBeneficiarios = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Beneficiarios_JF gestion_Beneficiarios_JF = new Modulos.Gestion_Beneficiarios_JF();
                gestion_Beneficiarios_JF.setVisible(true);
                dispose();

            }
        };
        bModuloBeneficiarios.addActionListener(eGestionBeneficiarios);

    }

}
