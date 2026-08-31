package com.tfg.reusablegui.core;

import com.tfg.reusablegui.api.GuiContext;
import com.tfg.reusablegui.api.GuiMessage;
import com.tfg.reusablegui.api.ReusableGui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase abstracta base para todas las GUIs reutilizables del framework.
 *
 * Implementa la interfaz ReusableGui y proporciona soporte para:
 * - composición jerárquica de GUIs,
 * - validación recursiva,
 * - aplicación de cambios al modelo,
 * - mensajería entre componentes,
 * - inicialización y liberación de recursos.
 *
 * Las clases derivadas deben implementar al menos el método applySelf(),
 * que define cómo se guardan los datos propios del panel en el modelo.
 */

public abstract class AbstractReusablePanel extends JPanel implements ReusableGui {

    //GUIs hijas añadidas directamente al contenedor.
    private final List<ReusableGui> children = new ArrayList<>();
    //Grupos lógicos de GUIs hijas utilizados en estructuras jerárquicas.
    private final List<ChildGroup> childGroups = new ArrayList<>();
    private GuiContext context;

    /**
     * Constructor base del panel reusable.
     * Añade un margen interior común a todas las GUIs del framework.
     */
    public AbstractReusablePanel() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
     
    @Override
    public final JComponent getView() {
        return this;
    }

    /**
    * Inicializa la GUI dentro del contexto compartido del framework.
    *
    * El contexto se propaga recursivamente a todas las GUIs hijas
    * y se registra la GUI en el sistema de mensajería.
    *
    * @param context contexto compartido
    */
    @Override
    public void initReusable(GuiContext context) {
        this.context = context; //Guarda el contexto
        //Se registra en el sistema de mensajería
        if (context != null) context.router().register(this); 

        // La inicialización se propaga recursivamente
        // a toda la jerarquía de GUIs reutilizables.
        for (ReusableGui c : children) c.initReusable(context);
        for (ChildGroup g : childGroups) for (ReusableGui c : g.children()) c.initReusable(context);

        onInitReusable();
    }

    /**
    * Método gancho ejecutado al finalizar la inicialización.
    *
    * Las clases derivadas pueden sobrescribir este método para
    * realizar tareas de inicialización específicas.
    */
    protected void onInitReusable() {}

    /**
    * Añade una GUI hija a la jerarquía actual.
    *
    * @param child GUI hija a añadir
    * @throws IllegalArgumentException si child es null
    */
    @Override
    public void addChild(ReusableGui child) {
        if (child == null) throw new IllegalArgumentException("child null");
        children.add(child);
        //método hook que se ejecuta justo después de añadir un hijo a una GUI.
        onChildAdded(child); 
    }

    /**
    * Añade un grupo de GUIs hijas bajo un nombre lógico.
    *
    * @param groupName nombre del grupo
    * @param groupChildren GUIs pertenecientes al grupo
    * @throws IllegalArgumentException si los parámetros son inválidos
    */
    @Override
    public void addChildGroup(String groupName, List<ReusableGui> groupChildren) {
        if (groupName == null || groupName.isBlank()) throw new IllegalArgumentException("groupName vacío");
        if (groupChildren == null) throw new IllegalArgumentException("groupChildren null");
        childGroups.add(new ChildGroup(groupName, List.copyOf(groupChildren)));
        onChildGroupAdded(groupName, groupChildren); // Método gancho
    }

    /**
    * Método gancho ejecutado tras añadir una GUI hija.
    *
    * @param child GUI recién añadida
    */
    protected void onChildAdded(ReusableGui child) {}

    /**
    * Método gancho ejecutado tras añadir un grupo de GUIs.
    *
    * @param name nombre del grupo
    * @param groupChildren GUIs del grupo
    */
    protected void onChildGroupAdded(String name, List<ReusableGui> groupChildren) {}

    /**
    * Devuelve las GUIs hijas directas.
    *
    * @return lista inmutable de GUIs hijas
    */
    public final List<ReusableGui> children() { return Collections.unmodifiableList(children); }
    
    /**
    * Devuelve los grupos de GUIs hijas.
    *
    * @return lista inmutable de grupos
    */
    public final List<ChildGroup> childGroups() { return Collections.unmodifiableList(childGroups); }

     /**
    * Ejecuta la validación recursiva sobre toda la jerarquía.
    *
    * Primero se validan las GUIs hijas y posteriormente
    * la GUI actual.
    *
    * @return true si la validación es correcta
    */
    @Override
    public final boolean validateAll() {
        for (ReusableGui c : children) {
            c.validateAll();
        }

        for (ChildGroup g : childGroups) {
            for (ReusableGui c : g.children()) {
                c.validateAll();
            }
        }
        return validateSelf();
    }

    /**
    * Aplica los cambios de forma recursiva.
    *
    * Primero se aplican los cambios propios y posteriormente
    * los de todas las GUIs hijas.
    */
    @Override
    public final void applyAll() {
        // aplica yo
        applySelf();
        // aplica hijos
        for (ReusableGui c : children) c.applyAll();
        for (ChildGroup g : childGroups) for (ReusableGui c : g.children()) c.applyAll();
    }

    /**
    * Cancela los cambios pendientes en toda la jerarquía.
    */
    @Override
    public void cancelAll() {
        cancelSelf();
        for (ReusableGui c : children) c.cancelAll();
        for (ChildGroup g : childGroups) for (ReusableGui c : g.children()) c.cancelAll();
    }

    /**
    * Libera los recursos asociados a la GUI y a todas las
    * GUIs hijas registradas.
    */
    @Override
    public void disposeAll() {
        disposeSelf();
        for (ReusableGui c : children) c.disposeAll();
        for (ChildGroup g : childGroups) for (ReusableGui c : g.children()) c.disposeAll();
        if (context != null) context.router().unregister(this);
    }

    /** Opcional: por defecto no valida nada */
    /**
     * Valida únicamente los datos propios de esta GUI.
     *
     * @return true si los datos propios son válidos
     */
    protected boolean validateSelf() { return true; }

    /**
    * Aplica los cambios propios de la GUI al modelo de datos.
    *
    * Este método debe ser implementado por todas las clases
    * derivadas.
    */
    protected abstract void applySelf();

    /**
    * Cancela los cambios propios de la GUI.
    *
    * La implementación por defecto no realiza ninguna acción.
    */
    protected void cancelSelf() {}
    
    /**
    * Libera los recursos propios de la GUI.
    *
    * La implementación por defecto no realiza ninguna acción.
    */
    protected void disposeSelf() {}

    /**
    * Envía un mensaje a otras GUIs a través del sistema de mensajería.
    *
    * @param message mensaje que se desea publicar
    */
    protected final void publish(GuiMessage message) {
        if (context != null) context.router().publish(this, message);
    }
}
