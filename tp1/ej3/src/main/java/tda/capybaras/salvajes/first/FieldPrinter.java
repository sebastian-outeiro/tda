package tda.capybaras.salvajes.first;

public class FieldPrinter {

    public static void print(Field field, Integer size){
        System.out.println("Regions: " + field.getRegions());
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                System.out.print(field.print(new Position(x,y)) + "  ");
            }
            System.out.println();
        }
    }
}
