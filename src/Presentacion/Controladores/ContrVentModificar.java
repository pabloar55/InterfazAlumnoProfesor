package Presentacion.Controladores;

import Logica.Persona;
import Presentacion.Vistas.VentanaModificar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContrVentModificar {
    private VentanaModificar vm;
    private ContrVentPrincipal cp;
    private Persona personaAntigua;
    private ArrayList<Persona> personas;
    public ContrVentModificar(VentanaModificar vm, ContrVentPrincipal cp) {
        this.vm = vm;
        this.cp= cp;
        personaAntigua=cp.getPersonaSeleccionada();
        vm.getAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona personaNueva = new Persona(vm.getTextoDni().getText(), vm.getTextoNombre().getText(), vm.getTextoApe().getText(), vm.getTextoTelef().getText());
                if (vm.getCheckAlu().isSelected() && vm.getCheckProf().isSelected()) {
                    personaNueva.setRol("ProfesorAlumno");
                }else if (vm.getCheckAlu().isSelected()) {
                    personaNueva.setRol("Alumno");
                } else if (vm.getCheckProf().isSelected()) {
                    personaNueva.setRol("Profesor");
                }else{
                    JOptionPane.showMessageDialog(vm, "Selecciona al menos un rol", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    cp.getDb().modificarPersonaDB(personaNueva, personaAntigua);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vm, "No se pudo modificar la persona en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    personas= cp.getDb().cargarDatos();
                    cp.limpiarTabla();
                    cp.displayPersonasDB(personas);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vm, "No se pudo cargar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                vm.dispose();

            }
        });
    }
}
