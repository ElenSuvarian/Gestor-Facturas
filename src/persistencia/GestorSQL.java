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
import modelo.Factura;

/**
 * Clase creada para el manejo de ficheros SQL.Concretamente, recopila los datos
 * de cada cliente y los almacena en un ficero .sql .
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class GestorSQL {

    /**
     * Método estático encargado de crear y reescribir un fichero SQL con los
     * datos de un cliente.
     *
     * @param _f Objeto de tipo Factura instanciado con sus correspondientes
     * datos.
     */
    public static void crearSQL(Factura _f) {
        try {
            FileWriter fw;
            //Crea si no existe el fichero, y reescribe si existe.
            fw = new FileWriter("clientes" + ".sql", true);
            //Introducimos los datos del cliente
            fw.write("Insert into clientes values ('" + _f.getNombre() + "', '" + _f.getDireccion() + "', '" + _f.getNif() + "'); \r\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println("ERROR EN LA ESCRITURA DEL FICHERO");
        }
    }

}
