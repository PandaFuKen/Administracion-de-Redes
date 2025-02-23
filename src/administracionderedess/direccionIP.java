/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;

//
//CLASE SOLO PARA EL MANEJO DE LA DIRECCION IP
public class direccionIP {
    private String direccion;
    public int prefijo;
            
    public direccionIP(String direccionIP, int prefijo){
        this.direccion = direccionIP;
        this.prefijo = prefijo;
    }

   //LA DIRECCION IP ORIGINAL LA DIVIDIMOS EN OCTETOS 
    public String[] dividirEnOctetos() {
        return direccion.split("\\.");
    }

    //OBTENEMOS LA DIRECCION IP
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccionIP) {
        this.direccion = direccion;
    }
    //OBTENEMOS EL PREFIJO
    public int getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(int prefijo) {
        this.prefijo = prefijo;
    }
    
}
