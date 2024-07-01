package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
    JUJUY(770000, 53219),
    SALTA(1200000, 155488),
    TUCUMAN(1600000, 22524),
    CATAMARCA(370000, 102602),
    LA_RIOJA(400000, 89680),
    SANTIAGO_DEL_ESTERO(900000, 136351);

    private int poblacion; 
    private int superficie; 

    
    private Provincia(int poblacion, int superficie) {
        this.poblacion = poblacion;
        this.superficie = superficie;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double calcularDensidadPoblacional() {
        return (double) poblacion / superficie;
    }
}
