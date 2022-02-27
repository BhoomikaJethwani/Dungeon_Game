package random;

import java.util.List;
import java.util.Random;

/**
 * This class represents a Random number Generator.
 */
public class RandomInterfaceImpl implements RandomInterface {

  private List<Integer> num;
  private final Random rand;

  /**
   * Creates a new random number generator. This constructor sets the seed of the random number
   * generator to a value very likely to be distinct from any other invocation of this constructor.
   */
  public RandomInterfaceImpl() {
    rand = new Random();
  }


  @Override
  public int getRandomNumber(int minimum, int maximum) {

    return rand.nextInt((maximum - minimum) + 1) + minimum;
  }

  @Override
  public int[] getRandomRange(int minimum, int maximum, int count) {
    int[] num = new int[count];
    // num = this.ints(minimum, maximum).distinct().limit(count).toArray();
    return num;
  }

  @Override
  public boolean nextBoolean() {
    return rand.nextBoolean();
  }
}
