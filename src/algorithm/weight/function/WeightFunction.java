package algorithm.weight.function;

import algorithm.City;

/**
 * Функция определения веса
 */
public interface WeightFunction {
    double getWeight(City city1, City city2);
}
