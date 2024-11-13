package TDA;

public class Vuelo {
    private String codVuelo;
    private Avion codAvion;
    private Ruta numRuta;
    private String semDia;
    private String horaDia;
    private Avion[] aviones;
    private Ruta[] rutas;

    //CONSTRUCTORES
    public Vuelo (String unVuelo){
        this.codVuelo = unVuelo;
        this.codAvion = null;
        this.numRuta = null;
        this.semDia = "";
        this.horaDia = "";
        this.aviones = new Avion[20];
        this.rutas = new Ruta[20];
    }

    //VISUALIZADORES
    public String getVuelo(){
        return this.codVuelo;
    }
    public Avion getAvion(){
        return this.codAvion;
    }
    public Ruta getRuta(){
        return this.numRuta;
    }
    public String getDia(){
        return this.semDia;
    }
    public String getHora(){
        return this.horaDia;
    }

    //MODIFICADORES
    public void setVuelo(String unVuelo){
        this.codVuelo = unVuelo;
    }
    public void setAvion (Avion unAvion){
        boolean existe = false;
        int i = 0;
        while (!existe && i<aviones.length){
            if(unAvion.equals(aviones[i])){
                existe = true;
                this.codAvion = unAvion;
            }
            i++;
        }
        if (!existe){
            System.out.println("El avion ingresado no existe");
        }
    }
    public void setRuta (Ruta unaRuta){
        boolean existe = false;
        int i = 0;
        while (!existe && i<rutas.length){
            if (unaRuta.equals(rutas[i])){
                existe = true;
                this.numRuta = unaRuta;
            }
            i++;
        }
        if (!existe){
            System.out.println("La ruta ingresada no existe");
        }
    }
    public void setDia(String unDia){
        this.semDia = unDia;
    }
    public void setHora(String unaHora){
        this.horaDia = unaHora;
    }
}
