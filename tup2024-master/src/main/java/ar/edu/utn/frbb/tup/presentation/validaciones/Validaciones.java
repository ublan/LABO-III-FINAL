package ar.edu.utn.frbb.tup.presentation.validaciones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validaciones {
    public static boolean esString(Object objeto) {
        return objeto instanceof String;
    }

    public static LocalDate obtenerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        Scanner scanner = new Scanner(System.in);
        while (!fechaValida) {
            try {
                System.out.println(mensaje);
                fecha = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
                if (fecha.isAfter(LocalDate.now())) {
                    System.out.println("La fecha no puede ser mayor al año actual. Ingrese una fecha válida:");
                } else {
                    fechaValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }

        return fecha;
    }

    public static long ingresarDni(Scanner scanner) {
        long dni = -1;
        boolean valid = false;
        while (!valid) {
            System.out.println("Ingrese el DNI del cliente:");
            try {
                dni = scanner.nextLong();
                if (dni >= 1000000 && dni <= 85000000) {
                    valid = true;
                } else {
                    System.out.println("DNI inválido. Ingrese un DNI entre 1000000 y 85000000:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
                scanner.next();
            }
        }
        return dni;
    }

    public static int validarSaldoInicial(Scanner scanner) {
        int saldoInicial;

        do {
            System.out.println("Ingrese el saldo inicial de la cuenta:");
            saldoInicial = scanner.nextInt();
            if (saldoInicial < 0) {
                System.out.println("El saldo inicial no puede ser negativo. Ingrese un valor válido.");
            }
        } while (saldoInicial < 0);

        return saldoInicial;
    }
}