/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.apuesta;

import modelo.gestores.*;
import modelo.persona.Cliente;
import modelo.persona.Vendedor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * @author Valentin Meier
 */
public class Apuesta {

    public void crear() {
        GestorClientes clientes = new GestorClientes();
        GestorQuinielas quinielas = new GestorQuinielas();
        GestorSucursales sucursales = new GestorSucursales();
        GestorVendedores vendedores = new GestorVendedores();
        GestorCiudades ciudades = new GestorCiudades();

        Scanner input = new Scanner(System.in);

        if (!quinielas.existenQuinielas()) {
            System.out.println("No existen quinielas en la DB. No se puede crear una apuesta ");
            return;
        }

        if (!clientes.existenClientes()) {
            System.out.println("No existen clientes en la DB. No se puede crear una apuesta");
            return;
        }


        if (!sucursales.existenSucursales()) {
            System.out.println("No existen sucursales en la DB. No se puede crear una apuesta");
            return;
        }

        if (!vendedores.existenVendedores()) {
            System.out.println("No existen vendedores en la DB. No se puede crear una apuesta");
            return;
        }

        if (!ciudades.existenCiudades()) {
            System.out.println("No existen ciudades en la db.No se puede crear una apuesta");
            return;
        }


        System.out.println("Seleccióna el cliente:");
        clientes.listar();
        int seleccion = input.nextInt();
        input.nextLine();
        Cliente clienteElegido = clientes.buscarCliente(seleccion);

        System.out.println("Seleccióna el vendedor:");
        vendedores.listar();
        seleccion = input.nextInt();
        input.nextLine();
        Vendedor vendedorElegido = vendedores.buscarVendedor(seleccion);

        if (clienteElegido == null && vendedorElegido == null) {
            System.out.println(" No se ha podido realizar la apuesta");
        } else {
            Jugada nuevaJugada = new Jugada();
            nuevaJugada.iniciarJugada();

            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String fechaHoraFormateada = fechaHoraActual.format(formato);
            assert clienteElegido != null;
            System.out.println("=============================================================================\n"
                    + fechaHoraFormateada
                    + "\t"
                    + "Quiniela: " + vendedorElegido.getSucursal().getQuiniela().getNombre()
                    + "\t"
                    + "Sucursal: " + vendedorElegido.getSucursal().getNumeroSucursal()
                    + "\n" + "Sorteo: " + nuevaJugada.getTipoDeSorteo() + "\n"
                    + "Vendedor: " + vendedorElegido.getNombre() + " " + vendedorElegido.getApellido()
                    + "\n" + "Comprador= " + clienteElegido.getNombre() + " " + clienteElegido.getApellido()
                    + "\n=============================================================================\n"
                    + "\t" + "Apuesta"
                    + "\n=============================================================================\n"
                    + "Importe" + "\t" + "Número" + "\t" + "Posición\n"
                    + nuevaJugada.imprimirJugada()
                    + "\n=============================================================================\n"
                    + "Monto total:" + nuevaJugada.getTotalApuesta()
            );
        }

    }


    public void iniciarApuesta() {
        mostrarMenu();
        boolean menu = true;
        do {
            Scanner input = new Scanner(System.in);
            if (!input.hasNextInt()) {
                System.out.println(" Selecciona un número valido");
            } else {
                switch (input.nextInt()) {
                    case 1:
                        crear();
                        mostrarMenu();
                        break;
                    case 2:
                        menu = false;
                        break;
                    default:
                        System.out.println(" Selecciona un número valido");
                }
            }
        } while (menu);

    }

    public static void mostrarMenu() {
        System.out.println("""
                 Menú apuesta:
                1- Crear apuesta
                2- Ir al menú inicial""");
    }
}
