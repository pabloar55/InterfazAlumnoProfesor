package Presentacion.Controladores;

import Logica.Persona;
import Presentacion.Vistas.VentanaAniadir;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ContrVentAniadir {
    public ContrVentAniadir(VentanaAniadir va, ContrVentPrincipal cp) {

        va.getAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona persona = new Persona(va.getTextoDni().getText(), va.getTextoNombre().getText(),
                        va.getTextoApe().getText(), va.getTextoTelef().getText());
                boolean esProfe = va.getCheckProf().isSelected();
                boolean esAlumno = va.getCheckAlu().isSelected();
                if (esProfe && esAlumno) {
                    persona.setRol("ProfesorAlumno");
                } else if (esAlumno) {
                    persona.setRol("Alumno");
                } else if (esProfe) {
                    persona.setRol("Profesor");
                }else{
                    JOptionPane.showMessageDialog(va, "Seleciona al menos un rol", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    cp.getDb().aniadirPersonaDB(persona);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(va, "No se pudo añadir la neuva persona en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                cp.mostrarPersonaAniadida(persona);
                va.dispose();
            }
        });
        va.getCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                va.dispose();
            }
        });
    }
}
