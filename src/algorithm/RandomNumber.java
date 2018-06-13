package algorithm;

import java.util.Date;
import java.util.Random;

/**
 * Keeps static randomizer (if you will want to repeat with same start parameters)
 */
public class RandomNumber {
    public static Random random = new Random(new Date().getTime());
}
