package ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MostrarCliente {

    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void mostrarCliente(String dni) {
        List<String> clientes = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 1 && campos[3].trim().equals(dni.trim())) {
                    clientes.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clientes.isEmpty()) {
            System.out.println("No se encontraron clientes con el DNI: " + dni);
        } else {
            System.out.println("----------------------------");
            for (String cliente : clientes) {
                String[] campos = cliente.split(",");
                System.out.println("ID: " + campos[0]);
                System.out.println("Nombre: " + campos[1]);
                System.out.println("Apellido: " + campos[2]);
                System.out.println("DNI: " + campos[3]);
                System.out.println("Fecha de Nacimiento: " + campos[4]);
                System.out.println("Tipo de Persona: " + campos[5]);
                System.out.println("Banco: " + campos[6]);
                System.out.println("Fecha de Alta: " + campos[7]);
                System.out.println("----------------------------");
            }
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
