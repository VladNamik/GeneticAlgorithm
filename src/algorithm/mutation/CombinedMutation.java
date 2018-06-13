package algorithm.mutation;

import algorithm.Phenotype;
import algorithm.RandomNumber;

/**
 * GreedyMutation + SinglePointMutation
 */
public class CombinedMutation implements Mutation {
    private double singlePointMutationChance;
    private SinglePointMutation singlePointMutation;
    private GreedyMutation greedyMutation;

    public CombinedMutation(double singlePointMutationChance) {
        this.singlePointMutationChance = singlePointMutationChance;
        this.singlePointMutation = new SinglePointMutation();
        this.greedyMutation = new GreedyMutation();
    }

    @Override
    public Phenotype mutation(Phenotype phenotype) {
        if (RandomNumber.random.nextDouble() < singlePointMutationChance)
            return singlePointMutation.mutation(phenotype);
        else
            return greedyMutation.mutation(phenotype);
    }
}
