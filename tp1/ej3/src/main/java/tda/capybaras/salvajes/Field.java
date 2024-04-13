package tda.capybaras.salvajes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Field {

    private final Set<Position> positionsOccupy = new HashSet<>();
    private final List<Region> regions = new ArrayList<>();

    public Field(List<Position> silos) {
        positionsOccupy.addAll(silos);
    }

    private Field(Set<Position> positionsOccupy, List<Region> regions) {
        this.positionsOccupy.addAll(positionsOccupy);
        this.regions.addAll(regions);
    }

    public boolean isRegionFree(Region region) {
        return region.positions().stream().noneMatch(positionsOccupy::contains);
    }

    public Integer amountOfRegions() {
        return regions.size();
    }

    public List<Region> getRegions() {
        return regions;
    }

    public Field copyAddingRegion(Region newRegion){
        Field newField = new Field(positionsOccupy, regions);
        newField.addRegion(newRegion);
        return newField;
    }

    public Field sum(Field otherField){
        Field newField = new Field(positionsOccupy, regions);
        newField.addRegions(otherField.regions);
        return newField;
    }

    private void addRegion(Region region){
        this.positionsOccupy.addAll(region.positions());
        this.regions.add(region);
    }

    private void addRegions(List<Region> newRegions){
        newRegions.forEach(this::addRegion);
    }
}
