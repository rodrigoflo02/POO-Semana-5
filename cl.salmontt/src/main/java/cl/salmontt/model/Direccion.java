package cl.salmontt.model;

public class Direccion {
    /**
     * SE CREAN LOS ATRIBUTOS CON MODIFICADOR DE ACCESO PRIVADO PARA LA CLASE DIRECCION
     */
    private String region;
    private String ciudad;
    private String comuna;
    private String calle;
    private int numero_casa;

    /**
     * SE IMPLEMENTA METODO CONSTRUCTOR CON ATRIBUTOS QUE RECIBIRA LOS DATOS DE LA CLASE
     * @param region
     * @param ciudad
     * @param comuna
     * @param calle
     * @param numero_casa
     */
    public Direccion(String calle,int numero_casa, String comuna,String ciudad, String region) {
        this.region = region;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.calle = calle;
        this.numero_casa = numero_casa;
    }

    /**
     * SE IMPLMENETA GETTERS Y SETTERS
     * @return
     */
    //REGION
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    //CIUDAD
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    //COMUNA
    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    //CALLE
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    //NUMERO CASA
    public int getNumero_casa() {
        return numero_casa;
    }
    public void setNumero_casa(int numero_casa) {
        this.numero_casa = numero_casa;
    }

    /**
     * A TRAVES DEL METODO ToString se retornara en una CADENA DE TEXTO
     * @return STRING CON LA INFORMACINO CONTENIDA DE LOS ATRIBUTOS
     */

    @Override
    public String toString() {
        return "Direccion: " + calle + " " + numero_casa + ", " + comuna + ", " + ciudad + ", " + "Region " + region;
    }
}
