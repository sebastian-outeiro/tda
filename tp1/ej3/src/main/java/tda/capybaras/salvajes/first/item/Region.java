package tda.capybaras.salvajes.first.item;

public class Region implements Item {
    private static Integer COUNTER = 0;
    private final String id;

    public static Region create() {
        COUNTER++;
        return new Region(COUNTER);
    }

    private Region(Integer id) {
        this.id = "R" + id;
    }

    @Override
    public String print() {
        return id;
    }
}
