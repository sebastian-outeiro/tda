package tda.capybaras.salvajes.second;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Region {
    private final List<Position> positions;

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
}
