package com.tfg.reusablegui.api;

import javax.swing.JComponent;
import java.util.List;

/**
 * Interfaz principal del framework de GUIs reutilizables.
 *
 * Define el contrato común que deben cumplir todos los componentes
 * gráficos reutilizables del sistema.
 *
 * Una GUI reutilizable puede:
 * - inicializarse dentro de un contexto compartido,
 * - contener otras GUIs hijas,
 * - validar sus datos,
 * - aplicar cambios al modelo,
 * - comunicarse con otras GUIs mediante mensajes.
 */

public interface ReusableGui {

    /**
     * Devuelve el componente gráfico Swing asociado a la GUI reusable.
     *
     * @return componente visual de la GUI
     */
    JComponent getView();
    
    /**
     * Inicializa la GUI dentro del contexto del framework.
     *
     * @param context contexto compartido del sistema
     */
    void initReusable(GuiContext context);

    /**
     * Añade una GUI hija a la jerarquía actual.
     *
     * @param child GUI hija a añadir
     */
    void addChild(ReusableGui child);

    /**
     * Añade un grupo de GUIs hijas bajo un mismo nombre lógico.
     *
     * @param groupName nombre del grupo
     * @param children lista de GUIs hijas
     */
    void addChildGroup(String groupName, List<ReusableGui> children);


    /**
     * Valida esta GUI y todas las GUIs hijas asociadas.
     *
     * @return true si la validación completa es correcta; false en caso contrario
     */
    boolean validateAll();

    /**
     * Aplica los cambios al modelo de esta GUI y de toda su jerarquía hija.
     */
    void applyAll();

    /**
     * Cancela los cambios pendientes de esta GUI y de sus posibles GUIs hijas.
     *
     * La implementación por defecto no realiza ninguna acción, permitiendo que
     * las clases concretas sobrescriban este método únicamente cuando necesiten
     * restaurar estado o descartar modificaciones temporales.
     */
    default void cancelAll() {}


    /**
     * Libera recursos asociados a esta GUI y sus hijas.
     */
    default void disposeAll() {}

    /**
     * Recibe un mensaje procedente de otra GUI del sistema.
     *
     * @param message mensaje recibido
     */
    default void onMessage(GuiMessage message) {}

     /**
     * Devuelve el título descriptivo de la GUI.
     *
     * Este título puede ser utilizado por contenedores visuales, como pestañas
     * o árboles de navegación, para identificar la interfaz.
     *
     * @return título de la GUI
     */
    default String getTitle() {
        return getClass().getSimpleName();
    }
}