package tda.ej1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private final List<Influencer> influencers = new ArrayList<>();
    private final Set<String> influencersDiscarded = new HashSet<>();
    private Integer marketPenetration = 0;

    public Solution() {
    }

    public Solution(List<Influencer> influencers, Set<String> influencersDiscarded, Integer marketPenetration) {
        this.influencers.addAll(influencers);
        this.influencersDiscarded.addAll(influencersDiscarded);
        this.marketPenetration = marketPenetration;
    }

    public Solution copyAdding(Influencer influencer) {
        Solution newSolution = new Solution(influencers, influencersDiscarded, marketPenetration);
        newSolution.addInfluencer(influencer);
        return newSolution;
    }

    public boolean canAddInfluencer(Influencer influencer) {
        return !influencersDiscarded.contains(influencer.id());
    }

    public void addInfluencer(Influencer influencer) {
        influencers.add(influencer);
        marketPenetration += influencer.marketPenetration();
        influencersDiscarded.addAll(influencer.influencersToDiscard());
    }

    public List<Influencer> getInfluencers() {
        return influencers;
    }

    public Integer getMarketPenetration() {
        return marketPenetration;
    }

    public Set<String> getInfluencersDiscarded() {
        return influencersDiscarded;
    }


    public void print(){
        System.out.println("Total Market Penetration = " + marketPenetration + ". IDs influencers discarded = " + influencersDiscarded);
        System.out.println("Influencers included order by market penetration: ");
        influencers.forEach(System.out::println);
    }
}
