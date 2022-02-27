package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonImpl;
import dungeon.Location;
import dungeon.LocationImpl;
import dungeon.Smell;
import monster.OtyughHealth;
import org.junit.Before;
import org.junit.Test;
import player.Player;
import random.RandomInterface;
import random.RandomInterfaceTesterImpl;

/**
 * This class tests a Dungeon Interface and all its methods.
 */
public class DungeonTest {

  Dungeon dungeon1;
  Dungeon dungeon2;
  Location start;
  Location end;
  Player player;
  RandomInterface rand;

  /**
   * Setting up objects for all the tests.
   */
  @Before
  public void setUp() throws Exception {
    rand = new RandomInterfaceTesterImpl();
    dungeon1 = new DungeonImpl(4, 5, 11, true, rand, 11, 50);

    dungeon2 = new DungeonImpl(4, 6, 10, false, rand, 10, 50);

    start = new LocationImpl(1, 2, 3);
    end = new LocationImpl(1, 2, 3);
  }

  /**
   * Tests if Otyugh present at start and end.
   */
  @Test
  public void testOtyughAtStartEnd() {
    assertEquals(false, dungeon1.getStart().isHasOtyugh());
    assertEquals(false, dungeon1.getEnd().isHasOtyugh());
  }

  /**
   * Tests Otyughs.
   */
  @Test
  public void testSetOtyugh() {
    assertEquals(11, dungeon1.getNoOfOtyugh());
    assertEquals(11, dungeon1.getNoOfOtyugh());
  }

  /**
   * Tests Otyugh Smell.
   */
  @Test
  public void testOtyughSmell() {
    dungeon2 = new DungeonImpl(4, 6, 10, false, rand, 0, 50);
    dungeon1.getLocations().get(1).setHasOtyugh(true);
    //Move player to 1 position away from Otyugh
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(2));
    assertEquals(Smell.MorePungent, dungeon1.getSmell());

