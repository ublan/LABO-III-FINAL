package ar.edu.utn.frbb.tup.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import ar.edu.utn.frbb.tup.service.operaciones.modulos.*;

public class SummitMovimientos {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";
    private static final String NOMBRE_ARCHIVO2 = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Movimientos.txt";

    public static void registrarMovimiento(String CBU, double monto, String tipoMovimiento) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO2, true))) {
            escritor.write(CBU + "," + monto + "," + tipoMovimiento);
            escritor.newLine();
            System.out.println("Movimiento registrado en " + NOMBRE_ARCHIVO2 + " correctamente.");
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo de movimientos: " + ex.getMessage());
        }

        Deposito.sobreescribirCuentaDeposito(CBU, monto);
    }

    public static void registrarMovimientoTransferencia(String CBU_INICIO, String CBU_FINAL, double monto,
            String tipoMovimiento) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO2, true))) {
            escritor.write(CBU_INICIO + "," + CBU_FINAL + "," + monto + "," + tipoMovimiento);
            escritor.newLine();
            System.out.println("Movimiento registrado en " + NOMBRE_ARCHIVO2 + " correctamente.");
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo de movimientos: " + ex.getMessage());
        }
    }

    public static void registrarMovimientoRetiro(String CBU, double monto, String tipoMovimiento) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO2, true))) {
            escritor.write(CBU + "," + monto + "," + tipoMovimiento);
            escritor.newLine();
            System.out.println("Movimiento registrado en " + NOMBRE_ARCHIVO2 + " correctamente.");
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo de movimientos: " + ex.getMessage());
        }

        Retiro.sobreescribirCuentaRetiro(CBU, monto);
    }

    public static String buscarCuentaPorCBU(String CBU) {
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 0) {
                    String cbuCuenta = campos[0];
                    if (cbuCuenta.equals(CBU)) {
                        return cbuCuenta;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // e.ArchivoIlegibleException("Error al leer el archivo de cuentas");
        }
        return null; // Si no se encuentra la cuenta
    }

    public static double obtenerSaldo(String CBU) {
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 3) {
                    String cbuCuenta = campos[0];
                    if (cbuCuenta.equals(CBU)) {
                        return Double.parseDouble(campos[3]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // e.ArchivoIlegibleException("Error al leer el archivo de cuentas");
        }
        return 0.0; // Si no se encuentra la cuenta
    }
}
