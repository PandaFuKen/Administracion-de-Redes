/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;

public class NoHost{
    
    private String direccionIP;
    private int prefijo;
    private CaracteristicasIP caracteristicasIP;
    private int octeto1;
    private int octeto2;
    private int octeto3;
    private int octeto4;
    private int direccionIpBinario[] = new int[32];//DIRECCION IP EN FORMA DE NUMERO BINARIO
        
    private int cantHost=0;
    private double totalHost;
    private int posicion = 0;
    
    public NoHost(String direccionIP, int prefijo, CaracteristicasIP caracteristicasIP){
    
        this.caracteristicasIP = caracteristicasIP;
        
        this.direccionIP = caracteristicasIP.getDireccion();
        this.prefijo = caracteristicasIP.getPrefijo();
        conClase conClase = new conClase(direccionIP,prefijo,caracteristicasIP);//OBTENEMOS LA DIRECCION IP EN BINARIO
        this.octeto1 = caracteristicasIP.getOcteto1();
        this.octeto2 = caracteristicasIP.getOcteto2();
        this.octeto3 = caracteristicasIP.getOcteto3();
        this.octeto4 = caracteristicasIP.getOcteto4();
    }
    
    public void calcularPropiedades(){
        conClase conClase = new conClase(direccionIP,prefijo,caracteristicasIP);//OBTENEMOS LA DIRECCION IP EN BINARIO

        System.out.println("Numero de Host de la red: "+CalcularNumHost());
        System.out.println("Mascara de Subred en binario: "+conClase.MascaraSubredEnBinario());
        System.out.println("Direccion IP en binario: "+conClase.ObtenerDireccionIpBinario());
        System.out.println("operacion AND: "+conClase.OperacionAnd());
        System.out.println("Direccion de red: "+conClase.ObtenerDireccionDeRed());
    }

    public int CalcularNumHost(){
        conClase conClase = new conClase(direccionIP,prefijo,caracteristicasIP);//OBTENEMOS LA DIRECCION IP EN BINARIO
        String direccionIpBinarioString = conClase.CalcularDireccionIpDecimalABinario();
        for (int i = 0; i <32; i++) {//LLENAMOS EL ARREGLO CON LA DIRECCION EN BINARIO
                direccionIpBinario[i] = Character.getNumericValue(direccionIpBinarioString.charAt(i));
        }
        System.out.println("\n");
        for (int i = 0; i <32; i++) {
                    if (i==8 || i==16 || i==24) {
                        System.out.print(".");
                    }
                   System.out.print(direccionIpBinario[i]);//IMPRIMIMOS LA DIRECCION IP EN FORMA BINARIA
                }
            System.out.println("\n");
            
        //CALCULAMOS LA CANTIDAD DE HOST DE LA DIRECCION IP   
        for (int i = 31; i>=prefijo; i--) {//OBTENEMOS LA PARTE DE HOST (|--RED--|--HOST--|)
            if(direccionIpBinario[i]==1){//CUANDO EL NUMERO DE LA PARTE DE HOST SEA 1
                totalHost = Math.pow(2,posicion);//ELEVAMOS EL 2 A LA POCICION EN LA QUE SE ENCUENTRA
                cantHost+=totalHost;//ACTUALIZAMOS LA CANTIDAD DE HOST CON CADA CICLO
                //System.out.println("Posicion: "+posicion+" 2^"+posicion+" = "+totalHost);//IMPRIMIMOS LA CANTIDAD DE HOST EN CADA POSICION    

            }
        posicion++;//CON CADA CICLO LA POSICION AUMENTA
        }
            return cantHost;//RETORNAMOS LA CANTIDAD TOTAL DE HOST.
    }
    
}

