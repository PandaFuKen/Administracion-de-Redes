/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package administracionderedess;

import java.util.Scanner;


public class AdministracionDeRedess {    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner( System.in);
        
        //LABEL PARA INGRESAR LOS DATOS
        
        // OBTENEMOS LA DIRECCION IP
        System.out.print("Introduce una direccion IP: ");
        String direccionIP = scanner.nextLine();
        
        //OBTENEMOS EL PREFIJO DE LA DIRECCION IP
        System.out.print("/: ");
        int prefijo = scanner.nextInt();

        //CLASE PARA OBTENER LAS CARACTERISTICAS DE LA DIRECCION IP
        caracteristicasIP caracteristicasIP = new caracteristicasIP(direccionIP, prefijo);
        
        //ETIQUETAS PARA MOSTRAR LOS DATOS DE LA DIRECCION IP
        
        // SALIDA DE DATOS
        if (caracteristicasIP.prefijoValido() && caracteristicasIP.dirIpValida()) {// SI LA DIRECCION IP ES VALIDA
            System.out.println("Direccion IP: " + caracteristicasIP.getDireccion());//MOSTRAMOS LA DIRECCION IP ORIGINAL
            System.out.println("Prefijo: /" + caracteristicasIP.getPrefijo());//MOSTRAMOS EL PREFIJO
            System.out.println("Clase: " + caracteristicasIP.obtenerClase());//MOSTRAMOS EL TIPO DE CLASE DE LA DIRECCION
        } else {//EN CASO DE QUE NO SEA VALIDA LA DIRECCION O EL PREFIJO MOSTRAMOS LOS MENSAJES
            if (!caracteristicasIP.prefijoValido()) {
                System.out.println("Numero de prefijo no valido");
            }
            if (!caracteristicasIP.dirIpValida()) {
                System.out.println("Direccion IP no valida");
            }
        }

        scanner.close();
        
       
    }
    
}
