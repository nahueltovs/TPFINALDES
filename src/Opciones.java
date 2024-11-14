import java.util.Scanner;

import TDA.Avion;
import TDA.Ruta;
import TDA.Vuelo;

public class Opciones {
    private final static Scanner sc = new Scanner(System.in);

    public static void procOpciones(String a){
        boolean bandera = true;
        System.out.println("Ingrese opcion: ");
        int respuesta = Integer.parseInt(sc.nextLine());
        switch (respuesta) {
            case 0:
                bandera = false;
                break;
        
            default:
                break;
        }
    }
    public static void agregarAvion (Avion[] listaAviones, Avion nuevoAvion){
        
    }
    public static boolean cumpleStandar (Avion unAvion){
        String codigoAvion = unAvion.getIDavion().toUpperCase();
        String aux = "0123456789";
        int i = 0;
        boolean cumplio = true;
        if (codigoAvion.length()>=6 || codigoAvion.length()<=8){
            while (cumplio && i<=codigoAvion.length()){
                if (codigoAvion.charAt(i)=='L'){
                    i++;
                    if(codigoAvion.charAt(i)=='Q'){
                        
                    }
                }else{
                    cumplio=false;
                }
            }
        }else{
            cumplio=false;
        }
        return cumplio;
    }
}
