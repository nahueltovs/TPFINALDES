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

    //PUNTO 7: Mostrar los datos de un avión dado
    public static void mostrarDatAvion(Avion[] listaAviones){
        String codAvion;
        int i=0;
        boolean encontro = false;
        System.out.println("Ingrese el codigo del avion para ver sus datos: ");
        codAvion = sc.nextLine();
        //AGREGAR METODO DE SI CUMPLE ESTANDARD Y SI EXSITE EN LA LISTA DE AVIONES TAMBIEN ES POSIBLE PARA
        //EVITAR EL CASO EN EL QUE SI NO SE ENCUENTRA
        while (!encontro && listaAviones[i]!=null && i<=listaAviones.length){
            if (codAvion==listaAviones[i].getIDavion()){
                encontro = true;
                System.out.println("Los datos del avion son: " +listaAviones[i].toString());
            }
            i++;
        }
        if (!encontro){
            System.out.println("No se encontro el avion solicitado");
        }
    }

    //PUNTO8: Dado un rango formado por dos distancias (solicitar al usuario)
    //devolver en un arreglo todos los vuelos cuya ruta tenga una distancia comprendida en ese rango.
    public static Vuelo[] distComprendidas (Vuelo[]listaVuelos){
        int dist1,dist2,i,j;
        i=0;
        j=0;
        System.out.println("Ingrese la primera distancia: ");
        dist1 = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese la segunda distancia: ");
        dist2 = Integer.parseInt(sc.nextLine());
        while(listaVuelos[i]!=null && i<=listaVuelos.length){
            if ((listaVuelos[i].getDistVuelo()>=dist1&&listaVuelos[i].getDistVuelo()<=dist2)||
            (listaVuelos[i].getDistVuelo()<=dist1&&listaVuelos[i].getDistVuelo()>=dist2)){

            }
        }
    }

}
