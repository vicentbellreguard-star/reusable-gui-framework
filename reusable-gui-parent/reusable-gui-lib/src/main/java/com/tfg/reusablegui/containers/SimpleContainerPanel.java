package com.tfg.reusablegui.containers;

import com.tfg.reusablegui.api.ReusableGui;
import com.tfg.reusablegui.core.AbstractReusablePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Contenedor reutilizable simple.
 *
 * Permite combinar hasta dos GUIs reutilizables dentro de una misma
 * interfaz visual.
 *
 * Los componentes se muestran de forma vertical y centrada, siendo
 * adecuado para escenarios sencillos donde únicamente se requiere
 * presentar una o dos interfaces relacionadas.
 *
 * Este contenedor representa la forma más simple de composición
 * soportada por el framework.
 */

public class SimpleContainerPanel extends AbstractReusablePanel {

    // Panel que contiene las GUIs hijas.
    private final JPanel content = new JPanel();
    private int count = 0; // Contador del número de hijos insertados

    /**
    * Construye un contenedor simple vacío.
    *
    * Inicializa la estructura visual encargada de mostrar
    * las GUIs hijas de forma vertical y centrada.
    */
    public SimpleContainerPanel() {
        setLayout(new GridBagLayout());

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(content, gbc);
    }

    /**
    * Añade una GUI hija al contenedor.
    *
    * El contenedor admite un máximo de dos GUIs hijas.
    * Cada interfaz se encapsula dentro de un panel auxiliar
    * para mantener la alineación visual.
    *
    * @param child GUI hija añadida
    * @throws IllegalStateException si se intenta añadir más de dos GUIs
    */
    @Override
    protected void onChildAdded(ReusableGui child) {
        count++;

        if (count > 2) {
            throw new IllegalStateException("SimpleContainerPanel admite máximo 2 GUIs.");
        }

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        wrapper.add(child.getView());

        content.add(wrapper);

        if (count == 1) {
            content.add(Box.createVerticalStrut(15));
        }

        revalidate();
        repaint();
    }

    /**
    * No realiza ninguna acción.
    *
    * Este contenedor no mantiene estado propio ni está asociado a un 
    * modelo de datos, por lo que la operación de aplicación de cambios 
    * se delega completamente en las GUIs hijas.
    */
    @Override
    protected void applySelf() {}
}
