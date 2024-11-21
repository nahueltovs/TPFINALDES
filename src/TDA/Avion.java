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
        return this.idAvion.equals(unAvion.getIDavion());
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
        if (unaCantVuelos >=0){
            this.cantVuelos += unaCantVuelos;
        }
    }
    public void setAsientos(int unaCantAsien){
        this.cantAsientos = unaCantAsien;
    }
    public void setKMrecor(int unaCantKM){
        if (unaCantKM >=0){
            this.kmRecorridos += unaCantKM;
        }
    }

    //PROPIAS DEL TIPO
    public static boolean verifIDAvion(String unaPatente){
        String[] patenteArr = unaPatente.split("-");
        boolean res = false;
        // Expresion regular verifica que el string este formado por 3 letras
        String regLetras = "[A-Z]{3}";
        // Expresion regular verifica que el string contenga 3 numeros seguidos
        String regNumeros = "[0-9]{3}";

        // Primer caso verifica que empiece con "LV" si no con LQ, en caso contrario res seguria siendo falso.
        if(patenteArr[0].equalsIgnoreCase("LV")) {
            if(patenteArr[1].matches(regLetras)) {
                res = true;
            }else if(patenteArr[1].startsWith("X") ||patenteArr[1].startsWith("S") || patenteArr[1].startsWith("SX") && patenteArr[1].substring(1).matches(regNumeros)){
                res = true;
            }
        }else if(patenteArr[0].equalsIgnoreCase("LQ") && patenteArr[1].matches(regLetras)){
            res = true;
        }
        return res;
    }
}
