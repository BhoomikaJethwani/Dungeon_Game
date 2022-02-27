package dungeon;

import java.util.Random;

/**
 * Enum for direction.
 */
public enum Direction {
  North, South, East, West;

  public static Direction getRandom() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}