package com.tfg.reusablegui.core;

import com.tfg.reusablegui.api.ReusableGui;
import java.util.List;

/**
 * Representa un grupo lógico de GUIs reutilizables.
 *
 * Esta estructura se utiliza para organizar varias interfaces
 * bajo un mismo identificador, permitiendo construir composiciones
 * jerárquicas dentro del framework.
 *
 * Los grupos son utilizados principalmente por contenedores
 * jerárquicos, como TreeContainerPanel, donde cada grupo puede
 * representarse como un nodo de navegación que contiene varias
 * GUIs asociadas.
 *
 * @param name nombre lógico del grupo
 * @param children colección de GUIs pertenecientes al grupo
 */

public record ChildGroup(String name, List<ReusableGui> children) {}