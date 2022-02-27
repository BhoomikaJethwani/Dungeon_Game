package random;

/**
 * A Random interface to generate a particular random number.
 */
public interface RandomInterface {

  /**
   * Returns random integer value.
   *
   * @return random integer.
   */
  int getRandomNumber(int minimum, int maximum);

  /**
   * Returns range of random integer value.
   *
   * @return a range of random integer.
   */
  int[] getRandomRange(int minimum, int maximum, int count);

  /**
   * Returns random boolean.
   *
   * @return a boolean.
   */
  boolean nextBoolean();

}
