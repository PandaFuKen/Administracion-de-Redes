/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;

public class conClase {
    
    //OBTENEMOS LA DIRECCION IP Y EL PREFIJO ORIGINAL QUE INGRESO EL USUARIO
    private String direccionIP;
    private int prefijo;
    
    //OBTENEMOS LAS CARACTERISTICASA DE LA DIRECCION IP COMO SU PREFIJO, CLASE Y EL VALOR DE LOS OCTETOS
    private caracteristicasIP caracteristicasIP;
    private final int octeto1;
    private final int octeto2;
    private final int octeto3;
    private final int octeto4;    
    private final int direccionIpBinario[] = new int[32];//DIRECCION IP EN FORMA DE NUMERO BINARIO
    private final int mascaraBinario[]= new int[32];//MASCARA DE SUBRED EN FORMA DE NUMERO BINARIO
    private final int mascaraDecimal[]= new int[32];//MASCARA DE SUBRED EN FORMA DECIMAL
    private final int direccionAnd[]= new int[32];//DIRECCION RESULTADO DE LA OPERACION AND
    
    //DEFINIMOS OCTETOS PARA OPERACION AND
    private int primerOctetoAndDecimal;
    private int segundoOctetoAndDecimal;
    private int tercerOctetoAndDecimal;
    private int cuartoOctetoAndDecimal;
    
    //DEFINIMOS OCTETOS PARA BROADCAST
    private int primerOctetoBroadcastDecimal;
    private int segundoOctetoBroadcastDecimal;
    private int tercerOctetoBroadcastDecimal;
    private int cuartoOctetoBroadcastDecimal;
    
    
    public conClase(String direccionIP, int prefijo, caracteristicasIP caracteristicasIP){
        
        this.direccionIP = caracteristicasIP.getDireccion();
        
        this.prefijo = caracteristicasIP.getPrefijo();
        
        this.octeto1 = caracteristicasIP.getOcteto1();
        this.octeto2 = caracteristicasIP.getOcteto2();
        this.octeto3 = caracteristicasIP.getOcteto3();
        this.octeto4 = caracteristicasIP.getOcteto4();
        
    }
//LLAMAR A CIERTA CLASE YB RETORNA SU VALOR:
    //String resultado = mascaraSubred.obtenerMascaraEnBinario();
    public void calcularPropiedades(){
            //PROCEDIMIENTO PARA OBTENER LAS PROPIEDADES DE LA RED
            
            System.out.println("Direccion IP en binario: "+ObtenerDireccionIpBinario());
            System.out.println("Mascara de Subred en binario: "+MascaraSubredEnBinario());
            System.out.println("operacion AND: "+OperacionAnd());
            System.out.println("Direccion de Red: "+ObtenerDireccionDeRed());
            System.out.println("Primera IP utilizable: "+ObtenerPrimeraUtilizable());
            System.out.println("Ultima IP utilizable: "+ObtenerUltimaUtilizable());
            System.out.println("Broadcast: "+ObtenerDireccionBroadcast());
        }

    public String MascaraSubredEnBinario(){

       for (int i = 0; i < prefijo; i++ ) {//LLENAR LA MASCARA CON CLASE A
                    mascaraBinario[i] = 1;
                }

                for (int i = prefijo; i <= 31; i++ ) {
                    mascaraBinario[i] = 0;
            }
            
            StringBuilder mascaraBinarioString = new StringBuilder();
                //IMPRIMIR MASCARA DE SUBRED EN BINARIO
                for (int i = 0; i < 32; i++) {
                if (i==8 || i==16 || i==24) {
                    mascaraBinarioString.append(".");
                }
                mascaraBinarioString.append(mascaraBinario[i]);
            }
                return mascaraBinarioString.toString();
    }

    //AL LLAMANRA ESTA CLASE SE OBTIENE LA DIRECCION IP EN BINARIO SIN SEPARACIONES POR PUNTO DECIMAL
    //SOLO SE MANEJA EN OTRAS CLASES DONDE SE DEBEN REALIZAR OPERACIONES
    public String CalcularDireccionIpDecimalABinario(){
        MascaraSubredEnBinario();

            String primerOctetoBinario = convertirABinario(octeto1);
            //System.out.println("1 octeto bin: "+primerOctetoBinario);
            String segundoOctetoBinario = convertirABinario(octeto2);
            //System.out.println("2 octeto bin: "+segundoOctetoBinario);
            String tercerOctetoBinario = convertirABinario(octeto3);
            //System.out.println("3 octeto bin: "+tercerOctetoBinario);
            String cuartoOctetoBinario = convertirABinario(octeto4);
            //System.out.println("4 octeto bin: "+cuartoOctetoBinario);

         //LLENAMOS EL ARREGLO CON LA DIRECCION IP EN BINARIO.
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
            //System.out.print("Direccion IP en binario: ");

            StringBuilder direccionIpBinarioString = new StringBuilder();
            for (int i = 0; i <32; i++) {
               direccionIpBinarioString.append(direccionIpBinario[i]);
            }

            return direccionIpBinarioString.toString();
    }
    
    //AL LLAMAR A ESTA CLASE SE OBTIENE LA DIRECCION IP EN BINARIO CON PUNTO DECIMAL
    //LISTA PARA IMPRIMIR
    public String ObtenerDireccionIpBinario(){
        CalcularDireccionIpDecimalABinario();
     StringBuilder direccionIpBinarioString = new StringBuilder();
            for (int i = 0; i <32; i++) {
                if (i==8 || i==16 || i==24) {
                    System.out.print(".");
                }
               direccionIpBinarioString.append(direccionIpBinario[i]);
            }

            return direccionIpBinarioString.toString();
    }

