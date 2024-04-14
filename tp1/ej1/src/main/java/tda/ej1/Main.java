package tda.ej1;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Por favor, proporciona solamente la ruta del archivo como argumento");
            return;
        }
        Parser rawData = new Parser(args[0]);
        Campaign campaign = new Campaign();
        Solution solution = campaign.solve(rawData.getInfluencers());
        solution.print();

    }

}