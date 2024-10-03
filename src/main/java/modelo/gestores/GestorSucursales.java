package modelo.gestores;

import modelo.domicilio.Ciudad;

import modelo.persona.Vendedor;
import modelo.persona.quiniela.PersonaQuiniela;
import modelo.persona.quiniela.Sucursal;
import repositorio.RepositorioQuiniela;

import java.util.Scanner;

public class GestorSucursales implements GestorPersonas {
    Sucursal[] sucursales = RepositorioQuiniela.sucursales;
    boolean listaVacia = true;

    @Override
    public void crear() {

        GestorQuinielas revisarQuinielas = new GestorQuinielas();
        boolean hayQuinielas = false;
        for (PersonaQuiniela quiniela : revisarQuinielas.quinielas) {
            if (quiniela != null) {
                hayQuinielas = true;
                break;
            }
        }

        if (!hayQuinielas) {
            System.out.println("No se pueden crear sucursales porque no hay quinielas creadas.");
            return;
        }

        int numeroSucursal;
        PersonaQuiniela quinielaEncargada = null;
        Ciudad ciudad;
        Scanner input = new Scanner(System.in);


        System.out.println("Ingresa el número de la sucursal:");
        numeroSucursal = input.nextInt();
        input.nextLine();


        System.out.println("Selecciona la quiniela a cargo:");
        GestorQuinielas gestorQuiniela = new GestorQuinielas();
        gestorQuiniela.listar();
        boolean seleccionValida = false;
        int quinielaSeleccionada;

        while (!seleccionValida) {
            quinielaSeleccionada = input.nextInt();
            input.nextLine();

            switch (gestorQuiniela.puedeAgregarSucursal(quinielaSeleccionada)) {
                case 0:
                    System.out.println("La quiniela tiene todas sus sucursales ocupadas.");
                    System.out.println("¿Quieres intentar con otra quiniela o cancelar? (1 - Intentar otra, 2 - Cancelar)");
                    int opcion = input.nextInt();
                    input.nextLine();

                    if (opcion == 2) {
                        System.out.println("Creación de sucursal cancelada.");
                        return;
                    }
                    gestorQuiniela.listar();
                    break;
                case 1:
                    System.out.println("Selecciona una opción válida.");
                    break;
                case 2:
                    seleccionValida = true;
                    quinielaEncargada = gestorQuiniela.quinielas[quinielaSeleccionada - 1];
                    break;
            }
        }

        GestorCiudades gestorCiudad = new GestorCiudades();
        ciudad = gestorCiudad.crearElegirCiudad();

        Sucursal nuevaSucursal = new Sucursal(numeroSucursal, ciudad, quinielaEncargada);

        int contadorSucursalesRepo = 0;
        for (Sucursal sucursal : sucursales) {
            if (sucursal != null) {
                contadorSucursalesRepo++;
            }
        }
        if ((contadorSucursalesRepo >= sucursales.length)) {
            System.out.println("Haz alcanzado el limite de sucursales que puedes anotar.");
            return;
        }

        if (quinielaEncargada != null) {
            if (quinielaEncargada.asignarSucursal(nuevaSucursal)) {
                for (int i = 0; i < sucursales.length; i++) {
                    if (sucursales[i] == null) {
                        sucursales[i] = nuevaSucursal;
                        break;
                    }
                }
                System.out.println("Sucursal creada y asignada correctamente.");
            } else {
                System.out.println("No se pudo asignar la sucursal.");
            }
        }
    }

