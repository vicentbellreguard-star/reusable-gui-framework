package com.tfg.demo.gui;

import com.tfg.demo.model.A1;
import com.tfg.reusablegui.core.AbstractReusablePanel;
import javax.swing.*;
import java.awt.*;

/**
 * GUI reutilizable encargada de editar los atributos
 * específicos de la entidad A1.
 *
 * Este panel complementa a APanel mostrando la información laboral asociada 
 * al empleado, incluyendo el puesto de trabajo y el salario.
 *
 * Su principal objetivo es demostrar la composición de múltiples 
 * GUIs reutilizables sobre un mismo modelo de datos.
 */

public class A1ExtraPanel extends AbstractReusablePanel {

    // Modelo de datos asociado a la interfaz.
    private final A1 model;

    private final JTextField puestoField = new JTextField(20);
    private final JSpinner salarioSpinner =
            new JSpinner(new SpinnerNumberModel(0.0, 0.0, 200000.0, 100.0));

    /**
    * Construye el panel asociado a una instancia de A1.
    *
    * @param model modelo que será editado por la interfaz
    */
    public A1ExtraPanel(A1 model) {
        this.model = model;
        buildUi();
        loadFromModel();
    }

    /**
    * Construye la interfaz gráfica del panel.
    *
    * Se crean los controles necesarios para editar
    * el puesto de trabajo y el salario.
    */
    private void buildUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Puesto:"), gbc);
        gbc.gridx = 1;
        add(puestoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Salario:"), gbc);
        gbc.gridx = 1;
        add(salarioSpinner, gbc);
    }

    /**
    * Carga los valores del modelo en los componentes gráficos.
    */
    private void loadFromModel() {
        puestoField.setText(model.getPuesto());
        salarioSpinner.setValue(model.getSalario());
    }

    /**
    * Valida los datos introducidos por el usuario.
    *
    * @return true si el puesto no está vacío y el salario es válido
    */
    @Override
    protected boolean validateSelf() {
        String puesto = puestoField.getText().trim();
        double salario = ((Number) salarioSpinner.getValue()).doubleValue();

        return !puesto.isEmpty() && salario >= 0.0;
    }

    /**
    * Aplica los cambios realizados por el usuario al modelo.
    */
    @Override
    protected void applySelf() {
        model.setPuesto(puestoField.getText().trim());
        model.setSalario(((Number) salarioSpinner.getValue()).doubleValue());
    }

    @Override
    public String getTitle() {
        return "Datos laborales";
    }
}