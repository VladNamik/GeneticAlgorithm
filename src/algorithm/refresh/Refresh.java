package algorithm.refresh;

import algorithm.Population;

/**
 * Population update; removal of redundant individuals
 */
public interface Refresh {
    Population refresh(Population population);
}