    @Override
    public void listar() {
        int contador = 0;
        for (int i = 0; i < sucursales.length; i++) {
            if (sucursales[i] != null) {
                if (contador == 0) {
                    System.out.println("===========================\n" + "Listado de sucursales:");
                }
                System.out.println("===========================\n" +
                        (i + 1) + "- Sucursal: " + sucursales[i].getnumeroSucursal() +
                        " Perteneciente a la quiniela: " + sucursales[i].getQuiniela().getNombre());
                contador++;
            }
        }
        if (contador == 0) {
            listaVacia = true;
            System.out.println("===========================\n" +
                    "No hay sucursales anotadas en la DB. Cantidad de sucursales disponibles para anotar: 0/"
                    + sucursales.length + "\n"
                    + "==========================="
            );
        } else {
            listaVacia = false;
            System.out.println("===========================\nCantidad de sucursales anotadas: " + contador + "/" + sucursales.length);
        }
    }

    @Override
    public void actualizar() {
        listar();
        if (!listaVacia) {
            System.out.println("Selecciona la sucursal a actualizar:");
            Scanner input = new Scanner(System.in);
            boolean actualizar = true;

            do {
                if (input.hasNextInt()) {
                    int sucursalSeleccionada = (input.nextInt() - 1);
                    boolean validacion = (sucursalSeleccionada >= 0 && sucursalSeleccionada < sucursales.length);
                    if (validacion && sucursales[sucursalSeleccionada] != null) {
                        input.nextLine();
                        System.out.println("""
                                Selecciona que deseas modificar:
                                1- Número de sucursal
                                2- Quiniela encargada
                                3- Provincia
                                4- Ciudad
                                5- Cancelar""");
                        boolean menuInterno = true;

                        do {
                            if (input.hasNextInt()) {
                                int seleccionMenu = input.nextInt();
                                input.nextLine();
                                Ciudad ciudad = sucursales[sucursalSeleccionada].getCiudad();

                                switch (seleccionMenu) {
                                    case 1:
                                        System.out.println("Selecciona un nuevo número:");
                                        int nuevoNumero = input.nextInt();
                                        sucursales[sucursalSeleccionada].setNumeroSucursal(nuevoNumero);
                                        System.out.println("Se ha actualizado correctamente.");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;

                                    case 2:
                                        System.out.println("Selecciona una nueva quiniela encargada:");
                                        GestorQuinielas gestorQuiniela = new GestorQuinielas();
                                        gestorQuiniela.listar();
                                        boolean seleccionValida = false;

                                        while (!seleccionValida) {
                                            int quinielaSeleccionada = input.nextInt();
                                            input.nextLine();

                                            switch (gestorQuiniela.puedeAgregarSucursal(quinielaSeleccionada)) {
                                                case 0:
                                                    System.out.println("La quiniela tiene todas sus sucursales ocupadas. Selecciona otra quiniela.");
                                                    break;
                                                case 1:
                                                    System.out.println("Selecciona una opción válida.");
                                                    break;
                                                case 2:
                                                    Sucursal sucursal = sucursales[sucursalSeleccionada];
                                                    PersonaQuiniela quinielaAnterior = sucursal.getQuiniela();
                                                    if (quinielaAnterior != null) {
                                                        quinielaAnterior.removerSucursal(sucursal);
                                                    }

                                                    PersonaQuiniela nuevaQuiniela = gestorQuiniela.quinielas[quinielaSeleccionada - 1];
                                                    sucursal.setQuiniela(nuevaQuiniela);

                                                    nuevaQuiniela.asignarSucursal(sucursal);

                                                    System.out.println("Quiniela encargada actualizada correctamente.");
                                                    seleccionValida = true;
                                                    menuInterno = false;
                                                    actualizar = false;
                                                    break;
                                            }
                                        }
                                        break;


                                    case 3:
                                        System.out.println("Selecciona una nueva provincia:");
                                        String nuevaProvincia = input.nextLine();
                                        Ciudad nuevaCiudadProvincia = new Ciudad(nuevaProvincia, ciudad.getCiudd());
                                        sucursales[sucursalSeleccionada].setCiudad(nuevaCiudadProvincia);
                                        System.out.println("Se ha actualizado correctamente.");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;

                                    case 4:
                                        System.out.println("Selecciona una nueva ciudad:");
                                        String nuevaCiudad = input.nextLine();
                                        Ciudad nuevaCiudadNombre = new Ciudad(ciudad.getProvincia(), nuevaCiudad);
                                        sucursales[sucursalSeleccionada].setCiudad(nuevaCiudadNombre);
                                        System.out.println("Se ha actualizado correctamente.");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;

                                    case 5:
                                        menuInterno = false;
                                        break;

                                    default:
                                        System.out.println("Selecciona una opción válida.");
                                }
                            } else {
                                System.out.println("Selecciona una opción válida.");
                                input.nextLine();
                            }
                        } while (menuInterno);

                    } else {
                        System.out.println("Selecciona una opción válida.");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Selecciona una opción válida.");
                    input.nextLine();
                }
            } while (actualizar);
        } else {
            System.out.println("No hay sucursales para actualizar.");
        }
    }


    @Override
    public void borrar() {
        listar();
        if (!listaVacia) {
            System.out.println("Selecciona la sucursal a borrar");
            Scanner input = new Scanner(System.in);
            boolean borrar = true;
            do {
                if (input.hasNextInt()) {
                    int sucursalSeleccionada = (input.nextInt() - 1);
                    boolean validacion = (sucursalSeleccionada >= 0 && sucursalSeleccionada < sucursales.length);
                    if (validacion && sucursales[sucursalSeleccionada] != null) {
                        input.nextLine();
                        System.out.println("Seguro deseas eliminar la sucursal: " +
                                sucursales[sucursalSeleccionada].getNumeroSucursal() + " de la quiniela:" + sucursales[sucursalSeleccionada].getQuiniela().getNombre() + "\n" +
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
                                        PersonaQuiniela quinielaAnterior = sucursales[sucursalSeleccionada].getQuiniela();
                                        quinielaAnterior.removerSucursal(sucursales[sucursalSeleccionada]);
                                        sucursales[sucursalSeleccionada] = null;
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
        System.out.println("Selecciona la sucursal a ver");
        listar();
        if (!listaVacia) {
            Scanner input = new Scanner(System.in);
            boolean validacion = false;
            int indiceSucursal;

            do {
                System.out.println("Ingresa el número de la sucursal:");
                if (input.hasNextInt()) {
                    indiceSucursal = input.nextInt() - 1;
                    if (indiceSucursal >= 0 && indiceSucursal < sucursales.length && sucursales[indiceSucursal] != null) {
                        Sucursal sucursal = sucursales[indiceSucursal];
                        System.out.println("Número de la sucursal: " + sucursal.getnumeroSucursal() + "\n" +
                                "Quiniela a cargo: " + sucursal.getQuiniela().getNombre() + "\n" +
                                "Provincia: " + sucursal.getCiudad().getProvincia() + "\n" +
                                "Ciudad: " + sucursal.getCiudad().getCiudd() + "\n");

                        System.out.println("Vendedores en la sucursal:");
                        for (Vendedor vendedor : sucursal.getVendedors()) {
                            if (vendedor != null) {
                                System.out.println("- Vendedor: " + vendedor.getNombre() + " " + vendedor.getApellido());
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


    public int puedeAgregarVendedor(int posicionSucursal) {
        if (posicionSucursal < 1 || posicionSucursal > sucursales.length) {
            return 1;
        }
        Sucursal sucursal = sucursales[posicionSucursal - 1];

        if (sucursal == null) {
            return 1;
        }

        for (Vendedor vendedor : sucursal.getVendedors()) {
            if (vendedor == null) {
                return 2;
            }
        }

        return 0;
    }

    public boolean existenSucursales() {
        for (int i = 0; i < sucursales.length; i++) {
            if (sucursales[i] != null) {
                return true;
            }
        }
        return false;
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
                 Menú gestionar sucursales:
                1- Listar sucursales
                2- Crear sucursales
                3- Actualizar sucursales
                4- Eliminar sucursales
                5- Mostrar información de una sucursal
                6- Ir al menú anterior""");
    }
}
