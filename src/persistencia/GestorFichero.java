/*
 *  ███████╗██╗     ███████╗███╗   ██╗     ███████╗██╗   ██╗██╗    ██╗  █████╗   ██████╗  ██╗ █████╗  ███╗   ██╗
 *  ██╔════╝██║     ██╔════╝████╗  ██║    ██╔════╝██║   ██║██║    ██║██╔══██╗ ██╔══██╗ ██║██╔══██╗████╗  ██║
 *  █████╗   ██║     █████╗   ██╔██╗ ██║    ███████╗██║   ██║██║    ██║███████║ ██████╔╝██║███████║██╔██╗ ██║
 *  ██╔══╝   ██║     ██╔══╝   ██║╚██╗██║   ╚════██║██║   ██╚██╗  ██╔╝██╔══██║ ██╔══██╗██║██╔══██║██║╚██╗██║
 *  ███████╗█████╗███████╗██║ ╚████║   ███████║╚██████╔╚████╔╝  ██║  ██║  ██║  ██║ ██║██║   ██║██║  ╚████║
 *  ╚══════╝╚════╝╚══════╝╚═╝  ╚═══╝   ╚══════╝ ╚═════╝  ╚═══╝    ╚═╝  ╚═╝  ╚═╝  ╚═╝ ╚═╝╚═╝   ╚═╝╚═╝    ╚═══╝                                                                                                  
 */
package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Float.parseFloat;
import java.util.Properties;
import modelo.Factura;

/**
 * Clase encargada del manejo de ficheros, lectura y escritura.
 *
 * @author Elen Suvarian
 * @version 1.1
 * @since 03.2017
 */
public class GestorFichero {

    private static float iva; //Variable estática para almacenar el valos del IVA

    /**
     * Método que escribe en un fichero .factura un objeto que recibe como
     * parámetro.
     *
     * @param _f Objeto de tipo Factura instanciada con sus correspondientes
     * datos.
     */
    public static void guardarFactura(Factura _f) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(_f.getNif() + ".factura");//el nombre del fichero será el NIF del cliente.
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(_f);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR EN EL ACCESO AL FICHERO");
        } catch (IOException ex) {
            System.out.println("ERROR EN LA ESCRITURA DEL FICHERO");
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                System.out.println("ERROR EN EL ACCESO AL FICHERO AL INTENTAR CERRARLO");
            }
        }
    }

    /**
     * Método que busca el fichero .factura por el NIF dentro del directorio
     * raiz.
     *
     * @param _nif NIF del cliente.
     * @return Objeto de tipo de Factura instanciado con sus corrspondientes
     * datos.
     */
    public static Factura buscarFactura(String _nif) {
        File fichero = new File(_nif + ".factura"); //Fichero de nombre NIF del cliente
        Factura f = null; //Factura donde guardaremos el objeto liedo del fichero
        //Vemos si el fichero existe:
        if (fichero.exists()) {
            FileInputStream fis;
            ObjectInputStream ois = null;
            //Leemos el objeto
            try {
                fis = new FileInputStream(_nif + ".factura");
                ois = new ObjectInputStream(fis);
                f = (Factura) ois.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println("ERROR EN EL ACCESO AL FICHERO");
            } catch (ClassNotFoundException | IOException ex) {
                System.out.println("ERROR EN LA LECTURA DEL FICHERO");
            } finally {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("ERROR EN EL ACCESO AL FICHERO AL INTENTAR CERRARLO");
                }
            }
            //Si no existe, imprimimos un mensaje de error
        } else {
            System.out.println("\n\n------>  LA FACTURA CON NIF " + _nif + " NO EXISTE  <------");
        }
        return f;
    }

    /**
     * Método que nos permite leer la configuración del programa desde un
     * fichero "properties"
     */
    public void cargarPropiedades() {
        String ivaLeido; //valor del IVA del fichero properties de tipo String
        float ivaReal; //valor del IVA convertido a float
        //Cargamos el fichero properties, instanciando un objeto de su clase (prop)
        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "resources/datos.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            prop.load(inputStream);
            ivaLeido = prop.getProperty("IVA"); //Obtenemos el valor de la clave IVA del fichero
            //Obtenemos el iva como String
            ivaReal = parseFloat(ivaLeido); //Lo convertirmos en float
            iva = ivaReal;
        } catch (IOException ex) {
            System.out.println("ERROR AL ACCEDER AL ARCHIVO DE CONFIGURACIÓN");
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                System.out.println("ERROR AL INTENTAR CERRAR EL ARCHIVO");
            }
        }
    }

    /**
     * Método para obtener el valor del IVA
     *
     * @return valor del IVA
     */
    public static float getIva() {
        return iva;
    }
}


/*
             LEER VARIOS OBJETOS DE UN MISMO FICHERO: 
            -------------------------------------------

for (int i=0;i<3;i++){
    Asignatura a1 = (Asignatura) oos.readObject();
    a1.mostrarDatos();
}
*/
