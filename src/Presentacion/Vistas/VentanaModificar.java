package Presentacion.Vistas;

import Presentacion.Controladores.ContrVentModificar;
import Presentacion.Controladores.ContrVentPrincipal;

import javax.swing.*;
import java.awt.*;

public class VentanaModificar extends JFrame {

    private JTextField textoNombre;
    private JTextField textoDni;
    private JTextField textoApe;
    private JTextField textoTelef;
    private JPanel panel;
    private JPanel panelSiNo;
    private JButton aceptar;
    private JButton cancelar;
    private JPanel panelCheck;
    private JCheckBox checkProf;
    private JCheckBox checkAlu;
    private ContrVentModificar cvm;
    private ContrVentPrincipal cp;
    private int filaSelec;

    public JCheckBox getCheckProf() {
        return checkProf;
    }

    public JCheckBox getCheckAlu() {
        return checkAlu;
    }

    public JTextField getTextoNombre() {
        return textoNombre;
    }

    public JTextField getTextoDni() {
        return textoDni;
    }

    public JTextField getTextoApe() {
        return textoApe;
    }

    public JTextField getTextoTelef() {
        return textoTelef;
    }

    public JButton getAceptar() {
        return aceptar;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public VentanaModificar(ContrVentPrincipal cp) {
        this.cp = cp;
        JPanel panelDni = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDni.add(new JLabel("DNI:"));
        filaSelec = cp.getVentanaPrincipal().getTabla().getSelectedRow();
        textoDni = new JTextField(cp.getVentanaPrincipal().getModelo().getValueAt(filaSelec, 0).toString(), 8){
            @Override
            public boolean isEditable() {
                return false;
            }
        };
        panelDni.add(textoDni);

        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNombre.add(new JLabel("Nombre:"));
        textoNombre = new JTextField(cp.getVentanaPrincipal().getModelo().getValueAt(filaSelec, 1).toString(), 8);
        panelNombre.add(textoNombre);

        JPanel panelApe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelApe.add(new JLabel("Apellido:"));
        textoApe = new JTextField(cp.getVentanaPrincipal().getModelo().getValueAt(filaSelec, 2).toString(), 8);
        panelApe.add(textoApe);

        JPanel panelTelef = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTelef.add(new JLabel("Teléfono:"));
        textoTelef = new JTextField(cp.getVentanaPrincipal().getModelo().getValueAt(filaSelec, 3).toString(), 8);
        panelTelef.add(textoTelef);

        checkProf = new JCheckBox("Profesor");
        checkAlu = new JCheckBox("Alumno");
        panelCheck = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCheck.add(checkProf);
        panelCheck.add(checkAlu);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panelDni);
        panel.add(panelNombre);
        panel.add(panelApe);
        panel.add(panelTelef);
        panel.add(panelCheck);
        add(panel, BorderLayout.NORTH);
        panelSiNo = new JPanel();
        aceptar = new JButton("Aceptar");
        cancelar = new JButton("Cancelar");
        panelSiNo.add(aceptar);
        panelSiNo.add(cancelar);
        add(panelSiNo, BorderLayout.SOUTH);
        setSize(300, 400);
        setTitle("Modificar persona");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cvm = new ContrVentModificar(this, cp);
    }
}
