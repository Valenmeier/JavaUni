package modelo.gestores;

import modelo.domicilio.Ciudad;
import modelo.domicilio.Domicilio;
import modelo.persona.Cliente;
import repositorio.RepositorioQuiniela;


import java.util.Scanner;


public class GestorClientes implements GestorPersonas {
    Cliente[] clientes = RepositorioQuiniela.clientes;
    boolean listaVacia = true;

    @Override
    public void crear() {
        String nombre;
        String apellido;
        String email;
        int dni;
        Domicilio domicilio;

        Scanner input = new Scanner(System.in);

        System.out.println("Ingresa el nombre del cliente");
        nombre = input.nextLine();

        System.out.println("Ingresa el apellido del cliente");
        apellido = input.nextLine();

        System.out.println("Ingresa el email del cliente");
        email = input.nextLine();

        System.out.println("Ingresa el dni del cliente");
        dni = input.nextInt();
        input.nextLine();

        GestorCiudades gestorCiudad = new GestorCiudades();

        System.out.println("Ingresa la calle del cliente");
        String calle = input.nextLine();

        System.out.println("Ingresa el número del domicilio del cliente");
        int numero = input.nextInt();
        input.nextLine();

        domicilio = new Domicilio(gestorCiudad.crearElegirCiudad(), calle, numero);

        Cliente cliente = new Cliente(nombre, apellido, email, dni, domicilio);

        boolean agregado = false;
        int contador = 0;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = cliente;
                agregado = true;
                System.out.println("Se ha agregado correctamente");
                break;
            } else {
                contador++;
            }
        }
        if (!agregado)
            System.out.println("No se ha podido agregar al cliente debido a que tienes " + contador + "/" + clientes.length);
    }

    @Override
    public void listar() {
        int contador = 0;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (contador == 0) {
                    System.out.println("===========================\n" + "Listado de clientes:");
                }
                System.out.println("===========================\n" + (i + 1) + "- " + clientes[i].getNombre() + " " + clientes[i].getApellido());
                contador++;

            }
        }
        if (contador == 0) {
            listaVacia = true;
            System.out.println("===========================\n" +
                    "No hay clientes anotados en la DB. Cantidad de personas disponibles para anotar: 0/"
                    + clientes.length + "\n"
                    + "==========================="
            );
        } else {
            listaVacia = false;
            System.out.println("===========================\nCantidad de clientes anotados: " + contador + "/" + clientes.length);
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
                    int usuarioSeleccionado = (input.nextInt() - 1);
                    boolean validacion = (usuarioSeleccionado >= 0 && usuarioSeleccionado < clientes.length);
                    if (validacion && clientes[usuarioSeleccionado] != null) {
                        input.nextLine();
                        System.out.println("""
                                Selecciona que deseas modificar:
                                1- Nombre
                                2- Apellido
                                3- Email
                                4- Dni
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
                                Domicilio domicilio = clientes[usuarioSeleccionado].getDomicilio();
                                Ciudad ciudad = clientes[usuarioSeleccionado].getDomicilio().getCiudad();
                                switch (seleccionMenu) {
                                    case 1:
                                        System.out.println("Selecciona un nuevo nombre");
                                        String nuevoNombre = input.nextLine();
                                        clientes[usuarioSeleccionado].setNombre(nuevoNombre);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 2:
                                        System.out.println("Selecciona un nuevo apellido");
                                        String nuevoApellido = input.nextLine();
                                        clientes[usuarioSeleccionado].setApellido(nuevoApellido);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 3:
                                        System.out.println("Selecciona un nuevo email");
                                        String nuevoEmail = input.nextLine();
                                        clientes[usuarioSeleccionado].setEmail(nuevoEmail);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 4:
                                        System.out.println("Selecciona un nuevo dni");
                                        int nuevoDni = input.nextInt();
                                        clientes[usuarioSeleccionado].setDni(nuevoDni);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 5:
                                        System.out.println("Selecciona un nueva calle");
                                        String nuevaCalle = input.nextLine();
                                        domicilio.setCalle(nuevaCalle);
                                        clientes[usuarioSeleccionado].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 6:
                                        System.out.println("Selecciona un nuevo numero de calle");
                                        int nuevoNro = input.nextInt();
                                        domicilio.setNro(nuevoNro);
                                        clientes[usuarioSeleccionado].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 7:
                                        System.out.println("Selecciona una nueva provincia");

                                        String nuevaProvincia = input.nextLine();
                                        Ciudad nuevaCiudadProvincia = new Ciudad(nuevaProvincia, ciudad.getCiudd());
                                        domicilio.setCiudad(nuevaCiudadProvincia);

                                        clientes[usuarioSeleccionado].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 8:
                                        System.out.println("Selecciona una nueva ciudad");
                                        String nuevaCiudad = input.nextLine();
                                        Ciudad nuevaCiudadNombre = new Ciudad(ciudad.getProvincia(), nuevaCiudad);
                                        domicilio.setCiudad(nuevaCiudadNombre);
                                        clientes[usuarioSeleccionado].setDomicilio(domicilio);
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
            System.out.println("Selecciona el cliente a borrar");
            Scanner input = new Scanner(System.in);
            boolean borrar = true;
            do {
                if (input.hasNextInt()) {
                    int usuarioSeleccionado = (input.nextInt() - 1);
                    boolean validacion = (usuarioSeleccionado >= 0 && usuarioSeleccionado < clientes.length);
                    if (validacion && clientes[usuarioSeleccionado] != null) {
                        input.nextLine();
                        System.out.println("Seguro deseas eliminar al cliente:" +
                                clientes[usuarioSeleccionado].getNombre() + "\n" +
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
                                        clientes[usuarioSeleccionado] = null;
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
        System.out.println("Selecciona al cliente a ver");
        listar();
        if (!listaVacia) {
            Scanner input = new Scanner(System.in);
            boolean validacion = false;
            int indiceCliente;

            do {
                System.out.println("Ingresa el número del cliente:");
                if (input.hasNextInt()) {
                    indiceCliente = input.nextInt() - 1;
                    if (indiceCliente >= 0 && indiceCliente < clientes.length && clientes[indiceCliente] != null) {
                        System.out.println("Nombre: " + clientes[indiceCliente].getNombre() + "\n" +
                                "Apellido: " + clientes[indiceCliente].getApellido() + "\n" +
                                "Email: " + clientes[indiceCliente].getEmail() + "\n" +
                                "DNI: " + clientes[indiceCliente].getDni() + "\n" +
                                "Provincia: " + clientes[indiceCliente].getDomicilio().getCiudad().getProvincia() + "\n" +
                                "Ciudad: " + clientes[indiceCliente].getDomicilio().getCiudad().getCiudd() + "\n");
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

    public boolean existenClientes() {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarCliente(int posicion) {
        int clienteElegido = posicion - 1;

        if (clientes[clienteElegido] != null) {
            return clientes[clienteElegido];
        }

        return null;
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
                 Menú gestionar clientes:
                1- Listar clientes
                2- Crear clientes
                3- Actualizar clientes
                4- Eliminar clientes
                5- Mostrar información de un cliente
                6- Ir al menú anterior""");
    }

}
