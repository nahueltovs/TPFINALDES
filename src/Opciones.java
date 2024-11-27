import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import TDA.Avion;
import TDA.Ruta;
import TDA.Vuelo;

public class Opciones {
    private final static Scanner sc = new Scanner(System.in);

    public static void procOpciones(Vuelo[][] vuelos, Avion[] aviones, Ruta[] rutas) {
        boolean bandera = true;
    
        while (bandera) {
            try {
                System.out.println("Seleccione una opcion valida:\n1 - Agregar un nuevo avión a la lista de aviones\n2 - Agregar un nuevo vuelo al cronograma de vuelos de la semana\n3 - Marcar la realización efectiva de un vuelo\n4 - Calcular y mostrar el promedio de pasajeros que efectivamente volaron\n5 - Mostrar para un día dado, la lista de vuelos ordenada por distancia en kilómetros\n6 - Mostrar los datos de un avión dado\n7 - Dado un rango formado por dos distancias, devolver en un arreglo todos los vuelos cuya ruta tenga una distancia comprendida en ese rango\n8 - Calcular la cantidad de horarios sin vuelos en la semana\n9 - Mostrar para cada día de la semana el primer horario en que hay un vuelo internacional.");
                int respuesta = Integer.parseInt(sc.nextLine());
    
                switch (respuesta) {
                    case 0:
                        bandera = false;
                        System.out.println("Saliendo del programa...");
                        break;
                    case 1:
                        agregarAvion(aviones);
                        break;
                    case 2:
                        agregVuelo(vuelos, aviones, rutas);
                        break;
                    case 3:
                        confirmVuelo(vuelos, aviones);
                        break;
                    case 4:
                        pasajerosVolaron(vuelos, aviones);
                        break;
                    case 5:
                        System.out.println("Ingrese el dia a ordenar: ");
                        String diaParaOrdenar = sc.nextLine();
                        exporteResultados(listVuelos(vuelos, diaParaOrdenar));
                        break;
                    case 6:
                        mostrarDatAvion(aviones);
                        break;
                    case 7:
                        distComprendidas(vuelos);
                        break;
                    case 8:
                        System.out.println("La cantidad de horarios sin vuelos en la semana es de: "+horariosSinVuelos(vuelos, 0, 0, 0));
                        break;
                    case 9:
                        vueloInternacional(vuelos);
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero valido.");
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }
        }
    }
    
    //PUNTO 2: Agregar un nuevo avión a la lista de aviones.
    public static void agregarAvion (Avion[]listAviones){
        String patente;
        boolean bandera = false;
        int i =0;
        System.out.println("Ingrese el ID del nuevo avion, en mayusculas, respetando que cumple el Estandar internacional");
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
            i++;
        }
        return existe;
    }
    public static boolean verificarRuta (Ruta[] listaRutas, String numRuta){
        boolean existe = false;
        int i = 0;
        while(listaRutas[i]!=null && i<listaRutas.length && !existe){
            if (numRuta.equalsIgnoreCase(listaRutas[i].getNumRuta())){
                existe = true;
            }
            i++;
        }
        return existe;
    }

    //PUNTO 3:Agregar un nuevo vuelo al cronograma de vuelos de la semana
    //solicitando el número de vuelo,  el avión, la hora y el día que debe ocurrir, la ruta que debe recorrer y la cantidad de pasajeros. 
    //Se deberán hacer los controles correspondientes de acuerdo a las restricciones
    //emitir mensajes al usuario en caso de que no se pueda cumplir con lo solicitado
    public static void agregVuelo (Vuelo[][]cronograma, Avion[] aviones, Ruta[] rutas){
        System.out.println("Ingrese el codigo del vuelo: ");
        String codVuelo = sc.nextLine();
        System.out.println("Ingrese el ID del avion: ");
        String codAvion = sc.nextLine();
        System.out.println("Ingrese el numero de la ruta: ");
        String numRuta = sc.nextLine();
        System.out.println("Ingrese el dia del vuelo: ");
        String diaVuelo = sc.nextLine();
        System.out.println("Ingrese la hora del vuelo(de 8 a 22): ");
        String horaVuelo = sc.nextLine();

        int dia = stringADia(diaVuelo);
        int hora = Integer.parseInt(horaVuelo);

        boolean datosValidos = true;

        if (hora < 8 || hora>22){
            System.out.println("Error la hora esta fuera del rango");
            datosValidos = false;
        }
        if (datosValidos&& cronograma[dia][hora-8]!=null){
            System.out.println("Ya se encontro un vuelo asignado a ese horario");
            datosValidos = false;
        }
        if (datosValidos){
            boolean existeAvion = verificarExiste(aviones, codAvion);
            boolean existeRuta = verificarRuta(rutas, numRuta);
            if (!existeAvion){
                System.out.println("El avion ingresado no existe");
            }
            if (!existeRuta){
                System.out.println("La ruta ingresada no existe");
            }
            if (existeAvion && existeRuta){
                Avion elAvion = aviones[buscarPosAvion(aviones, codAvion)];
                Ruta laRuta = rutas[buscarPosRuta(rutas, numRuta)];
                if (elAvion!=null && laRuta!=null){
                    cronograma[dia][hora-8] = new Vuelo(codVuelo, elAvion, laRuta, diaVuelo, horaVuelo+":00");
                    System.out.println("Vuelo agregado exitosamente");
                }
            }
        }
    }
    //PUNTO 4: Marcar la realización efectiva de un vuelo, es decir, poder determinar cuando el vuelo aterriza en la ciudad destino.
    //En esta opción se deberán actualizar los datos de los aviones de acuerdo al vuelo realizado (cantidad de vuelos y kilómetros recorridos)
    public static void confirmVuelo (Vuelo[][] cronograma, Avion[] aviones){
        String vueloConfirmado;
        Vuelo vueloEncontrado = null;
        int i=0;
        boolean encontrado = false;
        System.out.println("Ingrese el codigo de vuelo (AR####) a actualizar: ");
        vueloConfirmado = sc.nextLine();
        while (i<cronograma.length && !encontrado){
            int j=0;
            while (j<cronograma[0].length && !encontrado){
                if (cronograma[i][j]!=null && cronograma[i][j].getVuelo().equalsIgnoreCase(vueloConfirmado)){
                    vueloEncontrado = cronograma[i][j];
                    encontrado = true;
                }
                j++;
            }
            i++;
        }
        if (encontrado){
            vueloEncontrado.setAterrizo(true);
            Avion avionAterrizado;
            avionAterrizado = vueloEncontrado.getAvion();
            if (avionAterrizado!=null){
                avionAterrizado.setVuelos(1);
                avionAterrizado.setKMrecor(vueloEncontrado.getDistVuelo());
            }
            System.out.println("Se han actualizado los datos del avion y vuelo.");
        }else{
            System.out.println("No se encontro el vuelo solicitado "+vueloConfirmado);
        }
    }

