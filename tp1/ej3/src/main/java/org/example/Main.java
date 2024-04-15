package org.example;

public class Main {
    public static void main(String[] args) {

      Campo campo=new Campo(8,0,1);
      campo.mostrarSilo();


      Agrimensor agrimensor= new Agrimensor(campo);
      agrimensor.divison(0, campo.extension, 0, campo.extension, campo.silo);

      campo.mostrarCampo();
      campo.mostrarCantidadRegiones();
    }
}