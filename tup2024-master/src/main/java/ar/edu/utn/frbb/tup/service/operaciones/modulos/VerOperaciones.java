package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ar.edu.utn.frbb.tup.persistence.SummitMovimientos;

public class VerOperaciones {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";

    public static void verOperaciones() {
        Scanner scanner = new Scanner(System.in);
        String CBU;
        String CbuValidado = null;

        try {
            while (CbuValidado == null) {
                System.out.println("Ingrese el CBU para ver las operaciones: ");
                CBU = scanner.nextLine();
                CbuValidado = SummitMovimientos.buscarCuentaPorCBU(CBU);

                if (CbuValidado == null) {
                    System.out.println("El CBU ingresado no existe. Por favor, intente nuevamente.");
                }
            }

            // Una vez validado el CBU, obtener y mostrar las operaciones
            List<String> operaciones = obtenerOperaciones(CbuValidado);

            if (operaciones.isEmpty()) {
                System.out.println("No se encontraron operaciones para el CBU proporcionado.");
            } else {
                System.out.println("*****************************************");
                System.out.println("*      OPERACIONES REALIZADAS           *");
                System.out.println("*****************************************");
                for (String operacion : operaciones) {
                    System.out.println("* " + operacion);
                }
                System.out.println("*****************************************");
            }
        } finally {
            // No cerrar el scanner aquí
        }

        // Pausa antes de volver al menú principal
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }

    private static List<String> obtenerOperaciones(String CBU) {
        List<String> operaciones = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 3 && campos[0].equals(CBU)) {
                    operaciones.add(formatearOperacion(campos));
                } else if (campos.length == 4 && (campos[0].equals(CBU) || campos[1].equals(CBU))) {
                    operaciones.add(formatearOperacionTransferencia(campos, CBU));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return operaciones;
    }

    private static String formatearOperacion(String[] campos) {
        String cbu = campos[0];
        String monto = campos[1];
        String tipoMovimiento = campos[2];
        return String.format("CBU: %s | Tipo: %s | Monto: %s", cbu, tipoMovimiento, monto);
    }

    private static String formatearOperacionTransferencia(String[] campos, String CBU) {
        String cbuOrigen = campos[0];
        String cbuDestino = campos[1];
        String monto = campos[2];
        String tipoMovimiento = campos[3];
        if (cbuOrigen.equals(CBU)) {
            return String.format("CBU Origen: %s | CBU Destino: %s | Tipo: %s | Monto: %s", cbuOrigen, cbuDestino,
                    tipoMovimiento, monto);
        } else {
            return String.format("CBU Origen: %s | CBU Destino: %s | Tipo: %s | Monto: %s", cbuOrigen, cbuDestino,
                    tipoMovimiento, monto);
        }
    }
}
