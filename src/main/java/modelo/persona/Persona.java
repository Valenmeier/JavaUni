/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.persona;

import modelo.domicilio.Domicilio;

/**
 *
 * @author Valentin Meier
 */
public abstract class Persona {

    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    private Domicilio domicilio;


    public Persona(String nombre, String apellido, String email, int dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }


    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public abstract String generarCodigo();

}
