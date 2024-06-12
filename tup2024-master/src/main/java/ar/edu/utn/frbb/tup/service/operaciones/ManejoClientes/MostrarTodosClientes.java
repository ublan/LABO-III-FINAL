package ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes;

import java.io.*;
import java.util.Scanner;

public class MostrarTodosClientes {

    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void mostrarTodosLosClientes() {
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Esperar a que el usuario presione Enter antes de volver al menú
        System.out.println("Presione Enter para volver al menú...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}