    public String OperacionAnd(){
        CalcularDireccionIpDecimalABinario();
        
        //LLENAMOS EL ARREGLO CON LOS RESULTADOS DE LA OPERACION AND
            for (int i = 0; i < 32; i++) {
                if (direccionIpBinario[i] == 1 && mascaraBinario[i] == 1) {
                    direccionAnd[i] = 1;
                }else if(direccionIpBinario[i]!= 1 && mascaraBinario[i]!= 1) {
                    direccionAnd[i]=0;
                }
            }
            //IMPRIME DIRECCION EN BINARIO DE LA OPERACION AND
            StringBuilder direccionAndString = new StringBuilder();
           // System.out.print("AND: ");
            for (int i = 0; i < 32; i++) {
                if (i==8 || i==16 || i==24) {
                    direccionAndString.append(".");
                }
                direccionAndString.append(direccionAnd[i]);
            }
            return direccionAndString.toString();
    }
    
    public String ObtenerDireccionDeRed(){
        OperacionAnd();
        //OBTENER DIRECCION DE RED
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
             primerOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal1);

            String conversionAndBinarioDecimal2 = segundoOctetoAndBinarioADecimal.toString(); 
             segundoOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal2);

            String conversionAndBinarioDecimal3 = tercerOctetoAndBinarioADecimal.toString(); 
             tercerOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal3);

            String conversionAndBinarioDecimal4 = cuartoOctetoAndBinarioADecimal.toString(); 
             cuartoOctetoAndDecimal = convertirBinarioADecimal(conversionAndBinarioDecimal4);

            //RETORNAMOS LA DIRECCION DE RED
            return (+primerOctetoAndDecimal+"."+segundoOctetoAndDecimal+"."+tercerOctetoAndDecimal+"."+cuartoOctetoAndDecimal);
    }
    
    private String CalcularPrimeraUtilizable(int primerOctetoAndDecimal, int segundOctetoAndDecimal, int tercerOctetoAndDecimal, int cuartoOctetoAndDecimal){
    
        ObtenerDireccionDeRed();
        
        cuartoOctetoAndDecimal = cuartoOctetoAndDecimal + 1;
            String PrimeraUtilizable = (+ primerOctetoAndDecimal + "." + segundOctetoAndDecimal + "." + tercerOctetoAndDecimal + "." + cuartoOctetoAndDecimal);
            return PrimeraUtilizable;
    }

    public String ObtenerDireccionBroadcast(){
        OperacionAnd(); 
        //PARA OBTENER LA DIRECCION DE BROADCAST, CONVERTIMOS LA PARTE DE HOST A 1
            for (int i = prefijo; i < 32; i++) {//TODOS LOS DIGITOS QUE ESTEN EN UNA POSICION SUPERIOR A LA DEL PREFIJO, SE CONVIERTEN EN UNO
                direccionAnd[i] = 1;
            }
            //CONVERTIR LOS OCTETOS DE BINARIO A DECIMAL PARA OBTENER EL BROADCAST
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
             primerOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal1);

            String conversionBroadcastBinarioDecimal2 = segundoOctetoBroadcastBinarioADecimal.toString(); 
             segundoOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal2);

            String conversionBroadcastBinarioDecimal3 = tercerOctetoBroadcastBinarioADecimal.toString(); 
             tercerOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal3);

            String conversionBroadcastBinarioDecimal4 = cuartoOctetoBroadcastBinarioADecimal.toString(); 
            cuartoOctetoBroadcastDecimal = convertirBinarioADecimal(conversionBroadcastBinarioDecimal4);

            //IMPRIMIMOS LA DIRECCION DE BROADCAST
            String Broadcast = (+primerOctetoBroadcastDecimal+"."+segundoOctetoBroadcastDecimal+"."+tercerOctetoBroadcastDecimal+"."+cuartoOctetoBroadcastDecimal);
            return Broadcast;
    }
    
    private String CalcularUltimaUtilizable(int primerOctetoBroadcastDecimal, int segundoOctetoBroadcastDecimal, int tercerOctetoBroadcastDecimal, int cuartoOctetoBroadcastDecimal){   
        ObtenerDireccionBroadcast();        
           cuartoOctetoBroadcastDecimal = cuartoOctetoBroadcastDecimal-1;
            String ultimaUtilizable = (+ primerOctetoBroadcastDecimal + "." + segundoOctetoBroadcastDecimal + "." + tercerOctetoBroadcastDecimal+ "." + cuartoOctetoBroadcastDecimal);
            return ultimaUtilizable;
    }
    
    public String ObtenerPrimeraUtilizable(){
        String obtenerPrimeraIpUtilizable = CalcularPrimeraUtilizable(primerOctetoAndDecimal, segundoOctetoAndDecimal, tercerOctetoAndDecimal, cuartoOctetoAndDecimal);
        return obtenerPrimeraIpUtilizable;
    
    }
    
    public String ObtenerUltimaUtilizable(){
        ObtenerDireccionBroadcast();
        String obtenerUltimaUtilizable = CalcularUltimaUtilizable(primerOctetoBroadcastDecimal, segundoOctetoBroadcastDecimal, tercerOctetoBroadcastDecimal, cuartoOctetoBroadcastDecimal);
        return obtenerUltimaUtilizable;
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