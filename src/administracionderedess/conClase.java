/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;

/**
 *
 * @author luism
 */
public class conClase {
    
    private String direccionIP;
    private int prefijo;
    private caracteristicasIP caracteristicasIP;
    private int octeto1;
    private int octeto2;
    private int octeto3;
    private int octeto4;    
    public conClase(String direccionIP, int prefijo, caracteristicasIP caracteristicasIP){
        
        this.direccionIP = caracteristicasIP.getDireccion();
        
        this.prefijo = caracteristicasIP.getPrefijo();
        
        this.octeto1 = caracteristicasIP.getOcteto1();
        this.octeto2 = caracteristicasIP.getOcteto2();
        this.octeto3 = caracteristicasIP.getOcteto3();
        this.octeto4 = caracteristicasIP.getOcteto4();
        
    }
    
    public void calcularPropiedades(){
        
        int direccionIpBinario[] = new int[32];//DIRECCION IP EN FORMA DE NUMERO BINARIO
        int mascaraBinario[]= new int[32];//MASCARA DE SUBRED EN FORMA DE NUMERO BINARIO
        int mascaraDecimal[]= new int[32];//MASCARA DE SUBRED EN FORMA DECIMAL
        int direccionAnd[]= new int[32];//DIRECCION RESULTADO DE LA OPERACION AND

//-------------------------------------------------------------------------------------------------------------------------------
        //OBTENER LA MASCARA A PARTIR DE SU PREFIJO
        
            for (int i = 0; i < prefijo; i++ ) {//LLENAR LA MASCARA CON CLASE A
                mascaraBinario[i] = 1;
            }
            
            for (int i = prefijo; i <= 31; i++ ) {
                mascaraBinario[i] = 0;
            }
       
        
        //DIRECCION IP DE DECIMAL A BINARIO
        System.out.println("Direccion IP en binario: ");
        
        String primerOctetoBinario = convertirABinario(octeto1);
        System.out.println("1 octeto bin: "+primerOctetoBinario);
        String segundoOctetoBinario = convertirABinario(octeto2);
        System.out.println("2 octeto bin: "+segundoOctetoBinario);
        String tercerOctetoBinario = convertirABinario(octeto3);
        System.out.println("3 octeto bin: "+tercerOctetoBinario);
        String cuartoOctetoBinario = convertirABinario(octeto4);
        System.out.println("4 octeto bin: "+cuartoOctetoBinario);
        
        
        for (int i = 0; i <= 7; i++) {
        direccionIpBinario[i] = Character.getNumericValue(primerOctetoBinario.charAt(i));
    }

    for (int i = 8; i <= 15; i++) {
        direccionIpBinario[i] = Character.getNumericValue(segundoOctetoBinario.charAt(i - 8));
    }

    for (int i = 16; i <= 23; i++) {
        direccionIpBinario[i] = Character.getNumericValue(tercerOctetoBinario.charAt(i - 16));
    }

    for (int i = 24; i <= 31; i++) {
        direccionIpBinario[i] = Character.getNumericValue(cuartoOctetoBinario.charAt(i - 24));
    }
        //MOSTRAMOS LA DIRECCION IP EN BINARIO
        System.out.print("Direccion IP en binario: ");
        for (int i = 0; i <32; i++) {
            if (i==8 || i==16 || i==24) {
                System.out.print(".");
            }
            System.out.print(""+direccionIpBinario[i]);
        }
        
        System.out.println("\n");
        
//-------------------------------------------------------------------------------------------------------------------------------
        //MASCARA DE SUBRED EN BINARIO
        System.out.print("Mascara de subred: ");
        for (int i = 0; i < 32; i++) {
            if (i==8 || i==16 || i==24) {
                System.out.print(".");
            }
            System.out.print("" + mascaraBinario[i]);
            
        }
        
        System.out.println("\n");
//-------------------------------------------------------------------------------------------------------------------------------
        //OPERACION AND
        for (int i = 0; i < 32; i++) {
            if (direccionIpBinario[i] == 1 && mascaraBinario[i] == 1) {
                direccionAnd[i] = 1;
            }else if(direccionIpBinario[i]!= 1 && mascaraBinario[i]!= 1) {
                direccionAnd[i]=0;
            }
        }
        
        //IMPRIME DIRECCION EN BINARIO DE LA OPERACION AND
        System.out.print("AND: ");
        for (int i = 0; i < 32; i++) {
            if (i==8 || i==16 || i==24) {
                System.out.print(".");
            }
            System.out.print("" + direccionAnd[i]);
            
        }
//-------------------------------------------------------------------------------------------------------------------------------
        //OBTENER DIRECCION DE RED
        System.out.println("\n");

        //VARIABLES 
        StringBuilder primerOctetoAndBinarioADecimal = new StringBuilder();
        StringBuilder segundoOctetoAndBinarioADecimal = new StringBuilder();
        StringBuilder tercerOctetoAndBinarioADecimal = new StringBuilder();
        StringBuilder cuartoOctetoAndBinarioADecimal = new StringBuilder();

        //OBTENEMOS LOS OCTETOS DEL ARREGLO AND
        for (int i = 0; i < 8; i++) { primerOctetoAndBinarioADecimal.append(direccionAnd[i]);}  //PRIMER OCTETO DEL ARREGLO AND
        for (int i = 8; i < 16; i++) { segundoOctetoAndBinarioADecimal.append(direccionAnd[i]);}  //SEGUNDO OCTETO DEL ARREGLO AND
        for (int i = 16; i < 24; i++) { tercerOctetoAndBinarioADecimal.append(direccionAnd[i]);}  //TERCER OCTETO DEL ARREGLO AND
        for (int i = 24; i < 32; i++) { cuartoOctetoAndBinarioADecimal.append(direccionAnd[i]);}  //CUARTO OCTETO DEL ARREGLO AND
        
        //CONVERTIMOS DE BINARIO A DECIMAL E IMPRIMIMOS LO QUE SERIA LA DIRECCION DE RED
        String conversionAndBinarioDecimal1 = primerOctetoAndBinarioADecimal.toString(); 
        int primerOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal1);
        
