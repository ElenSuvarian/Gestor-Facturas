/*
 *  ███████╗██╗     ███████╗███╗   ██╗     ███████╗██╗   ██╗██╗    ██╗  █████╗   ██████╗  ██╗ █████╗  ███╗   ██╗
 *  ██╔════╝██║     ██╔════╝████╗  ██║    ██╔════╝██║   ██║██║    ██║██╔══██╗ ██╔══██╗ ██║██╔══██╗████╗  ██║
 *  █████╗   ██║     █████╗   ██╔██╗ ██║    ███████╗██║   ██║██║    ██║███████║ ██████╔╝██║███████║██╔██╗ ██║
 *  ██╔══╝   ██║     ██╔══╝   ██║╚██╗██║   ╚════██║██║   ██╚██╗  ██╔╝██╔══██║ ██╔══██╗██║██╔══██║██║╚██╗██║
 *  ███████╗█████╗███████╗██║ ╚████║   ███████║╚██████╔╚████╔╝  ██║  ██║  ██║  ██║ ██║██║   ██║██║  ╚████║
 *  ╚══════╝╚════╝╚══════╝╚═╝  ╚═══╝   ╚══════╝ ╚═════╝  ╚═══╝    ╚═╝  ╚═╝  ╚═╝  ╚═╝ ╚═╝╚═╝   ╚═╝╚═╝    ╚═══╝                                                                                                  
 */

import java.util.Scanner;
import modelo.Factura;
import modelo.Producto;
import persistencia.GestorFichero;
import persistencia.GestorHTML;
import persistencia.GestorMenu;
import persistencia.GestorSQL;

/**
 * Clase que nos permite tratar y gestionar facturas.
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class GestorFacturas {

    private static final int CANTPRODUCTOS = 3; //Cantidad de Productos

    /**
     * Método principal que nos reedirecciona a los métodos dependiendo de la
     * opción elegida del menú por el usuario.
     *
     * @param args
     */
    public static void main(String[] args) {
        int opcion;//Opcion elegida por el usuario en el menú.
        GestorMenu.mostrarBienvenida(); //Mostrar un mensaje de bienvenida.
        Factura aux; //Utilizado para almacenar la factura (Necesitamos el NIF)en el case 3 y no volver a pedirlo otra vez.
        //Instanciamos una objeto de tipo GestorFichero para poder cargar la configuración del programa
        GestorFichero cargProp = new GestorFichero();
        cargProp.cargarPropiedades(); //Llamamos al método que leerá las propiedades

        //reedirigimos dependiendo la opcion del menú
        do {
            opcion = GestorMenu.mostrarMenu(); //Recogemos la opción elegida.

            switch (opcion) {
                case 1:
                    agregarFactura(); //llamamos a este método estático de la propia.
                    break;
                case 2:
                    mostrarFactura(); //Llamamos a este método estático de la propia clase.
                    break;
                case 3:
                    if ((aux = GestorFichero.buscarFactura(pedirNif())) != null) {//Si el objeto devuelto es distinto a null, procedemos:
                        GestorHTML.crearFicheroHTML(GestorFichero.buscarFactura(aux.getNif())); //Encadenación de metodos que van devolviendo y ejecutando alfinal el metodo crearFicheroHTML
                    } else {//Si el objeto devuelto es null , no hacemos nada

                    }
                    break;
                case 4:
                    System.out.println("\n~~~ GRACIAS POR UTILIZAR NUESTROS SERVICIOS ~~~\n");
                    break;
                default:
                    System.out.println("\n ----------- OPCION INCORRECTA  ---------");
            }
        } while (opcion != 4);
    }

    /**
     * Método estático para recoger los datos de la Factura y los Productos:
     */
    public static void agregarFactura() {
        Producto p; //Instanciación del objeto donde se van a ir guardando los productos en cada iteración del bucle for.
        Factura f; //Instanciación del objeto factura que vamos a utilizar.
        Scanner sc = new Scanner(System.in);
        String nombreCliente; //Nombre y apllido del cliente.
        String nif; //NIF del cliente.
        String direccion; //Dirección fiscal del cliente.
        String nombreProducto; //Nombre del producto
        float precioProducto; //Precio unitario del producto.
        int unidadesProducto; //Unidades del producto.

        //Leemos los datos del la factura
        System.out.print("\n Introduzca Nombre y Apellido del cliente: ");
        nombreCliente = sc.nextLine();
        System.out.print("\n Introduzca el Nif del cliente: ");
        nif = sc.nextLine();
        System.out.print("\n Introduzca la Direccion del cliente: ");
        direccion = sc.nextLine();

        f = new Factura(nombreCliente, nif, direccion); //Instanciamos la Factura.

        for (int i = 0; i < CANTPRODUCTOS; i++) { //Leemos los datos de los 3 productos
            System.out.print("\n Introduzca el Nombre del Producto: ");
            nombreProducto = sc.nextLine();
            System.out.print("\n Introduzca el Precio del Producto: ");
            precioProducto = sc.nextFloat();
            System.out.print("\n Introduzca la Cantidad del Producto: ");
            unidadesProducto = sc.nextInt();
            sc.nextLine();
            p = new Producto(nombreProducto, precioProducto, unidadesProducto);//Instanciamos el producto.
            f.agregarProducto(p); //Agregamos el producto al arrayList.
        }
        GestorFichero.guardarFactura(f);//Llamamos al método estatico de la clase GestorFichero que guardará la factura en un fichero .factura.
        GestorSQL.crearSQL(f);//llamamos al método estático crearSQL y le enviamos la factura.
    }

    /**
     * Método que llama a buscarFactura() de la clase GestorFichero y recibe la
     * factura y la muestra.
     */
    public static void mostrarFactura() {

        Factura f; //Instanciación para recoger la factura desde el fichero .factura .
        String nif; //Recoge el NIF del cliente para enviarlo al método de buscarFactura().

        nif = pedirNif();//Recogemos el NIF

        f = GestorFichero.buscarFactura(nif); //Llamamos al método buscarFactura() y dependiendo de lo que recibamos...
        if (f == null) {   //Si el método buscarFactura devuelve un objeto null, es que no ha encontrado el fichero, por lo tanto no realizamos nada.
        } else {//Si recibimos un objeto distinto a null, entonces mostramos sus datos.
            f.mostrarDatos();
        }

    }

    /**
     * Método para recoger por teclado el NIF del cliente.
     *
     * @return NIF del cliente
     */
    public static String pedirNif() {
        String nif; //NIF del cliente
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca el NIF: ");
        nif = sc.nextLine();
        return nif;
    }

}
