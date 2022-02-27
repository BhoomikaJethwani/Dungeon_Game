package dungeon;

import java.util.Random;

/**
 * Enum for Smell in the Dungeon.
 */
public enum Smell {
  LessPungent, MorePungent, NoSmell;

  public static Smell getRandom() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
