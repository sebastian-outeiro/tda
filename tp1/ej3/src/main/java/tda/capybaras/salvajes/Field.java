package tda.capybaras.salvajes;

import tda.capybaras.salvajes.item.Item;
import tda.capybaras.salvajes.item.Region;
import tda.capybaras.salvajes.item.Silo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Field {

    private Map<Position, Region> regions = new HashMap<>();
    private Map<Position, Silo> silos = new HashMap<>();

    public Field(List<Position> silos) {
        silos.forEach(this::addSilo);
    }

    private Field(Map<Position, Region> regions, Map<Position, Silo> silos){
        this.silos = silos;
        this.regions = regions;
    }

    public boolean isRegionFree(List<Position> region) {
        return region.stream().allMatch(this::isPositionFree);
    }

    public boolean isPositionFree(Position position) {
        return !(regions.containsKey(position) || silos.containsKey(position));
    }

    private void addSilo(Position position) {
        this.silos.put(position, Silo.create());
    }

    private void addRegion(List<Position> positions){
        if(positions.stream().noneMatch(this::isPositionFree))
            return;
        Region region = Region.create();
        positions.forEach(position -> regions.put(position, region));
    }

    public Integer getRegions() {
        return regions.size()/3;
    }

    public Integer getSilos() {
        return silos.size();
    }

    public Field copyAdding(List<Position> region){
        Field field = new Field(new HashMap<>(this.regions), this.silos);
        field.addRegion(region);
        return field;
    }

    public Field plus(Field other) {
        if(!other.regions.keySet().stream().allMatch(this::isPositionFree))
            return this;
        HashMap<Position, Region> newRegions = new HashMap<>(this.regions);
        newRegions.putAll(other.regions);
        return new Field(newRegions, this.silos);
    }

    public String print(Position position) {
        if(regions.containsKey(position))
            return regions.get(position).print();
        else
            if(silos.containsKey(position))
                return silos.get(position).print();
            else
                return "X";
    }
}


/**
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
 **/
