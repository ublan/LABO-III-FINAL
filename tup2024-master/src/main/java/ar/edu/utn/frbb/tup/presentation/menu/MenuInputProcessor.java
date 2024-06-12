package ar.edu.utn.frbb.tup.presentation.menu;

import java.util.Scanner;

import ar.edu.utn.frbb.tup.model.Banco;
import ar.edu.utn.frbb.tup.presentation.input.BaseInputProcessor;
import ar.edu.utn.frbb.tup.presentation.input.ClienteInputProcessor;
import ar.edu.utn.frbb.tup.presentation.input.CuentaInputProcessor;
import ar.edu.utn.frbb.tup.presentation.validaciones.ValidacionesMenu;
import ar.edu.utn.frbb.tup.service.control.ClienteService;
import ar.edu.utn.frbb.tup.service.control.CuentaService;
import ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes.BorrarCliente;
import ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes.ModificarCliente;
import ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes.MostrarCliente;
import ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes.MostrarTodosClientes;
import ar.edu.utn.frbb.tup.service.operaciones.ManejoCuentas.BorrarCuenta;
import ar.edu.utn.frbb.tup.service.operaciones.ManejoCuentas.MostrarCuentas;
import ar.edu.utn.frbb.tup.service.operaciones.modulos.Deposito;
import ar.edu.utn.frbb.tup.service.operaciones.modulos.Retiro;
import ar.edu.utn.frbb.tup.service.operaciones.modulos.Transferencia;
import ar.edu.utn.frbb.tup.service.operaciones.modulos.VerOperaciones;
import ar.edu.utn.frbb.tup.service.operaciones.modulos.VerSaldo;

public class MenuInputProcessor extends BaseInputProcessor{

    CuentaService cuentaService = new CuentaService();

    ClienteInputProcessor clienteInputProcessor = new ClienteInputProcessor();
    CuentaInputProcessor cuentaInputProcessor = new CuentaInputProcessor(new CuentaService(), new ClienteService());
    boolean exit = false;


    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            clearScreen();

            System.out.println("***********************************************");
            System.out.println("*                                             *");
            System.out.println("*           Bienvenido a la aplicación        *");
            System.out.println("*                del Banco!                   *");
            System.out.println("*                                             *");
            System.out.println("***********************************************");
            System.out.println("*                                             *");
            System.out.println("*  1. Ir al menú Cliente                      *");
            System.out.println("*  2. Ir al menú Cuenta                       *");
            System.out.println("*  3. Operaciones                             *");
            System.out.println("*  0. Salir                                   *");
            System.out.println("*                                             *");
            System.out.println("***********************************************");
            System.out.print(" Ingrese su opción (0-3): ");

            String opcion = scanner.nextLine();
            while (!ValidacionesMenu.esNumero(opcion) || Integer.parseInt(opcion) < 0 || Integer.parseInt(opcion) > 3) {
                System.out.println("Opción inválida. Por favor seleccione un número entre 0 y 3.");
                opcion = scanner.nextLine();
            }

            int opcionInt = Integer.parseInt(opcion);
            clearScreen();

