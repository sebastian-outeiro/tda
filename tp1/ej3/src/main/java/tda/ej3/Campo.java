package tda.ej3;

public class Campo {
    public Region regiones[][];
    public Integer extension;
    public Integer lColocadas=1;
    public Coordenadas silo;
    public Campo(Integer extension,int colSilo, int filSilo) {

        this.regiones = new Region[extension][extension];
        for (int fila=0;fila<extension;fila++){
            for (int col=0;col<extension;col++){
                regiones[fila][col]= new Region(col,fila);
            }
        }
        this.extension=extension;
        this.silo= new Coordenadas(colSilo,filSilo);
        regiones[filSilo][colSilo].setContenido(" Silo ");

    }

    public void mostrarSilo (){
        System.out.println("El silo esta en (" + silo.getFil() + "," + silo.getCol() + ") " +regiones[silo.getFil()][silo.getCol()].getContenido());
    }

    public void mostrarCampo () {
        imprimir("Campo ");
        for (int col=0;col<this.extension;col++)
            imprimir("|  " + col + "  |");
        System.out.println();
        for (int fila=0;fila<this.extension;fila++){
            imprimir(fila + "   ");
            for (int col=0;col<this.extension;col++){
                imprimir("|" + regiones[fila][col].getContenido() + "| ");
            }
            System.out.println();
        }
    }

    private void imprimir(String value) {
        System.out.printf("%1$10s", value);
    }

    public void mostrarCantidadRegiones(){
        System.out.println("Se generaron: "+ (this.lColocadas-1)+" Regiones");
    }
    public Coordenadas getSilo() {
        return silo;
    }
}
