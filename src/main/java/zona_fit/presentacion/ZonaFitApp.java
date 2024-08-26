package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;


import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos este objeto para poder usar los metodos
        IClienteDAO clienteDao = new ClienteDAO();
        System.out.println("*** Zona Fit App ***");
        //System.out.println("*** Listado de Clientes ***");
        //listarClientesParaMain(clienteDao);
        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, clienteDao);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public static int mostrarMenu(Scanner consola) {
        System.out.print("""
                Menu:
                1.Buscar Cliente por Id
                2.Agregar cliente
                3.Modificar cliente
                4.Eliminar cliente
                5.Listar clientes
                6.Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    public static boolean ejecutarOpciones(int opcion, Scanner consola, IClienteDAO clienteDao) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("\n--- Buscar cliente ---");
                System.out.print("Ingrese el id del cliente: ");
                var idCLiente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCLiente);
                var encontrado = clienteDao.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.println("\nEl cliente se ha encontrado: " + cliente);
                } else {
                    System.out.println("\nEl cliente no se ha encontrado: " + cliente);
                }
            }
            case 2 -> {
                System.out.println("\n--- Agregar cliente ---");
                System.out.print("Ingrese el nombre del cliente: ");
                var nombreCliente = consola.nextLine();
                System.out.print("Ingrese el apellido del cliente: ");
                var apellidoCliente = consola.nextLine();
                System.out.print("Ingrese la membresia del cliente: ");
                var membresiaCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(nombreCliente, apellidoCliente, membresiaCliente);
                var clienteAgregado = clienteDao.agregarCliente(cliente);
                if (clienteAgregado) {
                    System.out.println("\nEl cliente se ha agregado: " + cliente + "\n");
                    //listarClientesParaMain(clienteDao);
                } else {
                    System.out.print("\nEl cliente no se ha agregado: " + cliente + "\n");
                }
            }
            case 3 -> {
                System.out.println("\n--- Modificar cliente ---");
                System.out.print("Ingrese el id del cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDao.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.print("Ingrese el nuevo nombre del cliente, si es el " +
                            "nombre coloquelo: ");
                    var nombreCliente = consola.nextLine();
                    System.out.print("Ingrese el nuevo apellido del cliente: ");
                    var apellidoCliente = consola.nextLine();
                    System.out.print("Ingrese el nuevo valor de la membresia: ");
                    var membresiaCliente = Integer.parseInt(consola.nextLine());
                    var clienteModificar = new Cliente(idCliente, nombreCliente, apellidoCliente, membresiaCliente);
                    var clienteModificado = clienteDao.modificarCliente(clienteModificar);
                    if (clienteModificado) {
                        System.out.print("El cliente se ha actualizado: " + clienteModificar);
                    } else {
                        System.out.print("El cliente no se ha actualizado: " + clienteModificar);
                    }
                } else {
                    System.out.println("El cliente no se ha encontrado: " + cliente);
                }
            }
            case 4 -> {
                System.out.println("\n--- Eliminar cliente ---");
                System.out.print("Ingrese el id del cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDao.eliminarCliente(cliente);
                if (eliminado) {
                    System.out.println("El cliente se ha eliminado: " + cliente);
                } else {
                    System.out.println("El cliente no se ha encontrado: " + cliente);
                }
            }
            case 5 -> {
                System.out.println("\n--- Listar cliente ---");
                var listadoClientes = clienteDao.listarClientes();
                for (var cliente : listadoClientes) {
                    System.out.println(cliente);
                }
            }
            case 6 -> {
                salir = true;
            }
            default -> {
                System.out.println("Opcion invalida: " + opcion);
            }
        }
        return salir;
    }

    //public static void opcionBuscarCliente(Scanner consola, IClienteDAO clienteDao) {
    //System.out.print("Ingrese el id del cliente: ");
    //var idCLiente = Integer.parseInt(consola.nextLine());
    //var cliente = new Cliente(idCLiente);
    //var encontrado = clienteDao.buscarClientePorId(cliente);
    //if (encontrado) {
    //System.out.println("\nEl cliente se ha encontrado: " + cliente);
    //} else {
    //System.out.println("\nEl cliente no se ha encontrado: " + cliente);
    // }
    //}

    //public static void opcionAgregarCliente(Scanner consola, IClienteDAO clienteDao) {
    //System.out.print("Ingrese el nombre del cliente: ");
    //var nombreCliente = consola.nextLine();
    //System.out.print("Ingrese el apellido del cliente: ");
    //var apellidoCliente = consola.nextLine();
    //System.out.print("Ingrese la membresia del cliente: ");
    //var membresiaCliente = Integer.parseInt(consola.nextLine());
    //var cliente = new Cliente(nombreCliente, apellidoCliente, membresiaCliente);
    //var clienteAgregado = clienteDao.agregarCliente(cliente);
    //if (clienteAgregado) {
    //     System.out.println("\nEl cliente se ha agregado: " + cliente + "\n");
    //    //listarClientesParaMain(clienteDao);
    //} else {
    //   System.out.print("\nEl cliente no se ha agregado: " + cliente + "\n");
    //}
    // }

    public static void opcionModificarCliente(Scanner consola, IClienteDAO clienteDao) {
        System.out.println("\n--- Modificar cliente ---");
        System.out.print("Ingrese el id del cliente: ");
        var idCliente = Integer.parseInt(consola.nextLine());
        var cliente = new Cliente(idCliente);
        var encontrado = clienteDao.buscarClientePorId(cliente);
        if (encontrado) {
            System.out.print("Ingrese el nuevo nombre del cliente, si es el " +
                    "nombre coloquelo: ");
            var nombreCliente = consola.nextLine();
            System.out.print("Ingrese el nuevo apellido del cliente: ");
            var apellidoCliente = consola.nextLine();
            System.out.print("Ingrese el nuevo valor de la membresia: ");
            var membresiaCliente = Integer.parseInt(consola.nextLine());
            var clienteModificar = new Cliente(idCliente, nombreCliente, apellidoCliente, membresiaCliente);
            var clienteModificado = clienteDao.modificarCliente(clienteModificar);
            if (clienteModificado) {
                System.out.print("El cliente se ha actualizado: " + clienteModificar);
            } else {
                System.out.print("El cliente no se ha actualizado: " + clienteModificar);
            }
        } else {
            System.out.println("El cliente no se ha encontrado: " + cliente);
        }
    }

    public static void opcionEliminarCliente(Scanner consola, IClienteDAO clienteDao) {
        System.out.print("Ingrese el id del cliente: ");
        var idCliente = Integer.parseInt(consola.nextLine());
        var cliente = new Cliente(idCliente);
        var eliminado = clienteDao.eliminarCliente(cliente);
        if (eliminado) {
            System.out.println("El cliente se ha eliminado: " + cliente);
        } else {
            System.out.println("El cliente no se ha encontrado: " + cliente);
        }
    }

    //public static void listarClientesParaMain(IClienteDAO clienteDao2) {
    //IClienteDAO clienteDao = new ClienteDAO();
    //var listadoClientes = clienteDao.listarClientes();
    //for (var cliente : listadoClientes) {
    //    System.out.println(cliente);
    // }
    //}
}
