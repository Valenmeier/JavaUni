package modelo.gestores;

import modelo.domicilio.Ciudad;
import modelo.persona.Cliente;
import repositorio.RepositorioQuiniela;

import java.util.Scanner;


public class GestorCiudades implements GestorPersonas {
    Ciudad[] ciudades = RepositorioQuiniela.ciudades;
    Boolean listaCiudadesVacia = true;
    int ciudadCreada = 0;

    @Override
    public void crear() {
        String provincia;
        String ciudad;

        Scanner input = new Scanner(System.in);
        System.out.println("Ingresa el nombre de la provincia");
        provincia = input.nextLine();
        System.out.println("Ingresa el nombre de la ciudad");
        ciudad = input.nextLine();

        Ciudad nuevaciudad = new Ciudad(provincia, ciudad);

        boolean agregado = false;
        int contador = 0;

        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] == null) {
                ciudades[i] = nuevaciudad;
                agregado = true;
                ciudadCreada = i;
                break;
            } else {
                contador++;
            }
        }
        if (!agregado)
            System.out.println("No se ha podido agregar la ciudad debido a que ya tienes " + contador + "/" + ciudades.length + " agregadas");
    }

    @Override
    public void listar() {
        int contador = 0;
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null) {
                System.out.println((i + 1) + "- " + ciudades[i].getProvincia() + ", " + ciudades[i].getCiudd());
                contador++;
                listaCiudadesVacia = false;
            }
        }
        if (contador == 0) {
            System.out.println("No hay ninguna ciudad registrada en la DB " + "cantidad de ciudades disponibles para anotar:" + contador + "/"
                    + ciudades.length + "\n"
                    + "===========================");
            listaCiudadesVacia = true;
        }
    }

    @Override
    public void actualizar() {
        listar();
        if (!listaCiudadesVacia) {
            System.out.println("Selecciona la ciudad a actualizar");
            Scanner input = new Scanner(System.in);
            boolean actualizar = true;
            do {
                if (input.hasNextInt()) {
                    int ciudadSeleccionada = (input.nextInt() - 1);
                    boolean validacion = (ciudadSeleccionada >= 0 && ciudadSeleccionada < ciudades.length);
                    if (validacion && ciudades[ciudadSeleccionada] != null) {
                        input.nextLine();
                        System.out.println("""
                                Selecciona que deseas modificar:
                                1- Provincia
                                2- Ciudad
                                3- Cancelar""");
                        boolean menuInterno = true;
                        do {
                            if (input.hasNextInt()) {
                                int seleccionMenu = (input.nextInt());
                                input.nextLine();
                                switch (seleccionMenu) {
                                    case 1:
                                        System.out.println("Selecciona una nueva provincia");
                                        String nuevaProvincia = input.nextLine();
                                        ciudades[ciudadSeleccionada].setProvincia(nuevaProvincia);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 2:
                                        System.out.println("Selecciona una nueva ciudad");
                                        String nuevaCiudad = input.nextLine();
                                        ciudades[ciudadSeleccionada].setCiudd(nuevaCiudad);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 3:
                                        menuInterno = false;
                                        break;
                                }
                            } else {
                                System.out.println("Selecciona una opción valida");
                                input.nextLine();
                            }
                        } while (menuInterno);

                    } else {
                        System.out.println("Selecciona una opción valida");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Selecciona una opción valida");
                    input.nextLine();
                }
            } while (actualizar);
        } else {
            System.out.println("No hay ciudades para actualizar");
        }
    }

    @Override
    public void borrar() {
        listar();
        if (!listaCiudadesVacia) {
            System.out.println("Selecciona el ciudades a borrar");
            Scanner input = new Scanner(System.in);
            boolean borrar = true;
            do {
                if (input.hasNextInt()) {
                    int usuarioSeleccionado = (input.nextInt() - 1);
                    boolean validacion = (usuarioSeleccionado >= 0 && usuarioSeleccionado < ciudades.length);
                    if (validacion && ciudades[usuarioSeleccionado] != null) {
                        input.nextLine();
                        System.out.println("Seguro deseas eliminar la ciudad:" +
                                ciudades[usuarioSeleccionado].getCiudd() + "\n" +
                                "1- Si, borrar\n" +
                                "2- No, conservar\n"
                        );
                        boolean menuInterno = true;
                        do {
                            if (input.hasNextInt()) {
                                int seleccionMenu = (input.nextInt());
                                input.nextLine();
                                switch (seleccionMenu) {
                                    case 1:
                                        ciudades[usuarioSeleccionado] = null;
                                        System.out.println("Se ha borrado correctamente");
                                        menuInterno = false;
                                        borrar = false;
                                        break;
                                    case 2:
                                        menuInterno = false;
                                        borrar = false;
                                        break;
                                }
                            } else {
                                System.out.println("Selecciona una opción valida");
                                input.nextLine();
                            }
                        } while (menuInterno);

                    } else {
                        System.out.println("Selecciona una opción valida");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Selecciona una opción valida");
                    input.nextLine();
                }
            } while (borrar);
        } else {
            System.out.println("No hay usuarios para borrar");
        }
    }

    public boolean existenCiudades() {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null) {
                return true;
            }
        }
        return false;
    }


    @Override
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
                        listar();
                        mostrarMenu();
                        break;
                    case 2:
                        crear();
                        mostrarMenu();
                        break;
                    case 3:
                        actualizar();
                        mostrarMenu();
                        break;
                    case 4:
                        borrar();
                        mostrarMenu();
                        break;
                    case 5:
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
                 Menú gestionar ciudades:
                1- Listar ciudades
                2- Crear ciudades
                3- Actualizar ciudades
                4- Eliminar ciudades
                5- Ir al menú anterior""");
    }

    public Ciudad crearElegirCiudad() {
        Ciudad ciudad = null;
        System.out.println("""
                Deseas usar una ciudad existente o crear una nueva?
                1- Elegir una ciudad existente
                2- Crear una ciudad""");
        boolean menu = true;
        do {
            Scanner input = new Scanner(System.in);
            if (!input.hasNextInt()) {
                System.out.println(" Selecciona un número valido");
            } else {
                switch (input.nextInt()) {
                    case 1:
                        listar();
                        if (!listaCiudadesVacia) {
                            System.out.println("Cuál deseas elegir?");
                            while (!input.hasNextInt() && ciudades[(input.nextInt() - 1)] == null) {
                                System.out.println(" Selecciona una opción valida");
                            }
                            ciudad = ciudades[(input.nextInt() - 1)];
                            menu = false;
                        } else {
                            System.out.println("""
                                    Deseas usar una ciudad existente o crear una nueva?
                                    1- Elegir una ciudad existente
                                    2- Crear una ciudad""");
                        }
                        break;
                    case 2:
                        crear();
                        ciudad = ciudades[ciudadCreada];
                        System.out.println("Ciudad creada y agregada exitosamente al perfil");
                        menu = false;
                        break;
                    default:
                        System.out.println(" Selecciona un número valido");
                }
            }
        } while (menu);
        return ciudad;
    }

}
