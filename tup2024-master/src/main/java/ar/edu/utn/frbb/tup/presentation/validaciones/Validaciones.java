package ar.edu.utn.frbb.tup.presentation.validaciones;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validaciones {
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
                scanner.next(); // Limpiar la entrada no válida
            }
        }
        return dni;
    }
}