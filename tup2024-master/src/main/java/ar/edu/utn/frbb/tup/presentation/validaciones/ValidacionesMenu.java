package ar.edu.utn.frbb.tup.presentation.validaciones;

public class ValidacionesMenu {
    public static boolean esNumero(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
