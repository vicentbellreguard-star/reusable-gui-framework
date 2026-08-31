package com.tfg.demo.gui;

import com.tfg.demo.model.D;
import com.tfg.reusablegui.api.GuiMessage;
import com.tfg.reusablegui.api.GuiMessageType;
import com.tfg.reusablegui.core.AbstractReusablePanel;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * GUI reutilizable asociada a la entidad D.
 *
 * Este panel permite configurar los permisos y niveles
 * de acceso asociados a un determinado perfil.
 *
 * Además de la edición de datos, esta interfaz demuestra
 * el funcionamiento del sistema de mensajería del framework,
 * publicando eventos cuando se producen cambios en el nivel
 * de acceso seleccionado por el usuario.
 *
 * Dichos eventos pueden ser recibidos por otras GUIs
 * registradas en el sistema, permitiendo la sincronización
 * automática de información entre componentes desacoplados.
 */

public class DPanel extends AbstractReusablePanel {

    // Modelo de datos asociado a la interfaz.
    private final D model;

    private final JTextField rolField = new JTextField(20);
    private final JSlider nivelAccesoSlider = new JSlider(0, 100, 0);
    private final JSpinner minimoSpinner =
            new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
    private final JSpinner maximoSpinner =
            new JSpinner(new SpinnerNumberModel(100, -1000, 1000, 1));
    private final JCheckBox habilitadoCheck = new JCheckBox("Habilitado");

    /**
    * Construye el panel asociado a una instancia de D.
    *
    * @param model modelo que será editado por la interfaz
    */
    public DPanel(D model) {
        this.model = model;
        buildUi();
        loadFromModel();
        connectEvents();
    }

    /**
    * Construye la interfaz gráfica del panel.
    *
    * Se crean los controles necesarios para gestionar
    * permisos, niveles de acceso y restricciones asociadas.
    */
    private void buildUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nivelAccesoSlider.setMajorTickSpacing(20);
        nivelAccesoSlider.setPaintTicks(true);
        nivelAccesoSlider.setPaintLabels(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Rol:"), gbc);
        gbc.gridx = 1;
        add(rolField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nivel de acceso:"), gbc);
        gbc.gridx = 1;
        add(nivelAccesoSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Mínimo:"), gbc);
        gbc.gridx = 1;
        add(minimoSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Máximo:"), gbc);
        gbc.gridx = 1;
        add(maximoSpinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(habilitadoCheck, gbc);
    }

    /**
    * Carga los valores almacenados en el modelo dentro
    * de los componentes gráficos de la interfaz.
    */
    private void loadFromModel() {
        rolField.setText(model.getRol());
        nivelAccesoSlider.setValue(model.getNivelPotencia());
        minimoSpinner.setValue(model.getMinimo());
        maximoSpinner.setValue(model.getMaximo());
        habilitadoCheck.setSelected(model.isHabilitado());
    }

    /**
    * Registra los eventos necesarios para la comunicación entre componentes.
    *
    * Cuando el usuario modifica el nivel de acceso mediante
    * el control deslizante, se publica un mensaje para que
    * otras GUIs interesadas puedan reaccionar al cambio.
    */
    private void connectEvents() {
        nivelAccesoSlider.addChangeListener(e -> {
            int nivelAcceso = nivelAccesoSlider.getValue();

            publish(new GuiMessage(
                    GuiMessageType.VALUE_CHANGED,
                    BPanel.TOPIC_NIVEL_ACCESO_CAMBIADO,
                    Map.of("nivelAcceso", nivelAcceso)
            ));
        });
    }

    /**
    * Valida los datos introducidos por el usuario.
    *
    * @return true si el rol es válido y el valor mínimo
    * no supera al máximo permitido
    */
    @Override
    protected boolean validateSelf() {
        String rol = rolField.getText().trim();
        int minimo = (Integer) minimoSpinner.getValue();
        int maximo = (Integer) maximoSpinner.getValue();

        return !rol.isEmpty() && minimo <= maximo;
    }

    /**
    * Aplica los cambios realizados por el usuario sobre el modelo asociado.
    */
    @Override
    protected void applySelf() {
        model.setRol(rolField.getText().trim());
        model.setNivelPotencia(nivelAccesoSlider.getValue());
        model.setMinimo((Integer) minimoSpinner.getValue());
        model.setMaximo((Integer) maximoSpinner.getValue());
        model.setHabilitado(habilitadoCheck.isSelected());
    }

    @Override
    public String getTitle() {
        return "Permisos";
    }
}