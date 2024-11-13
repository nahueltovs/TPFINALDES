package TDA;

public class Avion {
    private String idAvion;
    private String modAvion;
    private int cantVuelos;
    private int cantAsientos;
    private int kmRecorridos;

    // CONSTRUCTORES
    public Avion(String unIDavion) {
        this.idAvion = unIDavion;
        this.modAvion = "";
        this.cantVuelos = 0;
        this.cantAsientos = 0;
        this.kmRecorridos = 0;
    }

    public Avion(String unIDavion, String unMODavion, int unaCantVuelos, int unaCantAsien, int unaCantKM) {
        this.idAvion = unIDavion;
        this.modAvion = unMODavion;
        this.cantVuelos = unaCantVuelos;
        this.cantAsientos = unaCantAsien;
        this.kmRecorridos = unaCantKM;
    }

    // OBSERVADORES
    public String getIDavion(){
        return this.idAvion;
    }
    public String getMODavion(){
        return this.modAvion;
    }
    public int getVuelos(){
        return this.cantVuelos;
    }
    public int getAsientos(){
        return this.cantAsientos;
    }
    public int getKMrecor(){
        return this.kmRecorridos;
    }
    public boolean equals (Avion unAvion){
        return this.idAvion==unAvion.getIDavion();
    }
    public String toString (){
        return "ID avion: " +this.idAvion + " Modelo: " +this.modAvion + " Cantidad de vuelos: " +this.cantVuelos + 
        " Cantidad de asientos: " +this.cantAsientos + " KM recorridos: " +this.kmRecorridos;
    }

    //MODIFICADORES
    public void setModelo(String unModelo){
        this.modAvion = unModelo;
    }
    public void setVuelos(int unaCantVuelos){
        this.cantVuelos += unaCantVuelos;
    }
    public void setAsientos(int unaCantAsien){
        this.cantAsientos = unaCantAsien;
    }
    public void setKMrecor(int unaCantKM){
        this.kmRecorridos += unaCantKM;
    }
}
