package tda.capybaras.salvajes.first;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int size = 8;
        Field field = solution.solve(size, List.of(new Position(1,2)));
        FieldPrinter.print(field, size);
    }
}