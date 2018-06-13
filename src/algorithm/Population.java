package algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Aggregate of individuals (phenotypes)
 */
public class Population {
    private ArrayList<Phenotype> list = new ArrayList<>();

    public void addAll(Phenotype... phenotypes) {
        addAll(Arrays.asList(phenotypes));
    }

    public void addAll(List<Phenotype> phenotypeList) {
        list.addAll(phenotypeList);
        list.sort(Phenotype.comparator);
    }

    public void add(Phenotype phenotype) {
        boolean isSet = false;
        for (int i = 0; i < list.size() - 1; i++)
            if (list.get(i).getFitness() < phenotype.getFitness() && list.get(i + 1).getFitness() > phenotype.getFitness()) {
                list.add(i + 1, phenotype);
                isSet = true;
                break;
            }
        if (!isSet) {
            if (list.size() > 0 && phenotype.getFitness() < list.get(0).getFitness())
                list.add(0, phenotype);
            else
                list.add(phenotype);
        }

    }

    public List<Phenotype> getAll() {
        return list;
    }

    public Phenotype getBest() {
        return list.get(0);
    }

    public void sort() {
        list.sort(Phenotype.comparator);
    }

}
