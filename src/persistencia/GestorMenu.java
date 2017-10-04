/*
 *  ███████╗██╗     ███████╗███╗   ██╗     ███████╗██╗   ██╗██╗    ██╗  █████╗   ██████╗  ██╗ █████╗  ███╗   ██╗
 *  ██╔════╝██║     ██╔════╝████╗  ██║    ██╔════╝██║   ██║██║    ██║██╔══██╗ ██╔══██╗ ██║██╔══██╗████╗  ██║
 *  █████╗   ██║     █████╗   ██╔██╗ ██║    ███████╗██║   ██║██║    ██║███████║ ██████╔╝██║███████║██╔██╗ ██║
 *  ██╔══╝   ██║     ██╔══╝   ██║╚██╗██║   ╚════██║██║   ██╚██╗  ██╔╝██╔══██║ ██╔══██╗██║██╔══██║██║╚██╗██║
 *  ███████╗█████╗███████╗██║ ╚████║   ███████║╚██████╔╚████╔╝  ██║  ██║  ██║  ██║ ██║██║   ██║██║  ╚████║
 *  ╚══════╝╚════╝╚══════╝╚═╝  ╚═══╝   ╚══════╝ ╚═════╝  ╚═══╝    ╚═╝  ╚═╝  ╚═╝  ╚═╝ ╚═╝╚═╝   ╚═╝╚═╝    ╚═══╝                                                                                                  
 */
package persistencia;

import java.util.Scanner;

/**
 * Clase gestor del menú. Posibilita impresión de una mensaje de bienvenida por
 * pantalla, y además, mostrar las opciones del menú.
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class GestorMenu {

    /**
     * Método para mostrar un mensaje de bienvenida por pantalla
     */
    public static void mostrarBienvenida() {
        System.out.println("      .--.   |V|\n"
                + "     /    \\ _| /\n"
                + "     q .. p \\ /\n"
                + "      \\--/  //         ====>   BIENVENIDO AL GESTOR DE FACTURAS   <====\n"
                + "     __||__//\n"
                + "    /.    _/\n"
                + "   // \\  /\n"
                + "  //   ||\n"
                + "  \\\\  /  \\\n"
                + "   )\\|    |\n"
                + "  / || || |\n"
                + "  |/\\| || |\n"
                + "     | || |\n"
                + "     \\ || /\n"
                + "   __/ || \\__\n"
                + "  \\____/\\____/");

    }

    /**
     * Método que muestra un menú por pantalla y lee la opción del usuario por
     * teclado
     *
     * @return Opción elegida por el usuario
     */
    public static int mostrarMenu() {
        int opcion; //Variable donde recoger la opcion del menú
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n  1 - Agregar Factura");
        System.out.println("  2 - Consultar Factura");
        System.out.println("  3 - Generar Factura en HTML");
        System.out.println("  4 - Salir\n\n");

        System.out.print("Introduzca una opcion:  ");
        opcion = sc.nextInt();
        return opcion;
    }
}
