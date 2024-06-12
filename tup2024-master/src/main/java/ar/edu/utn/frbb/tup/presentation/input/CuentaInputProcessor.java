package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.persistence.SummitCuenta;
import ar.edu.utn.frbb.tup.service.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.control.ClienteService;
import ar.edu.utn.frbb.tup.service.control.CuentaService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CuentaInputProcessor extends BaseInputProcessor {
    private final CuentaService cuentaService;
    private final ClienteService clienteService;

    public CuentaInputProcessor(CuentaService cuentaService, ClienteService clienteService) {
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
    }

    public void altaCuenta() {
        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta = new Cuenta();
        clearScreen();

        // Buscar cliente por DNI para vincular la cuenta
        System.out.println("Ingrese el DNI del cliente para vincular la cuenta:");
        long dni = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Cliente cliente = SummitCuenta.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("No se encontró un cliente con el DNI ingresado.");
            return;
        }

        cuenta.setCBU();// Generar CBU para la cuenta
        cuenta.setTitular(cliente);// Asociar el cliente a la cuenta

        System.out.println("Ingrese el nombre de la cuenta:");
        String nombreCuenta = scanner.nextLine();
        cuenta.setNombre(nombreCuenta);

        System.out.println("Ingrese el tipo de cuenta (CORRIENTE, AHORRO):");
        String tipoCuentaStr = scanner.nextLine().toUpperCase();
        TipoCuenta tipoCuenta = TipoCuenta.valueOf(tipoCuentaStr);
        cuenta.setTipoCuenta(tipoCuenta);

        cuenta.setMoneda("ARS");

        System.out.println("Ingrese el saldo inicial de la cuenta:");
        int saldoInicial = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        cuenta.setBalance(saldoInicial);

        cuenta.setFechaCreacion(LocalDateTime.now());

        try {
            cuentaService.darDeAltaCuenta(cuenta);
            
            System.out.println("Cuenta registrada con éxito.");
        } catch (CuentaAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        clearScreen();
    }
}





