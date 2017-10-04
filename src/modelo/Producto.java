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

/**
 * Clase correspondiente a las propiedades individuales de un producto.
 * Establecimiento y obtención de sus atributos, y la posibilidad de mostrarse
 * por pantalla. Serializable.
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class Producto implements Serializable {

    private String nombre; //Nombre del producto.
    private float precioUnitario; //Precio unitario del producto.
    private int unidades; //Unidades del producto.

    /**
     * Método para obtener el nombre del producto.
     *
     * @return Nombre del producto..
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener el precio unitario del producto.
     *
     * @return Precio unitario del producto.
     */
    public float getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Método para obtener unidades del producto.
     *
     * @return Unidades del producto.
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     * Método para establecer el nombre del producto.
     *
     * @param nombre Nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para establecer el precio unitario del producto.
     *
     * @param precioUnitario Precio unitario del producto.
     */
    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Método para establecer las unidades del producto.
     *
     * @param unidades Unidades del producto.
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * Constructor de Producto que siempre se creará con su nombre. precio y
     * cantidad.
     *
     * @param nombre Nombre del producto.
     * @param precioUnitario Precio unitario del producto.
     * @param unidades Unidades del producto.
     */
    public Producto(String nombre, float precioUnitario, int unidades) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.unidades = unidades;
    }
    /**
     * Método para mostrar por pantalla los datos del producto
     */
    public void mostrarDatos() {
        System.out.printf("%s                     *  %d Ud.  *  %.2f€   *  %.2f€ \n",this.nombre,this.unidades,this.precioUnitario,(this.precioUnitario * this.unidades) );
    }

}
