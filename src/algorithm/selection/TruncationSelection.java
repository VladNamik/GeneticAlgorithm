package algorithm.selection;

import algorithm.Phenotype;
import algorithm.Population;
import algorithm.RandomNumber;
import algorithm.crossing.Crossing;

import java.util.ArrayList;
import java.util.List;

/**
 * Truncation selection
 * Only best selected
 */
public class TruncationSelection implements Selection {
    private int crossingCoefficient; // percentage of parents involved in selection
    private boolean deleteParent; // delete parent(-s) or not
    private boolean twoChild; // two children with different genotypes from two parents

    public TruncationSelection(int crossingCoefficient, boolean deleteParent, boolean twoChild) {
        this.crossingCoefficient = crossingCoefficient;
        this.deleteParent = deleteParent;
        this.twoChild = twoChild;
    }

    @Override
    public Population selection(Population population, Crossing crossing) {
        int populationSize = population.getAll().size();
        int pairQuantity = (int) (populationSize * (double) crossingCoefficient / 100 / 2);

        List<Phenotype> childrenList = new ArrayList<>(pairQuantity);
        List<Phenotype> parentsList = new ArrayList<>(pairQuantity * 2);

        for (int i = 0; i < pairQuantity * 2; i++)
            parentsList.add(population.getAll().get(i));


        for (int i = 0; i < pairQuantity; i++) {
            int firstParentNumber = (int) (parentsList.size() * RandomNumber.random.nextDouble());
            int secondParentNumber = (int) (parentsList.size() * RandomNumber.random.nextDouble());

            if (parentsList.size() >= 2) // exclude the case of identical phenotypes
            {
                while (secondParentNumber == firstParentNumber)
                    secondParentNumber = (int) (parentsList.size() * RandomNumber.random.nextDouble());
            }

            Phenotype firstParent = parentsList.get(firstParentNumber);
            Phenotype secondParent = parentsList.get(secondParentNumber);
            Phenotype child = crossing.crossing(firstParent, secondParent);

            int k = 0;
            while (firstParent.getList().equals(child.getList()) || secondParent.getList().equals(child.getList())) // exclude the case of identical phenotypes
            {
                if (k == 5) {
                    child.setAlive(false);
                    break;
                }
                k++;
                child = crossing.crossing(firstParent, secondParent);
            }

            childrenList.add(child);


            if (twoChild) // one more if necessary
            {
                Phenotype secondChild = crossing.crossing(secondParent, firstParent);

                k = 0;
                while (firstParent.getList().equals(secondChild.getList()) || secondParent.getList().equals(secondChild.getList())) //исключаем случай одинаковых фенотипов
                {
                    if (k == 5) {
                        secondChild.setAlive(false);
                        break;
                    }
                    secondChild = crossing.crossing(secondParent, firstParent);
                    k++;
                }

                childrenList.add(secondChild);
            }


            if (deleteParent) // delete parent if needed
            {
                firstParent.setAlive(false);
                if (firstParent.getFitness() == population.getBest().getFitness()) // keep his life, if he is the best
                {
                    firstParent.setAlive(true);
                    //childrenList.forEach((x) -> {if (x.getFitness() < firstParent.getFitness()) firstParent.setAlive(false);});
                }
            }
            if (deleteParent && twoChild) {
                secondParent.setAlive(false);
                if (secondParent.getFitness() == population.getBest().getFitness()) // keep his life, if he is the best
                {
                    secondParent.setAlive(true);
                    //childrenList.forEach((x) -> {if (x.getFitness() <= secondParent.getFitness()) secondParent.setAlive(false);});
                }
            }
        }


        population.addAll(childrenList);
        return population;
    }
}
