/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;

//CLASE A TRAVES DE LA CUAL SE CALCULA LA DIRECCION IP A PARTIR DE LA INFORMACION DEL NUMERO DE HOST
//Y DE LA DIRECCION DE RED.

public class obtenerDireccionIP {
    private int numeroDeHost;//NUMERO DE HOST
    private String binario;
    private int hostBinario[];
    private String direccionIP;
    private String clase = "";
    private int prefijo;

    private caracteristicasIP caracteristicasIP;
    private int direccionDeLaRedBinario[] = new int[32];//DIRECCION DE LA RED EN FORMA BINARIA (A ESTA SE MODIFICA CON LOS HOST EN BINARIO)
    private int octeto1;
    private int octeto2;
    private int octeto3;
    private int octeto4;

    public obtenerDireccionIP(int numeroDeHost, String direccionIPs){//OBTENEMOS LOS DATOS DEL NUMERO DE HOST Y LA DIRECCION IP DE LA RED

        this.direccionIP = direccionIPs;
        this.numeroDeHost = numeroDeHost;

        caracteristicasIP caracteristicasIP = new caracteristicasIP(direccionIP, obtenerPrefijo(clase));
        this.direccionIP = direccionIPs;
        this.caracteristicasIP = caracteristicasIP;
        this.prefijo = caracteristicasIP.getPrefijo();

        conClase conClase = new conClase(direccionIP,obtenerPrefijo(clase),caracteristicasIP);//OBTENEMOS LA DIRECCION IP EN BINARIO
        this.octeto1 = caracteristicasIP.getOcteto1();
        this.octeto2 = caracteristicasIP.getOcteto2();
        this.octeto3 = caracteristicasIP.getOcteto3();
        this.octeto4 = caracteristicasIP.getOcteto4();
    }

    public void calcularPropiedades(){

        System.out.println("Clase de la red: "+obtenerClase());
        System.out.println("Obtener Prefijo: "+obtenerPrefijo(clase));
        System.out.println("Numero de Host: "+numeroDeHost);
        System.out.println("Numero de Host en binario: "+convertirABinario(numeroDeHost));
        System.out.println("Direccion de la red en forma Binaria: "+MostrarArregloIpBinario());
        System.out.println("Direccion Ip en forma binaria: "+direccionIP());
        System.out.println("Direccion Ip en forma decimal: "+binarioADecimal(direccionIP()));

        //LLAMAMOS A LA FUNCION QUE CALCULA LA DIRECCION DE RED
        caracteristicasIP caracteristicasIP = new caracteristicasIP(direccionIP, obtenerPrefijo(clase));
        conClase conClase = new conClase(direccionIP,obtenerPrefijo(clase),caracteristicasIP);//OBTENEMOS LA DIRECCION IP EN BINARIO;
        System.out.println("Direccion de red: "+conClase.ObtenerDireccionDeRed());
    }
    public String[] dividirEnOctetos() {//DIVIDIMOS EN OCTETOS LA DIRECCION IP DE LA RED
        return direccionIP.split("\\.");
    }

    //OBTENER LA CLASE DE LA RED
    public String obtenerClase(){//CLASE PARA EL MANEJO DE LA DIRECCION IP DE LA RED
        String[] octetos = dividirEnOctetos();
        this.octeto1 = Integer.parseInt(octetos[0]);
        this.octeto2 = Integer.parseInt(octetos[1]);
        this.octeto3 = Integer.parseInt(octetos[2]);
        this.octeto4 = Integer.parseInt(octetos[3]);


        //VERIFICAMOS SI EL VALOR DE LOS OCTETOS ES VALIDO PARA UNA DIRECCION IP
        if (octeto1 >= 1 && octeto1 <= 255 && octeto2 >= 0 && octeto2 <= 255 && octeto3 >= 0 && octeto3 <= 255 && octeto4 >= 0 && octeto4 <= 255){
            //DETERMINAMOS LA CLASE DE LA DIRECCION IP
            int primerOcteto = Integer.parseInt(dividirEnOctetos()[0]);
            if (primerOcteto >= 1 && primerOcteto <= 126) clase = "A";
            if (primerOcteto >= 128 && primerOcteto <= 191) clase = "B";
            if (primerOcteto >= 192 && primerOcteto <= 223) clase = "C";
            if (primerOcteto >= 224 && primerOcteto <= 239) clase = "D";
            if (primerOcteto >= 240 && primerOcteto <= 255) clase = "E";
        }else{
            System.exit(0);
        }
        return clase;
    }

