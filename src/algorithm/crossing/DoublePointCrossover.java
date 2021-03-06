package algorithm.crossing;

import algorithm.City;
import algorithm.Phenotype;
import algorithm.RandomNumber;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Two-point crossover
 * "Ordered crossover"
 */
public class DoublePointCrossover implements Crossing {
    @Override
    public Phenotype crossing(Phenotype phenotype1, Phenotype phenotype2) {
        List<City> genes1 = phenotype1.getList();
        List<City> genes2 = phenotype2.getList();

        if (phenotype1.getList().size() < 3)
            throw new IllegalArgumentException();
        int firstPoint = Math.abs(RandomNumber.random.nextInt() % (phenotype1.getList().size() - 2));
        int secondPoint = firstPoint + Math.abs(RandomNumber.random.nextInt() % (phenotype1.getList().size() - 1 - firstPoint));

        List<City> childGenes = new ArrayList<>(genes1.size());
        for (int i = firstPoint; i < secondPoint; i++) // add to the middle
            childGenes.add(genes1.get(i));

        List<City> bufList = new LinkedList<>();
        for (int i = secondPoint; i < genes2.size(); i++)
            bufList.add(genes2.get(i));
        for (int i = 0; i < secondPoint; i++)
            bufList.add(genes2.get(i));

        for (City gen : childGenes)
            if (bufList.contains(gen))
                bufList.remove(gen);


        for (int i = secondPoint; i < genes2.size(); i++) // add to the end
            childGenes.add(bufList.remove(0));

        for (int i = 0; i < firstPoint; i++) // add to the beginning
            childGenes.add(i, bufList.remove(0));

        return new Phenotype(phenotype1.getRoads(), childGenes);
    }
}
