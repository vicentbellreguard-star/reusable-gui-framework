package com.tfg.demo.model;

/**
 * Entidad que representa información profesional asociada
 * a una configuración determinada.
 *
 * Esta clase forma parte de la estructura jerárquica utilizada
 * en la aplicación de demostración y contiene una instancia
 * de la entidad D.
 *
 * Su objetivo es ilustrar la composición de objetos dentro
 * del modelo de dominio y servir como base para las pruebas
 * de navegación jerárquica implementadas mediante TreeContainerPanel.
 */

public class C {

    private String departamento;
    private int nivelProfesional;
    private double evaluacion;
    private boolean participa;
    private String observaciones;

    private D d;

    public C() {
    }

    public C(String departamento, int nivel, double evaluacion,
             boolean participa, String observaciones, D d) {
        this.departamento = departamento;
        this.nivelProfesional = nivel;
        this.evaluacion = evaluacion;
        this.participa = participa;
        this.observaciones = observaciones;
        this.d = d;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getNivel() {
        return nivelProfesional;
    }

    public void setNivel(int nivelProfesional) {
        this.nivelProfesional = nivelProfesional;
    }

    public double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public boolean isVisible() {
        return participa;
    }

    public void setVisible(boolean participa) {
        this.participa = participa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }
}