    //PUNTO 5: Calcular y mostrar el promedio de pasajeros que efectivamente volaron en forma recursiva 
    //(suponiendo que el avión va con sus asientos completos).
    //El promedio es de todos los vuelos que hay en la matriz que tengan la marca de haber aterrizado
    public static void pasajerosVolaron (Vuelo[][] cronograma, Avion[] vuelos){
        System.out.println("El promedio de los pasajeros que volaron es: "
        + calcPasajeros(cronograma, 0, 0, 0)/(double) calcularPromedio(cronograma, 0, 0, 0));
        System.out.println("De: "+calcPasajeros(cronograma, 0, 0, 0)+" Pasajeros en: " + calcularPromedio(cronograma, 0, 0, 0) + " Vuelos confirmados");
    }
    //LOS METODOS SON PRIVADOS YA QUE SON SOLO ACCEDIDOS EN ESTA OPCION PARA LA CUENTA
    private static int calcPasajeros (Vuelo[][] cronograma, int i, int j, int auxPasaj){
        int cantPasaj = 0;
        if (i>=cronograma.length){
            cantPasaj = auxPasaj;
        }else{
            if (j>=cronograma[0].length){
                cantPasaj = calcPasajeros(cronograma, i+1, 0, auxPasaj);
            }else{
                if (cronograma[i][j]!=null && cronograma[i][j].getAterrizo()){
                    auxPasaj += cronograma[i][j].getCantAsientos();
                }
                cantPasaj = calcPasajeros(cronograma, i, j+1, auxPasaj);
            }
        }
        return cantPasaj;
    }
    private static int calcularPromedio(Vuelo[][] cronograma, int i, int j, int contador) {
        int promedio = 0;
        if (i >= cronograma.length){
            promedio = contador;
        }else{
            if (j >= cronograma[0].length){
                promedio = calcularPromedio(cronograma, i+1, 0, contador);
            }else{
                if (cronograma[i][j] != null && cronograma[i][j].getAterrizo()){
                    contador++;
                }
                promedio = calcularPromedio(cronograma, i, j + 1, contador);
            }
        }
        return promedio;
    }
    //PUNTO6: Mostrar para un día dado, la lista de vuelos ordenada por distancia en kilómetros en forma ascendente en un archivo de texto.
    public static Vuelo[] listVuelos (Vuelo[][] cronograma, String dia){
        int fila = stringADia(dia);
        int cantidadVuelos = 0;
        for (int i=0;i<cronograma[0].length;i++){
            if (cronograma[fila][i]!=null){
                cantidadVuelos++;
            }
        }
        Vuelo[]vuelosArreglados = new Vuelo[cantidadVuelos];
        int aux=0;
        for (int j=0;j<cronograma[0].length;j++){
            if (cronograma[fila][j]!=null){
                vuelosArreglados[aux]=cronograma[fila][j];
                aux++;
            }
        }
        return vuelosArreglados;
    }
    //UTILICE INSERCION YA QUE SE TRATA DE UN ARREGLO CORTO (NO MAS DE 14 ELEMENTOS)
    public static void ordenadoInsercion(Vuelo[] nuevosVuelos){
        int j,i,n;
        Vuelo aux;
        n = nuevosVuelos.length;
        for (i=1;i<n;i++){
            aux=nuevosVuelos[i];
            j=i;
            while(j>0 && aux.getDistVuelo()<nuevosVuelos[j-1].getDistVuelo()){
                nuevosVuelos[j]=nuevosVuelos[j-1];
                j--;
            }
            nuevosVuelos[j]=aux;
        }
    }
    //ORDENADO HEAPSORT (EL QUE TOCO DESPUES DE HACER LA OPERACION)
    public static void ordenadoHeapSort (Vuelo[] nuevVuelos){
        int n = nuevVuelos.length;
        for (int i=n/2-1;i>=0;i--){
            reacomodarHeap(nuevVuelos,n,i);
        }
        //almacenamos elementos del arreglo
        for (int i=n-1;i>0;i--){
            Vuelo aux = nuevVuelos[0];
            nuevVuelos[0]=nuevVuelos[i];
            nuevVuelos[i]=aux;
            reacomodarHeap(nuevVuelos, i, 0);
        }
    }
    //Metodo exclusivo del HeapSort
    private static void reacomodarHeap (Vuelo[] nuevVuelos, int n, int i){
        int mayor = i;
        int izq = 2*i+1;
        int der = 2*i+2;
        if (izq<n&&nuevVuelos[izq].getDistVuelo()>nuevVuelos[mayor].getDistVuelo()){
            mayor = izq;
        }
        if (der<n&&nuevVuelos[der].getDistVuelo()>nuevVuelos[mayor].getDistVuelo()){
            mayor = der;
        }
        if (mayor!=i){
            Vuelo aux = nuevVuelos[i];
            nuevVuelos[i]=nuevVuelos[mayor];
            nuevVuelos[mayor]=aux;
            reacomodarHeap(nuevVuelos, n, mayor);
        }
    }
    //Creo el archivo de texto
    public static void exporteResultados(Vuelo[] listVuelos){
        Vuelo[] listaInsercion = listVuelos;
        Vuelo[] listaHeapSort = listVuelos;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.txt"))){
            //Uso un medidor de tiempo de ejecucion
            long startTime = System.nanoTime();
            ordenadoInsercion(listaInsercion);
            long endTime = System.nanoTime();
            long durationInsercion = endTime - startTime;

            writer.write("Ordenamiento por Insercion:\n");
            writer.write("Tiempo de ejecucion: " +durationInsercion+" nanosegundos"+"\n");
            writer.write("Vuelos ordenados por distancia:\n");
            for (int i=0; i<listaInsercion.length;i++){
                writer.write(listaInsercion[i].toString()+"\n");
            }
            long startTime2 = System.nanoTime();
            ordenadoHeapSort(listaHeapSort);
            long endTime2 = System.nanoTime();
            long durationHeapSort = endTime2-startTime2;
            writer.write("Ordenamiento por HeapSort:\n");
            writer.write("Tiempo de ejecucion: "+durationHeapSort+" nanosegundos"+"\n");
            for (int j=0; j<listaHeapSort.length; j++){
                writer.write(listaHeapSort[j].toString()+"\n");
            }
            writer.close();
        } catch(IOException e){
            System.out.println("Error al escribir en el archivo: "+e.getMessage());
        }
    }



    //PUNTO 7: Mostrar los datos de un avión dado
    public static void mostrarDatAvion(Avion[] listaAviones){
        String codAvion;
        int i=0;
        boolean encontro = false;
        System.out.println("Ingrese el codigo del avion para ver sus datos: ");
        codAvion = sc.nextLine();
        if (Avion.verifIDAvion(codAvion)){
            while (listaAviones[i]!=null && !encontro && i<=listaAviones.length){
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
    public static void distComprendidas (Vuelo[][]cronogramaVuelos){
        //Genero un arreglo de 50 vuelos auxiliar para no recorrer dos veces la lista de vuelos
        Vuelo[] vuelosFiltrados = new Vuelo[100];
        int dist1,dist2,j;
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
        for (int i=0; i<vuelosComprendidos.length;i++){
            System.out.println(vuelosComprendidos[i] + " ");
        }
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
        while(rutas[i]!=null && i < rutas.length && !encontrado){
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
        while(aviones[i]!=null && i < aviones.length && !encontrado){
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
                dia = "Numero invalido";
                break;
        }
        return dia;
    }
    public static int stringADia(String dia) {
        int num;
        switch (dia.toLowerCase()) { // convierto a minusculas para ignorar como es ingresado
            case "lunes":
                num = 0;
                break;
            case "martes":
                num = 1;
                break;
            case "miercoles":
                num = 2;
                break;
            case "jueves":
                num = 3;
                break;
            case "viernes":
                num = 4;
                break;
            case "sabado":
                num = 5;
                break;
            case "domingo":
                num = 6;
                break;
            default:
                num = -1; // nunca sucedera
                break;
        }
        return num;
    }
    public static int convertirHora(String hora) {
        return Integer.parseInt(hora.split(":")[0])-8;
    }
    public static void soutVuelos(Vuelo[][] unvuelo){
        for (int i=0; i<unvuelo.length;i++){
            for (int j=0; j<unvuelo[0].length;j++){
                System.out.println(unvuelo[i][j]);
            }
        }
    }
}


