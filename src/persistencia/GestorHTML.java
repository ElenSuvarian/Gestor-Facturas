/*
 *  ███████╗██╗     ███████╗███╗   ██╗     ███████╗██╗   ██╗██╗    ██╗  █████╗   ██████╗  ██╗ █████╗  ███╗   ██╗
 *  ██╔════╝██║     ██╔════╝████╗  ██║    ██╔════╝██║   ██║██║    ██║██╔══██╗ ██╔══██╗ ██║██╔══██╗████╗  ██║
 *  █████╗   ██║     █████╗   ██╔██╗ ██║    ███████╗██║   ██║██║    ██║███████║ ██████╔╝██║███████║██╔██╗ ██║
 *  ██╔══╝   ██║     ██╔══╝   ██║╚██╗██║   ╚════██║██║   ██╚██╗  ██╔╝██╔══██║ ██╔══██╗██║██╔══██║██║╚██╗██║
 *  ███████╗█████╗███████╗██║ ╚████║   ███████║╚██████╔╚████╔╝  ██║  ██║  ██║  ██║ ██║██║   ██║██║  ╚████║
 *  ╚══════╝╚════╝╚══════╝╚═╝  ╚═══╝   ╚══════╝ ╚═════╝  ╚═══╝    ╚═╝  ╚═╝  ╚═╝  ╚═╝ ╚═╝╚═╝   ╚═╝╚═╝    ╚═══╝                                                                                                  
 */
package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Factura;
import modelo.Producto;

