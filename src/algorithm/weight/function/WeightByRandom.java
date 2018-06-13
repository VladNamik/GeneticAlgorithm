package algorithm.weight.function;

import algorithm.City;
import algorithm.RandomNumber;

import java.util.Random;

/**
 * Determine weight randomly
 */
public class WeightByRandom implements WeightFunction{
    @Override
    public double getWeight(City city1, City city2) {
        return RandomNumber.random.nextDouble() * Integer.MAX_VALUE;
    }
}
