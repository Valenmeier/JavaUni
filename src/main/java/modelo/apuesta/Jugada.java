package modelo.apuesta;

import java.util.ArrayList;
import java.util.Scanner;

public class Jugada {
    String tipoDeSorteo;
    ArrayList<int[]> jugadas = new ArrayList<>();
    int totalApuesta;


    public Jugada() {
    }

    public void iniciarJugada() {
        Scanner input = new Scanner(System.in);

        System.out.println("""
                Selecciona el tipo de sorteo:
                1- Mañana
                2- Tarde
                3- Nocturno
                """);

        while (true) {
            if (input.hasNextInt()) {
                int seleccion = input.nextInt();
                input.nextLine();
                switch (seleccion) {
                    case 1:
                        tipoDeSorteo = "Mañana";
                        break;
                    case 2:
                        tipoDeSorteo = "Tarde";
                        break;
                    case 3:
                        tipoDeSorteo = "Nocturno";
                        break;
                    default:
                        System.out.println("Selecciona una opción válida.");
                        continue;
                }
                break;
            } else {
                System.out.println("Selecciona una opción válida.");
                input.nextLine();
            }
        }

        generarJugadas();
    }

    public void generarJugadas() {
        Scanner input = new Scanner(System.in);

        while (jugadas.size() < 10) {
            int[] jugada = new int[3];
            System.out.println("Establece un monto a apostar: ");
            while (true) {
                if (input.hasNextInt()) {
                    jugada[0] = input.nextInt();
                    input.nextLine();
                    break;
                } else {
                    System.out.println("Introduce un monto válido.");
                    input.nextLine();
                }
            }

            System.out.println("Establece un número de 2 a 4 cifras para apostar: ");
            while (true) {
                if (input.hasNextInt()) {
                    int numeroApuesta = input.nextInt();
                    input.nextLine();
                    if (numeroApuesta >= 10 && numeroApuesta <= 9999) {
                        jugada[1] = numeroApuesta;
                        break;
                    } else {
                        System.out.println("Introduce un número de 2 a 4 cifras.");
                    }
                } else {
                    System.out.println("Introduce un número válido.");
                    input.nextLine();
                }
            }

            System.out.println("Selecciona una posición entre el 1 y el 10: ");
            while (true) {
                if (input.hasNextInt()) {
                    int posicion = input.nextInt();
                    input.nextLine();
                    if (posicion >= 1 && posicion <= 10) {
                        jugada[2] = posicion;
                        break;
                    } else {
                        System.out.println("Selecciona una posición válida entre el 1 y el 10.");
                    }
                } else {
                    System.out.println("Introduce una posición válida.");
                    input.nextLine();
                }
            }
            boolean jugadaExistente = false;
            for (int[] j : jugadas) {
                if (j[1] == jugada[1] && j[2] == jugada[2]) {
                    jugadaExistente = true;
                    break;
                }

            }

            if (jugadaExistente) {
                System.out.println("Esa posición en esta jugada ya ha sido agregada.");
            } else {
                totalApuesta += jugada[0];
                jugadas.add(jugada);
                System.out.println("Jugada agregada.");
            }

            System.out.println("¿Deseas agregar otra jugada? (s/n)");
            String respuesta = input.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                break;
            }
        }

    }

    public String imprimirJugada() {
        StringBuilder mensaje = new StringBuilder();
        for (int[] jugada : jugadas) {
            mensaje.append(jugada[0]).append("\t").append(jugada[1]).append("\t").append("\t").append("\t").append("\t").append(jugada[2]).append("\n");
        }
        return mensaje.toString();
    }

    public int getTotalApuesta() {
        return totalApuesta;
    }

    public String getTipoDeSorteo() {
        return tipoDeSorteo;
    }

}
