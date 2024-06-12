package ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes;

import java.io.*;
import java.util.*;

public class BorrarCliente {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void borrarCliente(String dni) {
        List<String> clientes = new ArrayList<>();
        boolean clienteEncontrado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (!campos[3].equals(dni)) {
                    clientes.add(linea);
                } else {
                    clienteEncontrado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clienteEncontrado) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Seguro que quieres borrar al Cliente con DNI " + dni + "?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
                    for (String cliente : clientes) {
                        escritor.write(cliente);
                        escritor.newLine();
                    }
                    System.out.println("El cliente con DNI " + dni + " ha sido eliminado.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Operación cancelada. El cliente con DNI " + dni + " no ha sido eliminado.");
            }
        } else {
            System.out.println("El cliente con DNI " + dni + " no existe.");
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
