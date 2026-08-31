package com.tfg.demo.gui;

import com.tfg.demo.model.A;
import com.tfg.reusablegui.core.AbstractReusablePanel;
import javax.swing.*;
import java.awt.*;

/**
 * GUI reutilizable encargada de editar los atributos
 * comunes definidos en la entidad A.
 *
 * Esta interfaz proporciona la edición de los datos
 * básicos compartidos por las clases A1 y A2, incluyendo
 * nombre, edad, DNI y estado de activación.
 *
 * Gracias a esta implementación, la misma GUI puede ser
 * reutilizada en distintas jerarquías de objetos,
 * evitando duplicación de código y favoreciendo la
 * reutilización de interfaces gráficas.
 */

public class APanel extends AbstractReusablePanel {

    // Modelo de datos asociado a la interfaz.
    private final A model;

    private final JTextField nombreField = new JTextField(20);
    private final JSpinner edadSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
    private final JTextField dniField = new JTextField(10);
    private final JCheckBox activoCheck = new JCheckBox("Activo");

    /**
    * Construye el panel asociado a una instancia de A.
    *
    * @param model modelo que será editado por la interfaz
    */
    public APanel(A model) {
        this.model = model;
        buildUi();
        loadFromModel();
    }

    /**
    * Construye la interfaz gráfica del panel.
    *
    * Se crean los controles necesarios para editar los
    * atributos básicos compartidos por todas las entidades derivadas de A.
    */
    private void buildUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        add(edadSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;
        add(dniField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(activoCheck, gbc);
    }

    /**
    * Carga los valores almacenados en el modelo dentro
    * de los componentes gráficos de la interfaz.
    */
    private void loadFromModel() {
        nombreField.setText(model.getNombre());
        edadSpinner.setValue(model.getEdad());
        dniField.setText(model.getDni());
        activoCheck.setSelected(model.isActivo());
    }

    /**
    * Valida los datos introducidos por el usuario.
    *
    * Se comprueba que:
    *   - el nombre no esté vacío.
    *   - el nombre no supere los 50 caracteres.
    *   - la edad esté dentro del rango permitido.
    *   - el DNI tenga un formato válido.
    *
    * @return true si todos los datos son válidos
    * @throws IllegalArgumentException si se detecta
    * algún error de validación
    */
    @Override
    protected boolean validateSelf() {
        String nombre = nombreField.getText().trim();
        String dni = dniField.getText().trim();
        int edad = (Integer) edadSpinner.getValue();

        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre está vacío.");
        }

        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede superar 50 caracteres.");
        }

        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y 120.");
        }

        if (!dni.matches("\\d{8}[A-Za-z]")) {
            throw new IllegalArgumentException("El DNI tiene un formato inválido.");
        }

        return true;
    }

    /**
    * Aplica los cambios realizados por el usuario sobre el modelo asociado.
    */
    @Override
    protected void applySelf() {
        model.setNombre(nombreField.getText().trim());
        model.setEdad((Integer) edadSpinner.getValue());
        model.setDni(dniField.getText().trim());
        model.setActivo(activoCheck.isSelected());
    }

    @Override
    public String getTitle() {
        return "Datos básicos";
    }
}