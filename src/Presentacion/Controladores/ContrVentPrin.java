package Presentacion.Controladores;

import Logica.Persona;
import Persistencia.InicializarDatabase;
import Presentacion.Vistas.VentanaPrin;
import Presentacion.Vistas.VentanaSecun;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContrVentPrin {
    private VentanaPrin v;
    private InicializarDatabase db;

    public VentanaPrin getV() {
        return v;
    }

    public InicializarDatabase getDb() {
        return db;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    private ArrayList<Persona> personas;

    public ContrVentPrin(VentanaPrin v, InicializarDatabase db) {
        this.v = v;
        this.db= db;
        personas= db.cargarDatos();
        displayPersonasDB(personas);
        v.getAniadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaSecun vs = new VentanaSecun(ContrVentPrin.this);
            }
        });
        v.getBorrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int fila = v.getTabla().getSelectedRow();
                if (fila==-1){
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
    }

    public VentanaPrin getVentanaPrin() {
        return v;
    }
    public void displayPersonasDB(ArrayList<Persona> personas){
        for (Persona p : personas){
            displayPersonaAniadir(p);
        }
    }
    public void displayPersonaAniadir (Persona p){
        if (p.getRol().equals("alumno")){
            String[] s = {p.getDni(), p.getNombre(), p.getApellido(), p.getTelefono(),"" ,"X" };
            v.getModelo().addRow(s);
        }
        if (p.getRol().equals("profesor")){
            String[] s = {p.getDni(), p.getNombre(), p.getApellido(), p.getTelefono(),"X" ,"" };
            v.getModelo().addRow(s);
        }
    }
}