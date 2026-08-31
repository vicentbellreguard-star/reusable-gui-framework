package com.tfg.reusablegui.api;

import java.util.Map;

/**
 * Representa un mensaje intercambiado entre GUIs del framework.
 *
 * Esta clase encapsula la información necesaria para que
 * dos interfaces reutilizables puedan comunicarse de forma
 * desacoplada mediante el sistema de mensajería.
 *
 * Cada mensaje está formado por:
 *  - un tipo de mensaje,
 *  - un tópico o identificador lógico.
 *  - un conjunto de datos asociados.
 *
 * Los mensajes son distribuidos por el componente
 * MessageRouter a todas las GUIs registradas.
 */

public final class GuiMessage {
    private final GuiMessageType type;
    private final String topic;
    // Datos transportados por el mensaje.
    private final Map<String, Object> payload;

    /**
    * Construye un nuevo mensaje.
    *
    * @param type tipo del mensaje
    * @param topic tópico asociado
    * @param payload datos transportados
    */
    public GuiMessage(GuiMessageType type, String topic, Map<String, Object> payload) {
        this.type = type;
        this.topic = topic;
        this.payload = payload;
    }

    public GuiMessageType type() { return type; }
    public String topic() { return topic; }
    public Map<String, Object> payload() { return payload; }
}