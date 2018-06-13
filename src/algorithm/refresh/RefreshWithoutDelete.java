package algorithm.refresh;

import algorithm.Population;

/**
 * Deletes only when the upper bound is reached, then refresh is performed on the remaining
 */
public class RefreshWithoutDelete implements Refresh{
    private int upperBound;
    private Refresh refresh;

    public RefreshWithoutDelete(int upperBound, Refresh refresh) {
        this.upperBound = upperBound;
        this.refresh = refresh;
    }

    @Override
    public Population refresh(Population population) {
        if (population.getAll().size() < upperBound)
            return population;
        else
            refresh.refresh(population);
        return population;
    }
}
