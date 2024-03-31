package tda.capybaras.salvajes;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Influencer> influencers = List.of(
                new Influencer("1","Cacho",8,List.of("3","4")),
                new Influencer("2","Lucho",10,List.of("5")),
                new Influencer("3","Suncho",15,List.of("1")),
                new Influencer("4","Pucho",40,List.of("1")),
                new Influencer("5","Tucho",25,List.of("2")),
                new Influencer("6","Fercho",9,List.of())
        );
        Campaign campaign = new Campaign();
        Solution solution = campaign.solve(influencers);
        System.out.println(solution);
    }

}