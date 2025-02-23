/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;
//LA DIRECCION IP DE LA CLASE direccionIP ES DE TIPO STRING
//EN ESTA CLASE SE HACE EL MANEJO DE VALORES DE TIPO ENTERO

import javax.swing.JOptionPane;


//CLASE PARA OBTENER LAS CARACTERISTICAS DE LA DIRECCION IP
public class CaracteristicasIP extends direccionIP {

    public int octeto1;
    public int octeto2;
    public int octeto3;
    public int octeto4;
    
    public CaracteristicasIP(String direccionIP, int prefijo) {
        super(direccionIP, prefijo);
        
        //MANEJAMOS LOS OCTETOS QUE SE DIVIDIERON EN EL ARREGLO 'dividirEnOctetos'
      
            String[] octetos = dividirEnOctetos();
            this.octeto1 = Integer.parseInt(octetos[0]);
            this.octeto2 = Integer.parseInt(octetos[1]);
            this.octeto3 = Integer.parseInt(octetos[2]);
            this.octeto4 = Integer.parseInt(octetos[3]);
        
        
    }
    
    //OBTENEMOS CADA OCTETO POR SEPARADO PARA SU FACIL MANEJO
    public int getOcteto1() {
        return octeto1;
    }

    public void setOcteto1(int octeto1) {
        this.octeto1 = octeto1;
        actualizarDireccion();
    }

    public int getOcteto2() {
        return octeto2;
    }

    public void setOcteto2(int octeto2) {
        this.octeto2 = octeto2;
        actualizarDireccion();
    }

    public int getOcteto3() {
        return octeto3;
    }

    public void setOcteto3(int octeto3) {
        this.octeto3 = octeto3;
        actualizarDireccion();
    }

    public int getOcteto4() {
        return octeto4;
    }

    public void setOcteto4(int octeto4) {
        this.octeto4 = octeto4;
        actualizarDireccion();
    }
    
    //ACTUALIZAMOS LA DIRECCION ORIGINAL PERO ESTA VES COMO ENTERO
    private void actualizarDireccion() {
        setDireccion(octeto1 + "." + octeto2 + "." + octeto3 + "." + octeto4);
    }

    //VALIDACION DE DATOS DE ENTRADA
    //VALIDACION DEL PREFIJO
        public boolean prefijoValido() {
            int prefijo = getPrefijo();
            return prefijo >= 0 && prefijo <= 32;
        }
    //VALIDACION DE LA DIRECCION IP
        public boolean dirIpValida() {
            return octeto1 >= 1 && octeto1 <= 255 && 
                   octeto2 >= 0 && octeto2 <= 255 &&
                   octeto3 >= 0 && octeto3 <= 255 &&
                   octeto4 >= 0 && octeto4 <= 255;
            
        }
        
        //OBTENEMOS LA CLASE DE LA DIRECCION IP
        public String obtenerClase() {
        int primerOcteto = Integer.parseInt(dividirEnOctetos()[0]);
        if (primerOcteto >= 1 && primerOcteto <= 126) return "A";
        if (primerOcteto >= 128 && primerOcteto <= 191) return "B";
        if (primerOcteto >= 192 && primerOcteto <= 223) return "C";
        if (primerOcteto >= 224 && primerOcteto <= 239) return "D";
        if (primerOcteto >= 240 && primerOcteto <= 255) return "E";
        return "Desconocida";
    }
        
}

