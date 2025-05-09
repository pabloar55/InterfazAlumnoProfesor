package Presentacion.Vistas;

import Persistencia.InicializarDatabase;
import Presentacion.Controladores.ContrVentPrin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
        aniadir = new JButton("Añadir");
        borrar = new JButton("Borrar");
        panelBotones = new JPanel();
        panelBotones.add(aniadir);
        panelBotones.add(borrar);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ContrVentPrin c = new ContrVentPrin(this, new InicializarDatabase());
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