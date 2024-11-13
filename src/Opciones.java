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
}
