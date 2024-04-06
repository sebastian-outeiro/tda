import java.util.List;

public class Influencer implements Comparable{
    private String id;
    private String name;
    private Integer marketPenetration;
    private List<String> influencersToDiscard;

    public Influencer(String id, String name, Integer marketPenetration, List<String> influencersToDiscard) {
        this.id = id;
        this.name = name;
        this.marketPenetration = marketPenetration;
        this.influencersToDiscard = influencersToDiscard;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMarketPenetration() {
        return marketPenetration;
    }

    public List<String> getInfluencersToDiscard() {
        return influencersToDiscard;
    }

    @Override
    public int compareTo(Object o) {
        Influencer compareWith = (Influencer) o;
        return compareWith.getMarketPenetration().compareTo(this.marketPenetration);
    }

    @Override
    public String toString() {
        return "Influencer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
