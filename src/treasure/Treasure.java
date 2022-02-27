package treasure;

import java.util.Random;

/**
 * Enum for Treasures.
 */
public enum Treasure {
  DIAMOND, RUBY, SAPPHIRE;

  public static Treasure getRandom() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
