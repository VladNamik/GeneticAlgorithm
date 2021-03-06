package algorithm.selection;

import algorithm.Phenotype;
import algorithm.Population;
import algorithm.RandomNumber;
import algorithm.crossing.Crossing;

import java.util.ArrayList;
import java.util.List;

/**
 * Proportional selection
 * Better unit has more chances to be selected
 */
public class ProportionalSelection implements Selection {
    private int childrenCoefficient; // the percentage of children who will appear on the number of parents
    private double[] probabilityArray;

    public ProportionalSelection(int childrenCoefficient) {
        this.childrenCoefficient = childrenCoefficient;
    }


    @Override
    public Population selection(Population population, Crossing crossing) {
        probabilityArray = getProbabiltyArray(population.getAll());
        List<Phenotype> parentsList = population.getAll();
        List<Phenotype> childrenList = new ArrayList<>((int) (parentsList.size() * ((double) childrenCoefficient / 100)));

        for (int i = 0; i < childrenCoefficient; i++) {
            double buf = RandomNumber.random.nextDouble();
            Phenotype firstParent = parentsList.get(0);
            double sum = 0;
            for (int j = 0; j < parentsList.size(); j++)  // choose first parent
            {
                sum += probabilityArray[j];
                if (buf < sum) {
                    firstParent = parentsList.get(j);
                    break;
                }
            }

            Phenotype secondParent = parentsList.get(1);
            sum = 0;
            for (int j = 0; j < parentsList.size(); j++) // choose second parent
            {
                sum += probabilityArray[j];
                if (buf < sum) {
                    secondParent = parentsList.get(j);
                    break;
                }
            }
            if (secondParent == firstParent)
                secondParent = parentsList.get(1);
            if (secondParent == firstParent)
                secondParent = parentsList.get(0);


            Phenotype child = crossing.crossing(firstParent, secondParent);

            int k = 0;
            while (firstParent.getList().equals(child.getList()) || secondParent.getList().equals(child.getList())) // exclude the case of identical phenotypes
            {
                if (k == 6) {
                    child.setAlive(false);
                    break;
                }
                k++;
                child = crossing.crossing(firstParent, secondParent);
            }

            childrenList.add(child);


        }
        population.addAll(childrenList);
        return population;
    }


    // array of object selection probabilities
    private double[] getProbabiltyArray(List<Phenotype> list) {
        double[] probabilityArray = new double[list.size()];
        double sum = 0;
        for (Phenotype phenotype : list)
            sum += phenotype.getFitness();

        for (int i = 0; i < probabilityArray.length; i++)
            probabilityArray[i] = sum / list.get(i).getFitness();

        sum = 0;
        for (int i = 0; i < probabilityArray.length; i++)
            sum += probabilityArray[i];

        for (int i = 0; i < probabilityArray.length; i++)
            probabilityArray[i] = probabilityArray[i] / sum;

        return probabilityArray;
    }

}
