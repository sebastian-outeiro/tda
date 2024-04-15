package tda.ej3;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("ERROR - Faltan parámetros. [Tamaño, Posición Columna Silo, Posición Fila Silo]");
            exit(1);
        }

        List<Integer> params = Stream.of(args).map(Integer::valueOf).toList();

        Campo campo = new Campo(params.get(0), params.get(1), params.get(2));
        campo.mostrarSilo();


        Agrimensor agrimensor = new Agrimensor(campo);
        agrimensor.divison(0, campo.extension, 0, campo.extension, campo.silo);

        campo.mostrarCampo();
        campo.mostrarCantidadRegiones();
    }
}