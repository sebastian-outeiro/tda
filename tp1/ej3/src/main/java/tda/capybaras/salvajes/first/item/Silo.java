package tda.capybaras.salvajes.first.item;

public class Silo implements Item {
    private static Integer COUNTER = 0;
    private final String id;

    public static Silo create() {
        COUNTER++;
        return new Silo(COUNTER);
    }

    private Silo(Integer id) {
        this.id = "S" + id;
    }

    @Override
    public String print() {
        return id;
    }
}
