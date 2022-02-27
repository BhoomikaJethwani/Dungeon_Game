package control;

import dungeon.Dungeon;

/**
 * Represents a Controller for Dungeon model: handle user moves by executing them using the model;
 * convey the next move outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of Dungeon Model. When the game is over, the playGame method ends.
   *
   * @param d a non-null Dungeon Model
   * @return boolean true or false based on if player wins or looses
   */
  boolean playGame(Dungeon d);
}

