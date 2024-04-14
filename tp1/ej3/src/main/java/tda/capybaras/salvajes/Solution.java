package tda.capybaras.salvajes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
        Position midNext;
        if (end.x() - init.x() > end.y() - init.y()){
            firstEnd = new Position(end.x() / 2, end.y());
            secondInit = new Position(end.x() / 2 + 1, init.y());
            midNext = new Position(0,1);
        } else{
            firstEnd = new Position(end.x(), end.y() / 2);
            secondInit = new Position(init.x(),end.y() / 2 + 1);
            midNext = new Position(1,0);
        }
        List<Field> first = createFields(baseField, init, firstEnd);
        List<Field> second = createFields(baseField, secondInit, end);

        return this.join(first, second, totalSize, Position.min(firstEnd, secondInit), firstEnd, midNext);
    }

    private List<Field> join(List<Field> firstHalf,
                             List<Field> secondHalf,
                             Integer totalSize,
                             Position midStart,
                             Position midEnd,
                             Position midNext) {
        List<Field> result = new ArrayList<>();
        for (Field first: firstHalf){
            for (Field second: secondHalf){
                Field newField = first.plus(second);
                Integer maxRegions = (totalSize - newField.getSilos()) / 3;
                if(newField.getRegions().equals(maxRegions))
                    result.add(newField);
                else
                    result.addAll(this.joinCompletingEmptySpaces(newField, midStart, midEnd, midNext));
            }
        }
        return result;
    }

    private List<Field> joinCompletingEmptySpaces(Field newField, Position midStart, Position midEnd, Position midNext) {
        boolean beforeFree = false;
        boolean downBeforeFree = false;
        boolean end = false;
        Position actual = midStart;
        while(!end){
            if(newField.isPositionFree(actual)){

            }
        }
    }

    private List<Field> createBasicResponse(Field baseField, Position init, Position end) {
        Position plusX = init.plus(1, 0);
        Position plusY = init.plus(0, 1);
        List<List<Position>> newRegions = List.of(
                List.of(init, plusX, plusY),
                List.of(init, plusX, end),
                List.of(init, plusY, end),
                List.of(plusX, plusY, end)
        );
        return newRegions.stream()
                .filter(baseField::isRegionFree)
                .map(baseField::copyAdding)
                .toList();
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
