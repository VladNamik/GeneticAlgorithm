package algorithm.weight.function;

import algorithm.City;
import algorithm.RandomNumber;

import java.util.Random;

/**
 * Определяем вес рандомно
 */
public class WeightByRandom implements WeightFunction{
    @Override
    public double getWeight(City city1, City city2) {
        return RandomNumber.random.nextDouble() * Integer.MAX_VALUE;
    }
}
