package algorithm.selection;

import algorithm.Population;
import algorithm.crossing.Crossing;

/**
 * Способ выбора из популяции для последующего скрещевания
 */
public interface Selection {
    Population selection(Population population, Crossing crossing);
}