    //OBTENEMOS EL PREFIJO DE LA RED
    public int obtenerPrefijo(String clase){

        if (clase == "A"){
            prefijo = 8;
        }if(clase == "B"){
            prefijo = 16;
        }if (clase == "C"){
            prefijo = 24;
        }if (clase == "D"){
            System.out.print("Prefijo no valido");
            System.exit(0);
        }if (clase == "E"){
            System.out.print("Prefijo no valido");
            System.exit(0);
        }
        return prefijo;
    }

    //OBTENEMOS EL NUMERO DE HOST EN FORMA BINARIA
    //FUNCION PARA CONVERTIR UN NUMERO DECIMAL A BINARIO
    public void MiClase(String binario) {
        this.binario = binario;
        this.hostBinario = new int[binario.length()]; // Ahora tiene un tamaño válido
    }

    //EL NUMERO DE HOST LO CONVERTIMOS EN BINARIO
    public String convertirABinario(int numeroDecimal) {
        binario = Integer.toBinaryString(numeroDecimal);//REALIZAMO9S LA CONVERSION A BINARIO
        MiClase(binario);

        for (int i = 0; i < binario.length(); i++) {
            hostBinario[i] = Character.getNumericValue(binario.charAt(i));
        }
        return binario;
    }

    //OBTENEMOS LA DIRECCION DE LA IP EN FORMA BINARIA Y LLENAMOS EL ARREGLO
    private void obtenerDireccionIpBinario(){
        conClase conClase = new conClase(direccionIP, obtenerPrefijo(clase), caracteristicasIP);//OBTENEMOS LA DIRECCION IP EN BINARIO
        String direccionIpBinarioString = conClase.CalcularDireccionIpDecimalABinario();

        for (int i = 0; i <32; i++) {//LLENAMOS EL ARREGLO CON LA DIRECCION EN BINARIO
            direccionDeLaRedBinario[i] = Character.getNumericValue(direccionIpBinarioString.charAt(i));
        }

    }

    //LLENAMOS EL ARREGLO CON LA DIRECCION IP PARA REALIZAR LAS SIGUIENTES OPERACIONES
    public String MostrarArregloIpBinario() {
        obtenerDireccionIpBinario();

        StringBuilder direccionIpBinarioString = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (i == 8 || i == 16 || i == 24) {
                direccionIpBinarioString.append(".");
            }
            direccionIpBinarioString.append(direccionDeLaRedBinario[i]);
        }

        return direccionIpBinarioString.toString();
    }
    //REEMPLAZAMOS LA PARTE DE HOST POR EL NUMERO DE HOST EN FORMA BINARIA A PARTIR DEL LADO DERECHO
    public String direccionIP(){
        //ARREGLO DE LA DIRECCION IP BINARIO TAMAÑO DE 0 A 31.
        //ARREGLO DEL NUMERO DE HOST EN BINARIO (OBTENER TAMAÑO) E INSERTAR DE DERECHA A IZQUIERDA EN DIRECCION IP EN BINARIO
        for (int i = 31, j = binario.length() - 1; j >= 0; i--, j--) {
            direccionDeLaRedBinario[i] = hostBinario[j];
        }
        StringBuilder direccionIpBinarioString = new StringBuilder();
        for (int i = 0; i <32; i++) {
            if (i == 8 || i == 16 || i == 24) {
                direccionIpBinarioString.append(".");
            }
            direccionIpBinarioString.append(direccionDeLaRedBinario[i]);
        }
        return direccionIpBinarioString.toString();
    }

    //CONVERTIMOS LA DIRECCION IP RESULTANTE A FORMA DECIMAL
    public String binarioADecimal(String direccionBinaria) {
        // Separar la dirección en bloques de 8 bits
        String[] bloques = direccionBinaria.split("\\.");
        StringBuilder direccionDecimal = new StringBuilder();

        // Convertir cada bloque a decimal
        for (int i = 0; i < bloques.length; i++) {
            int decimal = Integer.parseInt(bloques[i], 2); // Convierte binario a decimal
            direccionDecimal.append(decimal);
            if (i < bloques.length - 1) {
                direccionDecimal.append(".");
            }
        }

        return direccionDecimal.toString();
    }

    //OBTENEMOS LA  DIRECCION DE RED

}