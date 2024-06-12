package ar.edu.utn.frbb.tup.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;

public class SummitCliente {

    private static final String NOMBRE_ARCHIVO = "C:\\Users\\joaqu\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void escribirEnArchivo(Cliente cliente) {
        boolean archivoNuevo = !(new File(NOMBRE_ARCHIVO).exists());

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            // Si el archivo es nuevo, escribir la cabecera
            if (archivoNuevo) {
                escritor.write("id,nombre,apellido,dni,fechaNacimiento,tipoPersona,banco,fechaAlta");
                escritor.newLine();
            }

            escritor.write(cliente.getDni() + ",");
            escritor.write(cliente.getNombre() + ",");
            escritor.write(cliente.getApellido() + ",");
            escritor.write(cliente.getDni() + ",");
            escritor.write(cliente.getFechaNacimiento().toString() + ",");
            escritor.write(cliente.getTipoPersona() + ",");
            escritor.write(cliente.getBanco() + ",");
            escritor.write(cliente.getFechaAlta() + ".");
            escritor.newLine();

            System.out.println("Datos del cliente guardados en " + NOMBRE_ARCHIVO + " correctamente.");
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    public static Cliente findByDni(String dni) {
        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            // Leer la cabecera
            lector.readLine();

            // Leer cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                // Validar que la línea tiene la cantidad esperada de datos
                if (datos.length < 7) {
                    System.err.println("Línea mal formada: " + linea);
                    continue;
                }

                if (datos[3].equals(dni)) { // Comparar el DNI en el archivo con el DNI proporcionado
                    Cliente cliente = new Cliente();
                    cliente.setDni(Long.parseLong(dni));
                    cliente.setNombre(datos[1]);
                    cliente.setApellido(datos[2]);
                    cliente.setFechaNacimiento(LocalDate.parse(datos[3]));
                    cliente.setTipoPersona(TipoPersona.fromString(datos[4]));
                    cliente.setBanco(datos[5]);
                    cliente.setFechaAlta(LocalDate.parse(datos[6]));
                    return cliente;
                }
            }
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo: " + ex.getMessage());
        }
        return null;
    }
}