        String conversionAndBinarioDecimal2 = segundoOctetoAndBinarioADecimal.toString(); 
        int segundoOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal2);
        
        String conversionAndBinarioDecimal3 = tercerOctetoAndBinarioADecimal.toString(); 
        int tercerOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal3);
        
        String conversionAndBinarioDecimal4 = cuartoOctetoAndBinarioADecimal.toString(); 
        int cuartoOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal4);
       
        //IMPRIMIMOS LA DIRECCION DE RED
       // System.out.println("Direccion de red: "+primerOctetoAndDecimal+"."+segundoOctetoAndDecimal+"."+tercerOctetoAndDecimal+"."+cuartoOctetoAndDecimal);
       
//-------------------------------------------------------------------------------------------------------------------------------        
        //PARA OBTENER LA DIRECCION DE BROADCAST, CONVERTIMOS LA PARTE DE HOST A 1
        
        for (int i = prefijo; i < 32; i++) {//TODOS LOS DIGITOS QUE ESTEN EN UNA POSICION SUPERIOR A LA DEL PREFIJO, SE CONVIERTEN EN UNO
            direccionAnd[i] = 1;
        }
        //CONVERTIR LOS ICTETOS DE BINARIO A DECIMAL PARA OBTENER EL BROADCAST
        
        StringBuilder primerOctetoBroadcastBinarioADecimal = new StringBuilder();
        StringBuilder segundoOctetoBroadcastBinarioADecimal = new StringBuilder();
        StringBuilder tercerOctetoBroadcastBinarioADecimal = new StringBuilder();
        StringBuilder cuartoOctetoBroadcastBinarioADecimal = new StringBuilder();

        //OBTENEMOS LOS OCTETOS DEL ARREGLO AND
        for (int i = 0; i < 8; i++) { primerOctetoBroadcastBinarioADecimal.append(direccionAnd[i]);}  //PRIMER OCTETO DEL ARREGLO AND
        for (int i = 8; i < 16; i++) { segundoOctetoBroadcastBinarioADecimal.append(direccionAnd[i]);}  //SEGUNDO OCTETO DEL ARREGLO AND
        for (int i = 16; i < 24; i++) { tercerOctetoBroadcastBinarioADecimal.append(direccionAnd[i]);}  //TERCER OCTETO DEL ARREGLO AND
        for (int i = 24; i < 32; i++) { cuartoOctetoBroadcastBinarioADecimal.append(direccionAnd[i]);}  //CUARTO OCTETO DEL ARREGLO AND
        
        //CONVERTIMOS DE BINARIO A DECIMAL E IMPRIMIMOS LO QUE SERIA LA DIRECCION DE RED
        String conversionBroadcastBinarioDecimal1 = primerOctetoBroadcastBinarioADecimal.toString(); 
        int primerOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal1);
        
        String conversionBroadcastBinarioDecimal2 = segundoOctetoBroadcastBinarioADecimal.toString(); 
        int segundoOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal2);
        
        String conversionBroadcastBinarioDecimal3 = tercerOctetoBroadcastBinarioADecimal.toString(); 
        int tercerOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal3);
        
        String conversionBroadcastBinarioDecimal4 = cuartoOctetoBroadcastBinarioADecimal.toString(); 
        int cuartoOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal4);
       
        //IMPRIMIMOS LA DIRECCION DE BROADCAST
       // System.out.println("Broadcast: "+primerOctetoBroadcastDecimal+"."+segundoOctetoBroadcastDecimal+"."+tercerOctetoBroadcastDecimal+"."+cuartoOctetoBroadcastDecimal);
        
        System.out.println("\n");
        
