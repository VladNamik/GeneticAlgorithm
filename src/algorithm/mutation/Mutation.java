package algorithm.mutation;

import algorithm.Phenotype;

/**
 * Способ мутации
 * Выбирается один из фенотипа и
 */
public interface Mutation {
    Phenotype mutation(Phenotype phenotype);
}