/**
 * Clase que permite la escritura de ficheros HTML con los datos de una factura
 * y sus productos.
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class GestorHTML {

    /**
     * Método para crear un fichero HTML con formato e incluir los datos del
     * cliente y sus productos.
     *
     * @param _f Objeto de tipo factura instanciada con sus correspondientes
     * datos.
     */
    public static void crearFicheroHTML(Factura _f) {
        ArrayList<Producto> productos; //Instanciación para obtener el ArrayList de una factura.
        try {
            FileWriter fw;
            float base=0;// Importe total de todos los productos juntos sin IVA
            ArrayList alAux = _f.getProductos(); //Instanciación para obtener los datos de cada producto
            Producto pAux;//Instanciación para obtener los productos del ArrayList
            fw = new FileWriter(_f.getNif() + ".html"); //Como nombre del fichero el NIF del cliente.
            //Escribimos el código HTML
            fw.write("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "\n"
                    + "<head>\n"
                    + "    <title>Factura</title>\n"
                    + "    <meta charset=\"utf-8\" /> \n"
                    + "    <meta name='author' content='Elen Suvarian'>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body bgcolor=\"grey\">\n"
                    + "        <table style=\"margin:20%;\" width=50% height=300px  border=\"12px\" bordercolor=\"#3B0B39\" bgcolor=\"#CEE3F6\">\n"
                    + "            <tr>\n"
                    + "                <th>\n"
                    + "                    Nombre del Cliente:\n"
                    + "                </th>\n"
                    + "                <td>\n"
                    + _f.getNombre() //Obtenemos el nombre del cliente.
                    + "                </td>\n"
                    + "                <th>\n"
                    + "                    PRECIO\n"
                    + "                </th>\n"
                    + "                <th>\n"
                    + "                    CANTIDAD\n"
                    + "                </th>\n"
                    + "            <tr>\n"
                    + "                <th>\n"
                    + "                    NIF:\n"
                    + "                </th>\n"
                    + "                <td colspan=\"3\">\n"
                    + _f.getNif() //Obtenemos el NIF del cliente.
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <th>\n"
                    + "                    Dirección Fiscal:\n"
                    + "                </th>\n"
                    + "                <td colspan=\"3\">\n"
                    + _f.getDireccion() //Obtenemos la dirección fiscal del cliente.
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <th>\n"
                    + "                   Producto 1:\n"
                    + "                </th>\n"
                    + "                <td>\n");
            pAux = (Producto) alAux.get(0); //Obtenemos el primer producto del cliente.
            fw.write(pAux.getNombre() + "</td>\n" //Obtenemos el nombre del primer producto.
                    + "                 <td>\n"
                    + pAux.getPrecioUnitario() + "€" //Obtenemos el precio del primer Producto.
                    + "                </td>\n"
                    + "                 <td>\n"
                    + pAux.getUnidades() //Obtenemos las unidades del primer producto.
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "             <tr>\n"
                    + "                <th>\n"
                    + "                   Producto 2:\n"
                    + "                </th>\n"
                    + "                <td>\n");
            base=(pAux.getPrecioUnitario()*pAux.getUnidades()); //Sumamos a la base el importe total del primer artículo
            pAux = (Producto) alAux.get(1); //Obtenemos el segundo producto del cliente.
            fw.write(pAux.getNombre() //Obtenemos y ecribimos el nombre del segundo producto.
                    + "                </td>\n"
                    + "                 <td>\n"
                    + pAux.getPrecioUnitario() + "€" //Obtenemos y escribimos el precio del segundo producto.
                    + "                </td>\n"
                    + "                 <td>\n"
                    + pAux.getUnidades() //Obtenemos y escribimos la cantidad del segundo producto.
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "             <tr>\n"
                    + "                <th>\n"
                    + "                   Producto 3:\n"
                    + "                </th>\n"
                    + "                <td>\n");
            base=base+(pAux.getPrecioUnitario()*pAux.getUnidades()); //Sumamos a la base el importe total del segundo artículo
            pAux = (Producto) alAux.get(2); //Obtenemos el tercer producto del cliente.
            fw.write(pAux.getNombre() //Obtenemos y escribimos el nombre del tercer producto.
                    + "                </td>\n"
                    + "                 <td>\n"
                    + pAux.getPrecioUnitario() + "€" //Obtenemos y escribimos el precio del tercer producto.
                    + "                </td>\n"
                    + "                 <td>\n"
                    + pAux.getUnidades());//Obtenemos y escribimos la cantidad del tercer producto.
            base=base+(pAux.getPrecioUnitario()*pAux.getUnidades());//Sumamos a la base el importe total del primer artículo
            fw.write( "                </td>\n"
                    + "            </tr>\n"
                    + "             <tr>\n" 
                    + "                <th>\n" 
                    + "                    \n" 
                    + "                </th>\n" 
                    + "                <th>\n" 
                    + "                    Base: \n" 
                    + "                </th>\n" 
                    + "                <th>\n" 
                    + "                </th>\n" 
                    + "                <td>\n" 
                    +                    base + "€"  //Escribimos el importe total de todos los productos. 
                    + "                </td>\n" 
                    + "            </tr>\n" 
                    + "            <tr>\n" 
                    + "                <th>\n" 
                    + "                    \n" 
                    +"                </th>\n" 
                    + "                <th>\n" 
                    + "                    Base IVA 21%:\n" 
                    + "                </th>\n" 
                    + "                <th>\n" 
                    + "                </th>\n" 
                    + "                <td>\n" 
                    +                  base*(GestorFichero.getIva()/100)+ "€" //Obtenemos y escribimos el porcentaje del IVA
                    + "                </td>\n" 
                    + "            </tr>\n" 
                    + "            <tr>\n" 
                    + "                <th>\n" 
                    + "                    \n" 
                    + "                </th>\n" 
                    + "                <th>\n" 
                    + "                    Total:\n" 
                    + "                </th>\n" 
                    + "                <th>\n" 
                    + "                </th>\n" 
                    + "                <th bgcolor='red'>\n" 
                    +                  ((base*(GestorFichero.getIva()/100))+base)+ "€" //Escribimos el importe total con IVA
                    + "                </th>\n" 
                    + "            </tr>\n"   
                    + "        </table>\n"
                    + "    </div>\n"
                    + "</body>");
            productos = _f.getProductos(); //Obtenemos el arrayList de productos.
            fw.close();
            System.out.println("Se ha creado el archivo HTML");
        } catch (IOException ex) {
            System.out.println("ERROR EN LA ESCRITURA DEL FICHERO");
        }
    }
}
