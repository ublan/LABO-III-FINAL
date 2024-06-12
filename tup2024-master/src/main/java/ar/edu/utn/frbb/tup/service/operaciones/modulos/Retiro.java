package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ar.edu.utn.frbb.tup.persistence.SummitMovimientos;

public class Retiro {
    private static final String TIPO_MOVIMIENTO = "RETIRO";
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";

    public static void Retirar() {
        Scanner scanner = new Scanner(System.in);

        try {
            String CBU;
            String CbuValidado = null;
            while (CbuValidado == null) {
                System.out.println("Ingresar el CBU al que desea retirar: ");
                CBU = scanner.nextLine();
                CbuValidado = SummitMovimientos.buscarCuentaPorCBU(CBU);

                if (CbuValidado == null) {
                    System.out.println("El CBU ingresado no existe. Por favor, intente nuevamente.");
                }
            }
            System.out.println("Ingresar el monto a retirar: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Monto inválido. Ingresar un número.");
                scanner.next(); // Descarta la entrada inválida
            }
            double monto = scanner.nextDouble();

            // Validar que el monto sea positivo
            if (monto <= 0) {
                System.out.println("El monto debe ser positivo.");
                return;
            }

            // Registrar el movimiento
            SummitMovimientos.registrarMovimientoRetiro(CbuValidado, monto, TIPO_MOVIMIENTO);
        } finally {
            // Cerrar el scanner para liberar recursos

        }
    }

    public static void sobreescribirCuentaRetiro(String CbuValidado, double monto) {
        List<String> lineas = new ArrayList<>();
        boolean cuentaEncontrada = false;

        // Leer el archivo Cuentas.txt y actualizar el balance
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length >= 6) {
                    String cbuCuenta = campos[0];
                    if (cbuCuenta.equals(CbuValidado)) {
                        double balanceActual = Double.parseDouble(campos[3]);
                        double nuevoBalance = balanceActual - monto;
                        campos[3] = String.valueOf(nuevoBalance);
                        cuentaEncontrada = true;
                    }
                }
                lineas.add(String.join(",", campos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Si la cuenta fue encontrada, sobrescribir el archivo con los nuevos datos
        if (cuentaEncontrada) {
            try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(NOMBRE_ARCHIVO))) {
                for (String linea : lineas) {
                    escritor.write(linea);
                    escritor.newLine();
                }
                System.out.println("El balance ha sido actualizado correctamente.");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró una cuenta con el CBU proporcionado.");
        }
    }
}
