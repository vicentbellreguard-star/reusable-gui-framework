package com.tfg.demo.gui;

import com.tfg.demo.model.C;
import com.tfg.reusablegui.core.AbstractReusablePanel;

import javax.swing.*;
import java.awt.*;

/**
 * GUI reutilizable asociada a la entidad C.
 *
 * Este panel permite editar la información relacionada
 * con la configuración profesional del empleado, incluyendo
 * su departamento, nivel, evaluación y observaciones.
 *
 * Forma parte de la composición jerárquica utilizada en
 * la demostración basada en TreeContainerPanel.
 */

public class CPanel extends AbstractReusablePanel {

    // Modelo de datos asociado a la interfaz.
    private final C model;

    private final JTextField departamentoField = new JTextField(20);
    private final JSpinner nivelSpinner =
            new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
    private final JSpinner umbralSpinner =
            new JSpinner(new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1));
    private final JCheckBox visibleCheck = new JCheckBox("Participa en proyectos");
    private final JTextField observacionesField = new JTextField(20);

    /**
    * Construye el panel asociado a una instancia de C.
    *
    * @param model modelo que será editado por la interfaz
    */
    public CPanel(C model) {
        this.model = model;
        buildUi();
        loadFromModel();
    }

    /**
    * Construye la interfaz gráfica del panel.
    *
    * Se crean los controles necesarios para editar
    * los atributos gestionados por la entidad C.
    */
    private void buildUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1;
        add(departamentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nivel profesional:"), gbc);
        gbc.gridx = 1;
        add(nivelSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Evaluación empleado:"), gbc);
        gbc.gridx = 1;
        add(umbralSpinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(visibleCheck, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1;
        add(observacionesField, gbc);
    }

    /**
    * Carga los valores almacenados en el modelo dentro
    * de los componentes gráficos de la interfaz.
    */
    private void loadFromModel() {
        departamentoField.setText(model.getDepartamento());
        nivelSpinner.setValue(model.getNivel());
        umbralSpinner.setValue(model.getEvaluacion());
        visibleCheck.setSelected(model.isVisible());
        observacionesField.setText(model.getObservaciones());
    }

    /**
    * Valida los datos introducidos por el usuario.
    *
    * @return true si el campo departamento contiene un valor válido
    */
    @Override
    protected boolean validateSelf() {
        return !departamentoField.getText().trim().isEmpty();
    }

    /**
    * Aplica los cambios realizados por el usuario sobre el modelo asociado.
    */
    @Override
    protected void applySelf() {
        model.setDepartamento(departamentoField.getText().trim());
        model.setNivel((Integer) nivelSpinner.getValue());
        model.setEvaluacion(((Number) umbralSpinner.getValue()).doubleValue());
        model.setVisible(visibleCheck.isSelected());
        model.setObservaciones(observacionesField.getText().trim());
    }

    @Override
    public String getTitle() {
        return "Información profesional";
    }
}