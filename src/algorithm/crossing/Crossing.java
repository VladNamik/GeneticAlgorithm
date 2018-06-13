package algorithm.crossing;

import algorithm.Phenotype;

/**
 * The way information is transmitted to a descendant
 */
public interface Crossing {
    Phenotype crossing(Phenotype phenotype1, Phenotype phenotype2);
}
