package com.tfg.reusablegui.containers;

import com.tfg.reusablegui.api.ReusableGui;
import com.tfg.reusablegui.core.AbstractReusablePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Contenedor reutilizable basado en pestañas.
 *
 * Permite organizar varias GUIs reutilizables dentro de un componente 
 * JTabbedPane, proporcionando una navegación estructurada mediante pestañas.
 *
 * Cada GUI hija se representa como una pestaña independiente,
 * permitiendo al usuario alternar entre distintas secciones
 * de la interfaz sin necesidad de abrir nuevas ventanas.
 *
 * Este contenedor resulta especialmente útil cuando la información puede 
 * dividirse en categorías o apartados relacionados entre sí.
 */

public class TabbedContainerPanel extends AbstractReusablePanel {

    // Componente encargado de gestionar las pestañas.
    private final JTabbedPane tabs = new JTabbedPane();

    /**
    * Construye un contenedor basado en pestañas vacío.
    *
    * Inicializa el componente JTabbedPane que actuará como
    * contenedor principal de las GUIs hijas.
    */
    public TabbedContainerPanel() {
        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
    }

    /**
    * Añade una GUI hija como una nueva pestaña.
    *
    * El título de la pestaña se obtiene mediante
    * el método getTitle() de la GUI añadida.
    *
    * @param child GUI hija añadida al contenedor
    */
    @Override
    protected void onChildAdded(ReusableGui child) {
        tabs.addTab(child.getTitle(), child.getView());
    }

    /**
    * Añade un grupo de GUIs reutilizables al contenedor.
    *
    * Cada GUI perteneciente al grupo se incorpora como una pestaña 
    * independiente dentro del componente JTabbedPane.
    *
    * @param name nombre lógico del grupo
    * @param groupChildren GUIs pertenecientes al grupo
    */   
    @Override
    protected void onChildGroupAdded(String name, List<ReusableGui> groupChildren) {
        for (ReusableGui gui : groupChildren) {
            tabs.addTab(gui.getTitle(), gui.getView());
        }
    }

    /**
    * No realiza ninguna acción.
    *
    * El contenedor únicamente actúa como mecanismo de composición visual y 
    * no mantiene información propia asociada a un modelo de datos.
    */
    @Override
    protected void applySelf() {}
}