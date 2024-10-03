package modelo;

import modelo.apuesta.Apuesta;
import modelo.gestores.Gestor;

import java.util.Scanner;

/**
 * @author Valentin Meier
 */
public class Quiniela {

    public static void main(String[] args) {
        boolean menu = true;
        mostrarMenu();
        do {
            Scanner input = new Scanner(System.in);
            if (!input.hasNextInt()) {
                System.out.println("La opcion seleccionada no es valida, porfavor selecciona nuevamente");
            } else {

                switch (input.nextInt()) {
                    case 1:
                        Gestor gestor = new Gestor();
                        gestor.iniciarGestor();
                        mostrarMenu();
                        break;
                    case 2:
                        Apuesta apuesta = new Apuesta();
                        apuesta.iniciarApuesta();
                        mostrarMenu();
                        break;
                    case 3:
                        System.out.println("""
                                =========================
                                Saliendo del programa...
                                =========================
                                ¡Ten un buen dia!
                                =========================""");
                        menu = false;
                        break;
                    default:
                        System.out.println("La opcion seleccionada no es valida, porfavor selecciona nuevamente");
                        break;
                }
            }
        } while (menu);

    }

    public static void mostrarMenu() {
        System.out.println("""
                Menu inicial:
                1- Gestor
                2- Crear apuesta
                3- Salir""");
    }
}
