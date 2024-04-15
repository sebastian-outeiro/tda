package tda.ej1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Parser {

    private final List<Influencer> influencers = new ArrayList<>();

    public Parser(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> notWorkingWith = new ArrayList<>();
                String[] information = line.split(",");

                for (int i = 3; i < information.length; i++) {
                    notWorkingWith.add(information[i]);
                }
                this.influencers.add(new Influencer(information[0], information[1], parseInt(information[2]), notWorkingWith));
            }
        } catch (
                IOException e) {
            System.out.println("Error al leer el archivoi: " + e.getMessage());
        }

    }

    public List<Influencer> getInfluencers() {
        return this.influencers;
    }


}
