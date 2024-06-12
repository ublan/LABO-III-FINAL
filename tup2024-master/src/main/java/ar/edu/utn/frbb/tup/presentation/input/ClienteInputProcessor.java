package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.presentation.validaciones.Validaciones;
import ar.edu.utn.frbb.tup.service.control.ClienteService;
import ar.edu.utn.frbb.tup.service.exception.ClienteAlreadyExistsException;

import java.time.LocalDate;
//import java.util.Scanner;


public class ClienteInputProcessor extends BaseInputProcessor{

    ClienteService clienteService = new ClienteService();

    public void altaCliente() {
        // Scanner scanner = new Scanner(System.in);
        // Ingreso de datos del Cliente
        Cliente cliente = new Cliente();
        clearScreen();
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        cliente.setNombre(nombre);

        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();
        cliente.setApellido(apellido);

        System.out.println("Ingrese el DNI del cliente:"); // CAMBIOS MIOS ingresar dni
        long dni = scanner.nextLong();
        while (dni < 1000000 || dni > 85000000) {
            scanner.nextLine();
            System.out.println("DNI inválido. Ingrese un DNI entre 1000000 y 85000000:");
            dni = scanner.nextLong();

        }

        cliente.setDni(dni);

        System.out.println("Ingrese la fecha de nacimiento del cliente (Formato: YYYY-MM-DD):"); //CAMBIOS MIOS ingresar fecha nacimiento
        LocalDate fechaNacimiento = null;
        boolean isfechaValida = false;
        while (!isfechaValida) {
            try {
                fechaNacimiento = LocalDate.parse(scanner.nextLine());
                isfechaValida = true;
            } catch (RuntimeException e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cliente.setFechaNacimiento(fechaNacimiento);

        System.out.println("Ingrese el tipo de persona Física(F) o Jurídica(J):");
        String tipoPersonaStr = scanner.nextLine().toUpperCase();
        while (!tipoPersonaStr.equals("F") && !tipoPersonaStr.equals("J")) {
            System.out.println("Tipo de persona inválido. Ingrese NATURAL o JURIDICA:");
            tipoPersonaStr = scanner.nextLine().toUpperCase();
        }
        TipoPersona tipoPersona = TipoPersona.fromString(tipoPersonaStr);
        cliente.setTipoPersona(tipoPersona);

        System.out.println("Ingrese el banco del cliente:");
        String banco = scanner.nextLine();
        cliente.setBanco(banco);

        System.out.println("Ingrese la fecha de alta del cliente (Formato: YYYY-MM-DD):");
        LocalDate fechaAlta = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaAlta = LocalDate.parse(scanner.nextLine());
                fechaValida = true;
            } catch (RuntimeException e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cliente.setFechaAlta(fechaAlta);

        try {
            clienteService.darDeAltaCliente(cliente);
        } catch (ClienteAlreadyExistsException e) {
            System.out.println();
            System.out.println();
            System.out.println(e.getMessage());
        }
        

        clearScreen();

    }
}

