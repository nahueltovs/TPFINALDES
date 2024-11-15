package TDA;

public class Vuelo {
    private String codVuelo;
    private Avion avionVuelo;
    private Ruta rutaVuelo;
    private String semDia;
    private String horaDia;

    //CONSTRUCTORES
    public Vuelo (String unVuelo){
        this.codVuelo = unVuelo;
        this.avionVuelo = null;
        this.rutaVuelo = null;
        this.semDia = "";
        this.horaDia = "";
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
    public boolean equals(Vuelo unVuelo){
        return this.codVuelo==unVuelo.getVuelo();
    }
    public String toString(){
        return "Codigo vuelo: "+this.codVuelo+" En el avion con codigo: "+this.avionVuelo.getIDavion()+" Con ruta: " +this.rutaVuelo.getNumRuta()+
        " El dia: "+this.semDia+" A la hora: "+this.horaDia;
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

    //Propias del tipo
    public int getDistVuelo(){
        return this.rutaVuelo.getDist();
    }
}
