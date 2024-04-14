package tda.capybaras.salvajes.second;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Field {
    Position start;
    Position end;
    Set<Position> silos = new HashSet<>();
    Map<Position, Region> regions = new HashMap<>();

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

    public void print() {
        System.out.println("Regions: " + regionsSize());
        System.out.print("  ");
        for (int x = start.x(); x < end.x(); x++)
            System.out.print(" | " + x);
        System.out.print(" |");
        System.out.println();
        Integer actualId = 0;
        Map<Region, Integer> ids = new HashMap<>();
        for (int y = start.y(); y < end.y(); y++) {
            System.out.print(y + " ");
            for (int x = start.x(); x < end.x(); x++) {
                Position actual = Position.of(x,y);
                if(regions.containsKey(actual)){
                    Region actualRegion = regions.get(actual);
                    if(!ids.containsKey(actualRegion)){
                        actualId++;
                        ids.put(actualRegion, actualId);
                    }
                    System.out.print(" | R" + ids.get(actualRegion));
                } else if (silos.contains(actual)){
                    System.out.print(" | S  ");
                } else
                    System.out.print(" | X  ");
            }
            System.out.println();
        }
    }
}
