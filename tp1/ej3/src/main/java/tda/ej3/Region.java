package tda.ej3;

public class Region {
    private String contenido;
    private Coordenadas coordenadas;

    public Region(int x, int y) {

        this.contenido = " Vacio";
        this.coordenadas=new Coordenadas(x,y);
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}

