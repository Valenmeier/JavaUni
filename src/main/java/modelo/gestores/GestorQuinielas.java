package modelo.gestores;


import modelo.domicilio.Ciudad;
import modelo.domicilio.Domicilio;
import modelo.persona.Vendedor;
import modelo.persona.quiniela.PersonaQuiniela;
import modelo.persona.quiniela.Sucursal;
import repositorio.RepositorioQuiniela;

import java.util.Scanner;


public class GestorQuinielas implements GestorPersonas {
    PersonaQuiniela[] quinielas = RepositorioQuiniela.quinielas;
    boolean listaVacia = true;

    @Override
    public void crear() {
        String nombre;
        String nombreDueno;
        String email;
        int dni;
        Domicilio domicilio;

        Scanner input = new Scanner(System.in);

        System.out.println("Ingresa el nombre De la quiniela");
        nombre = input.nextLine();

        System.out.println("Ingresa el nombre del dueño De la quiniela");
        nombreDueno = input.nextLine();

        System.out.println("Ingresa el email De la quiniela");
        email = input.nextLine();

        System.out.println("Ingresa el id De la quiniela");
        dni = input.nextInt();
        input.nextLine();

        GestorCiudades gestorCiudad = new GestorCiudades();

        System.out.println("Ingresa la calle De la quiniela");
        String calle = input.nextLine();

        System.out.println("Ingresa el número del domicilio De la quiniela");
        int numero = input.nextInt();
        input.nextLine();

        domicilio = new Domicilio(gestorCiudad.crearElegirCiudad(), calle, numero);

        PersonaQuiniela quiniela = new PersonaQuiniela(nombre, nombreDueno, email, dni, domicilio);

        boolean agregado = false;
        int contador = 0;
        for (int i = 0; i < quinielas.length; i++) {
            if (quinielas[i] == null) {
                quinielas[i] = quiniela;
                agregado = true;
                System.out.println("Se ha agregado correctamente");
                break;
            } else {
                contador++;
            }
        }
        if (!agregado)
            System.out.println("No se ha podido agregar la quiniela debido a que tienes " + contador + "/" + quinielas.length);
    }

    @Override
    public void listar() {
        int contador = 0;
        for (int i = 0; i < quinielas.length; i++) {
            if (quinielas[i] != null) {
                if (contador == 0) {
                    System.out.println("===========================\n" + "Listado de quinielas:");
                }
                System.out.println("===========================\n" + (i + 1) + "- " + quinielas[i].getNombre() + " - Dueño= " + quinielas[i].getApellido());
                contador++;

            }
        }
        if (contador == 0) {
            listaVacia = true;
            System.out.println("===========================\n" +
                    "No hay quinielas anotados en la DB. Cantidad de quinielas disponibles para anotar: 0/"
                    + quinielas.length + "\n"
                    + "==========================="
            );
        } else {
            listaVacia = false;
            System.out.println("===========================\nCantidad de quinielas anotados: " + contador + "/" + quinielas.length);
        }
    }

