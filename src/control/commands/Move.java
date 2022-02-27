package control.commands;

import dungeon.Direction;
import dungeon.Dungeon;

/**
 * Class for the command Move Player.
 */
public class Move implements Commands {

  Direction dir;

  /**
   * Constructor.
   *
   * @param dir the direction to move.
   */
  public Move(Direction dir) {
    this.dir = dir;
  }

  @Override
  public boolean playGame(Dungeon d) {

    if (d == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    return d.movePlayer(dir);
  }
}
