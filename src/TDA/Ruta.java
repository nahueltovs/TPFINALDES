package TDA;

public class Ruta {
    private String numRuta;
    private String ciudadOri;
    private String ciudadDesti;
    private int distKM;
    private String interNacional;

    //CONSTRUCTORES
    public Ruta (String unNumRuta){
        this.numRuta =unNumRuta;
        this.ciudadOri = "";
        this.ciudadDesti="";
        this.distKM= 0;
        this.interNacional = "";
    }
    public Ruta (String unNumRuta, String unOrigen, String unDestino,
    int unaDist, String esInternacional){
        this.numRuta = unNumRuta;
        this.ciudadOri = unOrigen;
        this.ciudadDesti = unDestino;
        this.distKM = unaDist;
        this.interNacional = esInternacional;
    }

    //OBSERVADORES
    public String getNumRuta(){
        return this.numRuta;
    }
    public String getOrigen(){
        return this.ciudadOri;
    }
    public String getDestino(){
        return this.ciudadDesti;
    }
    public int getDist(){
        return this.distKM;
    }
    public String getInter(){
        return this.interNacional;
    }
    public boolean equals(Ruta unaRuta){
        return this.numRuta.equals(unaRuta.getNumRuta());
    }
    public String toString(){
        return "Número ruta: "+this.numRuta+" Ciudad origen: "+this.ciudadOri+
        " Ciudad destino: "+this.ciudadDesti+" Distancia en KM: "+this.distKM+" Es internacional: "+this.interNacional;
    }

    //MODIFICADORES 
    public void setOrigen(String unOrigen){
        this.ciudadOri = unOrigen;
    }
    public void setDestino(String unDestino){
        this.ciudadDesti = unDestino;
    }
    public void setDistancia(int unaDist){
        this.distKM=unaDist;
    }
    public void setInternacional(String esInter){
        this.interNacional = esInter;
    }
    
}
