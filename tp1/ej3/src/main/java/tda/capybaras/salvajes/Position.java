package tda.capybaras.salvajes;

public record Position (Integer x, Integer y){
    public Position plus(Integer x, Integer y) {
        return new Position(this.x + x, this.y + y);
    }
}