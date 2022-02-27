package monster;

/**
 * An Otyugh represents the data that is collected for each monster inside the dungeon. Classes that
 * implement this interface will vary depending upon the type of location created.
 */
public interface Otyugh {

  /**
   * Returns the otyugh health.
   *
   * @return List of equipments in the game
   */
  OtyughHealth getHealth();

  /**
   * Injures the Otyugh by changing the health.
   */
  void injure();

}
