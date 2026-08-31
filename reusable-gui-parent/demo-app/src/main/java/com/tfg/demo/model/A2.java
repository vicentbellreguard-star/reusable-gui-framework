package com.tfg.demo.model;

/**
 * Entidad derivada de A que incorpora preferencias de configuración.
 *
 * Esta clase amplía los datos básicos definidos en A
 * añadiendo información relacionada con la configuración
 * del usuario, como el nivel de acceso y diversas opciones
 * de personalización.
 *
 * Se utiliza en la aplicación de demostración para mostrar
 * la reutilización de interfaces gráficas sobre modelos
 * relacionados mediante herencia.
 */

public class A2 extends A {

    private int nivelAcceso;
    private boolean notificacionesActivadas;
    private boolean temaOscuro;

    public A2() {
    }

    public A2(String nombre, int edad, String dni, boolean activo,
              int nivelAcceso, boolean notificacionesActivadas, boolean temaOscuro) {
        super(nombre, edad, dni, activo);
        this.nivelAcceso = nivelAcceso;
        this.notificacionesActivadas = notificacionesActivadas;
        this.temaOscuro = temaOscuro;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public boolean isNotificacionesActivadas() {
        return notificacionesActivadas;
    }

    public void setNotificacionesActivadas(boolean notificacionesActivadas) {
        this.notificacionesActivadas = notificacionesActivadas;
    }

    public boolean isTemaOscuro() {
        return temaOscuro;
    }

    public void setTemaOscuro(boolean temaOscuro) {
        this.temaOscuro = temaOscuro;
    }
}