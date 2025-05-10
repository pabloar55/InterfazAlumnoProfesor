package Presentacion.Vistas;

import Persistencia.InicializarDatabase;
import Presentacion.Controladores.ContrVentPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JPanel panel;
    private JPanel panelTabla;
    private JPanel panelBotones;
    private JButton aniadir;
    private JButton borrar;

    public VentanaPrincipal() {
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] cabeceras = {"DNI", "Nombre", "Apellidos", "Telefono", "Profesor", "Alumno" };
        modelo.setColumnIdentifiers(cabeceras);
        TableRowSorter<DefaultTableModel> ordenador= new TableRowSorter<>(modelo);
        tabla = new JTable(modelo);
        tabla.setRowSorter(ordenador);
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelTabla = new JPanel();
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        panel = new JPanel();
        panel.add(panelTabla, BorderLayout.CENTER);
        aniadir = new JButton("Añadir persona");
        borrar = new JButton("Borrar selección");
        panelBotones = new JPanel();
        panelBotones.add(aniadir);
        panelBotones.add(borrar);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Gestor colegio");
        ContrVentPrincipal c = new ContrVentPrincipal(this, new InicializarDatabase());
        setVisible(true);
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getPanelTabla() {
        return panelTabla;
    }

    public JPanel getPanelBotones() {
        return panelBotones;
    }

    public JButton getAniadir() {
        return aniadir;
    }

    public JButton getBorrar() {
        return borrar;
    }
}