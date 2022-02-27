package player;

import java.util.List;
import treasure.Treasure;

/**
 * A Thief represents the data that is collected for the Thief that stays in the dungeon. Classes
 * that implement this interface will vary depending upon the type of thief created.
 */
public interface Thief {


  /**
   * Steals the treasures from the player.
   *
   * @param treasures stolen treasures.
   */
  void stealTreasures(List<Treasure> treasures);

}
