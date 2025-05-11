package Presentacion.Vistas;

import Presentacion.Controladores.ContrVentPrincipal;
import Presentacion.Controladores.ContrVentAniadir;

import javax.swing.*;
import java.awt.*;

public class VentanaAniadir extends JFrame {
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
    private ContrVentAniadir cs;
    private ContrVentPrincipal cp;

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

    public VentanaAniadir(ContrVentPrincipal cp) {
        this.cp= cp;

        JPanel panelDni = new JPanel(new BorderLayout());
        panelDni.add(new JLabel("DNI"), BorderLayout.NORTH);
        textoDni = new JTextField();
        panelDni.add(textoDni, BorderLayout.SOUTH);

        JPanel panelNombre = new JPanel(new BorderLayout());
        panelNombre.add(new JLabel("Nombre"), BorderLayout.NORTH);
        textoNombre = new JTextField();
        panelNombre.add(textoNombre, BorderLayout.SOUTH);

        JPanel panelApe = new JPanel(new BorderLayout());
        panelApe.add(new JLabel("Apellidos"), BorderLayout.NORTH);
        textoApe = new JTextField();
        panelApe.add(textoApe, BorderLayout.SOUTH);

        JPanel panelTelef = new JPanel(new BorderLayout());
        panelTelef.add(new JLabel("Teléfono"), BorderLayout.NORTH);
        textoTelef = new JTextField();
        panelTelef.add(textoTelef, BorderLayout.SOUTH);

        checkProf = new JCheckBox("Profesor");
        checkAlu = new JCheckBox("Alumno");
        panelCheck = new JPanel(new BorderLayout());
        panelCheck.add(checkProf, BorderLayout.NORTH);
        panelCheck.add(checkAlu, BorderLayout.SOUTH);

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
        setTitle("Añadir persona");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cs = new ContrVentAniadir(this, cp);
    }
}