package dungeon;

import java.util.List;
import java.util.Map;
import monster.Otyugh;
import player.Thief;
import treasure.Treasure;

/**
 * A Location represents the data that is collected for each location inside the dungeon. Classes
 * that implement this interface will vary depending upon the type of location created.
 */
public interface Location {

  /**
   * Returns a boolean value if location has treasure.
   *
   * @return List of equipments in the game
   */
  boolean hasTreasure();

  /**
   * Returns boolean value if location is a cave.
   *
   * @return boolean value if location is a cave
   */
  boolean isCave();

  /**
   * Sets the number of entrances.
   */
  void setEntrances();

  /**
   * Sets the directions that are neighbours of the location.
   *
   * @param directions the directions that are neighbours
   */
  void setDirections(Direction directions);

  /**
   * Sets the Neighbours of Location.
   *
   * @param neighbourId the neighbour location ID
   * @param direction   the directions that are neighbours
   */
  void setNeighbours(int neighbourId, Direction direction);

  /**
   * Returns a List of movable directions.
   *
   * @return List of movable directions
   */
  List<Direction> getDirections();

  /**
   * Sets the random treasures in the caves.
   */
  void setTreasure();

  /**
   * Returns if location has thief.
   *
   * @return boolean value if location has thief.
   */
  boolean isHasThief();

  /**
   * Returns thief if location has thief.
   *
   * @return Thief object if location has thief.
   */
  Thief getThief();

  /**
   * Returns number of arrows in location.
   *
   * @return int number of arrows in location.
   */
  int getArrows();

  /**
   * Removes an arrow from the location.
   */
  void removeArrows();

  /**
   * Returns the Otyugh object at the location.
   *
   * @return Otyugh at th location
   */
  Otyugh getOtyugh();

  /**
   * Returns true if location has Otyugh.
   *
   * @return a boolean if location has Otyugh.
   */
  boolean isHasOtyugh();

  /**
   * Sets the variable if location has an Otyugh.
   *
   * @param hasOtyugh boolean value
   */
  void setHasOtyugh(boolean hasOtyugh);

  /**
   * Returns the Location type of the location.
   *
   * @return Location type of the location
   */
  LocationType getType();

  /**
   * Returns boolean value if location is start of dungeon.
   *
   * @return boolean if location is start
   */
  boolean isStart();

  /**
   * Sets the location as Start.
   *
   * @param start the start location
   */
  void setStart(boolean start);

  /**
   * Returns a boolean value if location is end of dungeon. * * @return boolean if location is end
   */
  boolean isEnd();

  /**
   * Sets the location as End.
   *
   * @param end the end location
   */
  void setEnd(boolean end);

  /**
   * Returns an Integer Location ID.
   *
   * @return Integer Location ID
   */
  int getId();

  /**
   * Returns an integer of X coordinate.
   *
   * @return integer of X coordinate
   */
  int getX();

  /**
   * Returns an integer of Y coordinate.
   *
   * @return integer of Y coordinate
   */
  int getY();

  /**
   * Returns a list of all the treasures in the cave.
   *
   * @return list of all the treasures in the cave
   */
  List<Treasure> getTreasures();

  /**
   * Returns a list of all the neighbours of locations.
   *
   * @return List of neighbours of the location
   */
  Map<Direction, Integer> getNeighbours();

  /**
   * Returns a list of all the equipments in the game.
   *
   * @param t Treasure to be removed
   */
  void removeTreasure(Treasure t);

  /**
   * Returns a list of all the equipments in the game.
   *
   * @return List of equipments in the game
   */
  String toString();

  /**
   * Sets the number of arrows.
   *
   * @param i no of arrows
   */
  void setArrows(int i);

  /**
   * Adds thief to location.
   */
  void addThief();

  /**
   * Adds Pit to location.
   */
  void addPit();

  /**
   * Returns Pit to location.
   *
   * @return boolean value if location has Pit.
   */
  boolean isHasPit();
}
