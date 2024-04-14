package tda.capybaras.salvajes.multiple;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Field {
    private final Position start;
    private final Position end;
    private final Set<Position> silos = new HashSet<>();
    private final Map<Position, Region> regions = new HashMap<>();

    public Field(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Field(Position start, Position end, Region region, List<Position> silos) {
        this.start = start;
        this.end = end;
        this.addRegion(region);
        this.addSilos(silos);
    }

    private void addSilos(Collection<Position> silos) {
        this.silos.addAll(silos);
    }

    private void addRegion(Region region) {
        region.getPositions().forEach(pos -> regions.put(pos, region));
    }

    private void addRegions(Map<Position, Region> regions) {
        this.regions.putAll(regions);
    }

    public Field plus(Field field) {
        Field newField = copyWith(start.min(field.start), end.max(field.end));
        newField.addRegions(field.regions);
        newField.addSilos(field.silos);
        return newField;
    }

    public Integer regionsSize() {
        return regions.size() / 3;
    }


    public boolean hasReachMaxRegions() {
        return (Position.sizeBetween(start, end) - silos.size()) / 3 == regionsSize();
    }

    public boolean isRegionFree(Region region) {
        return region.getPositions().stream().noneMatch(position ->
                regions.containsKey(position) || silos.contains(position)
        );
    }

    public Field copyWith(Region region) {
        Field newField = copyWith(start, end);
        newField.addRegion(region);
        return newField;
    }

    public Field copyWith(Position start, Position end) {
        Field newField = new Field(start, end);
        newField.addRegions(this.regions);
        newField.addSilos(silos);
        return newField;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public void print() {
        System.out.println("Regions: " + regionsSize());
        customPrint("");
        for (int x = start.x(); x <= end.x(); x++)
            customPrint(String.valueOf(x));
        System.out.println();
        for (int y = start.y(); y <= end.y(); y++) {
            customPrint(String.valueOf(y));
            for (int x = start.x(); x <= end.x(); x++) {
                Position actual = Position.of(x,y);
                printPosition(actual);
            }
            System.out.println();
        }
    }

    private void printPosition(Position pos){
        String value = regions.containsKey(pos) ? "R" + regions.get(pos).getId() : silos.contains(pos) ? "S" : "X";
        customPrint(value);
    }

    private void customPrint(String value){
        System.out.printf("%1$5s", value);
    }
}
