package algorithm.selection;

import algorithm.Population;
import algorithm.crossing.Crossing;

/**
 * Method of selection from the population for the subsequent crossing
 */
public interface Selection {
    Population selection(Population population, Crossing crossing);
}
