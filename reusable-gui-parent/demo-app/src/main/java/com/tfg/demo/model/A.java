package com.tfg.demo.model;

/**
 * Clase base del modelo de dominio.
 *
 * Representa la información común compartida por las
 * entidades A1 y A2 dentro de la aplicación de demostración.
 *
 * Esta clase se utiliza para demostrar la reutilización tanto de modelos 
 * como de interfaces gráficas mediante herencia y composición.
 */

public class A {

    private String nombre;
    private int edad;
    private String dni;
    private boolean activo;

    public A() {
    }

    /**
    * Construye una instancia completamente inicializada.
    *
    * @param nombre nombre de la persona
    * @param edad edad asociada
    * @param dni documento identificativo
    * @param activo indica si la entidad está activa
    */
    public A(String nombre, int edad, String dni, boolean activo) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}