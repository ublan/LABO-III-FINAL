package ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModificarCliente {

    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void modificarCliente(String Dni) {
        List<String> clientes = new ArrayList<>();
        List<String> nuevosDatos = new ArrayList<>();
        boolean clienteEncontrado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 1 && campos[3].trim().equals(Dni.trim())) {
                    clienteEncontrado = true;
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Cliente encontrado:");
                    System.out.println("ID: " + campos[0]);
                    System.out.println("Nombre: " + campos[1]);
                    System.out.println("Apellido: " + campos[2]);
                    System.out.println("DNI: " + campos[3]);
                    System.out.println("Fecha de Nacimiento: " + campos[4]);
                    System.out.println("Tipo de Persona: " + campos[5]);
                    System.out.println("Banco: " + campos[6]);
                    System.out.println("Fecha de Alta: " + campos[7]);
                    System.out.println("");
                    System.out.println("----------------------------");
                    System.out.println("¿Qué desea modificar?");
                    System.out.println("----------------------------");
                    System.out.println("1. Nombre");
                    System.out.println("2. Apellido");
                    System.out.println("3. DNI");
                    System.out.println("4. Fecha de Nacimiento");
                    System.out.println("5. Tipo de Persona");
                    System.out.println("6. Banco");
                    System.out.println("7. Fecha de Alta");
                    System.out.println("----------------------------");
                    System.out.println("Ingrese el número de la opción:");

                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            campos[1] = scanner.nextLine();
                            break;
                        case 2:
                            System.out.print("Ingrese el nuevo apellido: ");
                            campos[2] = scanner.nextLine();
                            break;
                        case 3:
                            System.out.print("Ingrese el nuevo DNI: ");
                            campos[3] = scanner.nextLine();
                            break;
                        case 4:
                            System.out.print("Ingrese la nueva fecha de nacimiento - (Formato: YYYY-MM-DD): ");
                            campos[4] = scanner.nextLine();
                            break;
                        case 5:
                            System.out.print("Ingrese el nuevo tipo de persona - Física(F) o Jurídica(J): ");
                            campos[5] = scanner.nextLine();
                            break;
                        case 6:
                            System.out.print("Ingrese el nuevo banco: ");
                            campos[6] = scanner.nextLine();
                            break;
                        case 7:
                            System.out.print("Ingrese la nueva fecha de alta - (Formato: YYYY-MM-DD): ");
                            campos[7] = scanner.nextLine();
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    nuevosDatos.add(String.join(",", campos));
                } else {
                    nuevosDatos.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clienteEncontrado) {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
                for (String datos : nuevosDatos) {
                    escritor.write(datos);
                    escritor.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Cliente modificado con éxito.");
        } else {
            System.out.println("No se encontraron clientes con el DNI: " + Dni);
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
