package test;

import static org.junit.Assert.assertEquals;

import dungeon.Direction;
import dungeon.Location;
import dungeon.LocationImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import player.Player;
import player.PlayerImpl;
import treasure.Treasure;
import org.junit.Before;

/**
 * This class tests a Player Interface and all its methods.
 */
public class PlayerTest {

  Player player;
  Location loc;

  /**
   * Setting up objects for all the tests.
   */
  @Before
  public void setUp() {
    loc = new LocationImpl(3, 4, 5);
    player = new PlayerImpl(loc);
  }

  /**
   * Tests players current location.
   */
  @Test
  public void testPlayerLoc() {
    assertEquals(loc, player.getCurrLocation());
  }

  /**
   * Tests players description, that is the treasures collected.
   */
  @Test
  public void testPlayerTreasureDesc() {
    List<Treasure> treasureList = new ArrayList<Treasure>();
    treasureList.add(Treasure.SAPPHIRE);
    treasureList.add(Treasure.RUBY);
    treasureList.add(Treasure.DIAMOND);
    player.collectTreasure(Treasure.RUBY);
    assertEquals("\n Player's Treasures Collected: " + treasureList,
        player.playerTreasureDesc());
  }

  /**
   * Tests players description of current location in dungeon.
   */
  @Test
  public void testPlayerDesc() {
    assertEquals("Player's Current Location: 5\n"
        + " Player's Neighbours: {} ", player.playerDesc());
  }

  /**
   * Tests player can pick up treasures.
   */
  @Test
  public void testPlayerTreasures() {
    List<Treasure> treasureList = new ArrayList<Treasure>();
    treasureList.add(Treasure.SAPPHIRE);
    treasureList.add(Treasure.RUBY);
    treasureList.add(Treasure.DIAMOND);
    player.collectTreasure(Treasure.DIAMOND);
    assertEquals(treasureList, player.getTreasure());
  }

  /**
   * Tests player can Move in all 4 directions.
   */
  @Test
  public void testPlayerMov() {
    loc.setNeighbours(2, Direction.North);
    assertEquals(true, player.canMove(Direction.North));

    loc.setNeighbours(3, Direction.South);
    assertEquals(true, player.canMove(Direction.South));

    loc.setNeighbours(3, Direction.East);
    assertEquals(true, player.canMove(Direction.East));

    loc.setNeighbours(3, Direction.West);
    assertEquals(true, player.canMove(Direction.West));
  }


}