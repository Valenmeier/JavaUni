package modelo.gestores;


import modelo.domicilio.Domicilio;
import modelo.persona.Cliente;
import modelo.persona.Vendedor;

import modelo.persona.quiniela.Sucursal;
import repositorio.RepositorioQuiniela;

import java.util.Scanner;

public class GestorVendedores implements GestorPersonas {
    Vendedor[] vendedores = RepositorioQuiniela.vendedores;
    boolean listaVacia = true;

    @Override
    public void crear() {
        GestorSucursales revisarSucursales = new GestorSucursales();
        boolean haySucursales = false;
        for (Sucursal sucursal : revisarSucursales.sucursales) {
            if (sucursal != null) {
                haySucursales = true;
                break;
            }
        }
        if (!haySucursales) {
            System.out.println("No se pueden crear vendedores porque no hay sucursales creadas.");
            return;
        }

        String nombre;
        String apellido;
        String email;
        int dni;
        String cuil;
        Domicilio domicilio;
        Sucursal sucursal = null;

        Scanner input = new Scanner(System.in);

        System.out.println("Ingresa el nombre del vendedor");
        nombre = input.nextLine();

        System.out.println("Ingresa el apellido del vendedor");
        apellido = input.nextLine();

        System.out.println("Ingresa el email del vendedor");
        email = input.nextLine();

        System.out.println("Ingresa el dni del vendedor");
        dni = input.nextInt();
        input.nextLine();

        System.out.println("Ingresa el CUIL del vendedor");
        cuil = input.nextLine();

        GestorCiudades gestorCiudad = new GestorCiudades();
        System.out.println("Ingresa la calle del vendedor");
        String calle = input.nextLine();

        System.out.println("Ingresa el número del domicilio del vendedor");
        int numero = input.nextInt();
        input.nextLine();

        domicilio = new Domicilio(gestorCiudad.crearElegirCiudad(), calle, numero);

        System.out.println("Selecciona la sucursal a cargo:");
        GestorSucursales gestorSucursal = new GestorSucursales();
        gestorSucursal.listar();
        boolean seleccionValida = false;
        int sucursalSeleccionada;

        while (!seleccionValida) {
            sucursalSeleccionada = input.nextInt();
            input.nextLine();

            switch (gestorSucursal.puedeAgregarVendedor(sucursalSeleccionada)) {
                case 0:
                    System.out.println("La sucursal tiene todos sus vendedores ocupados.");
                    System.out.println("¿Quieres intentar con otra sucursal o cancelar? (1 - Intentar otra, 2 - Cancelar)");
                    int opcion = input.nextInt();
                    input.nextLine();

                    if (opcion == 2) {
                        System.out.println("Creación de vendedor cancelada.");
                        return;
                    }
                    gestorSucursal.listar();
                    break;
                case 1:
                    System.out.println("Selecciona una opción válida.");
                    break;
                case 2:
                    seleccionValida = true;
                    sucursal = gestorSucursal.sucursales[sucursalSeleccionada - 1];
                    break;
            }
        }

        Vendedor vendedor = new Vendedor(cuil, sucursal, email, nombre, apellido, dni, domicilio);

        int contadorVendedoresRepo = 0;
        for (Vendedor vende : vendedores) {
            if (vende != null) {
                contadorVendedoresRepo++;
            }
        }
        if ((contadorVendedoresRepo >= vendedores.length)) {
            System.out.println("Haz alcanzado el limite de vendedores que puedes anotar.");
            return;
        }

        if (sucursal != null) {
            if (sucursal.asignarVendedor(vendedor)) {
                for (int i = 0; i < vendedores.length; i++) {
                    if (vendedores[i] == null) {
                        vendedores[i] = vendedor;
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
        for (int i = 0; i < vendedores.length; i++) {
            if (vendedores[i] != null) {
                if (contador == 0) {
                    System.out.println("===========================\nListado de vendedores:");
                }
                System.out.println((i + 1) + "- " + vendedores[i].getNombre() + " " + vendedores[i].getApellido() +
                        ", Quiniela: " + vendedores[i].getSucursal().getQuiniela().getNombre()+
                        ", Sucursal: " + vendedores[i].getSucursal().getNumeroSucursal());
                contador++;
            }
        }
        if (contador == 0) {
            listaVacia = true;
            System.out.println("No hay vendedores anotados en la DB.");
        } else {
            listaVacia = false;
            System.out.println("Cantidad de vendedores anotados: " + contador + "/" + vendedores.length);
        }
    }

    @Override
    public void actualizar() {
        listar();
        if (!listaVacia) {
            System.out.println("Selecciona el vendedor a actualizar");
            Scanner input = new Scanner(System.in);
            boolean actualizar = true;
            do {
                if (input.hasNextInt()) {
                    int vendedorSeleccionado = input.nextInt() - 1;
                    boolean validacion = (vendedorSeleccionado >= 0 && vendedorSeleccionado < vendedores.length);
                    if (validacion && vendedores[vendedorSeleccionado] != null) {
                        input.nextLine();
                        System.out.println("""
                                Selecciona que deseas modificar:
                                1- Nombre
                                2- Apellido
                                3- Email
                                4- DNI
                                5- CUIL
                                6- Calle
                                7- Número de calle
                                8- Sucursal
                                9- Cancelar""");
                        boolean menuInterno = true;
                        do {
                            if (input.hasNextInt()) {
                                int seleccionMenu = input.nextInt();
                                input.nextLine();
                                Domicilio domicilio = vendedores[vendedorSeleccionado].getDomicilio();
                                switch (seleccionMenu) {
                                    case 1:
                                        System.out.println("Ingresa el nuevo nombre");
                                        String nuevoNombre = input.nextLine();
                                        vendedores[vendedorSeleccionado].setNombre(nuevoNombre);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 2:
                                        System.out.println("Ingresa el nuevo apellido");
                                        String nuevoApellido = input.nextLine();
                                        vendedores[vendedorSeleccionado].setApellido(nuevoApellido);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 3:
                                        System.out.println("Ingresa el nuevo email");
                                        String nuevoEmail = input.nextLine();
                                        vendedores[vendedorSeleccionado].setEmail(nuevoEmail);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 4:
                                        System.out.println("Ingresa el nuevo DNI");
                                        int nuevoDni = input.nextInt();
                                        vendedores[vendedorSeleccionado].setDni(nuevoDni);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 5:
                                        System.out.println("Ingresa el nuevo CUIL");
                                        String nuevoCuil = input.nextLine();
                                        vendedores[vendedorSeleccionado].setCuil(nuevoCuil);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 6:
                                        System.out.println("Ingresa la nueva calle");
                                        String nuevaCalle = input.nextLine();
                                        domicilio.setCalle(nuevaCalle);
                                        vendedores[vendedorSeleccionado].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 7:
                                        System.out.println("Ingresa el nuevo número de calle");
                                        int nuevoNumero = input.nextInt();
                                        domicilio.setNro(nuevoNumero);
                                        vendedores[vendedorSeleccionado].setDomicilio(domicilio);
                                        System.out.println("Se ha actualizado correctamente");
                                        menuInterno = false;
                                        actualizar = false;
                                        break;
                                    case 8:
                                        System.out.println("Selecciona una nueva sucursal encargada:");
                                        GestorSucursales gestorSucursal = new GestorSucursales();
                                        gestorSucursal.listar();
                                        boolean seleccionValida = false;

                                        while (!seleccionValida) {
                                            int sucursalSeleccionada = input.nextInt();
                                            input.nextLine();

                                            switch (gestorSucursal.puedeAgregarVendedor(sucursalSeleccionada)) {
                                                case 0:
                                                    System.out.println("La sucursal tiene todos sus vendedores ocupados. Selecciona otra sucursal.");
                                                    break;
                                                case 1:
                                                    System.out.println("Selecciona una opción válida.");
                                                    break;
                                                case 2:
                                                    Vendedor vendedor = vendedores[vendedorSeleccionado];
                                                    Sucursal sucursalAnterior = vendedor.getSucursal();
                                                    if (sucursalAnterior != null) {
                                                        sucursalAnterior.removerVendedor(vendedor);
                                                    }

                                                    Sucursal nuevaSucursal = gestorSucursal.sucursales[sucursalSeleccionada - 1];
                                                    vendedor.setSucursal(nuevaSucursal);

                                                    nuevaSucursal.asignarVendedor(vendedor);

                                                    System.out.println("Quiniela encargada actualizada correctamente.");
                                                    seleccionValida = true;
                                                    menuInterno = false;
                                                    actualizar = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 9:
                                        menuInterno = false;
                                        break;
                                }
                            } else {
                                System.out.println("Selecciona una opción válida");
                                input.nextLine();
                            }
                        } while (menuInterno);

                    } else {
                        System.out.println("Selecciona una opción válida");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Selecciona una opción válida");
                    input.nextLine();
                }
            } while (actualizar);
        } else {
            System.out.println("No hay vendedores para actualizar");
        }
    }

    @Override
    public void borrar() {
        listar();
        if (!listaVacia) {
            System.out.println("Selecciona el vendedor a borrar");
            Scanner input = new Scanner(System.in);
            boolean borrar = true;
            do {
                if (input.hasNextInt()) {
                    int vendedorSeleccionado = input.nextInt() - 1;
                    boolean validacion = (vendedorSeleccionado >= 0 && vendedorSeleccionado < vendedores.length);
                    if (validacion && vendedores[vendedorSeleccionado] != null) {
                        input.nextLine();
                        System.out.println("Seguro deseas eliminar al vendedor: " +
                                vendedores[vendedorSeleccionado].getNombre() + "\n" +
                                "1- Si, borrar\n" +
                                "2- No, conservar");
                        boolean menuInterno = true;
                        do {
                            if (input.hasNextInt()) {
                                int seleccionMenu = input.nextInt();
                                input.nextLine();
                                switch (seleccionMenu) {
                                    case 1:
                                        Sucursal sucursalAnterior = vendedores[vendedorSeleccionado].getSucursal();
                                        sucursalAnterior.removerVendedor(vendedores[vendedorSeleccionado]);
                                        vendedores[vendedorSeleccionado] = null;
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
                                System.out.println("Selecciona una opción válida");
                                input.nextLine();
                            }
                        } while (menuInterno);
                    } else {
                        System.out.println("Selecciona una opción válida");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Selecciona una opción válida");
                    input.nextLine();
                }
            } while (borrar);
        } else {
            System.out.println("No hay vendedores para borrar");
        }
    }

    private void mostrarInformacion() {
        System.out.println("Selecciona al vendedor a ver");
        listar();
        if (!listaVacia) {
            Scanner input = new Scanner(System.in);
            boolean validacion = false;
            int indiceVendedor;

            do {
                System.out.println("Ingresa el número del vendedor:");
                if (input.hasNextInt()) {
                    indiceVendedor = input.nextInt() - 1;
                    if (indiceVendedor >= 0 && indiceVendedor < vendedores.length && vendedores[indiceVendedor] != null) {
                        Vendedor vendedor = vendedores[indiceVendedor];
                        System.out.println("Nombre: " + vendedor.getNombre() + "\n" +
                                "Apellido: " + vendedor.getApellido() + "\n" +
                                "Email: " + vendedor.getEmail() + "\n" +
                                "DNI: " + vendedor.getDni() + "\n" +
                                "CUIL: " + vendedor.getCuil() + "\n");

                        Sucursal sucursal = vendedor.getSucursal();
                        if (sucursal != null) {
                            System.out.println("Sucursal: " + sucursal.getnumeroSucursal() + "\n" +
                                    "Quiniela: " + sucursal.getQuiniela().getNombre());
                        } else {
                            System.out.println("No está asignado a ninguna sucursal.");
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

    public boolean existenVendedores() {
        for (int i = 0; i < vendedores.length; i++) {
            if (vendedores[i] != null) {
                return true;
            }
        }
        return false;
    }

    public Vendedor buscarVendedor(int posicion) {
        int vendedorElegido = posicion - 1;
        if (vendedores[vendedorElegido] != null) {
            return vendedores[vendedorElegido];
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
                System.out.println("Selecciona un número válido");
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
                        System.out.println("Selecciona un número válido");
                }
            }
        } while (menu);
    }

    public static void mostrarMenu() {
        System.out.println("""
                Menú gestionar vendedores:
                1- Listar vendedores
                2- Crear vendedor
                3- Actualizar vendedor
                4- Eliminar vendedor
                5- Mostrar información de vendedor
                6- Ir al menú anterior""");
    }
}
