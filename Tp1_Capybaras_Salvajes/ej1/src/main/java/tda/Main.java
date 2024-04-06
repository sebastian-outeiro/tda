import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        if (args.length !=1){
            System.out.println("Por favor, proporciona solamente la ruta del archivo como argumento");
            return;
        }
        Parser rawData= new Parser(args[0]);
        Campaign campaign = new Campaign();
        Solution solution = campaign.solve(rawData.getInfluencers());
        System.out.println(solution);

    }

}