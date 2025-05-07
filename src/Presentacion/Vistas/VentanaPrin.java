package Presentacion.Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaPrin extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JPanel panel;
    private JPanel panelTabla;
    private JPanel panelBotones;
    private JButton aniadir;
    private JButton borrar;

    public VentanaPrin() {
        modelo = new DefaultTableModel();
        String[] cabeceras = {"DNI", "Nombre", "Apellidos", "Telefono" };
        modelo.setColumnIdentifiers(cabeceras);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelTabla = new JPanel();
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        panel = new JPanel();
        panel.add(panelTabla, BorderLayout.CENTER);
        aniadir = new JButton();
        borrar = new JButton();
        panelBotones = new JPanel();
        panelBotones.add(aniadir);
        panelBotones.add(borrar);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
