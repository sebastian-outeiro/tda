package tda.capybaras.salvajes.second;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

public record Position(Integer x, Integer y) {
    public static Position of(Integer x, Integer y) {
        return new Position(x, y);
    }

    public static Integer sizeBetween(Position min, Position max){
        return max.minus(min).plus(Position.of(1,1)).size();
    }

    public Integer size() {
        return x * y;
    }

    public Position minus(Position p) {
        return plus(Position.of(-p.x, -p.y));
    }


    public Position plus(Position p) {
        return exec(p, Integer::sum);
    }

    public Position min(Position p){
        return exec(p, Integer::min);
    }

    public Position max(Position p){
        return exec(p, Integer::max);
    }

    private Position exec(Position p, BiFunction<Integer, Integer, Integer> function) {
        return new Position(function.apply(x, p.x), function.apply(y, p.y));
    }
}