//-------------------------------------------------------------------------------------------------------------------------------
        //OBTENEMOS LA PRIMERA Y ULTIMA DIRECCION UTILIZABLE
        
        System.out.println("Direccion de red: "+primerOctetoAndDecimal+"."+segundoOctetoAndDecimal+"."+tercerOctetoAndDecimal+"."+cuartoOctetoAndDecimal);
        
        //PRIMERA DIRECCION UTILIZABLE
        cuartoOctetoAndDecimal = cuartoOctetoAndDecimal+1;
        System.out.println("Primera utilizable: "+primerOctetoAndDecimal+"."+segundoOctetoAndDecimal+"."+tercerOctetoAndDecimal+"."+cuartoOctetoAndDecimal);

        
        //ULTIMA DIRECCION UTILIZABLE
        cuartoOctetoBroadcastDecimal = cuartoOctetoBroadcastDecimal-1;
        System.out.println("Ultima utilizable: "+primerOctetoBroadcastDecimal+"."+segundoOctetoBroadcastDecimal+"."+tercerOctetoBroadcastDecimal+"."+cuartoOctetoBroadcastDecimal);
        
        //DIRECCION DE BROADCAST
        cuartoOctetoBroadcastDecimal = cuartoOctetoBroadcastDecimal+1;
        System.out.println("Broadcast: "+primerOctetoBroadcastDecimal+"."+segundoOctetoBroadcastDecimal+"."+tercerOctetoBroadcastDecimal+"."+cuartoOctetoBroadcastDecimal);
        
        
    }
    
    
    //FUNCION PARA CONVERTIR UN NUMERO DECIMAL A BINARIO
    public static String convertirABinario(int numeroDecimal) {
        String binario = Integer.toBinaryString(numeroDecimal);//REALIZAMO9S LA CONVERSION A BINARIO
        // AÑADIMOS CEROS A LA IZQUIERDA PARA QUE DEN UN TOTAL DE 8 DIGITOS
        while (binario.length() < 8) {
            binario = "0" + binario;
        }
        return binario;
    }
    //FUNCION PARA CONVERTIR UN NUMERO DE BINARIO A DECIMAL
    public static int convertirBinarioADecimal(String binario) {
        int decimal = 0;
        int longitud = binario.length();

        for (int i = 0; i < longitud; i++) {
            if (binario.charAt(longitud - 1 - i) == '1') {
                decimal += Math.pow(2, i);
            }
        }

        return decimal;
    }
    
}//FIN DE LA CLASE
