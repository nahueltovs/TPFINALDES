import TDA.Avion;
import TDA.Ruta;
import TDA.Vuelo;

public class TpFinal {
    public static void main(String[] args) throws Exception {
        Avion[] aviones = new Avion[50];
        Ruta[] rutas = new Ruta[50];
        Vuelo[][] cronoVuelos= new Vuelo[7][15];
        Cargar.cargarArchivos(aviones, rutas, cronoVuelos);
        System.out.println("Cargando Menu...");
        Opciones.procOpciones(cronoVuelos, aviones, rutas);
    }

}
