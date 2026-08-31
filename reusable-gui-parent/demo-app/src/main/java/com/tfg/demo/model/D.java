package com.tfg.demo.model;

/**
 * Entidad que representa una configuración de permisos
 * y niveles de acceso.
 *
 * Esta clase forma parte de la estructura jerárquica
 * utilizada en la aplicación de demostración y sirve
 * como soporte para las pruebas del sistema de mensajería
 * implementado en el framework.
 *
 * Los cambios realizados sobre el nivel de acceso pueden
 * ser publicados mediante eventos y consumidos por otras
 * GUIs reutilizables registradas en el sistema.
 */

public class D {

    private String rol;
    private int nivelPotencia;
    private int minimo;
    private int maximo;
    private boolean habilitado;

    public D() {
    }

    public D(String rol, int nivelPotencia, int minimo, int maximo, boolean habilitado) {
        this.rol = rol;
        this.nivelPotencia = nivelPotencia;
        this.minimo = minimo;
        this.maximo = maximo;
        this.habilitado = habilitado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getNivelPotencia() {
        return nivelPotencia;
    }

    public void setNivelPotencia(int nivelPotencia) {
        this.nivelPotencia = nivelPotencia;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}