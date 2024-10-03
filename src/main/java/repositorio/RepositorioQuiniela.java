/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import modelo.persona.quiniela.PersonaQuiniela;
import modelo.domicilio.Ciudad;
import modelo.persona.Cliente;
import modelo.persona.Vendedor;
import modelo.persona.quiniela.Sucursal;

/**
 * @author Valentin Meier
 */
public abstract class RepositorioQuiniela {

    public static final int TAMANIO_PERSONA = 10;

    public static Cliente[] clientes = new Cliente[TAMANIO_PERSONA];
    public static Vendedor[] vendedores = new Vendedor[TAMANIO_PERSONA];
    public static Ciudad[] ciudades = new Ciudad[TAMANIO_PERSONA];
    public static PersonaQuiniela[] quinielas = new PersonaQuiniela[TAMANIO_PERSONA];
    public static Sucursal[] sucursales = new Sucursal[TAMANIO_PERSONA];
}
