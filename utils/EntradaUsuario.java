package utils;

import java.util.Scanner;

/**
 * Utilidad para lectura de entrada de usuario con validaciones.
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0
 */
public class EntradaUsuario {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lee un texto del usuario.
     * @param mensaje Mensaje a mostrar.
     * @return Texto ingresado.
     */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /**
     * Lee un número entero con validación.
     * @param mensaje Mensaje a mostrar.
     * @return Número entero ingresado.
     */
    public static int leerNumero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    /**
     * Lee un número decimal con validación.
     * @param mensaje Mensaje a mostrar.
     * @return Número decimal ingresado.
     */
    public static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número decimal válido.");
            }
        }
    }
}