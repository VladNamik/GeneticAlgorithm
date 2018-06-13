package algorithm.refresh;

import algorithm.Phenotype;
import algorithm.Population;

import java.util.List;

/**
 * Removes first those that are marked, then the "worst" of the population,
 * keeping a certain size of the population
 */
public class RefreshKeepBest implements Refresh {
    private int populationSize;

    public RefreshKeepBest(int populationSize) {
        this.populationSize = populationSize;
    }

    @Override
    public Population refresh(Population population) {
        List<Phenotype> list = population.getAll();

        int i = 0;
        while (list.size() > populationSize && i < list.size()) // remove non-survivors
        {
            if (!list.get(i).isAlive())
                list.remove(i);
            else
                i++;
        }

        while (list.size() > populationSize) {
            list.remove(list.size() - 1);
        }
        return population;
    }
}
