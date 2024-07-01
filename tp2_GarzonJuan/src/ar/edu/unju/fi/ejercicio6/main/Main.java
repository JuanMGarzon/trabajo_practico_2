package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {
    public static void main(String[] args) {

        FelinoSalvaje tigre = new FelinoSalvaje("Tanner", (byte)20, 186f);

        // Verificar que el objeto no es nulo
        if (Converter.isNotNull(tigre)) {
          
            Converter<FelinoSalvaje, FelinoDomestico> converter = x -> new FelinoDomestico(x.getNombre(), x.getEdad(), x.getPeso());

            // Conversion
            FelinoDomestico felino2 = converter.convert(tigre);

            converter.mostrarObjeto(felino2);
        }
    }
}
