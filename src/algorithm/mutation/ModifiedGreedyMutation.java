package algorithm.mutation;

import algorithm.Phenotype;
import algorithm.RandomNumber;

/**
 * Modified greedy mutation
 * With a given probability swaps the first/last with that in the middle
 */
public class ModifiedGreedyMutation implements Mutation {
    private SinglePointMutation singlePointMutation;
    private GreedyMutation greedyMutation;
    private double swapProbability;

    public ModifiedGreedyMutation(double swapProbability) {
        singlePointMutation = new SinglePointMutation();
        greedyMutation = new GreedyMutation();
        this.swapProbability = swapProbability;
    }

    @Override
    public Phenotype mutation(Phenotype phenotype) {
        if (RandomNumber.random.nextDouble() < swapProbability) {
            if (RandomNumber.random.nextDouble() < 0.5)
                singlePointMutation.swap(phenotype.getList(), 0, 1 + Math.abs(RandomNumber.random.nextInt() % (phenotype.getList().size() - 1)));
            else
                singlePointMutation.swap(phenotype.getList(), phenotype.getList().size() - 1, Math.abs(RandomNumber.random.nextInt() % (phenotype.getList().size() - 1)));
        }

        return greedyMutation.mutation(phenotype);
    }
}
