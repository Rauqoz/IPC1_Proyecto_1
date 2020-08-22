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

public class Menu_Pagador_JF extends JFrame {

    private JPanel panel;
    private JButton bModuloPago, bCerrarSesion;
    private JLabel lMenus;

    public Menu_Pagador_JF() throws HeadlessException {
        setSize(285, 250);
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
        lMenus.setBounds(110, 20, 66, 27);
        lMenus.setFont(new Font("Arial", 0, 18));
        panel.add(lMenus);

    }

    private void compBotones() {
        bModuloPago = new JButton("Modulo Pagos");
        bCerrarSesion = new JButton("Cerrar Sesion");

        bModuloPago.setMnemonic('p');
        bCerrarSesion.setMnemonic('c');

        bModuloPago.setBounds(60, 70, 160, 50);
        bCerrarSesion.setBounds(100, 150, 160, 40);

        panel.add(bModuloPago);
        panel.add(bCerrarSesion);

    }

    private void iniciarEventos() {
        ActionListener eCerarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulo_De_Ingreso_JF modulo_De_Ingreso_JF = new Modulo_De_Ingreso_JF();
                JOptionPane.showMessageDialog(null, "Cerrando Sesion de Pagador...");
                modulo_De_Ingreso_JF.setVisible(true);
                dispose();

            }
        };
        bCerrarSesion.addActionListener(eCerarSesion);

        ActionListener eGestionPagos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Modulos.Gestion_Pagos_JF gestion_Pagos_JF = new Modulos.Gestion_Pagos_JF();
                gestion_Pagos_JF.setVisible(true);
                dispose();

            }
        };
        bModuloPago.addActionListener(eGestionPagos);

    }

}
