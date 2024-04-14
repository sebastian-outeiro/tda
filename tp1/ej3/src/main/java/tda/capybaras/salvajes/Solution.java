package tda.capybaras.salvajes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public Field solve(Integer size, List<Position> silos) {
        Field emptyField = new Field(silos);
        List<Field> allFields = createFields(emptyField, new Position(0, 0), new Position(size - 1, size - 1));
        return allFields.stream().max(Comparator.comparingInt(Field::getRegions)).orElse(emptyField);
    }

    private List<Field> createFields(Field baseField, Position init, Position end) {
        Integer totalSize = sizeBetween(init, end);
        if (totalSize == 4)
            return createBasicResponse(baseField, init, end);
        Position firstEnd, secondInit;
        if (end.x() - init.x() > end.y() - init.y()){
            Integer x = (end.x() - init.x() + 1) / 2 + init.x();
            firstEnd = new Position(x - 1, end.y());
            secondInit = new Position(x, init.y());
        } else {
            Integer y = (end.y() - init.y() + 1) / 2 + init.y();
            firstEnd = new Position(end.x(), y - 1);
            secondInit = new Position(init.x(), y);
        }
        List<Field> first = createFields(baseField, init, firstEnd);
        List<Field> second = createFields(baseField, secondInit, end);

        return this.join(first, second, totalSize, Position.min(firstEnd, secondInit), Position.max(firstEnd, secondInit));
    }

    private List<Field> join(List<Field> firstHalf,
                             List<Field> secondHalf,
                             Integer totalSize,
                             Position midStart,
                             Position midEnd) {
        List<Field> result = new ArrayList<>();
        for (Field first: firstHalf){
            for (Field second: secondHalf){
                Field newField = first.plus(second);
                Integer maxRegions = (totalSize - newField.getSilos()) / 3;
                if(newField.getRegions().equals(maxRegions))
                    result.add(newField);
                else
                    result.addAll(this.joinCompletingEmptySpaces(newField, midStart, midEnd));
            }
        }
        return result;
    }

    private List<Field> joinCompletingEmptySpaces(Field newField, Position midStart, Position midEnd) {
        List<Field> results = new ArrayList<>();
        Position next = new Position(0, 1);
        Position zone = new Position(1,1);
        if (midEnd.x() - midStart.x() != 1)
            next = new Position(1,0);
        Position actualInit = midStart;
        Position actualEnd;
        do {
            actualEnd = actualInit.plus(zone);
            List<List<Position>> newRegions = createBasicRegions(actualInit, actualEnd);
            newRegions.forEach(region -> {
                if(newField.isRegionFree(region))
                    results.add(newField.copyAdding(region));
            });
            actualInit = actualInit.plus(next);
        } while (!actualEnd.equals(midEnd));
        return results;
    }
/**
    private List<Field> joinCompletingEmptySpaces(Field newField, Position midStart, Position midEnd) {
        List<Field> results = new ArrayList<>();
        Position next = new Position(0, 1);
        Position against = new Position(1, 0);
        if (midEnd.x() - midStart.x() != 1) {
            Position aux = next;
            next = against;
            against = aux;
        }
        Position last = midEnd.minus(against).plus(next);
        Position actual = midStart;
        boolean againstFree, beforeFree = false;
        while(!actual.equals(last)){
            if(newField.isPositionFree(actual)){
                beforeFree = true;
                Position localAgainst = actual.plus(against);
                if(newField.isPositionFree(localAgainst)){
                    againstFree = true;
                    Position beforeAgainst = localAgainst.minus(next);
                    if(!actual.equals(midStart) && newField.isPositionFree(beforeAgainst)){
                        results.add(newField.copyAdding(List.of(beforeAgainst, localAgainst, actual)));
                    }
                } else
                    againstFree = false;
            } else {
                beforeFree = false;
                againstFree = false;
            }

            actual = actual.plus(next);
        }

    }
**/
    private List<Field> createBasicResponse(Field baseField, Position init, Position end) {
        List<List<Position>> newRegions = createBasicRegions(init, end);
        return newRegions.stream()
                .filter(baseField::isRegionFree)
                .map(baseField::copyAdding)
                .toList();
    }

    private static List<List<Position>> createBasicRegions(Position init, Position end) {
        Position plusX = init.plus(1, 0);
        Position plusY = init.plus(0, 1);
        return List.of(
                List.of(init, plusX, plusY),
                List.of(init, plusX, end),
                List.of(init, plusY, end),
                List.of(plusX, plusY, end)
        );
    }

    private Integer sizeBetween(Position init, Position end) {
        return (end.x() - init.x() + 1) * (end.y() - init.y() + 1);
    }


    /**
    private List<Field> createMidRegions(Field newField, Position init, Position end) {
        Position delta;
        Integer points;
        if(init.x() < init.y()) {
            delta = new Position(1, 0);
            points = end.x() - init.x() + 1;
        } else {
            delta = new Position(0, 1);
            points = end.y() - init.y() + 1;
        }
        Position actual = init;
        for (int i = 0; i < points; i++){
            if(newField.isPositionFree(actual) && newField.isPositionFree(actual.plus())){

            } else
                actual = actual.plus(delta);
        }


        for(int x = init.x(); x < end.x(); x++){
            for (int y = init.y(); y < end.y(); y++){

            }
        }
    }
     **/

}
