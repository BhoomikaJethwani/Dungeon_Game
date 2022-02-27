package dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import monster.Otyugh;
import monster.OtyughImpl;
import player.Thief;
import player.ThiefImpl;
import treasure.Treasure;

/**
 * This class represents a Location in dungeon. It stores the coordinates and the ID.
 */
public class LocationImpl implements Location {

  int x;
  int y;
  int id;
  List<Treasure> treasure;
  List<Direction> directions;
  Map<Direction, Integer> neighbours;
  int entrances;
  LocationType type;
  boolean start;
  boolean end;
  boolean hasTreasure;
  boolean hasThief;
  Thief thief;


  int arrows;
  boolean hasOtyugh;
  Otyugh otyugh;
  boolean hasPit;

  /**
   * Constructs a Location with x, y coordinate and an ID.
   *
   * @param x  the x-coordinate
   * @param y  the y-coordinate
   * @param id the Location ID
   */
  public LocationImpl(int x, int y, int id) {
    this.x = x;
    this.y = y;
    this.id = id;

    this.hasThief = false;
    this.hasPit = false;
    this.treasure = new ArrayList<Treasure>();
    this.directions = new ArrayList<Direction>();
    this.neighbours = new HashMap<Direction, Integer>();
    this.entrances = 0;
    this.type = null;
    this.start = false;
    this.end = false;
    this.arrows = 0;
    this.hasOtyugh = false;
    hasTreasure = false;
  }

  @Override
  public boolean isHasThief() {
    return hasThief;
  }

  @Override
  public Thief getThief() {
    return thief;
  }

  @Override
  public int getArrows() {
    return arrows;
  }

  @Override
  public void removeArrows() {
    arrows--;
  }

  @Override
  public Otyugh getOtyugh() {
    return otyugh;
  }

  @Override
  public boolean isHasOtyugh() {
    return hasOtyugh;
  }

  @Override
  public void setHasOtyugh(boolean hasOtyugh) {

    if (hasOtyugh) {
      otyugh = new OtyughImpl();
    }

    this.hasOtyugh = hasOtyugh;
  }

  @Override
  public LocationType getType() {
    return type;
  }

  @Override
  public boolean isStart() {
    return start;
  }

  @Override
  public void setStart(boolean start) {
    this.start = start;
  }

  @Override
  public boolean isEnd() {
    return end;
  }

  @Override
  public void setEnd(boolean end) {
    this.end = end;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public boolean hasTreasure() {
    return hasTreasure;
  }

  @Override
  public boolean isCave() {
    return type == LocationType.Cave;
  }

  @Override
  public void setEntrances() {

    if (directions.size() == 2) {
      entrances = 2;
      type = LocationType.Tunnel;
    } else {
      type = LocationType.Cave;

      switch (directions.size()) {
        case 1:
          entrances = 1;
          break;
        case 3:
          entrances = 3;
          break;
        case 4:
          entrances = 4;
          break;
        default:
          break;
      }
    }
  }


  @Override
  public void setDirections(Direction directions) {
    this.directions.add(directions);
  }

  @Override
  public void setNeighbours(int neighbourId, Direction direction) {
    this.neighbours.put(direction, neighbourId);
  }

  @Override
  public List<Direction> getDirections() {
    return directions;
  }

  @Override
  public void setTreasure() {
    hasTreasure = true;
    treasure.add(Treasure.getRandom());
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Location)) {
      return false;
    }

    Location obj = (Location) o;
    return obj.getX() == this.getX() && obj.getY() == this.getY();
  }


  @Override
  public int hashCode() {
    return Long.hashCode(this.getX());
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }


  @Override
  public List<Treasure> getTreasures() {
    return treasure;
  }

  @Override
  public Map<Direction, Integer> getNeighbours() {
    return neighbours;
  }


  @Override
  public void removeTreasure(Treasure t) {
    treasure.remove(t);
    if (treasure.isEmpty()) {
      hasTreasure = false;
    }
  }

  @Override
  public String toString() {
    if (type == LocationType.Tunnel) {
      if (directions.contains(Direction.East) && directions.contains(Direction.North)) {

        return "| \n  -- ";
      }
    } else {
      return " " + getTreasures();
    }

    return "";
  }

  @Override
  public void setArrows(int i) {
    this.arrows = i;
  }

  @Override
  public void addThief() {
    thief = new ThiefImpl();
    this.hasThief = true;
  }

  @Override
  public void addPit() {
    hasPit = true;
  }

  @Override
  public boolean isHasPit() {
    return hasPit;
  }


}
