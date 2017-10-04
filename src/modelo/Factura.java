/*
 *  ███████╗██╗     ███████╗███╗   ██╗     ███████╗██╗   ██╗██╗    ██╗  █████╗   ██████╗  ██╗ █████╗  ███╗   ██╗
 *  ██╔════╝██║     ██╔════╝████╗  ██║    ██╔════╝██║   ██║██║    ██║██╔══██╗ ██╔══██╗ ██║██╔══██╗████╗  ██║
 *  █████╗   ██║     █████╗   ██╔██╗ ██║    ███████╗██║   ██║██║    ██║███████║ ██████╔╝██║███████║██╔██╗ ██║
 *  ██╔══╝   ██║     ██╔══╝   ██║╚██╗██║   ╚════██║██║   ██╚██╗  ██╔╝██╔══██║ ██╔══██╗██║██╔══██║██║╚██╗██║
 *  ███████╗█████╗███████╗██║ ╚████║   ███████║╚██████╔╚████╔╝  ██║  ██║  ██║  ██║ ██║██║   ██║██║  ╚████║
 *  ╚══════╝╚════╝╚══════╝╚═╝  ╚═══╝   ╚══════╝ ╚═════╝  ╚═══╝    ╚═╝  ╚═╝  ╚═╝  ╚═╝ ╚═╝╚═╝   ╚═╝╚═╝    ╚═══╝                                                                                                  
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import persistencia.GestorFichero;

/**
 * Clase correspondiente a las propiedades individuales de una factura.
 * Establecimiento y obtención de sus atributos, y la posibilidad de mostrarse
 * por pantalla. Serializable.
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class Factura implements Serializable {

    private String nombre; //Nombre y apellidos del cliente
    private String nif; //Nif del cliente
    private String direccion; //Dirección fiscal del cliente
    private ArrayList<Producto> productos; //ArrayList donde almacenamos los 3 productos de cada cliente

    /**
     * Método para obtener el nombre y apellido del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener el NIF del cliente.
     *
     * @return NIF del cliente.
     */
    public String getNif() {
        return nif;
    }

    /**
     * Método para obtener la dirección fiscal del cliente.
     *
     * @return Dirección fiscal del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Método para obtener el ArrayList de objetos de tipo Producto
     * correspondiente al cliente.
     *
     * @return ArrayList de objetos de tipo Producto.
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * Método para establecer el nombre y pellido del cliente.
     *
     * @param nombre Nombre y apellidos del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para establecer el NIF del cliente.
     *
     * @param nif NIF del cliente.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método para establecer la dirección fiscal del cliente
     *
     * @param direccion Dirección fiscal del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Constructor para crear una factura, siempre incluyendo su nombre, NIF y
     * dirección fiscal.
     *
     * @param nombre Nombre y apellidos del cliente.
     * @param nif NIF del cliente.
     * @param direccion Dirección fiscal del cliente.
     */
    public Factura(String nombre, String nif, String direccion) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.productos = new ArrayList();
    }

    /**
     * Constructor vacío para crear una factura vacía(Es utilizado en el menú de
     * la clase GestorFacturas).
     */
    public Factura() {
    }

    /**
     * Método para agregar un producto correspondiente a un cliente.
     *
     * @param _p Objeto de tipo Producto (Datos del producto).
     */
    public void agregarProducto(Producto _p) {
        productos.add(_p);
    }

    /**
     * Método que permite mostrar los datos del cliente y de sus productos.
     */
    public void mostrarDatos() {
        float total = 0;//Importe total de la factura.
        float iva = GestorFichero.getIva();//Obtenemos el valor del Iva desde el fichero de propiedades.

        System.out.println("\n\n\n=================================================================");
        System.out.println("Nombre: " + this.nombre);
        System.out.println("NIF: " + this.nif);
        System.out.println("Dirección fiscal: " + this.direccion);
        System.out.println("=================================================================");
        //Recorre el arrayList de Productos, llamando en cada iteración al método mostrarDatos() de cada producto.
        for (Producto p : productos) {
            p.mostrarDatos();
            //Obtenemos Precio unitario y cantidad de cada producto.
            total = total + (p.getPrecioUnitario() * p.getUnidades());
        }
        System.out.println("=================================================================");
        System.out.printf("Base                                   * %.2f€ \n", total);
        System.out.printf("IVA 21%%                                * %.2f€ \n", total * (iva / 100));
        System.out.printf("Total                                  * %.2f€ \n", (total * (iva / 100) + total));
        System.out.println("=================================================================");
    }
}
