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
    
    //PUNTO 2: Agregar un nuevo avión a la lista de aviones.
    public static void agregarAvion (Avion[]listAviones){
        String patente;
        boolean bandera = false;
        int i =0;
        System.out.println("Ingrese el ID del neuvo avion, en mayusculas, respetando que cumple el Estandar internacional");
        patente = sc.nextLine();
        if (verificarExiste(listAviones, patente)){
            System.out.println("Error, el avion ya existe");
        }else{
            if (Avion.verifIDAvion(patente)){
                while (i<listAviones.length && !bandera){
                    if (listAviones[i]==null){
                        System.out.println("Ingrese el modelo del avion: ");
                        String modelo = sc.nextLine();
                        System.out.println("Ingrese la cantidad de vuelos del avion: ");
                        int vuelos = Integer.parseInt(sc.nextLine());
                        System.out.println("Ingrese la cantidad de asientos del avion: ");
                        int asientos = Integer.parseInt(sc.nextLine());
                        System.out.println("Ingrese la cantidad de kilometros recorridos del avion: ");
                        int kmRecorridos = Integer.parseInt(sc.nextLine());
                        listAviones[i] = new Avion(patente, modelo, vuelos, asientos, kmRecorridos);
                        System.out.println("El avion ha sido agregado correctamente");
                        bandera = true;
                    }
                i++;
                }
            }else{
                System.out.println("La patente no cumple con el Estandar internacional");
            }
        }
    }

    //PARA VER SI EXISTE EL AVION
    public static boolean verificarExiste (Avion[] listaAviones, String patente){
        boolean existe = false;
        int i = 0;
        while(listaAviones[i]!=null && i<listaAviones.length && !existe){
            if (patente.equals(listaAviones[i].getIDavion())){
                existe = true;
            }
        }
        return existe;
    }

    //PUNTO 4: Marcar la realización efectiva de un vuelo, es decir, poder determinar cuando el vuelo aterriza en la ciudad destino.
    //En esta opción se deberán actualizar los datos de los aviones de acuerdo al vuelo realizado (cantidad de vuelos y kilómetros recorridos)
    public static void confirmVuelo (Vuelo[][] cronograma, Avion[] aviones, Ruta[] rutas){

    }

    //PUNTO 7: Mostrar los datos de un avión dado
    public static void mostrarDatAvion(Avion[] listaAviones){
        String codAvion;
        int i=0;
        boolean encontro = false;
        System.out.println("Ingrese el codigo del avion para ver sus datos: ");
        codAvion = sc.nextLine();
        if (Avion.verifIDAvion(codAvion)){
            while (!encontro && listaAviones[i]!=null && i<=listaAviones.length){
                if (codAvion.equals(listaAviones[i].getIDavion())){
                    encontro = true;
                    System.out.println("Los datos del avion son: " +listaAviones[i].toString());
                }
                i++;
            }
            if (!encontro){
                System.out.println("No se encontro el avion solicitado");
            }
        }
    }

    //PUNTO8: Dado un rango formado por dos distancias (solicitar al usuario)
    //devolver en un arreglo todos los vuelos cuya ruta tenga una distancia comprendida en ese rango.
    public static Vuelo[] distComprendidas (Vuelo[][]cronogramaVuelos){
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
        for (int fila = 0; fila < cronogramaVuelos.length; fila++) {
            for (int col = 0; col < cronogramaVuelos[fila].length; col++) {
                if (cronogramaVuelos[fila][col] != null) { // Evitar valores nulos
                    if (cronogramaVuelos[fila][col].getDistVuelo() >= dist1 && cronogramaVuelos[fila][col].getDistVuelo() <= dist2) {
                        vuelosFiltrados[j] = cronogramaVuelos[fila][col];
                        j++;
                    }
                }
            }
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
    //PUNTO10: Mostrar para cada día de la semana el primer horario en que hay un vuelo internacional. Si no hay, emitir un mensaje adecuado.
    public static void vueloInternacional (Vuelo[][] elSistema){
        for(int i=0; i<elSistema.length; i++){
            boolean bandera = false;
            int j=0;
            while (j < elSistema[0].length && !bandera) {
                if (elSistema[i][j].getInternacionalVuelo().equalsIgnoreCase("si")) {
                    System.out.println("Hay un vuelo internacional en el horario de las: " + (j + 8) + "hs" + " El dia: " + " " + diaAString(i));
                    bandera = true;
                }
                j++;
            }
            if (!bandera){
                System.out.println("No se encontraron vuelos internacionales el dia: " +" "+diaAString(i));
            }
        }
    }

    //USADO EN LA CARGA
    public static int buscarPosRuta(Ruta[] rutas, String numeroRuta){
        int pos = -1;
        boolean encontrado = false;
        int i = 0;
        while(i < rutas.length && !encontrado){
          if(rutas[i] != null && rutas[i].getNumRuta().equalsIgnoreCase(numeroRuta)){
            pos = i;
            encontrado = true;
          }
          i++;
        }
        return pos;
      }

      public static int buscarPosAvion(Avion[] aviones, String matricula){
        int pos = -1;
        boolean encontrado = false;
        int i = 0;
        while(i < aviones.length && !encontrado){
          if(aviones[i] != null && aviones[i].getIDavion().equalsIgnoreCase(matricula)){
            pos = i;
            encontrado = true;
          }
          i++;
        }
        return pos;
      }

      public static String diaAString (int num){
        String dia;
        switch (num) {
            case 0:
                dia = "Lunes";
                break;
            case 1:
                dia = "Martes";
                break;
            case 2:
                dia = "Miércoles";
                break;
            case 3:
                dia = "Jueves";
                break;
            case 4:
                dia = "Viernes";
                break;
            case 5:
                dia = "Sábado";
                break;
            case 6:
                dia = "Domingo";
                break;
            default:
                dia = "Número inválido";
                break;
        }
        return dia;
    }
}


