package tda;

public class Coordenadas {
    public int x;
    public int y;

    public Coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCol() {
        return x;
    }

    public int getFil() {
        return y;
    }
}
