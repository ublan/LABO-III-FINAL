package ar.edu.utn.frbb.tup.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;

public class SummitCuenta {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Cuentas.txt";
    private static final String NOMBRE_ARCHIVO1 = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void escribirEnArchivo(Cuenta cuenta) {
        boolean archivoNuevo = !(new File(NOMBRE_ARCHIVO).exists());

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            // Si el archivo es nuevo, escribir la cabecera
            if (archivoNuevo) {
                escritor.write("CBU,nombre,tipoCuenta,balance,moneda,fechaCreacion,titularDni");
                escritor.newLine();
            }

            escritor.write(cuenta.getCBU() + ",");
            escritor.write(cuenta.getNombre() + ",");
            escritor.write(cuenta.getTipoCuenta() + ",");
            escritor.write(cuenta.getBalance() + ",");
            escritor.write(cuenta.getMoneda() + ",");
            escritor.write(cuenta.getFechaCreacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ",");
            escritor.write(cuenta.getTitular().getDni() + "");
            escritor.newLine();

            System.out.println("Datos de la cuenta guardados en " + NOMBRE_ARCHIVO + " correctamente.");
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    public static Cliente buscarClientePorDni(long dni) {
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO1))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 0) {
                    try {
                        long dniCliente = Long.parseLong(campos[0]);
                        if (dniCliente == dni) {
                            Cliente cliente = new Cliente();
                            cliente.setDni(dniCliente);
                            cliente.setNombre(campos[1]);
                            cliente.setApellido(campos[2]);
                            return cliente;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir el primer campo a long: " + campos[0]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra el cliente
    }

}
