package algorithm.refresh;


import algorithm.Phenotype;
import algorithm.Population;
import algorithm.RandomNumber;

import java.util.List;

/**
 * Removes first those that are marked,
 * then with a certain probability removes those that are left.
 */
public class ProportionalRefresh implements Refresh{
    private int populationSize;

    public ProportionalRefresh(int populationSize)
    {
        this.populationSize = populationSize;
    }

    @Override
    public Population refresh(Population population) {
        List<Phenotype> list = population.getAll();

        int i = 0;
        while(list.size() > populationSize && i < list.size()) // remove non-survivors
        {
            if (!list.get(i).isAlive())
                list.remove(i);
            else
                i++;
        }

        while(list.size() > populationSize) {
            double[] probabilityArray = getProbabiltyArray(list);
            int k = list.size() - 1;
            while (k >= 1 && list.size() > populationSize) {
                if (RandomNumber.random.nextDouble() < probabilityArray[k])
                    list.remove(k);
                k--;
            }
        }

        return population;
    }

    // array of object selection probabilities
    private double[] getProbabiltyArray(List<Phenotype> list)
    {
        double[] probabilityArray = new double[list.size()];
        double sum = 0;
        for (Phenotype phenotype : list)
            sum += phenotype.getFitness();
        sum -= list.get(0).getFitness();

        for(int i = 1; i < probabilityArray.length; i++)
            probabilityArray[i] =  list.get(i).getFitness() / sum;
        probabilityArray[0] = 0;

        for(int i = 0; i < probabilityArray.length; i++)
            probabilityArray[i] *= (list.size() - populationSize);
        return probabilityArray;
    }
}
