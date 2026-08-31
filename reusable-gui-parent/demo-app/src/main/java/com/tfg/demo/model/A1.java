package com.tfg.demo.model;

/**
 * Entidad derivada de A que incorpora información laboral.
 *
 * Esta clase amplía los datos básicos definidos en A
 * añadiendo el puesto desempeñado y el salario asociado.
 *
 * Se utiliza en la aplicación de demostración para
 * ejemplificar la reutilización de interfaces gráficas
 * sobre modelos relacionados mediante herencia.
 */

public class A1 extends A {

    private String puesto;
    private double salario;

    public A1() {
    }

    /**
    * Construye una instancia completamente inicializada.
    *
    * @param nombre nombre de la persona
    * @param edad edad asociada
    * @param dni documento identificativo
    * @param activo indica si la entidad está activa
    * @param puesto puesto desempeñado
    * @param salario salario asociado al puesto
    */
    public A1(String nombre, int edad, String dni, boolean activo,
              String puesto, double salario) {
        super(nombre, edad, dni, activo);
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}