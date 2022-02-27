package random;

/**
 * Used for testing Random generated numbers.
 */
public class RandomInterfaceTesterImpl implements RandomInterface {

  /**
   * Gives a random number between the given range of values.
   *
   * @param minimum minimum value for method
   * @param maximum maximum value for method
   * @return a random integer value for method
   */

  @Override
  public int getRandomNumber(int minimum, int maximum) {
    return minimum + 1;
  }

  @Override
  public int[] getRandomRange(int minimum, int maximum, int count) {
    return new int[0];
  }

  @Override
  public boolean nextBoolean() {
    return false;
  }
}

