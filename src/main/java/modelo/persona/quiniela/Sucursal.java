/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.persona.quiniela;

import modelo.domicilio.Ciudad;
import modelo.persona.Vendedor;

/**
 * @author Valentin Meier
 */
public class Sucursal {

    private int numeroSucursal;
    private Ciudad ciudad;
    private PersonaQuiniela personaQuiniela;
    private Vendedor[] vendedores = new Vendedor[10];

    public Sucursal(int numeroSucursal, Ciudad ciudad, PersonaQuiniela personaQuiniela) {
        this.numeroSucursal = numeroSucursal;
        this.ciudad = ciudad;
        this.personaQuiniela = personaQuiniela;
    }

    public int getnumeroSucursal() {
        return numeroSucursal;
    }

    public int getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(int numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public PersonaQuiniela getQuiniela() {
        return personaQuiniela;
    }

    public void setQuiniela(PersonaQuiniela personaQuiniela) {
        this.personaQuiniela = personaQuiniela;
    }

    public Vendedor[] getVendedors() {
        return vendedores;
    }

    public void setVendedors(Vendedor[] vendedors) {
        this.vendedores = vendedors;
    }

    public boolean asignarVendedor(Vendedor nuevoVendedor) {
        for (int i = 0; i < vendedores.length; i++) {
            if (vendedores[i] == null) {
                vendedores[i] = nuevoVendedor;
                return true;
            }
        }
        System.out.println("Todas los vendedores de la sucursal estan ocupadas, no se ha podido agregar otro vendedor.");
        return false;
    }

    public void removerVendedor(Vendedor vendedor) {
        for (int i = 0; i < vendedores.length; i++) {
            if (vendedores[i] != null && vendedores[i].equals(vendedor)) {
                vendedores[i] = null;
                System.out.println("Sucursal removida de la quiniela anterior.");
                break;
            }
        }
    }
}
