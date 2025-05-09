package Presentacion.Vistas;

import Presentacion.Controladores.ContrVentPrin;
import Presentacion.Controladores.ContrVentSecun;

import javax.swing.*;
import java.awt.*;

public class VentanaSecun extends JFrame {
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
    private ContrVentSecun cs;
    private ContrVentPrin cp;

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

    public VentanaSecun(ContrVentPrin cp) {
        this.cp= cp;
        JPanel panelDni = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDni.add(new JLabel("DNI:"));
        textoDni = new JTextField(8);
        panelDni.add(textoDni);
        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNombre.add(new JLabel("Nombre:"));
        textoNombre = new JTextField(8);
        panelNombre.add(textoNombre);

        JPanel panelApe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelApe.add(new JLabel("Apellido:"));
        textoApe = new JTextField(8);
        panelApe.add(textoApe);

        JPanel panelTelef = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTelef.add(new JLabel("Teléfono:"));
        textoTelef = new JTextField(8);
        panelTelef.add(textoTelef);
        checkProf = new JCheckBox("Profesor");
        checkAlu = new JCheckBox("Alumno");
        panelCheck = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCheck.add(checkAlu);
        panelCheck.add(checkProf);
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
        setSize(250, 400);
        setTitle("Añadir persona");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cs = new ContrVentSecun(this, cp);
    }
}