    @Override
    public void actualizar() {
        listar();
        if (!listaVacia) {
            System.out.println("Selecciona el usuario a actualizar");
            Scanner input = new Scanner(System.in);
            boolean actualizar = true;
            do {
                if (input.hasNextInt()) {
                    int quinielasSeleccionada = (input.nextInt() - 1);
                    boolean validacion = (quinielasSeleccionada >= 0 && quinielasSeleccionada < quinielas.length);
                    if (validacion && quinielas[quinielasSeleccionada] != null) {
                        input.nextLine();
                        System.out.println("""
                                Selecciona que deseas modificar:
                                1- Nombre de quiniela
                                2- Nombre del dueño
                                3- Email
                                4- Identificador
                                5- Calle
                                6- Número de calle
                                7- Provincia
                                8- Ciudad
                                9- Cancelar""");
                        boolean menuInterno = true;
                        do {
                            if (input.hasNextInt()) {
                                int seleccionMenu = (input.nextInt());
                                input.nextLine();
                                Domicilio domicilio = quinielas[quinielasSeleccionada].getDomicilio();
                                Ciudad ciudad = quinielas[quinielasSeleccionada].getDomicilio().getCiudad();
                                switch (seleccionMenu) {
                                    case 1:
                                        System.out.println("Selecciona un nuevo nombre de quiniela");
                                        String nuevoNombre = input.nextLine();
                                        quinielas[quinielasSeleccionada].setNombre(nuevoNombre);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 2:
                                        System.out.println("Selecciona un nuevo nombre de dueño");
                                        String nuevoApellido = input.nextLine();
                                        quinielas[quinielasSeleccionada].setApellido(nuevoApellido);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 3:
                                        System.out.println("Selecciona un nuevo email");
                                        String nuevoEmail = input.nextLine();
                                        quinielas[quinielasSeleccionada].setEmail(nuevoEmail);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 4:
                                        System.out.println("Selecciona un nuevo identificador");
                                        int nuevoDni = input.nextInt();
                                        quinielas[quinielasSeleccionada].setDni(nuevoDni);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 5:
                                        System.out.println("Selecciona un nueva calle");
                                        String nuevaCalle = input.nextLine();
                                        domicilio.setCalle(nuevaCalle);
                                        quinielas[quinielasSeleccionada].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 6:
                                        System.out.println("Selecciona un nuevo numero de calle");
                                        int nuevoNro = input.nextInt();
                                        domicilio.setNro(nuevoNro);
                                        quinielas[quinielasSeleccionada].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 7:
                                        System.out.println("Selecciona una nueva provincia");

                                        String nuevaProvincia = input.nextLine();
                                        Ciudad nuevaCiudadProvincia = new Ciudad(nuevaProvincia, ciudad.getCiudd());
                                        domicilio.setCiudad(nuevaCiudadProvincia);

                                        quinielas[quinielasSeleccionada].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 8:
                                        System.out.println("Selecciona una nueva ciudad");
                                        String nuevaCiudad = input.nextLine();
                                        Ciudad nuevaCiudadNombre = new Ciudad(ciudad.getProvincia(), nuevaCiudad);
                                        domicilio.setCiudad(nuevaCiudadNombre);
                                        quinielas[quinielasSeleccionada].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 9:
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
            System.out.println("No hay clientes para actualizar");
        }
    }

    @Override
    public void borrar() {
        listar();
        if (!listaVacia) {
            System.out.println("Selecciona la quiniela a borrar");
            Scanner input = new Scanner(System.in);
            boolean borrar = true;
            do {
                if (input.hasNextInt()) {
                    int quinielasSeleccionada = (input.nextInt() - 1);
                    boolean validacion = (quinielasSeleccionada >= 0 && quinielasSeleccionada < quinielas.length);
                    if (validacion && quinielas[quinielasSeleccionada] != null) {
                        input.nextLine();
                        System.out.println("Seguro deseas eliminar la quiniela:" +
                                quinielas[quinielasSeleccionada].getNombre() + "\n" +
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
                                        quinielas[quinielasSeleccionada] = null;
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

    private void mostrarInformacion() {
        System.out.println("Selecciona la quiniela a ver");
        listar();
        if (!listaVacia) {
            Scanner input = new Scanner(System.in);
            boolean validacion = false;
            int indiceQuiniela;

            do {
                System.out.println("Ingresa el número de la quiniela:");
                if (input.hasNextInt()) {
                    indiceQuiniela = input.nextInt() - 1;
                    if (indiceQuiniela >= 0 && indiceQuiniela < quinielas.length && quinielas[indiceQuiniela] != null) {
                        PersonaQuiniela quiniela = quinielas[indiceQuiniela];
                        System.out.println("Nombre: " + quiniela.getNombre() + "\n" +
                                "Dueño: " + quiniela.getApellido() + "\n" +
                                "Email: " + quiniela.getEmail() + "\n" +
                                "DNI: " + quiniela.getDni() + "\n");

                        System.out.println("Sucursales asociadas:");
                        for (Sucursal sucursal : quiniela.getSucursales()) {
                            if (sucursal != null) {
                                System.out.println("- Sucursal número: " + sucursal.getnumeroSucursal() + ", Ciudad: " + sucursal.getCiudad().getCiudd());
                            }
                        }

                        validacion = true;
                    } else {
                        System.out.println("Selecciona una opción válida.");
                    }
                } else {
                    input.next();
                    System.out.println("Selecciona una opción válida.");
                }
            } while (!validacion);
        }
    }


    public int puedeAgregarSucursal(int posicionQuiniela) {
        if (posicionQuiniela < 1 || posicionQuiniela > quinielas.length) {
            return 1;
        }

        PersonaQuiniela quiniela = quinielas[posicionQuiniela - 1];
        if (quiniela == null) {
            return 1;
        }

        for (Sucursal sucursal : quiniela.getSucursales()) {
            if (sucursal == null) {
                return 2;
            }
        }

        return 0;
    }

    public boolean existenQuinielas() {
        for (int i = 0; i < quinielas.length; i++) {
            if (quinielas[i] != null) {
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
                        mostrarInformacion();
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

    public static void mostrarMenu() {
        System.out.println("""
                 Menú gestionar quinielas:
                1- Listar quinielas
                2- Crear quinielas
                3- Actualizar quinielas
                4- Eliminar quinielas
                5- Mostrar información de quiniela
                6- Ir al menú anterior""");
    }


}
