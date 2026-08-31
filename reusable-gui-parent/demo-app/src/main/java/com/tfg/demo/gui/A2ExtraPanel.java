package com.tfg.demo.gui;

import com.tfg.demo.model.A2;
import com.tfg.reusablegui.core.AbstractReusablePanel;
import javax.swing.*;
import java.awt.*;

/**
 * GUI reutilizable encargada de editar los atributos
 * específicos de la entidad A2.
 *
 * Este panel complementa a APanel mostrando las preferencias
 * de configuración asociadas al usuario, incluyendo el nivel
 * de acceso y diversas opciones de personalización.
 *
 * Su principal objetivo es demostrar la composición de
 * múltiples GUIs reutilizables sobre un mismo modelo
 * de datos mediante un contenedor basado en pestañas.
 */

public class A2ExtraPanel extends AbstractReusablePanel {

    private final A2 model;

    private final JSpinner nivelAccesoSpinner =
            new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
    private final JCheckBox notificacionesCheck =
            new JCheckBox("Notificaciones activadas");
    private final JCheckBox temaOscuroCheck =
            new JCheckBox("Tema oscuro");

    /**
    * Construye el panel asociado a una instancia de A2.
    *
    * @param model modelo que será editado por la interfaz
    */
    public A2ExtraPanel(A2 model) {
        this.model = model;
        buildUi();
        loadFromModel();
    }

    /**
    * Construye la interfaz gráfica del panel.
    *
    * Se crean los controles necesarios para editar
    * las preferencias específicas de la entidad A2.
    */
    private void buildUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nivel de acceso:"), gbc);
        gbc.gridx = 1;
        add(nivelAccesoSpinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(notificacionesCheck, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(temaOscuroCheck, gbc);
    }

    /**
    * Carga los valores almacenados en el modelo dentro
    * de los componentes gráficos de la interfaz.
    */
    private void loadFromModel() {
        nivelAccesoSpinner.setValue(model.getNivelAcceso());
        notificacionesCheck.setSelected(model.isNotificacionesActivadas());
        temaOscuroCheck.setSelected(model.isTemaOscuro());
    }

    /**
    * Valida los datos introducidos por el usuario.
    *
    * @return true si el nivel de acceso se encuentra
    * dentro del rango permitido
    */
    @Override
    protected boolean validateSelf() {
        int nivelAcceso = (Integer) nivelAccesoSpinner.getValue();
        return nivelAcceso >= 1 && nivelAcceso <= 10;
    }

    /**
    * Aplica los cambios realizados por el usuario
    * sobre el modelo de datos asociado.
    */
    @Override
    protected void applySelf() {
        model.setNivelAcceso((Integer) nivelAccesoSpinner.getValue());
        model.setNotificacionesActivadas(notificacionesCheck.isSelected());
        model.setTemaOscuro(temaOscuroCheck.isSelected());
    }

    @Override
    public String getTitle() {
        return "Preferencias";
    }
}