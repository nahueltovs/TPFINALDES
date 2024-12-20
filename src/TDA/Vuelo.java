package TDA;

public class Vuelo {
    private String codVuelo;
    private Avion avionVuelo;
    private Ruta rutaVuelo;
    private String semDia;
    private String horaDia;
    private boolean avionAterrizo;

    //CONSTRUCTORES
    public Vuelo (String unVuelo){
        this.codVuelo = unVuelo;
        this.avionVuelo = null;
        this.rutaVuelo = null;
        this.semDia = "";
        this.horaDia = "";
        this.avionAterrizo = false;
    }
    public Vuelo (String unVuelo, Avion unAvion, Ruta unaRuta, String diaSemana, String unaHora){
        this.codVuelo = unVuelo;
        this.avionVuelo = unAvion;
        this.rutaVuelo = unaRuta;
        this.semDia = diaSemana;
        this.horaDia = unaHora;
        this.avionAterrizo = false;
    }

    //VISUALIZADORES
    public String getVuelo(){
        return this.codVuelo;
    }
    public Avion getAvion(){
        return this.avionVuelo;
    }
    public Ruta getRuta(){
        return this.rutaVuelo;
    }
    public String getDia(){
        return this.semDia;
    }
    public String getHora(){
        return this.horaDia;
    }
    public boolean getAterrizo(){
        return this.avionAterrizo;
    }
    public boolean equals(Vuelo unVuelo){
        return this.codVuelo.equals(unVuelo.getVuelo());
    }
    public String toString(){
        return "Codigo vuelo: "+this.codVuelo+" Codigo avion: "+this.avionVuelo.getIDavion()+" Numero ruta: " +this.rutaVuelo.getNumRuta()+
        " Dia: "+this.semDia+" Hora: "+this.horaDia;
    }

    //MODIFICADORES
    public void setVuelo(String unVuelo){
        this.codVuelo = unVuelo;
    }
    public void setAvion (Avion unAvion){
       this.avionVuelo = unAvion;
    }
    public void setRuta (Ruta unaRuta){
        this.rutaVuelo = unaRuta;
    }
    public void setDia(String unDia){
        this.semDia = unDia;
    }
    public void setHora(String unaHora){
        this.horaDia = unaHora;
    }
    public void setAterrizo(boolean unaOrden){
        this.avionAterrizo = unaOrden;
    }

    //Propias del tipo
    public int getDistVuelo(){
        return this.rutaVuelo.getDist();
    }
    public String getInternacionalVuelo(){
        return this.rutaVuelo.getInter();
    }
    public int getCantAsientos(){
        return this.avionVuelo.getAsientos();
    }
}
