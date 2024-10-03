package modelo.gestores;

import java.util.Scanner;


public class Gestor {
    public Gestor() {
    }

    public void iniciarGestor() {
        mostrarMenu();
        boolean menu = true;
        do {
            Scanner input = new Scanner(System.in);
            if (!input.hasNextInt()) {
                System.out.println(" Selecciona un número valido");
            } else {
                switch (input.nextInt()) {
                    case 1:
                        gestionarClientes();
                        mostrarMenu();
                        break;
                    case 2:
                        gestionarVendedores();
                        mostrarMenu();
                        break;
                    case 3:
                        gestionarQuinielas();
                        mostrarMenu();
                        break;
                    case 4:
                        gestionarSucursales();
                        mostrarMenu();
                        break;
                    case 5:
                        gestionarCiudades();
                        mostrarMenu();
                        break;
                    case 6:
                        menu = false;
                        break;
                    default:
                        System.out.println(" Selecciona un número valido");
                }
            }
        } while (menu);

    }

    public void gestionarClientes() {
        GestorClientes gestor = new GestorClientes();
        gestor.iniciarGestor();
    }

    public void gestionarVendedores() {
        GestorVendedores gestor = new GestorVendedores();
        gestor.iniciarGestor();
    }

    public void gestionarQuinielas() {
        GestorQuinielas gestor = new GestorQuinielas();
        gestor.iniciarGestor();
    }

    public void gestionarSucursales() {
        GestorSucursales gestor = new GestorSucursales();
        gestor.iniciarGestor();
    }


    public void gestionarCiudades() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.iniciarGestor();
    }


    public void mostrarMenu() {
        System.out.println("""
                 Menú gestiones:
                1- Gestionar clientes
                2- Gestionar vendedores
                3- Gestionar quinielas
                4- Gestionar sucursales
                5- Gestionar ciudades
                6- Ir al menú inicial""");
    }
}
