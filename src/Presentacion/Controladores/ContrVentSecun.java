package Presentacion.Controladores;

import Logica.Persona;
import Presentacion.Vistas.VentanaSecun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContrVentSecun {
    public ContrVentSecun(VentanaSecun vs, ContrVentPrin cp) {
        vs.getAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vs.getCheckAlu().isSelected()) {
                    Persona persona = new Persona(vs.getTextoDni().getText(), vs.getTextoNombre().getText(),
                            vs.getTextoApe().getText(), vs.getTextoTelef().getText(), "alumno");
                    cp.displayPersonaAniadir(persona);
                    cp.getDb().aniadirPersonaDB(persona);
                }
                if (vs.getCheckProf().isSelected()) {
                    Persona persona = new Persona(vs.getTextoDni().getText(), vs.getTextoNombre().getText(),
                            vs.getTextoApe().getText(), vs.getTextoTelef().getText(), "profesor");
                    cp.displayPersonaAniadir(persona);
                    cp.getDb().aniadirPersonaDB(persona);
                }
                vs.dispose();
            }
        });
        vs.getCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vs.dispose();
            }
        });
    }
}