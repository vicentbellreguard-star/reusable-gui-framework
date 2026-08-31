package com.tfg.reusablegui.api;

import com.tfg.reusablegui.messaging.MessageRouter;

/**
 * Proporciona el contexto compartido entre las GUIs reutilizables
 * que forman parte de una misma composición.
 *
 * Esta clase actúa como punto de acceso a los servicios comunes
 * del framework, permitiendo que las distintas interfaces puedan
 * compartir recursos sin establecer dependencias directas entre sí.
 *
 * En la implementación actual, el contexto proporciona acceso al
 * sistema de mensajería representado por MessageRouter, utilizado
 * para la comunicación desacoplada entre componentes gráficos.
 */

public final class GuiContext {
    // Sistema de mensajería compartido por las GUIs.
    private final MessageRouter router;

     /**
     * Construye un nuevo contexto compartido.
     *
     * @param router sistema de mensajería asociado al contexto
     */
    public GuiContext(MessageRouter router) {
        this.router = router;
    }

      /**
     * Devuelve el sistema de mensajería asociado al contexto.
     *
     * @return router de mensajes
     */
    public MessageRouter router() {
        return router;
    }
}
