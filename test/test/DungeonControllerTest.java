package test;

import static org.junit.Assert.assertEquals;

import control.DungeonController;
import control.MVCController;
import control.MVCControllerIntr;
import dungeon.Dungeon;
import dungeon.MockDungeon;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests a Dungeon Controller Interface and if it calls the methods of the Model
 * correctly.
 */
public class DungeonControllerTest {

  Dungeon d;
  StringReader input;
  Appendable output;
  DungeonController dungeon;
  MVCControllerIntr mvcDungeon;

  /**
   * Setting up objects for all the tests.
   */
  @Before
  public void setUp() {
    output = new StringBuilder();
    d = new MockDungeon(output);

  }

  @Test
  public void testMVC() {
    output = new StringBuilder();
    d = new MockDungeon(output);
    mvcDungeon = new MVCController(d);
    mvcDungeon.playGame();
    String expected = "String expected = "
        + "        + \" Welcome to the Mighty Dungeon !! "
        + "Save yourself from the Otyugh and Run while you can !!\"\n"
        + "        + \"Method playerAtEnd called successfully !!null"
        + "Method locationHasTreasure called successfully !!\"\n"
        + "        + \"Method locationHasArrows called successfully !!"
        + "Method getSmell called successfully !!\\n\"\n"
        + "        +  \"Where to?Method movePlayer called successfully !!"
        + "Method isPlayerDead called successfully !!\"\n"
        + "        + \"Method playerAtEnd called successfully !!null"
        + "Method locationHasTreasure called successfully !!\"\n"
        + "        + \"Method locationHasArrows called successfully !!"
        + "Method getSmell called successfully !!\\n\"\n"
        + "        + \"\\n\"\n"
        + "        + \"Where to?Method movePlayer called successfully !!"
        + "Method isPlayerDead called successfully !!\"\n"
        + "        + \"Method playerAtEnd called successfully !!null\"\n"
        + "        + \"Method locationHasTreasure called successfully !!"
        + "Method locationHasArrows called successfully !!\"\n"
        + "        + \"Method getSmell called successfully !!\\n\"\n"
        + "        \\n\"\n";
    assertEquals(expected, output.toString());
  }

}