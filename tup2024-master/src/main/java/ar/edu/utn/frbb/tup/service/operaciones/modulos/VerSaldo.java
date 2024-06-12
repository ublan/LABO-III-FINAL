package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import java.util.Scanner;
import ar.edu.utn.frbb.tup.persistence.SummitMovimientos;

public class VerSaldo {

    public static void verSaldo() {
        Scanner scanner = new Scanner(System.in);
        String CBU;
        String CbuValidado = null;

        try {
            while (CbuValidado == null) {
                System.out.println("Ingrese el CBU para ver saldo: ");
                CBU = scanner.nextLine();
                CbuValidado = SummitMovimientos.buscarCuentaPorCBU(CBU);

                if (CbuValidado == null) {
                    System.out.println("El CBU ingresado no existe. Por favor, intente nuevamente.");
                }
            }

            // Una vez validado el CBU, obtener y mostrar el saldo
            double saldo = SummitMovimientos.obtenerSaldo(CbuValidado);

            System.out.println("*****************************************");
            System.out.println("*    SALDO DISPONIBLE EN TU CUENTA      *");
            System.out.println("*****************************************");
            System.out.println("* Saldo disponible: " + saldo + "       *");
            System.out.println("*                                       *");
            System.out.println("*                                       *");
            System.out.println("*****************************************");
        } finally {
        }

        // Pausa antes de volver al menú principal
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
