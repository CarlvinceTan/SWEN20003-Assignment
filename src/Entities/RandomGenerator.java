package Entities;

import bagel.Keys;
import java.util.Random;

/**
 * Interface for generating random values.
 */
public interface RandomGenerator {

    /**
     * Generates a random boolean value.
     *
     * @return A random boolean value
     */
    default boolean randomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Generates a random direction (left or right).
     *
     * @return A random direction (Keys.LEFT or Keys.RIGHT)
     */
    default Keys randomDirection() {
        if (randomBoolean()) {
            return Keys.LEFT;
        } else {
            return Keys.RIGHT;
        }
    }
}
