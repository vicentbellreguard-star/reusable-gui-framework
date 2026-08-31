package com.tfg.reusablegui.messaging;

import com.tfg.reusablegui.api.GuiMessage;
import com.tfg.reusablegui.api.ReusableGui;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Componente encargado de distribuir mensajes entre las GUIs
 * registradas en el framework.
 *
 * Implementa un mecanismo de comunicación desacoplada basado en publicación y
 * suscripción, permitiendo que distintas interfaces intercambien información 
 * sin establecer dependencias directas entre ellas.
 *
 * Las GUIs interesadas en recibir mensajes deben registrarse
 * previamente en el router. Una vez registradas, podrán recibir
 * las notificaciones publicadas por otros componentes.
 *
 * Este mecanismo facilita la construcción de aplicaciones modulares y favorece 
 * la reutilización de interfaces dentro de composiciones complejas.
 */

public final class MessageRouter {
    // Conjunto de GUIs registradas en el sistema de mensajería.
    private final Set<ReusableGui> nodes = new CopyOnWriteArraySet<>();

    /**
    * Registra una GUI en el sistema de mensajería.
    *
    * Una vez registrada, la GUI podrá recibir mensajes
    * publicados por otros componentes.
    *
    * @param gui GUI a registrar
    */
    public void register(ReusableGui gui) {
        nodes.add(gui);
    }

    /**
    * Elimina una GUI del sistema de mensajería.
    *
    * Tras su eliminación dejará de recibir mensajes.
    *
    * @param gui GUI a eliminar
    */
    public void unregister(ReusableGui gui) {
        nodes.remove(gui);
    }

    /**
    * Publica un mensaje a todas las GUIs registradas.
    *
    * El mensaje se distribuye a todos los componentes registrados excepto al
    * emisor original, evitando notificaciones redundantes.
    *
    * @param sender GUI que origina el mensaje
    * @param message mensaje publicado
    */
    public void publish(ReusableGui sender, GuiMessage message) {
        for (ReusableGui g : nodes) {
            if (g != sender) g.onMessage(message);
        }
    }
}
