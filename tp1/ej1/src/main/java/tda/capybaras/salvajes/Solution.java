package tda.capybaras.salvajes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private List<Influencer> influencers = new ArrayList<>();
    private Set<String> influencersDiscarded = new HashSet<>();
    private Integer marketPenetration = 0;

    public Solution() {
    }

    public Solution(List<Influencer> influencers, Set<String> influencersDiscarded, Integer marketPenetration) {
        this.influencers.addAll(influencers);
        this.influencersDiscarded.addAll(influencersDiscarded);
        this.marketPenetration = marketPenetration;
    }

    public Solution copyAdding(Influencer influencer){
        Solution newSolution = new Solution(influencers, influencersDiscarded, marketPenetration);
        newSolution.addInfluencer(influencer);
        return newSolution;
    }

    public boolean canAddInfluencer(Influencer influencer) {
        return !influencersDiscarded.contains(influencer.getId());
    }

    public void addInfluencer(Influencer influencer) {
        influencers.add(influencer);
        marketPenetration += influencer.getMarketPenetration();
        influencersDiscarded.addAll(influencer.getInfluencersToDiscard());
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

    @Override
    public String toString() {
        return "Solution{" +
                "influencers=" + influencers +
                ", influencersDiscarded=" + influencersDiscarded +
                ", marketPenetration=" + marketPenetration +
                '}';
    }
}
