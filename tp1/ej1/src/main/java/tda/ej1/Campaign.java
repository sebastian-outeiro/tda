package tda.ej1;

import java.util.List;

public class Campaign {

    public Solution solve(List<Influencer> influencers) {
        List<Influencer> influencersOrdered = influencers.stream()
                .sorted().toList();
        return backtrack(new Solution(), influencersOrdered, 0);
    }

    private Solution backtrack(Solution solution, List<Influencer> influencers, Integer position) {
        if (position >= influencers.size())
            return solution;
        Solution bestSolution = solution;
        Influencer actualInfluencer = influencers.get(position);

        if (solution.canAddInfluencer(actualInfluencer)) {
            Solution newSolution = solution.copyAdding(actualInfluencer);
            if (cost(newSolution, influencers, position) > bestSolution.getMarketPenetration()) {
                newSolution = backtrack(newSolution, influencers, position + 1);
                if (newSolution.getMarketPenetration() > bestSolution.getMarketPenetration())
                    bestSolution = newSolution;
            }
        }

        if (cost(solution, influencers, position) > bestSolution.getMarketPenetration()) {
            Solution newSolution = backtrack(solution, influencers, position + 1);
            if (newSolution.getMarketPenetration() > bestSolution.getMarketPenetration())
                bestSolution = newSolution;
        }
        return bestSolution;
    }

    private Integer cost(Solution solution, List<Influencer> influencers, Integer position) {
        Integer actualPenetration = solution.getMarketPenetration();
        Integer remainInfluencers = influencers.size() - position - 1;
        Integer nextPenetration = 0;
        if (position + 1 <= influencers.size() - 1) {
            nextPenetration = influencers.get(position + 1).marketPenetration();
        }
        return actualPenetration + remainInfluencers * nextPenetration;
    }
}
