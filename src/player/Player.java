package player;

import dungeon.Direction;
import dungeon.Location;
import java.util.List;
import treasure.Treasure;

/**
 * A Player represents the data that is collected for the player that travels the dungeon. Classes
 * that implement this interface will vary depending upon the type of player created.
 */
public interface Player {

  /**
   * Returns value if player is dead player.
   *
   * @return boolean value if player is dead player.
   */
  boolean isDead();

  /**
   * Sets value if player is dead player.
   *
   * @param dead if player is dead.
   */
  void setDead(boolean dead);

  /**
   * Returns number of arrows for the player.
   *
   * @return number of arrows with player
   */
  int getArrows();

  /**
   * Returns description of Players treasure.
   *
   * @return String description of Players treasure
   */
  String playerTreasureDesc();

  /**
   * Returns a String description of Players location.
   *
   * @return String description of Players location
   */
  String playerDesc();

  /**
   * Moves the player to the given direction.
   *
   * @param direction the direction to move to
   */
  void moveTo(Location direction);

  /**
   * Return a boolean true or false if the player can move in that direction.
   *
   * @param direction the direction to move to
   * @return boolean if the player can move
   */
  boolean canMove(Direction direction);

  /**
   * Takes a treasure and adds it to the player's collection.
   *
   * @param treasure treasure that the player collect
   */
  void collectTreasure(Treasure treasure);

  /**
   * Adds number of arrows for the player.
   *
   * @param arrows the number of arrows
   */
  void collectArrows(int arrows);

  /**
   * Returns a list of all the treasures with the player.
   *
   * @return List of treasures
   */
  List<Treasure> getTreasure();

  /**
   * Returns a string of description.
   *
   * @return a string of description
   */
  String toString();

  /**
   * Returns current location of player.
   *
   * @return Location Object that is the current location of player
   */
  int getCurrLocation();

  /**
   * Reduces number of arrows for the player.
   */
  void removeArrow();

  /**
   * Removes treasures of the player.
   */
  void removeTreasures();
}
