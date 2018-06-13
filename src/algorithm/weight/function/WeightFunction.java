package algorithm.weight.function;

import algorithm.City;

/**
 * Weight determination function
 */
public interface WeightFunction {
    double getWeight(City city1, City city2);
}
