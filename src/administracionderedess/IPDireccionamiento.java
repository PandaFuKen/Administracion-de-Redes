/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administracionderedess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IPDireccionamiento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in);
        
        // Usamos la máscara 255.255.255.192 y la IP base (para la parte que se modifica es el cuarto octeto)
        
        System.out.print("Introduce una direccion IP: ");
        String ip = scanner.nextLine();
        
        System.out.print("Introduce la mascara de subred: ");
        String mask = scanner.nextLine();
        
        generateSubnetCombinations(ip, mask);
    }

    public static void generateSubnetCombinations(String ip, String mask) {
        // Separamos en octetos la IP y la máscara
        String[] ipParts = ip.split("\\.");
        String[] maskParts = mask.split("\\.");

        //ESCANEAMOS CADA OCTETO DE LA MASCARA Y VALIDAMOS QUE EL VALOR DE CADA OCTETO SEA 255
        int subnetIndex = -1;
        int subnetValue = -1;
        for (int i = 0; i < maskParts.length; i++) {
            int val = Integer.parseInt(maskParts[i]);
            if (val < 255) {//EN CASO DE QUE SE ENCUENTRE UN OCTETO QUE SEA MENOR A 255
                subnetIndex = i;
                subnetValue = val;
                break;
            }
        }
        if (subnetIndex == -1) {//EN CASO DE QUE TODOS LOS OCTETOS SEAN 255
            System.out.println("La máscara no tiene octetos incompletos.");
            return;
        }

        // CONVERTIMOS LOS OCTETOS DE LA MASCARA EN FORMA BINARIA
        String subnetBinary = String.format("%8s", Integer.toBinaryString(subnetValue)).replace(" ", "0");
        // CONTAMOS LA CANTIDAD DE BITS EN 1 (LOS BITS DE LA SUBRED) EN ESE OCTETO
        int networkBits = subnetBinary.indexOf("0"); // EJEMPLO, EN CASO DE QUE SEA 11000000, DARA COMO RESULTADO 2
        // CALCULAMOS LAS COMBINACIONES POSIBLES USANDO SOLO ESOS BITS, DESCARTANDO LA COMBINACION DE 00 Y 11
        int max = (int) Math.pow(2, networkBits);
        int numCombinations = max - 2; // Ej: 2^2 - 2 = 4 - 2 = 2

        System.out.println("Octeto de la máscara en binario: " + subnetBinary);//OBTENEMOS EL OCTETO EN EL CUAL SE ENCUENTRA LA PARTE DE LA SUBRED
        System.out.println("Bits de red en ese octeto: " + networkBits);//OBTENEMOS LA CANTIDAD DE BITS DE LA SUBRED DE ESE OCTETO
        System.out.println("Generando " + numCombinations + " combinaciones posibles...\n");//MOSTRAMOS LA CANTIDAD DE COMBINACIONES POSIBLES

        // GENERAMOS LAS COMBINACIONES DE 1 DE LA PARTE DEL INTERVALO
        List<String> combinations = new ArrayList<>();
        for (int i = 1; i < max - 1; i++) {
            String binary = String.format("%" + networkBits + "s", Integer.toBinaryString(i)).replace(" ", "0");
            combinations.add(binary);
        }

        // PARA CADA COMBINACION SE CONSTRUYE UN NUEVO NIVEL DEL OCTETO
        // SE TOMA LA COMBINACION Y SE LE AÑADEN CEROS A LA DERECHA PARA FORMAR 8 BITS, EJEMPLO: 01 --> 01000000
        for (String combo : combinations) {
            // CONSTRUIMOS EL NUEVO CUARTO OCTETO DE LOS BITS DE SUBRED GENERADOS MAS LOS CEROS A LA DERECHA PARA LA PARTE DE HOST
            String fullOctetBinary = combo + "0".repeat(8 - networkBits);
            int newOctetValue = Integer.parseInt(fullOctetBinary, 2);

            // SE CLONA EL ARREGLO DE LA IP Y SE REEMPLAZA EL OCTETO CORRESPONDIENTE
            String[] newIpParts = ipParts.clone();
            //newIpParts almacena la direccion ip de esta forma ["192", "168", "1", "0"]
            newIpParts[subnetIndex] = String.valueOf(newOctetValue);
            //subnetIndex almacena el numero entero de la posicion del octeto en el cual se encuentra el intervalo de la subred

            // CALCULAMOS LA DIRECCION DE RED, LOS OCTETOS SIGUIENTES SE PONEN EN 0
            String networkAddress = getNetworkAddress(newIpParts, subnetIndex);

            // PARA OBTENER LA PRIMERA DIRECCION UTILIZABLE SE LE AUMENTA LA ULTIMA POSICION DEL OCTETO EN 1
            String firstUsable = getFirstUsable(networkAddress);

            // CALCULAMOS LA DIRECCION DE BROADCAST
            // EN EL OCTETO INCOMPLETO SE PONEN LOS BITS DE HOST EN 1
            String broadcastAddress = getBroadcastAddress(newIpParts, subnetIndex, networkBits);

            // PARA LA ULTIMA IP UTILIZABLE SE LE RESTA UN 1 AL ULTIMO OCTETO DE LA DIRECCION IP DEL BROADCAST
            String lastUsable = getLastUsable(broadcastAddress);
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Combinacion (bits de red): " + combo);
            System.out.println("Octeto generado (binario): " + fullOctetBinary + " -> " + newOctetValue);
            System.out.println("Direccion de Red: " + networkAddress);
            System.out.println("Primera Direccion Utilizable: " + firstUsable);
            System.out.println("Broadcast: " + broadcastAddress);
            System.out.println("Ultima Direccion Utilizable: " + lastUsable + "\n");
        }
    }

    // PARA LA DIRECCION DE RED, LA PARTE DE HOST POSTERIOR A LOS BITS DE SUBRED, SE PONEN EN 0
    public static String getNetworkAddress(String[] ipParts, int subnetIndex) {
        String[] parts = ipParts.clone();
        for (int i = subnetIndex + 1; i < parts.length; i++) {
            parts[i] = "0";
        }
        return String.join(".", parts);
    }

    // LA PRIMERA RED UTILIZABLE ES SUMANDOLE 1 AL ULTIMO OCTETO DE LA DIRECCION IP
    public static String getFirstUsable(String networkAddress) {
        String[] parts = networkAddress.split("\\.");
        parts[3] = String.valueOf(Integer.parseInt(parts[3]) + 1);
        return String.join(".", parts);
    }

    // LA DIRECCION DE BROADCAST EN EL OCTETO INCOMPLETO SE PONEN LOS BITS DE HOST EN 1
    // es decir, se OR con ((1 << (8 - networkBits)) - 1), y en los siguientes octetos se pone 255.
    public static String getBroadcastAddress(String[] ipParts, int subnetIndex, int networkBits) {
        String[] parts = ipParts.clone();
        int currentValue = Integer.parseInt(parts[subnetIndex]);
        int hostMask = (1 << (8 - networkBits)) - 1;
        int broadcastPart = currentValue | hostMask;
        parts[subnetIndex] = String.valueOf(broadcastPart);
        for (int i = subnetIndex + 1; i < parts.length; i++) {
            parts[i] = "255";
        }
        return String.join(".", parts);
    }

    // PARA OBTENER LA ULTIMA DIRECCION UTILIZABLE SE LE RESTA 1 AL ULTIMO OCTETO DE LA DIRECCION IP
    public static String getLastUsable(String broadcastAddress) {
        String[] parts = broadcastAddress.split("\\.");
        parts[3] = String.valueOf(Integer.parseInt(parts[3]) - 1);
        return String.join(".", parts);
    }
}