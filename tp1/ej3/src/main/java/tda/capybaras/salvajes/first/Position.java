package tda.capybaras.salvajes.first;

public record Position(Integer x, Integer y) {
    public static Position min(Position first, Position second) {
        return new Position(Integer.min(first.x(), second.x()),
                Integer.min(first.y(), second.y()));
    }

    public static Position max(Position first, Position second) {
        return new Position(Integer.max(first.x(), second.x()),
                Integer.max(first.y(), second.y()));
    }

    public Position plus(Integer x, Integer y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position plus(Position delta) {
        return this.plus(delta.x, delta.y);
    }

    public Position minus(Position op) {
        return new Position(x - op.x, y - op.y);
    }
}