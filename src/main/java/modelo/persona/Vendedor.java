/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.persona;

import java.time.LocalDate;
import modelo.domicilio.Domicilio;
import modelo.persona.quiniela.Sucursal;
import repositorio.RepositorioQuiniela;

/**
 *
 * @author Valentin Meier
 */
public class Vendedor extends Persona {

    private String codigo;
    private String cuil;
    private Sucursal sucursal;

    public Vendedor(String cuil, Sucursal sucursal, String email, String nombre, String apellido, int dni, Domicilio domicilio) {
        super(nombre, apellido, email, dni, domicilio);
        this.codigo = generarCodigo();
        this.cuil = cuil;
        this.sucursal = sucursal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String generarCodigo() {
        LocalDate currentDate
                = LocalDate.now();
        return "V-" + currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + currentDate.getYear() + "-" + contarVendedores();
    }


    private int contarVendedores() {
        int contar = 0;
        for (int i = 0; i < RepositorioQuiniela.vendedores.length; i++) {
            if (RepositorioQuiniela.vendedores[i] instanceof Vendedor)
                contar++;
        }
        return contar;
    }


}
