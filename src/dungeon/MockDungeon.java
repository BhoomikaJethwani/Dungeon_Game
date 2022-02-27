package dungeon;

import java.io.IOException;
import java.util.List;
import player.Player;
import treasure.Treasure;

/**
 * This class represents a Mock Dungeon for testing.
 */
public class MockDungeon implements Dungeon {

  private final Appendable out;

  /**
   * Constructor for the mock.
   */
  public MockDungeon(Appendable out) {
    this.out = out;
  }


  @Override
  public int[][] getDungeon() {
    try {
      out.append("Method isWrap called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return new int[0][];
  }

  @Override
  public boolean isWrap() {
    try {
      out.append("Method isWrap called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public int getPercentage() {
    try {
      out.append("Method getPercetage called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public int getNoOfArrowLocations() {
    try {
      out.append("Method getNoOfArrowLocations called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public int getNoOfOtyugh() {
    try {
      out.append("Method getNoOfOtyugh called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public List<Location> getLocations() {
    try {
      out.append("Method getLocation called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return null;
  }

  @Override
  public int getNoOfTreasureCaves() {
    try {
      out.append("Method getNoOfTreasureCaves called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public Location getLocationStart() {
    try {
      out.append("Method getLocationStart called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return null;
  }

  @Override
  public Location getLocationEnd() {
    try {
      out.append("Method getLocationEnd called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return null;
  }

  @Override
  public Player getPlayer() {
    try {
      out.append("Method getPlayer called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return null;
  }

  @Override
  public void setLocationType() {
    try {
      out.append("Method getLocation called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public boolean movePlayer(Direction direction) {
    try {
      out.append("Method movePlayer called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public boolean isPlayerDead() {
    try {
      out.append("Method isPlayerDead called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public boolean pickTreasure(Treasure t) {
    try {
      out.append("Method pickTreasure called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public boolean pickArrow() {
    try {
      out.append("Method pickArrow called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public boolean locationHasTreasure() {
    try {
      out.append("Method locationHasTreasure called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public int locationHasArrows() {
    try {
      out.append("Method locationHasArrows called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public int playerHasArrows() {
    try {
      out.append("Method playerHasArrows called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public String exitDungeon() {
    try {
      out.append("Method exitDungeon called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return "null";
  }

  @Override
  public void setEnd() {
    try {
      out.append("Method setEnd called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public void getEdges() {
    try {
      out.append("Method getEdges called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public int getRows() {
    try {
      out.append("Method getRows called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public Location getStart() {
    try {
      out.append("Method getStart called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return null;
  }

  @Override
  public Location getEnd() {
    try {
      out.append("Method getEnd called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return null;
  }

  @Override
  public int getColumns() {
    try {
      out.append("Method getColumns called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public void increaseInterconnectivity() {
    try {
      out.append("Method increaseInterconnectivity called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public int getInterconnectivityDegree() {
    try {
      out.append("Method getInterconnectivityDegree called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public int getNoOfTunnels() {
    try {
      out.append("Method getNoOfTunnels called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public int getNoOfCaves() {
    try {
      out.append("Method getNoOfCaves called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return 0;
  }

  @Override
  public void addTreasure(int percentage) {
    try {
      out.append("Method addTreasure called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public void addOtyugh(int noOfOtyugh) {
    try {
      out.append("Method addOtyugh called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public void addArrows(int percentage) {
    try {
      out.append("Method addArrows called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
  }

  @Override
  public boolean slayMonster(int caves, String direction) {
    try {
      out.append("Method slayMonster called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public boolean killPlayer() {
    try {
      out.append("Method killPlayer called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public Smell getSmell() {
    try {
      out.append("Method getSmell called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return Smell.NoSmell;
  }

  @Override
  public String displayDungeon() {
    try {
      out.append("Method displayDungeon called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return "null";
  }

  @Override
  public boolean playerAtEnd() {
    try {
      out.append("Method playerAtEnd called successfully !!");
    } catch (IOException ex) {
      throw new IllegalStateException("Appendable failed !!");
    }
    return false;
  }

  @Override
  public String playerDescription() {
    return "null";
  }

  @Override
  public Object getTreasures() {
    return "null";
  }

  @Override
  public int getArrows() {
    return 0;
  }

}
