package administracionderedess;
import javax.swing.table.DefaultTableModel;

public class CalculoHosts {
    private DefaultTableModel modelo;
    
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

    public CalculoHosts(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void calcularCantidadHosts(String direccionIP, int prefijo, CaracteristicasIP caracteristicasIP) {
         modelo.setRowCount(0); 
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
                    Object[] rowData = {posicion, totalHost};
                    modelo.addRow(rowData);

                }
            posicion++;//CON CADA CICLO LA POSICION AUMENTA
            }
                //return cantHost;//RETORNAMOS LA CANTIDAD TOTAL DE HOST.
    }
}



