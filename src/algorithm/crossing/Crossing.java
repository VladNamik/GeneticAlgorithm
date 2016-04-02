package algorithm.crossing;

import algorithm.Phenotype;

/**
 * Способ передачи информации потомку
 */
public interface Crossing {
    Phenotype crossing(Phenotype phenotype1, Phenotype phenotype2);
}
