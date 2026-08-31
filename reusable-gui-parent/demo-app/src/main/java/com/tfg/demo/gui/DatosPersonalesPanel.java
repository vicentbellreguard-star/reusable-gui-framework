package com.tfg.demo.gui;

import com.tfg.reusablegui.core.AbstractReusablePanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * GUI reutilizable generada mediante la plantilla
 * "Reusable GUI Panel" proporcionada por el plugin
 * de NetBeans.
 *
 * Esta clase se utiliza como ejemplo de personalización
 * de una interfaz creada automáticamente a partir
 * de la plantilla del framework.
 *
 * Su objetivo es demostrar que los desarrolladores
 * pueden generar nuevas GUIs reutilizables con una
 * estructura inicial ya preparada para integrarse
 * en el framework.
 */

public class DatosPersonalesPanel extends AbstractReusablePanel {

    private final JTextField nombreField = new JTextField(20);
    private final JSpinner edadSpinner =
            new JSpinner(new SpinnerNumberModel(25, 0, 120, 1));
    private final JTextField dniField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JCheckBox activoCheck = new JCheckBox();
    private final JTextArea notasArea = new JTextArea(4, 25);

    public DatosPersonalesPanel() {
        buildUi();
    }

    private void buildUi() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new GridLayout(2, 1, 0, 4));

        JLabel title = new JLabel("Formulario de datos personales");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        JLabel subtitle = new JLabel(
                "GUI reutilizable creada mediante la plantilla Reusable GUI Panel."
        );

        header.add(title);
        header.add(subtitle);

        return header;
    }

    private JPanel createContentPanel() {
        JPanel content = new JPanel(new GridLayout(1, 2, 15, 0));

        content.add(createDatosPersonalesPanel());
        content.add(createInfoPanel());

        return content;
    }

    private JPanel createDatosPersonalesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Datos personales",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addRow(panel, gbc, 0, "Nombre:", nombreField);
        addRow(panel, gbc, 1, "Edad:", edadSpinner);
        addRow(panel, gbc, 2, "DNI:", dniField);
        addRow(panel, gbc, 3, "Email:", emailField);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(estadoLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panel.add(estadoLabel, gbc);

        JPanel estadoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        estadoPanel.add(activoCheck);
        estadoPanel.add(new JLabel("Activo"));

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(estadoPanel, gbc);

        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));

        JTextArea infoArea = new JTextArea(
                "Esta ventana representa una GUI reutilizable creada "
                        + "a partir de la plantilla del plugin de NetBeans.\n\n"
                        + "Permite demostrar la personalización de una interfaz "
                        + "mediante componentes Swing estándar."
        );
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Información"));
        infoPanel.add(infoArea, BorderLayout.CENTER);

        notasArea.setLineWrap(true);
        notasArea.setWrapStyleWord(true);
        notasArea.setText("Notas adicionales...");

        JPanel notasPanel = new JPanel(new BorderLayout());
        notasPanel.setBorder(BorderFactory.createTitledBorder("Notas"));
        notasPanel.add(new JScrollPane(notasArea), BorderLayout.CENTER);

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(notasPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton guardarButton = new JButton("Guardar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton limpiarButton = new JButton("Limpiar");

        limpiarButton.addActionListener(e -> limpiarCampos());

        panel.add(guardarButton);
        panel.add(cancelarButton);
        panel.add(limpiarButton);

        return panel;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row,
                        String labelText, JComponent component) {
        JLabel label = new JLabel(labelText);
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(component, gbc);
    }

    private void limpiarCampos() {
        nombreField.setText("");
        edadSpinner.setValue(25);
        dniField.setText("");
        emailField.setText("");
        activoCheck.setSelected(false);
        notasArea.setText("");
    }

    @Override
    protected boolean validateSelf() {
        String nombre = nombreField.getText().trim();
        String dni = dniField.getText().trim();

        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre vacío");
        }

        if (!dni.matches("\\d{8}[A-Za-z]")) {
            throw new IllegalArgumentException("DNI inválido");
        }

        return true;
    }

    @Override
    protected void applySelf() {
        // En esta prueba visual no se aplica sobre un modelo real.
    }

    @Override
    public String getTitle() {
        return "Datos personales";
    }
}