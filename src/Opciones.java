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
        //Genero un arreglo de 50 vuelos auxiliar para no recorrer dos veces la lista de vuelos
        Vuelo[] vuelosFiltrados = new Vuelo[100];
        int dist1,dist2,i,j;
        i=0;
        j=0;
        do {
            System.out.println("Ingrese la primera distancia (menor): ");
            dist1 = Integer.parseInt(sc.nextLine());
            System.out.println("Ingrese la segunda distancia (mayor): ");
            dist2 = Integer.parseInt(sc.nextLine());
    
            if (dist1 > dist2) {
                System.out.println("Error: La primera distancia debe ser menor que la segunda. Intente nuevamente.");
            }
        } while (dist1 > dist2);
        while(i<listaVuelos.length && listaVuelos[i]!=null){
            if ((listaVuelos[i].getDistVuelo()>=dist1&&listaVuelos[i].getDistVuelo()<=dist2)){
                vuelosFiltrados[j] = listaVuelos[i];
                j++;
            }
            i++;
        }
        //CREO EL ARREGLO FINAL QUE VOY A DEVOLVER USANDO EL AUXILIAR. DE ESTA FORMA NO TENDRA ESPACIOS NULOS
        Vuelo[] vuelosComprendidos = new Vuelo[j];
        for (int k =0; k<j; k++){
            vuelosComprendidos[k]=vuelosFiltrados[k];
        }
        return vuelosComprendidos;
    }

    //PUNTO9: Calcular en forma recursiva la cantidad de horarios sin vuelos en la semana.
    public static int horariosSinVuelos (Vuelo[][] elSistema, int i, int j, int aux){
        int sinVuelos = 0;
        if (i>= elSistema.length){
            sinVuelos = aux;
        }else{
            if (j>=elSistema[0].length){
                sinVuelos = horariosSinVuelos(elSistema, i+1, 0, aux);
            }else{
                if(elSistema[i][j]==null){
                    aux++;
                }
                sinVuelos = horariosSinVuelos(elSistema, i, j+1, aux);
            }
        }
        return sinVuelos;
    }


}
