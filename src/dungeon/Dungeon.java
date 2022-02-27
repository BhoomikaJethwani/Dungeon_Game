package dungeon;

import java.util.List;
import player.Player;
import treasure.Treasure;

/**
 * A Dungeon represents the data that is collected for the caves and tunnels inside the dungeon and
 * the player moving inside the dungeon. Classes that implement this interface will vary depending
 * upon the type of dungeon created.
 */
public interface Dungeon {

  /**
   * Returns an integer of number of dungeon locations.
   *
   * @return int of dungeon locations
   */
  int[][] getDungeon();

  /**
   * Returns value of wrapping.
   *
   * @return boolean value of wrapping
   */
  boolean isWrap();

  /**
   * Returns an integer of percentage.
   *
   * @return int of number of percentage
   */
  int getPercentage();

  /**
   * Returns an integer of number of Location having arrows.
   *
   * @return int of number of locations with arrows
   */
  int getNoOfArrowLocations();

  /**
   * Returns no of Otyughs in dungeon.
   *
   * @return no of Otyughs in dungeon.
   */
  int getNoOfOtyugh();

  /**
   * Returns a list of locations in dungeon.
   *
   * @return list of location objects in dungeon.
   */
  List<Location> getLocations();

  /**
   * Returns an integer of number of Caves having treasure.
   *
   * @return int of number of caves with treasures
   */
  int getNoOfTreasureCaves();

  /**
   * Returns start location in dungeon.
   *
   * @return Start location object in dungeon.
   */
  Location getLocationStart();

  /**
   * Returns end location in dungeon.
   *
   * @return End location object in dungeon.
   */
  Location getLocationEnd();

  /**
   * Returns player inside in dungeon.
   *
   * @return player object in dungeon.
   */
  Player getPlayer();

  /**
   * Sets the location type of the dungeon.
   */
  void setLocationType();

  /**
   * Returns a boolean if player can move in the direction.
   *
   * @return a boolean value if player can move in the direction.
   */
  boolean movePlayer(Direction direction);

  /**
   * Returns a boolean if player is dead.
   *
   * @return a boolean value if player is dead.
   */
  boolean isPlayerDead();

  /**
   * Returns a boolean if player can pick the treasure.
   *
   * @param t treasure value.
   * @return a boolean value if player can can pick the treasure.
   */
  boolean pickTreasure(Treasure t);

  /**
   * Returns a boolean if player can pick the arrow.
   *
   * @return a boolean value if player can pick the arrow.
   */
  boolean pickArrow();


  /**
   * Returns a boolean if loaction has treasure.
   *
   * @return a boolean value if loaction has treasure.
   */
  boolean locationHasTreasure();

  /**
   * Returns an integer of loacation arrows.
   *
   * @return int of location arrows
   */
  int locationHasArrows();

  /**
   * Returns an integer of number of player arrows.
   *
   * @return int of player arrows
   */
  int playerHasArrows();

  /**
   * Returns a String at end of the game.
   *
   * @return List of equipments in the game
   */
  String exitDungeon();

  /**
   * Sets End Location of Dungeon.
   */
  void setEnd();

  /**
   * Assigns the edges of the graph.
   */
  void getEdges();

  /**
   * Returns an integer of number of rows.
   *
   * @return int of number of rows
   */
  int getRows();

  /**
   * Returns Start location of the tunnel.
   *
   * @return Location that is the start
   */
  Location getStart();

  /**
   * Returns End location of the tunnel.
   *
   * @return Location that is the end
   */
  Location getEnd();

  /**
   * Returns an integer of number of columns.
   *
   * @return int of number of columns
   */
  int getColumns();

  /**
   * Increases the interconnectivity of the dungeon.
   */
  void increaseInterconnectivity();

  /**
   * Returns an integer of Interconnectivity Degree.
   *
   * @return integer of Interconnectivity Degree
   */
  int getInterconnectivityDegree();

  /**
   * Returns the number of tunnels.
   *
   * @return integer the number of tunnels
   */
  int getNoOfTunnels();

  /**
   * Returns the number of Caves.
   *
   * @return integer the number of Caves
   */
  int getNoOfCaves();

  /**
   * Takes in a minimum percentage of treasures to be assigned and adds to caves.
   *
   * @param percentage of treasures to be assigned
   */
  void addTreasure(int percentage);

  /**
   * Takes in no of Otyughs to add in dungeon.
   *
   * @param noOfOtyugh no of Otyughs to be added
   */
  void addOtyugh(int noOfOtyugh);

  /**
   * Takes in no of arrows to add in dungeon.
   *
   * @param percentage of arrows to be added
   */
  void addArrows(int percentage);

  /**
   * Takes in a minimum percentage of treasures to be assigned and adds to caves.
   *
   * @param caves     the cave position
   * @param direction thedirection to shoot
   */
  boolean slayMonster(int caves, String direction);

  /**
   * Returns true if player killed and false otherwise.
   *
   * @return a boolean if player killed
   */
  boolean killPlayer();

  /**
   * Returns smell around the location.
   *
   * @return Smell from the dungeon locations
   */
  Smell getSmell();

  /**
   * Returns a String of dungeon.
   *
   * @return string of the dungeon model
   */
  String displayDungeon();

  /**
   * Returns a boolean if player reached the end.
   *
   * @return a boolean if player reached the end.
   */
  boolean playerAtEnd();

  /**
   * Returns a String of player description.
   *
   * @return a String of player description.
   */
  String playerDescription();

  /**
   * Returns treasures object.
   *
   * @return object treasures.
   */
  Object getTreasures();

  /**
   * Returns number of arrows.
   *
   * @return int arrows.
   */
  int getArrows();
}
