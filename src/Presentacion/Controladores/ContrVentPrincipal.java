package Presentacion.Controladores;

import Logica.Persona;
import Persistencia.InicializarDatabase;
import Presentacion.Vistas.VentanaModificar;
import Presentacion.Vistas.VentanaPrincipal;
import Presentacion.Vistas.VentanaAniadir;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContrVentPrincipal {

    private VentanaPrincipal v;
    private InicializarDatabase db;

    private ArrayList<Persona> personas;

    public ContrVentPrincipal(VentanaPrincipal v, InicializarDatabase db) {
        this.v = v;
        this.db = db;
        try {
            personas = db.cargarDatos();
            displayPersonasDB(personas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(v, "No se pudo cargar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        v.getAniadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAniadir vs = new VentanaAniadir(ContrVentPrincipal.this);
            }
        });
        v.getBorrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = v.getTabla().getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(v, "No has seleccionado ninguna fila");
                    return;
                }
                String dni = v.getModelo().getValueAt(fila, 0).toString();
                try {
                    db.borrarPersonaDB(dni);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(v, "No se pudo borrar el nombre", "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                v.getModelo().removeRow(fila);
            }
        });
        v.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = v.getTabla().rowAtPoint(e.getPoint());
                    VentanaModificar vm = new VentanaModificar(ContrVentPrincipal.this);
                }
                if (e.getClickCount() == 1  && v.getTabla().isCellSelected(0,0)) {
                    System.out.println("affafafaa");
                }
            }
        });
    }

    public void displayPersonasDB(ArrayList<Persona> personas) {
        for (Persona p : personas) {
            mostrarPersonaAniadida(p);
        }
    }

    public void mostrarPersonaAniadida(Persona p) {
        if (p.getRol().equals("Alumno")) {
            String[] s = {p.getDni(), p.getNombre(), p.getApellido(), p.getTelefono(), "", "X"};
            v.getModelo().addRow(s);
        }
        if (p.getRol().equals("Profesor")) {
            String[] s = {p.getDni(), p.getNombre(), p.getApellido(), p.getTelefono(), "X", ""};
            v.getModelo().addRow(s);
        }
        if (p.getRol().equals("ProfesorAlumno")) {
            String[] s = {p.getDni(), p.getNombre(), p.getApellido(), p.getTelefono(), "X", "X"};
            v.getModelo().addRow(s);
        }
    }
    public Persona getPersonaSeleccionada(){
        int fila = v.getTabla().getSelectedRow();
        Persona p = new Persona(v.getModelo().getValueAt(fila, 0).toString(), v.getModelo().getValueAt(fila, 1).toString(),
                v.getModelo().getValueAt(fila, 2).toString(), v.getModelo().getValueAt(fila, 3).toString());
        if (v.getModelo().getValueAt(fila, 4).equals("X") && v.getModelo().getValueAt(fila, 5).equals("X")) {
            p.setRol("ProfesorAlumno");
        }else if (v.getModelo().getValueAt(fila, 4).equals("X")) {
            p.setRol("Profesor");
        } else if (v.getModelo().getValueAt(fila, 5).equals("X")) {
            p.setRol("Alumno");
        }
        return p;
    }
    public void limpiarTabla(){
        v.getModelo().setRowCount(0);
    }
    public InicializarDatabase getDb() {
        return db;
    }

    public VentanaPrincipal getVentanaPrincipal() {
        return v;
    }
}