package player;

import dungeon.Direction;
import dungeon.Location;
import java.util.ArrayList;
import java.util.List;
import treasure.Treasure;

/**
 * This class represents a Player. A player moves across the dungeon from start to end and collects
 * treasures.
 */
public class PlayerImpl implements Player {

  private Location currentLocation;
  private final List<Treasure> treasures;
  private int arrows;

  @Override
  public boolean isDead() {
    return dead;
  }

  @Override
  public void setDead(boolean dead) {
    this.dead = dead;
  }

  boolean dead;

  /**
   * Constructs a Player with at a Start Location of dungeon.
   */
  public PlayerImpl(Location startLocation) {
    this.currentLocation = startLocation;
    treasures = new ArrayList<Treasure>();
    this.arrows = 3;
    dead = false;
  }


  @Override
  public int getArrows() {
    return arrows;
  }

  @Override
  public String playerTreasureDesc() {
    return "\n Player's Treasures Collected: " + treasures;
  }

  @Override
  public String playerDesc() {
    return "\n You are in a " + currentLocation.getType().toString()
        + "\n Doors lead to the " + currentLocation.getNeighbours().keySet();
  }

  @Override
  public void moveTo(Location location) {
    currentLocation = location;
  }

  @Override
  public boolean canMove(Direction direction) {
    return currentLocation.getDirections().contains(direction);
  }

  @Override
  public void collectTreasure(Treasure treasure) {
    this.treasures.add(treasure);
  }

  @Override
  public void collectArrows(int arrows) {
    this.arrows += arrows;
  }

  @Override
  public List<Treasure> getTreasure() {
    return treasures;
  }

  @Override
  public int getCurrLocation() {
    if (currentLocation != null) {
      return currentLocation.getId();
    }
    return 0;
  }

  @Override
  public void removeArrow() {
    arrows--;
  }

  @Override
  public void removeTreasures() {
    if (treasures != null) {
      treasures.clear();
    }

  }
}
