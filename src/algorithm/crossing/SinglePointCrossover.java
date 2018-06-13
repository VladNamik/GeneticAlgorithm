package algorithm.crossing;

import algorithm.City;
import algorithm.Phenotype;
import algorithm.RandomNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Single point crossover
 * "Partially displayed crossover"
 */

public class SinglePointCrossover implements Crossing {
    @Override
    public Phenotype crossing(Phenotype phenotype1, Phenotype phenotype2) {
        List<City> genes1 = phenotype1.getList();
        List<City> genes2 = phenotype2.getList();

        City[] comparisonArray = new City[genes1.size()]; // create a comparison array
        for (int i = 0; i < comparisonArray.length; i++)
            comparisonArray[i] = null;

        int crossoverPoint = Math.abs(RandomNumber.random.nextInt() % (genes1.size() - 1)) + 1; // select the crossover point

        for (int i = 0; i < crossoverPoint; i++) // populate the comparison array
            comparisonArray[genes1.get(i).getId()] = genes2.get(i);


        List<City> childGenes = new ArrayList<>(genes1.size());
        for (int i = 0; i < crossoverPoint; i++) // The first part is taken from the first parent
            childGenes.add(genes1.get(i));

        for (int i = crossoverPoint; i < genes1.size(); i++) // The second part is obtained either from the second parent or from the comparison array
        {
            City currentGen = genes2.get(i);
            while (comparisonArray[currentGen.getId()] != null) {
                currentGen = comparisonArray[currentGen.getId()];
            }
            childGenes.add(currentGen);
        }
        return new Phenotype(phenotype1.getRoads(), childGenes);
    }
}
