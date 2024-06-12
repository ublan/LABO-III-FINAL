package ar.edu.utn.frbb.tup.service.operaciones.ManejoCuentas;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BorrarCuenta {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";

    public static void borrarCuenta(String CBU) {
        List<String> cuentas = new ArrayList<>();
        boolean cuentaEncontrada = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (!campos[0].equals(CBU)) {
                    cuentas.add(linea);
                } else {
                    cuentaEncontrada = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cuentaEncontrada) {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
                for (String cuenta : cuentas) {
                    escritor.write(cuenta);
                    escritor.newLine();
                }
                System.out.println("La cuenta con CBU " + CBU + " ha sido eliminada.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La cuenta con CBU " + CBU + " no existe.");
        }
    }
}