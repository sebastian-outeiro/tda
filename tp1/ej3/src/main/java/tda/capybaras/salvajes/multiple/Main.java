package tda.capybaras.salvajes.multiple;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Position> silos = Set.of(Position.of(1, 2), Position.of(4,1));
        FieldBuilder builder = new FieldBuilder(silos, 8);
        builder.solve().print();
    }
}

