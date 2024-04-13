package tda.capybaras.salvajes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public Field solve(Integer size, List<Position> silos) {
        Field emptyField = new Field(silos);
        List<Field> allFields = createFields(emptyField, new Position(0, 0), new Position(size - 1, size - 1));
        return allFields.stream().max(Comparator.comparingInt(Field::amountOfRegions)).orElse(emptyField);
    }

    private List<Field> createFields(Field baseField, Position init, Position end) {
        if (sizeBetween(init, end) == 4)
            return createBasicResponse(baseField, init, end);
        Position firstEnd, secondInit;
        if (end.x() > end.y()){
            firstEnd = new Position(end.x() / 2, end.y());
            secondInit = new Position(end.x() / 2 + 1, init.y());
        } else{
            firstEnd = new Position(end.x(), end.y() / 2);
            secondInit = new Position(init.x(),end.y() / 2 + 1);
        }
        List<Field> first = createFields(baseField, init, firstEnd);
        List<Field> second = createFields(baseField, secondInit, end);

        List<Field> result = new ArrayList<>();
        for (Field firstField: first){
            for (Field secondField: second){
                result.add(firstField.sum(secondField));
            }
        }
        return result;
    }

    private List<Field> createBasicResponse(Field baseField, Position init, Position end) {
        Position plusX = init.plus(1, 0);
        Position plusY = init.plus(0, 1);
        List<Region> newRegions = List.of(
                new Region(List.of(init, plusX, plusY)),
                new Region(List.of(init, plusX, end)),
                new Region(List.of(init, plusY, end)),
                new Region(List.of(plusX, plusY, end))
        );
        return newRegions.stream()
                .filter(baseField::isRegionFree)
                .map(baseField::copyAddingRegion)
                .toList();
    }

    private Integer sizeBetween(Position init, Position end) {
        return (end.x() - init.x() + 1) * (end.y() - init.y() + 1);
    }
}
