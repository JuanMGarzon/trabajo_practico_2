package ar.edu.unju.fi.ejercicio3.main;
import ar.edu.unju.fi.ejercicio3.constantes.Provincia;

public class Main {
    public static void main(String[] args) {
    	
        Provincia[] provincias = Provincia.values();

        for (Provincia provincia : provincias) {
            System.out.println("Provincia: " + provincia);
            System.out.println("Cantidad de población: " + provincia.getPoblacion());
            System.out.println("Superficie: " + provincia.getSuperficie() + " km²");
            System.out.println("Densidad poblacional: " + provincia.calcularDensidadPoblacional() + " habitantes/km²");
            System.out.println();
        }
    }
}