            switch (opcionInt) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuCuenta();
                    break;
                case 3:
                    menuOperaciones();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no reconocida.");
                    break;
            }
        }
    }



    public static void menuCliente(){

        Scanner scanner = new Scanner(System.in);
        ClienteInputProcessor clienteInputProcessor = new ClienteInputProcessor();
        clearScreen();
        System.out.println("*****************************************");
        System.out.println("*    Bienvenido al menú de Cliente!     *");
        System.out.println("*****************************************");
        System.out.println("* 1. Crear un nuevo Cliente             *");
        System.out.println("* 2. Modificar un Cliente               *");
        System.out.println("* 3. Eliminar un Cliente                *");
        System.out.println("* 4. Mostrar un Cliente                 *");
        System.out.println("* 5. Mostrar todos los Clientes         *");
        System.out.println("* 0. Volver al menu principal           *");
        System.out.println("*****************************************");
        System.out.print("Ingrese su Opción (0-5): ");

        String opcion = scanner.nextLine();
        while (!ValidacionesMenu.esNumero(opcion) || Integer.parseInt(opcion) < 0 || Integer.parseInt(opcion) > 5) {
            System.out.println("Opción inválida. Por favor seleccione un número entre 0 y 5.");
            opcion = scanner.nextLine();
        }

        int opcionInt = Integer.parseInt(opcion);
        clearScreen();

        switch (opcionInt) {
            case 1:
                
                clienteInputProcessor.altaCliente();
                break;
            case 2:
                System.out.print("Ingrese el DNI del cliente: ");
                String Dni = scanner.nextLine();
                ModificarCliente.modificarCliente(Dni); 
                break;
            case 3:
                System.out.println("Ingrese el DNI del cliente que desea eliminar:"); //FUNCIONA BIEN pero hay que hacer para que le pregunte al Cliente que hacer con las cuentas asociadoas al Cliente
                String DNI = scanner.nextLine();
                BorrarCliente.borrarCliente(DNI);
                break;
            case 4:
                System.out.println("Ingrese el DNI del cliente para mostrar su Información:");
                String dni = scanner.nextLine();
                MostrarCliente.mostrarCliente(dni);
                break;
            case 5:
                MostrarTodosClientes.mostrarTodosLosClientes();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                break;
        }
        


    }

    public static void menuCuenta(){
        Scanner scanner = new Scanner(System.in);
        CuentaInputProcessor cuentaInputProcessor = new CuentaInputProcessor(new CuentaService(), new ClienteService());
        clearScreen();

        System.out.println("*****************************************");
        System.out.println("*     Bienvenido al menú de Cuenta!     *");
        System.out.println("*****************************************");
        System.out.println("* 1. Crear una nueva Cuenta             *");
        System.out.println("* 2. Eliminar una Cuenta                *");
        System.out.println("* 3. Mostrar Cuentas del cliente        *");
        System.out.println("* 0. Volver al menu principal           *");
        System.out.println("*****************************************");

        String opcion = scanner.nextLine();
        while (!ValidacionesMenu.esNumero(opcion) || Integer.parseInt(opcion) < 0 || Integer.parseInt(opcion) > 3) {
            System.out.println("Opción inválida. Por favor seleccione un número entre 0 y 3.");
            opcion = scanner.nextLine();
        }

        int opcionInt = Integer.parseInt(opcion);
        clearScreen();
              // Limpiar el buffer de entrada

            switch (opcionInt) {
                case 1:
                    cuentaInputProcessor.altaCuenta();
                    break;
                case 2:
                    
                    System.out.println("Ingrese el CBU de la cuenta que desea eliminar:");
                    String CBU = scanner.nextLine();
                    BorrarCuenta.borrarCuenta(CBU);
                    break;
                case 3:
                    System.out.println("Ingrese el DNI del cliente para mostrar sus cuentas:");
                    String dni = scanner.nextLine();
                    MostrarCuentas.mostrarCuentas(dni);
                    break;
                case 0:
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                    break;
            }

            System.out.println("Presione Enter para volver al menú principal...");
            scanner.nextLine();
      
    }

    public static void menuOperaciones() {
        Scanner scanner = new Scanner(System.in);
        clearScreen();

        System.out.println("*****************************************");
        System.out.println("*    Bienvenido al menú de Operaciones! *");
        System.out.println("*****************************************");
        System.out.println("* 1. Depositar                          *");
        System.out.println("* 2. Retirar                            *");
        System.out.println("* 3. Transferencia                      *");
        System.out.println("* 4. Consultar saldo                    *");
        System.out.println("* 5. Ver movimientos                    *");
        System.out.println("* 0. Volver al menu principal           *");
        System.out.println("*****************************************");
        System.out.print("Ingrese su Opción (0-5): ");

        String opcion = scanner.nextLine();
        while (!ValidacionesMenu.esNumero(opcion) || Integer.parseInt(opcion) < 0 || Integer.parseInt(opcion) > 5) {
            System.out.println("Opción inválida. Por favor seleccione un número entre 0 y 5.");
            opcion = scanner.nextLine();
        }

        int opcionInt = Integer.parseInt(opcion);
        clearScreen();

        switch (opcionInt) {
            case 1:
                Deposito.depositar();
                break;
            case 2:
                Retiro.Retirar();
                break;
            case 3:
                Transferencia.Transferir();
                break;
            case 4:
                VerSaldo.verSaldo();
                break;
            case 5:
                VerOperaciones.verOperaciones();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                break;
        }
    }


















































































    public void renderMenu(Banco banco) {

        while (!exit) {
            System.out.println("Bienveido a la aplicación de Banco!");
            System.out.println("1. Crear un nuevo Cliente");
            System.out.println("2. Crear una nueva Cuenta");
            System.out.println("3. Generar un movimiento");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    clienteInputProcessor.altaCliente();
                    break;
            case 2:
                cuentaInputProcessor.altaCuenta();
                    break;
//            case 3:
//                performTransaction();
//                break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor seleccione 1-4.");
            }
            clearScreen();
        }
    }
}
