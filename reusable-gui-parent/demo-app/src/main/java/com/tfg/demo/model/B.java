package com.tfg.demo.model;

/**
 * Entidad que representa una configuración compuesta
 * formada por varios objetos relacionados.
 *
 * Esta clase agrega instancias de A1, A2 y C, permitiendo
 * demostrar la edición jerárquica de objetos mediante el
 * contenedor TreeContainerPanel.
 *
 * Su estructura sirve como ejemplo de composición de modelos
 * complejos y constituye la base del escenario de prueba
 * utilizado para validar la navegación jerárquica del framework.
 */

public class B {

    private String configuracion;
    private int prioridad;
    private int potenciaReflejada;
    private double evaluacion;
    private boolean bloqueado;
    private String departamento;

    private A1 a1;
    private A2 a2;
    private C c;

    public B() {
    }

    public B(String configuracion, int prioridad, double evaluacion, boolean bloqueado, String departamento, int potenciaReflejada,
             A1 a1, A2 a2, C c) {
        this.configuracion = configuracion;
        this.prioridad = prioridad;
        this.evaluacion = evaluacion;
        this.bloqueado = bloqueado;
        this.departamento = departamento;
        this.a1 = a1;
        this.a2 = a2;
        this.c = c;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public A1 getA1() {
        return a1;
    }

    public void setA1(A1 a1) {
        this.a1 = a1;
    }

    public A2 getA2() {
        return a2;
    }

    public void setA2(A2 a2) {
        this.a2 = a2;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
    
    public int getPotenciaReflejada() {
    return potenciaReflejada;
    }

    public void setPotenciaReflejada(int potenciaReflejada) {
        this.potenciaReflejada = potenciaReflejada;
    }
}