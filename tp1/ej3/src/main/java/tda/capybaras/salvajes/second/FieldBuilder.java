package tda.capybaras.salvajes.second;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FieldBuilder {

    private final Integer size;
    Set<Position> allSilos;

    public FieldBuilder(Set<Position> allSilos, Integer size) {
        this.allSilos = allSilos;
        this.size = size;
    }

    public Field solve() {
        List<Field> fields = buildFields(Position.of(0, 0), Position.of(size - 1, size - 1));
        return fields.get(0);
    }

    List<Field> buildFields(Position start, Position end) {
        if (Position.sizeBetween(start, end) == 4)
            return buildBasicField(start, end);
        return divideAndBuildFields(start, end);
    }

    private List<Field> divideAndBuildFields(Position start, Position end) {
        Position delta = end.minus(start);
        if (delta.x() > delta.y())
            delta = Position.of(delta.x() / 2, delta.y());
        else
            delta = Position.of(delta.x(), delta.y() / 2);
        List<Field> first = buildFields(start, start.plus(delta));
        List<Field> second = buildFields(end.minus(delta), end);
        return join(first, second);
    }

    private List<Field> join(List<Field> firstHalf, List<Field> secondHalf) {
        boolean reachMaxRegions = false;
        List<Field> results = new ArrayList<>();
        for (Field first : firstHalf)
            for (Field second : secondHalf) {
                Field newField = first.plus(second);
                if (reachMaxRegions || newField.hasReachMaxRegions()){
                    results.add(newField);
                    reachMaxRegions = true;
                }
                else
                    results.addAll(this.reduceEmptySpaces(newField, first.end.min(second.start), first.end.max(second.start)));
            }
        return results;
    }

    private List<Field> reduceEmptySpaces(Field newField, Position start, Position end) {
        List<Field> results = new ArrayList<>();
        Position increment = (end.minus(start).x() != 1) ? Position.of(1, 0) : Position.of(0, 1);
        Position block = Position.of(1, 1);
        Position actual = start;
        Position loopEnd = end.minus(block).plus(increment);
        do {
            List<Position> positions = buildBasicBlockPositions(actual, actual.plus(block));
            results.addAll(buildBasicRegions(positions)
                    .stream()
                    .filter(newField::isRegionFree)
                    .map(newField::copyWith)
                    .toList());
            actual = actual.plus(increment);
        } while (!actual.equals(loopEnd));

        return results;
    }

    private List<Field> buildBasicField(Position start, Position end) {
        List<Position> positions = buildBasicBlockPositions(start, end);
        List<Position> localSilos = positions
                .stream()
                .filter(allSilos::contains)
                .collect(Collectors.toList());
        return buildBasicRegions(positions, localSilos).stream()
                .map(region -> new Field(start, end, region, localSilos))
                .toList();
    }

    private List<Position> buildBasicBlockPositions(Position start, Position end) {
        Position right = start.plus(Position.of(1, 0));
        Position down = start.plus(Position.of(0, 1));
        return List.of(start, end, right, down);
    }

    private List<Region> buildBasicRegions(List<Position> positions, Collection<Position> localSilos) {
        if (localSilos.size() > 2)
            return Collections.emptyList();
        return localSilos.stream().findFirst()
                .map(position -> buildRegionFiltering(positions, position))
                .orElse(buildBasicRegions(positions));
    }

    private List<Region> buildRegionFiltering(List<Position> positions, Position positionToFilter) {
        return List.of(Region.of(positions.stream().filter(pos -> pos != positionToFilter)));
    }

    private List<Region> buildBasicRegions(List<Position> p) {
        return List.of(Region.of(p.get(0), p.get(1), p.get(2)),
                Region.of(p.get(0), p.get(1), p.get(3)),
                Region.of(p.get(0), p.get(2), p.get(3)),
                Region.of(p.get(1), p.get(2), p.get(3)));
    }

}
