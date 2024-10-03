/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.persona.quiniela;

import modelo.domicilio.Domicilio;
import modelo.persona.Persona;

/**
 * @author Valentin Meier
 */
public class PersonaQuiniela extends Persona {

    private String RazonSocial;
    private String cuit;
    private Sucursal[] sucursales = new Sucursal[10];

    public PersonaQuiniela(String nombre, String apellido, String email, int dni, Domicilio domicilio) {
        super(nombre, apellido, email, dni, domicilio);
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Sucursal[] getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursal[] sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public String generarCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean asignarSucursal(Sucursal nuevaSucursal) {
        for (int i = 0; i < sucursales.length; i++) {
            if (sucursales[i] == null) {
                sucursales[i] = nuevaSucursal;
                return true;
            }
        }
        System.out.println("Todas las sucursales de la quiniela ocupadas, no se ha podido agregar otra sucursal.");
        return false;
    }

    public void removerSucursal(Sucursal sucursal) {
        for (int i = 0; i < sucursales.length; i++) {
            if (sucursales[i] != null && sucursales[i].equals(sucursal)) {
                sucursales[i] = null;
                System.out.println("Sucursal removida de la quiniela anterior.");
                break;
            }
        }
    }

}
