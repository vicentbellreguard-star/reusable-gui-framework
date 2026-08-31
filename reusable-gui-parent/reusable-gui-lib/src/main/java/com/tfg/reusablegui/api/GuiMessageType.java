package com.tfg.reusablegui.api;

/**
 * Enumeración que define los tipos de mensajes soportados
 * por el sistema de mensajería del framework.
 *
 * Los valores de esta enumeración permiten clasificar los
 * mensajes intercambiados entre GUIs reutilizables, facilitando
 * que cada componente pueda reaccionar únicamente a los eventos
 * que resulten relevantes para su funcionamiento.
 */

public enum GuiMessageType {
     
    VALUE_CHANGED, // Indica que se ha producido una modificación en algún dato.
    CUSTOM // Representa un mensaje personalizado definido por la aplicación.
}
