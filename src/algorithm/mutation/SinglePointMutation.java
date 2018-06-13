package algorithm.mutation;

import algorithm.City;
import algorithm.Phenotype;
import algorithm.RandomNumber;

import java.util.List;

/**
 * Swap two genes
 */
public class SinglePointMutation implements Mutation {
    @Override
    public Phenotype mutation(Phenotype phenotype) {
        List<City> genes = phenotype.getList();
        swap(genes, Math.abs(RandomNumber.random.nextInt() % genes.size()), Math.abs(RandomNumber.random.nextInt() % genes.size()));
        return phenotype;
    }

    public void swap(List<City> list, int i, int j) {
        City city = list.get(i);
        City city2 = list.get(j);

        list.remove(i);
        list.add(i, city2);

        list.remove(j);
        list.add(j, city);
    }
}
