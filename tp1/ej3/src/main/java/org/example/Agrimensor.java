package org.example;

public class Agrimensor {
    public Campo campo;

    public Agrimensor(Campo campo) {
        this.campo = campo;
    }
    public void divison(int inicioFila, int finFila, int inicioCol, int finCol, Coordenadas vacio){
        int medioFilas=inicioFila+(finFila-inicioFila)/2;
        int medioColumnas=inicioCol+(finCol-inicioCol)/2;

        if(finFila-inicioFila==2){
            llenadoMenor(inicioFila,inicioCol,vacio);
            return;
        }


        if (estaEntre(inicioFila,medioFilas-1,vacio.getFil())&& estaEntre(inicioCol,medioColumnas-1, vacio.getCol())){
            divison(inicioFila,medioFilas,inicioCol,medioColumnas,vacio);
            rellenar(0,inicioFila,finFila,inicioCol,finCol);
        }else{
            divison(inicioFila,medioFilas,inicioCol,medioColumnas,new Coordenadas(medioColumnas-1,medioFilas-1));
        }

        if (estaEntre(inicioFila,medioFilas-1,vacio.getFil())&& estaEntre(medioColumnas,finCol-1, vacio.getCol())){
            divison(inicioFila,medioFilas,medioColumnas,finCol,vacio);
            rellenar(1,inicioFila,finFila,inicioCol,finCol);
        }else{
            divison(inicioFila,medioFilas,medioColumnas,finCol,new Coordenadas(medioColumnas,medioFilas-1));
        }

        if (estaEntre(medioFilas,finFila-1,vacio.getFil())&& estaEntre(inicioCol,medioColumnas-1, vacio.getCol())){
            divison(medioFilas,finFila,inicioCol,medioColumnas,vacio);
            rellenar(2,inicioFila,finFila,inicioCol,finCol);
        }else{
            divison(medioFilas,finFila,inicioCol,medioColumnas,new Coordenadas(medioColumnas-1,medioFilas));
        }

        if (estaEntre(medioFilas,finFila-1,vacio.getFil())&& estaEntre(medioColumnas,finCol-1, vacio.getCol())){
            divison(medioFilas,finFila,medioColumnas,finCol,vacio);
            rellenar(3,inicioFila,finFila,inicioCol,finCol);
        }else{
            divison(medioFilas,finFila,medioColumnas,finCol,new Coordenadas(medioColumnas,medioFilas));
        }

    }

    public boolean estaEntre(int min, int max, int numero){
        return numero>=min && numero<=max;
    }
    public void llenadoMenor(int inicioFila, int inicioCol, Coordenadas evitarLLenar){
        if (estaEntre(inicioFila,inicioFila+1,campo.silo.getFil()) && estaEntre(inicioCol,inicioCol+1,campo.silo.getCol())){
              evitarLLenar=campo.silo;
        }
        for (int fila=inicioFila;fila<=inicioFila+1;fila++){
            for (int col=inicioCol;col<=inicioCol+1;col++){
                if (evitarLLenar.getCol()==col && evitarLLenar.getFil()==fila) {
                    continue;
                }
                campo.regiones[fila][col].setContenido("-L " + campo.lColocadas+"-");
            }
        }
        campo.lColocadas=campo.lColocadas+1;

    }

    public void rellenar(int caso, int inicioFila, int finFila, int inicioCol, int finCol){
        int medioFila=inicioFila+(finFila-inicioFila)/2;
        int medioColumna=inicioCol+(finCol-inicioCol)/2;
        switch (caso){
            case 0:
                campo.regiones[medioFila-1][medioColumna].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila][(medioColumna)-1].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila][(medioColumna)].setContenido("-L " + campo.lColocadas+"-");
                break;
            case 1:
                campo.regiones[medioFila-1][(medioColumna)-1].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila][(medioColumna)-1].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila][(medioColumna)].setContenido("-L " + campo.lColocadas+"-");
                break;
            case 2:
                campo.regiones[medioFila-1][(medioColumna)-1].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila-1][(medioColumna)].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila][(medioColumna)].setContenido("-L " + campo.lColocadas+"-");
                break;
            case 3:
                campo.regiones[medioFila-1][(medioColumna)-1].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila-1][(medioColumna)].setContenido("-L " + campo.lColocadas+"-");
                campo.regiones[medioFila][(medioColumna)-1].setContenido("-L " + campo.lColocadas+"-");
                break;
        }
        campo.lColocadas=campo.lColocadas+1;
    }

}
