package com.tfg.demo.gui;

import com.tfg.demo.model.B;
import com.tfg.reusablegui.api.GuiMessage;
import com.tfg.reusablegui.api.GuiMessageType;
import com.tfg.reusablegui.core.AbstractReusablePanel;
import javax.swing.*;
import java.awt.*;

/**
 * GUI reutilizable asociada a la entidad B.
 *
 * Este panel permite editar la información general de una
 * configuración y demuestra el mecanismo de comunicación
 * desacoplada implementado por el framework.
 *
 * La interfaz recibe notificaciones procedentes de DPanel
 * mediante el sistema de mensajería, actualizando
 * automáticamente determinados valores sin necesidad
 * de mantener referencias directas entre componentes.
 */

public class BPanel extends AbstractReusablePanel {

    // Tópico utilizado para notificar cambios en el nivel de acceso.
    public static final String TOPIC_NIVEL_ACCESO_CAMBIADO = "nivel-acceso-cambiado";

    // Modelo de datos asociado a la interfaz.
    private final B model;

    private final JTextField tituloField = new JTextField(20);
    private final JSpinner prioridadSpinner =
            new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
    private final JSpinner evaluacionSpinner =
            new JSpinner(new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1));
    private final JCheckBox bloqueadoCheck = new JCheckBox("Perfil bloqueado");
    private final JTextField departamentoField = new JTextField(20);

    private final JSpinner potenciaReflejadaSpinner =
            new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

    /**
    * Construye el panel asociado a una instancia de B.
    *
    * @param model modelo que será editado por la interfaz
    */
    public BPanel(B model) {
        this.model = model;
        buildUi();
        loadFromModel();
    }

    /**
    * Construye la interfaz gráfica del panel.
    *
    * Se crean los controles necesarios para editar los
    * atributos principales de la entidad B.
    */
    private void buildUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre de configuración:"), gbc);
        gbc.gridx = 1;
        add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Prioridad:"), gbc);
        gbc.gridx = 1;
        add(prioridadSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Evaluación media:"), gbc);
        gbc.gridx = 1;
        add(evaluacionSpinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(bloqueadoCheck, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1;
        add(departamentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Nivel de acceso asignado:"), gbc);
        gbc.gridx = 1;
        add(potenciaReflejadaSpinner, gbc);
    }

    /**
    * Carga los valores almacenados en el modelo dentro
    * de los componentes gráficos.
    */
    private void loadFromModel() {
        tituloField.setText(model.getConfiguracion());
        prioridadSpinner.setValue(model.getPrioridad());
        evaluacionSpinner.setValue(model.getEvaluacion());
        bloqueadoCheck.setSelected(model.isBloqueado());
        departamentoField.setText(model.getDepartamento());
        potenciaReflejadaSpinner.setValue(model.getPotenciaReflejada());
    }

    /**
    * Procesa los mensajes recibidos desde otras GUIs.
    *
    * Cuando se recibe una notificación indicando un cambio
    * en el nivel de acceso, el valor mostrado en la interfaz
    * se actualiza automáticamente.
    *
    * @param message mensaje recibido
    */
    @Override
    public void onMessage(GuiMessage message) {
        if (message.type() == GuiMessageType.VALUE_CHANGED
                && TOPIC_NIVEL_ACCESO_CAMBIADO.equals(message.topic())) {

            Object value = message.payload().get("nivelAcceso");

            if (value instanceof Integer nivelAcceso) {
                potenciaReflejadaSpinner.setValue(nivelAcceso);
            }
        }
    }

    /**
    * Valida los datos introducidos por el usuario.
    *
    * @return true si los campos obligatorios contienen datos
    */
    @Override
    protected boolean validateSelf() {
        return !tituloField.getText().trim().isEmpty()
                && !departamentoField.getText().trim().isEmpty();
    }

    /**
    * Aplica los cambios realizados por el usuario
    * sobre el modelo asociado.
    */
    @Override
    protected void applySelf() {
        model.setConfiguracion(tituloField.getText().trim());
        model.setPrioridad((Integer) prioridadSpinner.getValue());
        model.setEvaluacion(((Number) evaluacionSpinner.getValue()).doubleValue());
        model.setBloqueado(bloqueadoCheck.isSelected());
        model.setDepartamento(departamentoField.getText().trim());
        model.setPotenciaReflejada((Integer) potenciaReflejadaSpinner.getValue());
    }

    @Override
    public String getTitle() {
        return "Perfil general";
    }
}