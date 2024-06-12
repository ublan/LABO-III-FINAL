package ar.edu.utn.frbb.tup.service.operaciones.ManejoCuentas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostrarCuentas {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";

    public static void mostrarCuentas(String dni) {
        List<String> cuentas = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 6 && campos[6].trim().equals(dni.trim())) {
                    cuentas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas asociadas al DNI: " + dni);
        } else {
            System.out.println("Cuentas asociadas al DNI: " + dni);
            for (String cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }
}
