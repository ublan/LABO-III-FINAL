package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ar.edu.utn.frbb.tup.persistence.SummitMovimientos;

public class Transferencia {
    private static final String TIPO_MOVIMIENTO = "TRANSFERENCIA";
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void Transferir() {
        try {
            String CBU_INICIO;
            String CbuValidadoInicio = null;
            while (CbuValidadoInicio == null) {
                System.out.println("Ingrese su CBU: ");
                CBU_INICIO = scanner.nextLine();
                CbuValidadoInicio = SummitMovimientos.buscarCuentaPorCBU(CBU_INICIO);

                if (CbuValidadoInicio == null) {
                    System.out.println("El CBU ingresado no existe. Por favor, intente nuevamente.");
                }
            }

            double saldoDisponible = obtenerSaldo(CbuValidadoInicio);
            if (saldoDisponible == -1) {
                System.out.println("Error al obtener el saldo disponible.");
                return;
            }

            System.out.println("Ingresar el monto a transferir: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Monto inválido. Ingresar un número.");
                scanner.next(); // Descarta la entrada inválida
            }
            double monto = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character left by nextDouble()

            if (monto <= 0) {
                System.out.println("El monto debe ser positivo.");
                return;
            }

            if (monto > saldoDisponible) {
                System.out.println("Fondos insuficientes. Su saldo disponible es: " + saldoDisponible);
                return;
            }

            String CBU_FINAL;
            String CbuValidadoFinal = null;
            while (CbuValidadoFinal == null) {
                System.out.println("Ingrese el CBU del destinatario: ");
                CBU_FINAL = scanner.nextLine();
                CbuValidadoFinal = SummitMovimientos.buscarCuentaPorCBU(CBU_FINAL);

                if (CbuValidadoFinal == null) {
                    System.out.println("El CBU ingresado no existe. Por favor, intente nuevamente.");
                }
            }

            // Registrar el movimiento
            SummitMovimientos.registrarMovimientoTransferencia(CbuValidadoInicio, CbuValidadoFinal, monto,
                    TIPO_MOVIMIENTO);

            // Actualizar los saldos de las cuentas involucradas
            actualizarSaldos(CbuValidadoInicio, CbuValidadoFinal, monto);

        } finally {
            // El Scanner no se cierra aquí
        }
    }

    private static double obtenerSaldo(String CBU) {
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length >= 6) {
                    String cbuCuenta = campos[0];
                    if (cbuCuenta.equals(CBU)) {
                        return Double.parseDouble(campos[3]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // Indica que hubo un error al obtener el saldo
    }

    private static void actualizarSaldos(String CbuInicio, String CbuFinal, double monto) {
        List<String> lineas = new ArrayList<>();
        boolean cuentaInicioEncontrada = false;
        boolean cuentaFinalEncontrada = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length >= 6) {
                    String cbuCuenta = campos[0];
                    if (cbuCuenta.equals(CbuInicio)) {
                        double balanceActual = Double.parseDouble(campos[3]);
                        campos[3] = String.valueOf(balanceActual - monto);
                        cuentaInicioEncontrada = true;
                    }
                    if (cbuCuenta.equals(CbuFinal)) {
                        double balanceActual = Double.parseDouble(campos[3]);
                        campos[3] = String.valueOf(balanceActual + monto);
                        cuentaFinalEncontrada = true;
                    }
                }
                lineas.add(String.join(",", campos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cuentaInicioEncontrada && cuentaFinalEncontrada) {
            try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(NOMBRE_ARCHIVO))) {
                for (String linea : lineas) {
                    escritor.write(linea);
                    escritor.newLine();
                }
                System.out.println("Los balances han sido actualizados correctamente.");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontraron una o ambas cuentas con los CBUs proporcionados.");
        }
    }
}
