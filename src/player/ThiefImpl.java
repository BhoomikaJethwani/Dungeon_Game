package player;

import java.util.List;
import treasure.Treasure;

/**
 * This class represents a Thief. A Thief steals the player's treasures.
 */
public class ThiefImpl implements Thief {

  List<Treasure> treasures;

  @Override
  public void stealTreasures(List<Treasure> treasures) {
    for (Treasure t : treasures) {
      this.treasures.add(t);
    }

  }
}
