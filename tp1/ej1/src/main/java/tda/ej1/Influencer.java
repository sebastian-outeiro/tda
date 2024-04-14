package tda.ej1;

import java.util.List;

public record Influencer(String id,
                         String name,
                         Integer marketPenetration,
                         List<String> influencersToDiscard) implements Comparable<Influencer> {

    @Override
    public int compareTo(Influencer o) {
        return o.marketPenetration().compareTo(this.marketPenetration);
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + "). Market Penetration: " + marketPenetration;
    }
}