    //Move player to 2 position away from Otyugh
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(3));
    assertEquals(Smell.LessPungent, dungeon1.getSmell());

    dungeon1.getLocations().get(5).setHasOtyugh(true);
    //Check smell 2 position away from multiple Otyugh
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(3));
    assertEquals(Smell.MorePungent, dungeon1.getSmell());

    //Check No smell
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(10));
    assertEquals(Smell.NoSmell, dungeon1.getSmell());
  }

  /**
   * Tests if player killed by a Healthy Otyugh.
   */
  @Test
  public void killPlayer() {
    dungeon2 = new DungeonImpl(5, 6, 10, false, rand, 0, 50);
    //When the Otyugh is initialized it is healthy
    dungeon1.getLocations().get(1).setHasOtyugh(true);
    //Move player to location having Otyugh
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(1));
    assertEquals(true, dungeon1.killPlayer());

  }

  /**
   * Tests if player escapes from an Injured Otyugh.
   */
  @Test
  public void testEscapePlayer() {
    dungeon2 = new DungeonImpl(5, 6, 10, false, rand, 0, 50);
    //When the Otyugh is initialized it is healthy
    dungeon1.getLocations().get(1).setHasOtyugh(true);

    //Move player to location having an Otyugh 1 position away
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(2));
    //Player shoots at a distance of 1 in all 4 directions
    dungeon1.slayMonster(1, "N");
    dungeon1.slayMonster(1, "S");
    dungeon1.slayMonster(1, "E");
    dungeon1.slayMonster(1, "W");
    //Injure the Otyugh by shooting 1 position away in all directions towards the monster
    assertEquals(OtyughHealth.injured, dungeon1.getLocations().get(1).getOtyugh().getHealth());

    //Since the mock random generator used for testing always returns false
    //Player will always escape in this case
    assertEquals(false, dungeon1.killPlayer());

  }

  /**
   * Tests if player attacks Otyugh.
   */
  @Test
  public void testSlayMonster() {
    dungeon2 = new DungeonImpl(4, 6, 10, false, rand, 0, 50);
    //When the Otyugh is initialized it is healthy
    dungeon1.getLocations().get(1).setHasOtyugh(true);
    assertEquals(OtyughHealth.healthy, dungeon1.getLocations().get(1).getOtyugh().getHealth());

    //Move player to location having an Otyugh 1 position away
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(2));
    //Player shoots at a distance of 1 in all 4 directions
    dungeon1.slayMonster(1, "N");
    dungeon1.slayMonster(1, "S");
    dungeon1.slayMonster(1, "E");
    dungeon1.slayMonster(1, "W");
    //Test if the Otyugh is injured and not dead when one arrow is shot
    assertNotEquals(OtyughHealth.dead, dungeon1.getLocations().get(1).getOtyugh().getHealth());
    assertEquals(OtyughHealth.injured, dungeon1.getLocations().get(1).getOtyugh().getHealth());

    //Player shoots at a distance of 1 in all 4 directions
    dungeon1.slayMonster(1, "N");
    dungeon1.slayMonster(1, "S");
    dungeon1.slayMonster(1, "E");
    dungeon1.slayMonster(1, "W");
    //Test if the Otyugh is dead when another arrow is shot
    assertEquals(OtyughHealth.dead, dungeon1.getLocations().get(1).getOtyugh().getHealth());

  }

  /**
   * Tests if player has 3 arrows in start.
   */
  @Test
  public void testInitialArrows() {
    assertEquals(3, dungeon1.getPlayer().getArrows());
  }

  /**
   * Tests if player has 3 arrows in start.
   */
  @Test
  public void testReduceArrows() {
    assertEquals(3, dungeon1.getPlayer().getArrows());
    dungeon1.slayMonster(1, "N");
    //The number of arrows reduce after the monster is attacked
    assertEquals(2, dungeon1.getPlayer().getArrows());
  }

  /**
   * Tests if arrows added to locations.
   */
  @Test
  public void testAddArrows() {
    dungeon1.getLocations().get(1).setArrows(3);
    //Move player to location having an arrow
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(1));
    assertEquals(3, dungeon1.getLocations().get(1).getArrows());
  }

  /**
   * Test for Arrows assigned to locations.
   */
  @Test
  public void testArrowsPercentage() {
    Dungeon dungeon3 = new DungeonImpl(5, 5, 10, false, rand, 11, 50);
    dungeon3.addArrows(100);

    //Testing Random Locations having arrows
    assertEquals(true, dungeon1.getLocations().get(1).getArrows() > 0);
    assertEquals(true, dungeon1.getLocations().get(4).getArrows() > 0);
    assertEquals(true, dungeon1.getLocations().get(7).getArrows() > 0);
    assertEquals(true, dungeon1.getLocations().get(11).getArrows() > 0);
    assertEquals(true, dungeon1.getLocations().get(15).getArrows() > 0);
    assertEquals(true, dungeon1.getLocations().get(19).getArrows() > 0);

    Dungeon dungeon4 = new DungeonImpl(5, 6, 10, false, rand, 11, 50);
    dungeon3.addArrows(50);

    //Total No of locations are 30 (5 rows * 6 columns), half locations should have arrows
    assertEquals(15, dungeon4.getNoOfArrowLocations());
  }

  /**
   * Tests add Arrows to Dungeon.
   */
  @Test
  public void testPickArrows() {
    dungeon1.getLocations().get(1).setArrows(1);
    //Move player to location having an arrow
    dungeon1.getPlayer().moveTo(dungeon1.getLocations().get(1));
    assertEquals(true, dungeon1.pickArrow());
    //Check total arrows with the player
    assertEquals(4, dungeon1.getPlayer().getArrows());
  }

  /**
   * Tests interconnectivity degree.
   */
  @Test
  public void testGetInterconnectivity() {
    assertEquals(11, dungeon1.getInterconnectivityDegree());
    assertEquals(10, dungeon2.getInterconnectivityDegree());
  }

  /**
   * Tests Random number generator.
   */
  @Test
  public void testGetRandom() {
    assertEquals(11, rand.getRandomNumber(10, 20));
    assertEquals(18, rand.getRandomNumber(0, 20));
    assertEquals(1, rand.getRandomNumber(0, 10));
  }

  /**
   * Tests Rows.
   */
  @Test
  public void testGetRows() {
    assertEquals(dungeon1.getRows(), 4);
    assertEquals(dungeon2.getRows(), 4);
  }

  /**
   * Tests Columns.
   */
  @Test
  public void testGetColumns() {
    assertEquals(dungeon1.getColumns(), 5);
    assertEquals(dungeon2.getColumns(), 6);
  }

  /**
   * Test for Treasures assigned to locations.
   */
  @Test
  public void testTreasuresPercentage() {
    Dungeon dungeon3 = new DungeonImpl(5, 5, 10, false, rand, 11, 50);
    dungeon3.addTreasure(50);
    assertEquals(5, dungeon3.getNoOfTreasureCaves());
    assertEquals(10, dungeon3.getNoOfCaves());
  }

  /**
   * Tests player starts at the Start Location.
   */
  @Test
  public void testPlayerStart() {

    dungeon1.getLocations().get(1).setStart(true);
    start = dungeon1.getStart();
    assertEquals(start, dungeon1.getPlayer().getCurrLocation());

  }

  /**
   * Tests path between start and end Location.
   */
  @Test
  public void testStartEndLength() {
    int count = 0;
    boolean next = true;

    //Player starts at Start Locations
    start = dungeon1.getStart();
    assertEquals(start, dungeon1.getPlayer().getCurrLocation());

    //Loop continues till player reaches destination after finding the shortest path to end
    while (next) {
      next = dungeon1.movePlayer(Direction.getRandom());
      count++;
    }

    assertEquals(end, dungeon1.getPlayer().getCurrLocation());

    //Assert that moves to the shortest path is at least 5
    assertTrue(count >= 5);

  }

  /**
   * Tests path from one cave to other.
   */
  @Test
  public void testPathToCave() {

    boolean next = true;

    //Player starts at Start Locations
    start = dungeon1.getStart();
    assertEquals(start, dungeon1.getPlayer().getCurrLocation());

    //Loop continues till player reaches end for each cave randomly
    while (next) {
      dungeon1.setEnd();
      next = dungeon1.movePlayer(Direction.getRandom());
    }
    assertEquals(end, dungeon1.getPlayer().getCurrLocation());
  }

  /**
   * Tests player starts at the Start Location.
   */
  @Test
  public void testPlayerEnd() {

    //Setting start location
    dungeon1.getLocations().get(1).setStart(true);

    //Setting End location
    dungeon1.getLocations().get(19).setEnd(true);

    //Moving player to the end location
    dungeon1.movePlayer(Direction.South);
    dungeon1.movePlayer(Direction.East);
    dungeon1.movePlayer(Direction.South);
    dungeon1.movePlayer(Direction.East);
    dungeon1.movePlayer(Direction.South);

    //Tests that player Stops once it reaches End of Dungeon
    assertEquals(false, dungeon1.getPlayer().canMove(Direction.East));
    assertEquals(end, dungeon1.getPlayer().getCurrLocation());
  }

  /**
   * Tests Dungeon Creation.
   */
  @Test
  public void testDungeonDesc() {

    String expected1 = "\n"
        + " Displaying Dungeon Description: \n"
        + "               |                     |          |      \n"
        + " -  C  -   -   C          C          C  -   -   C  -  \n"
        + "    |          |          |          |          |      \n"
        + "\n"
        + "    |          |          |          |          |      \n"
        + "    C  -   -  C  -   -    C          C  -   -   C     \n"
        + "    |          |          |          |          |      \n"
        + "\n"
        + "    |          |          |          |          |      \n"
        + " -  C  -   -   C  -   -   C  -   -   C          T  -  \n"
        + "    |          |          |          |             \n"
        + "\n"
        + "    |          |          |          |             \n"
        + " -  C          C  -   -   T          T          T  -  \n"
        + "               |                     |          |      \n"
        + "\n"
        + "\n"
        + " Dungeon Start Location: 2, Dungeon End Location: 12\n"
        + " \n"
        + " MST with Interconnectivity 11: \n"
        + " Edge-0 source: 18 destination: 3\n"
        + " Edge-1 source: 13 destination: 8\n"
        + " Edge-2 source: 7 destination: 6\n"
        + " Edge-3 source: 15 destination: 10\n"
        + " Edge-4 source: 16 destination: 11\n"
        + " Edge-5 source: 14 destination: 9\n"
        + " Edge-6 source: 5 destination: 0\n"
        + " Edge-7 source: 8 destination: 3\n"
        + " Edge-8 source: 4 destination: 0\n"
        + " Edge-9 source: 17 destination: 16\n"
        + " Edge-10 source: 6 destination: 5\n"
        + " Edge-11 source: 19 destination: 15\n"
        + " Edge-12 source: 14 destination: 10\n"
        + " Edge-13 source: 19 destination: 4\n"
        + " Edge-14 source: 12 destination: 11\n"
        + " Edge-15 source: 7 destination: 2\n"
        + " Edge-16 source: 16 destination: 1\n"
        + " Edge-17 source: 13 destination: 12\n"
        + " Edge-18 source: 9 destination: 8\n"
        + " Edge-19 source: 10 destination: 5\n"
        + " Edge-20 source: 9 destination: 4\n"
        + " Edge-21 source: 15 destination: 0\n"
        + " Edge-22 source: 17 destination: 12\n"
        + " Edge-23 source: 6 destination: 1\n"
        + " Edge-24 source: 1 destination: 0\n"
        + " Edge-25 source: 4 destination: 3\n"
        + " Edge-26 source: 11 destination: 10\n"
        + " Edge-27 source: 11 destination: 6\n"
        + " Edge-28 source: 12 destination: 7\n"
        + " Edge-29 source: 18 destination: 13\n"
        + " \n"
        + " Location's Description:\n"
        + " Location : 0 has neighbours {South=15, East=1, West=4} "
        + "has treasures [SAPPHIRE, SAPPHIRE, SAPPHIRE, DIAMOND, SAPPHIRE, SAPPHIRE]\n"
        + " Location : 1 has neighbours {North=16, South=6, West=0} "
        + "has treasures [RUBY, SAPPHIRE, DIAMOND, RUBY]\n"
        + " Location : 2 has neighbours {South=7} "
        + "has treasures [RUBY, RUBY, SAPPHIRE, DIAMOND, RUBY, SAPPHIRE, SAPPHIRE, SAPPHIRE]\n"
        + " Location : 3 has neighbours {North=18, South=8, East=4} "
        + "has treasures [RUBY, RUBY, RUBY, SAPPHIRE, DIAMOND, DIAMOND, RUBY]\n"
        + " Location : 4 has neighbours {North=19, South=9, East=0, West=3} "
        + "has treasures [RUBY, SAPPHIRE, RUBY, SAPPHIRE, RUBY, SAPPHIRE]\n"
        + " Location : 5 has neighbours {North=0, South=10, East=6} "
        + "has treasures [SAPPHIRE, SAPPHIRE, DIAMOND, DIAMOND, "
        + "SAPPHIRE, DIAMOND, RUBY, RUBY, RUBY]\n"
        + " Location : 6 has neighbours {North=1, South=11, East=7, West=5} "
        + "has treasures [SAPPHIRE, RUBY, SAPPHIRE, SAPPHIRE]\n"
        + " Location : 7 has neighbours {North=2, South=12, West=6} "
        + "has treasures [DIAMOND, DIAMOND, DIAMOND, RUBY]\n"
        + " Location : 8 has neighbours {North=3, South=13, East=9} "
        + "has treasures [RUBY, SAPPHIRE, DIAMOND, DIAMOND, RUBY, RUBY]\n"
        + " Location : 9 has neighbours {North=4, South=14, West=8} "
        + "has treasures [RUBY, RUBY, RUBY, SAPPHIRE, SAPPHIRE]\n"
        + " Location : 10 has neighbours {North=5, South=15, East=11, West=14} "
        + "has treasures [SAPPHIRE, RUBY]\n"
        + " Location : 11 has neighbours {North=6, South=16, East=12, West=10} "
        + "has treasures [SAPPHIRE, SAPPHIRE, SAPPHIRE]\n"
        + " Location : 12 has neighbours {North=7, South=17, East=13, West=11} "
        + "has treasures [DIAMOND, RUBY, DIAMOND, SAPPHIRE, SAPPHIRE, DIAMOND]\n"
        + " Location : 13 has neighbours {North=8, South=18, West=12} "
        + "has treasures [SAPPHIRE, RUBY]\n"
        + " Location : 14 has neighbours {North=9, East=10} "
        + "has treasures []\n"
        + " Location : 15 has neighbours {North=0, West=19} "
        + "has treasures [SAPPHIRE]\n"
        + " Location : 16 has neighbours {North=11, South=1, East=17} "
        + "has treasures [DIAMOND, RUBY, SAPPHIRE]\n"
        + " Location : 17 has neighbours {North=12, West=16} "
        + "has treasures []\n"
        + " Location : 18 has neighbours {North=13, South=3} "
        + "has treasures []\n"
        + " Location : 19 has neighbours {South=4, East=15} "
        + "has treasures []";

    assertEquals(expected1, dungeon1.displayDungeon());

    String expected2 = "Displaying Dungeon Description: \n"
        + "                                          \n"
        + "    C  -   -  C  -   -  C  -   -  C  -   -  C  -   -  T     \n"
        + "              |         |         |        |          |      \n"
        + "\n"
        + "              |         |         |         |         |      \n"
        + "    T  -   -  C  -   -  C         C  -   -  C  -   -  C     \n"
        + "    |         |         |         |         |         |      \n"
        + "\n"
        + "    |         |         |         |         |         |      \n"
        + "    C         T  -   -  C  -   -  C  -   -  C  -   -  C     \n"
        + "              |         |         |         |      \n"
        + "\n"
        + "              |         |         |         |      \n"
        + "    C  -   -  T  -   -  C  -   -  C  -   -  C  -   -  T     \n"
        + "                                          \n"
        + "\n"
        + "\n"
        + " Dungeon Start Location: 9, Dungeon End Location: 19\n"
        + " \n"
        + " MST with Interconnectivity 10: \n"
        + " Edge-0 source: 21 destination: 15\n"
        + " Edge-1 source: 16 destination: 15\n"
        + " Edge-2 source: 10 destination: 4\n"
        + " Edge-3 source: 1 destination: 0\n"
        + " Edge-4 source: 20 destination: 19\n"
        + " Edge-5 source: 8 destination: 2\n"
        + " Edge-6 source: 11 destination: 5\n"
        + " Edge-7 source: 22 destination: 16\n"
        + " Edge-8 source: 23 destination: 17\n"
        + " Edge-9 source: 11 destination: 10\n"
        + " Edge-10 source: 23 destination: 22\n"
        + " Edge-11 source: 7 destination: 6\n"
        + " Edge-12 source: 14 destination: 13\n"
        + " Edge-13 source: 3 destination: 2\n"
        + " Edge-14 source: 9 destination: 3\n"
        + " Edge-15 source: 15 destination: 9\n"
        + " Edge-16 source: 10 destination: 9\n"
        + " Edge-17 source: 7 destination: 1\n"
        + " Edge-18 source: 8 destination: 7\n"
        + " Edge-19 source: 13 destination: 7\n"
        + " Edge-20 source: 12 destination: 6\n"
        + " Edge-21 source: 21 destination: 20\n"
        + " Edge-22 source: 19 destination: 18\n"
        + " Edge-23 source: 5 destination: 4\n"
        + " Edge-24 source: 2 destination: 1\n"
        + " Edge-25 source: 4 destination: 3\n"
        + " Edge-26 source: 17 destination: 11\n"
        + " Edge-27 source: 22 destination: 21\n"
        + " Edge-28 source: 15 destination: 14\n"
        + " Edge-29 source: 16 destination: 10\n"
        + " Edge-30 source: 14 destination: 8\n"
        + " Edge-31 source: 20 destination: 14\n"
        + " Edge-32 source: 17 destination: 16\n"
        + " \n"
        + " Location's Description:\n"
        + " Location : 0 has neighbours {East=1} "
        + "has treasures [RUBY, RUBY, SAPPHIRE]\n"
        + " Location : 1 has neighbours {South=7, East=2, West=0}"
        + " has treasures [SAPPHIRE, DIAMOND, SAPPHIRE, RUBY]\n"
        + " Location : 2 has neighbours {South=8, East=3, West=1} "
        + "has treasures [DIAMOND, DIAMOND, SAPPHIRE, RUBY, RUBY, SAPPHIRE, DIAMOND]\n"
        + " Location : 3 has neighbours {South=9, East=4, West=2} "
        + "has treasures [SAPPHIRE, SAPPHIRE, SAPPHIRE, RUBY, DIAMOND, SAPPHIRE, DIAMOND, RUBY]\n"
        + " Location : 4 has neighbours {South=10, East=5, West=3} "
        + "has treasures [RUBY, RUBY, SAPPHIRE, SAPPHIRE, RUBY, RUBY]\n"
        + " Location : 5 has neighbours {South=11, West=4} has treasures []\n"
        + " Location : 6 has neighbours {East=7, South=12} has treasures []\n"
        + " Location : 7 has neighbours {East=8, South=13, West=6, North=1}"
        + " has treasures [SAPPHIRE, SAPPHIRE, SAPPHIRE, "
        + "DIAMOND, DIAMOND, SAPPHIRE, SAPPHIRE, DIAMOND]\n"
        + " Location : 8 has neighbours {South=14, West=7, North=2} "
        + "has treasures [SAPPHIRE, DIAMOND, SAPPHIRE, RUBY, "
        + "DIAMOND, DIAMOND, DIAMOND, SAPPHIRE, DIAMOND]\n"
        + " Location : 9 has neighbours {South=15, East=10, North=3} "
        + "has treasures [RUBY, DIAMOND, DIAMOND, DIAMOND, DIAMOND]\n"
        + " Location : 10 has neighbours {East=11, South=16, West=9, North=4} "
        + "has treasures [DIAMOND, RUBY, DIAMOND, RUBY, SAPPHIRE]\n"
        + " Location : 11 has neighbours {South=17, West=10, North=5} "
        + "has treasures [RUBY, DIAMOND, DIAMOND, SAPPHIRE, DIAMOND, SAPPHIRE]\n"
        + " Location : 12 has neighbours {North=6} "
        + "has treasures [SAPPHIRE, DIAMOND, DIAMOND, RUBY, SAPPHIRE, "
        + "SAPPHIRE, RUBY, SAPPHIRE, SAPPHIRE, RUBY]\n"
        + " Location : 13 has neighbours {East=14, North=7} has treasures []\n"
        + " Location : 14 has neighbours {East=15, South=20, West=13, North=8}"
        + " has treasures [DIAMOND, SAPPHIRE, RUBY, DIAMOND, DIAMOND, RUBY, SAPPHIRE]\n"
        + " Location : 15 has neighbours {South=21, East=16, West=14, North=9} "
        + "has treasures [SAPPHIRE, SAPPHIRE, DIAMOND, RUBY, SAPPHIRE, RUBY]\n"
        + " Location : 16 has neighbours {South=22, East=17, West=15, North=10} "
        + "has treasures [RUBY, RUBY, RUBY, SAPPHIRE, DIAMOND, "
        + "DIAMOND, RUBY, SAPPHIRE, RUBY, SAPPHIRE]\n"
        + " Location : 17 has neighbours {South=23, West=16, North=11} "
        + "has treasures [RUBY, SAPPHIRE, RUBY, RUBY, RUBY, RUBY, RUBY]\n"
        + " Location : 18 has neighbours {East=19} has treasures [DIAMOND, RUBY, DIAMOND]\n"
        + " Location : 19 has neighbours {East=20, West=18} has treasures []\n"
        + " Location : 20 has neighbours {East=21, West=19, North=14} "
        + "has treasures [SAPPHIRE, DIAMOND, SAPPHIRE, RUBY]\n"
        + " Location : 21 has neighbours {East=22, West=20, North=15} "
        + "has treasures [DIAMOND, SAPPHIRE, RUBY, RUBY, DIAMOND, RUBY, RUBY, SAPPHIRE]\n"
        + " Location : 22 has neighbours {East=23, West=21, North=16} "
        + "has treasures [DIAMOND, RUBY, RUBY, DIAMOND, "
        + "RUBY, DIAMOND, RUBY, SAPPHIRE, SAPPHIRE, SAPPHIRE]\n"
        + " Location : 23 has neighbours {West=22, North=17} has treasures []";

    assertEquals(expected2, dungeon2.displayDungeon());
  }

}