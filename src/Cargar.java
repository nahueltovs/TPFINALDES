import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import TDA.Avion;
import TDA.Ruta;
import TDA.Vuelo;


public class Cargar {
    
    // Esto genera una ventana para seleccionar el archivo
    private final static JFileChooser elegir = new JFileChooser();
    private final static FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de texto", "txt");
    
    // Cambia la direccion inicial para facilitar la carga
    static {
      elegir.addChoosableFileFilter(filtro);
      elegir.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }
    
    public static void cargarArchivos(Avion[] aviones, Ruta[] rutas, Vuelo[][] vuelos){
      // En el caso de que se cargue el archivo se ejecuta la sentencia.
      aviones = cargarAviones(aviones);
      rutas = cargarRutas(rutas);
      vuelos = cargarVuelos(aviones, rutas, vuelos);
      for (int i = 0; i < vuelos.length; i++) {
        for (int j = 0; j < vuelos[i].length; j++) {
            if (vuelos[i][j] != null) {
                System.out.println("Dia " + i + ", Hora " + (j + 8) + ":00 --> " + vuelos[i][j]);
            }
        }
    }
  }
    public static Avion[] cargarAviones(Avion[] aviones){
      // O sea digamos, al utilizar diferentes sistemas operativos, no trabajan las direcciones de la misma manera
      // asi que esta forma que logro implementar aca, es realmente mas util a la hora de seleccionar los archives y no genere algun tipo de error en el imput.
      // En este caso, se selecciona el archivo de aviones.txt
      
      try {
        System.out.println("Seleccionar archivo de aviones: ");
        int returnVal = elegir.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
          File archivo = elegir.getSelectedFile();
          System.out.println("Se selecciono: " + archivo.getName());
          if(!archivo.getName().endsWith(".txt") || !archivo.getName().equalsIgnoreCase("aviones.txt")){
            throw new IOException("El archivo seleccionado no es aviones.txt");
          }else {
            int cantidadAviones = Files.readAllLines(archivo.toPath()).size();
            for(int i = 0; i < cantidadAviones; i++){
              String[] datos = Files.readAllLines(archivo.toPath()).get(i).split(";");
              //VALIDO QUE LA LINEA NO ESTE VACIA
              if(datos != null){
                aviones[i] = new Avion(datos[0], datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Integer.parseInt(datos[4]));
              }
            }
          }
        }else{
          throw new IOException("No se selecciono ningun archivo");
        }
      } catch (IOException error) {
        System.out.println("Error al cargar el archivo de aviones" + error);
      }
      return aviones;
    }

    public static Ruta[] cargarRutas(Ruta[] rutas){
        try {
            System.out.println("Seleccionar archivo de rutas: ");
            int returnVal = elegir.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
              File archivo = elegir.getSelectedFile();
              System.out.println("Se selecciono: " + archivo.getName());
              if(!archivo.getName().endsWith(".txt") || !archivo.getName().equalsIgnoreCase("rutas.txt")){
                throw new IOException("El archivo seleccionado no es rutas.txt");
              }else {
                int cantidadRutas = Files.readAllLines(archivo.toPath()).size();
                for(int i = 0; i < cantidadRutas; i++){
                  String[] datos = Files.readAllLines(archivo.toPath()).get(i).split(";");
                  //VALIDO QUE LA LINEA NO ESTE VACIA
                  if(datos != null){
                    rutas[i] = new Ruta(datos[0], datos[1], datos[2],Integer.parseInt(datos[3]),datos[4]);
                  }
                }
              }
            }else{
              throw new IOException("No se selecciono ningun archivo");
            }
          } catch (IOException error) {
            System.out.println("Error al cargar el archivo de rutas" + error);
        }
        return rutas;
    }

    public static Vuelo[][] cargarVuelos(Avion[] aviones, Ruta[] rutas, Vuelo[][] vuelos){
        try {
            System.out.println("Seleccionar archivo de vuelos: ");
            int returnVal = elegir.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
              File archivo = elegir.getSelectedFile();
              System.out.println("Se selecciono: " + archivo.getName());
              if(!archivo.getName().endsWith(".txt") || !archivo.getName().equalsIgnoreCase("vuelos.txt")){
                throw new IOException("El archivo seleccionado no es vuelos.txt");
              }else  {
                List<String> lineas = Files.readAllLines(archivo.toPath());
                for (String linea : lineas) {
                    String[] datos = linea.split(";");
                    int fila = Opciones.stringADia(datos[3]);
                    int columna = Opciones.convertirHora(datos[4]);
                    int posAvion = Opciones.buscarPosAvion(aviones, datos[1]);
                    int posRuta = Opciones.buscarPosRuta(rutas, datos[2]);
                    
                    // Asignar vuelo a la posicion [fila][columna]
                    vuelos[fila][columna] = new Vuelo(datos[0], aviones[posAvion], rutas[posRuta], datos[3], datos[4]);
                    System.out.println("Vuelo cargado en posicion: [" + fila + "][" + columna + "]");
                }
              }
            }else{
              throw new IOException("No se selecciono ningun archivo");
            }
          } catch (IOException error) {
            System.out.println("Error al cargar el archivo de vuelos" + error);
        }
        return vuelos;
    }
    public static void main(String[] args) {
    }
}
