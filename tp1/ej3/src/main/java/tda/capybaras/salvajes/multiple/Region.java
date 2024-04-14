package tda.capybaras.salvajes.multiple;

import java.util.List;
import java.util.stream.Stream;

public class Region {

    private static Integer NEXT = 0;
    private final List<Position> positions;
    private Integer id = null;

    public static Region of(Position... positions){
        return new Region(positions);
    }
    public static Region of(Stream<Position> positions){
        return new Region(positions.toList());
    }
    public Region(Position... positions) {
        this.positions = List.of(positions);
    }
    public Region(List<Position> positions) {
        this.positions = positions;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Integer getId() {
        setID();
        return id;
    }

    private void setID(){
        if(id != null) return;
        NEXT++;
        id = NEXT;
    }
}
