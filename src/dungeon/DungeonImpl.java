package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import monster.OtyughHealth;
import random.RandomInterface;
import player.Player;
import player.PlayerImpl;
import treasure.Treasure;

/**
 * This class represents a Dungeon. A Dungeon has caves and tunnels and a player moving across the
 * dungeon.
 */
public class DungeonImpl implements Dungeon {


  int[][] dungeon;
  int noOfTunnels;
  int noOfCaves;
  int noOfRows;
  int noOfColumns;
  int interconnectivityDegree;
  boolean wrap;
  int vertices;
  int[] parent = new int[vertices];
  List<Location> locations;
  int locationStart;
  int locationEnd;
  List<Edges> allEdges;
  List<Edges> paths;
  List<Edges> leftoverEdges;
  List<Edges> mst;
  Player player;
  int noOfTreasureCaves;
  RandomInterface rand;
  private int noOfArrowLocations;
  int noOfOtyugh;
  int locEnd;
  int percentage;

  /**
   * Constructs a Dungeon with random number of rows, columns, interconnectivity and wrapping.
   */
  public DungeonImpl(RandomInterface rand2) {
    this.noOfColumns = rand2.getRandomNumber(5, 20);
    this.noOfRows = rand2.getRandomNumber(5, 20);
    this.interconnectivityDegree = rand2.getRandomNumber(5, 20);
    dungeon = new int[noOfRows][noOfColumns];
    locations = new ArrayList<Location>();
    allEdges = new ArrayList<Edges>();
    paths = new ArrayList<Edges>();
    mst = new ArrayList<Edges>();
    vertices = noOfColumns * noOfRows;
    leftoverEdges = new ArrayList<Edges>();
    rand = rand2;
    this.wrap = rand.nextBoolean();
    getPotentialPath();
    kruskal();

    increaseInterconnectivity();

    setLocationType();
    setStart();
    setEnd();
    locEnd = locationEnd;
    addArrows(rand2.getRandomNumber(0, 100));
    addTreasure(rand2.getRandomNumber(0, 100));
    initializePlayer();
    noOfCaves = getNoOfCaves();
    noOfTunnels = getNoOfTunnels();
    this.noOfOtyugh = rand2.getRandomNumber(5, 10);
    addOtyugh(noOfOtyugh);

    if (noOfColumns < 5 && noOfRows < 5) {
      throw new IllegalArgumentException("No of Rows or Columns cannot be less than 5.");
    }
  }

  /**
   * Constructs a Dungeon with given number of rows, columns, interconnectivity and wrapping.
   *
   * @param noOfRows                the number of rows
   * @param noOfColumns             the number of columns
   * @param interconnectivityDegree the interconnectivity degree
   * @param wrap                    the wrapping value true or false
   * @param rand1                   Random Interface mocked
   * @param noOfOtyugh              the No of Otyugh in the caves
   * @param percentage              the percentage of treasures and arrows
   */
  public DungeonImpl(int noOfRows, int noOfColumns, int interconnectivityDegree, boolean wrap,
      RandomInterface rand1, int noOfOtyugh, int percentage)
      throws IllegalArgumentException {
    this.noOfColumns = noOfColumns;
    this.noOfRows = noOfRows;
    this.interconnectivityDegree = interconnectivityDegree;
    dungeon = new int[noOfRows][noOfColumns];
    locations = new ArrayList<Location>();
    allEdges = new ArrayList<Edges>();
    paths = new ArrayList<Edges>();
    mst = new ArrayList<Edges>();
    vertices = noOfColumns * noOfRows;
    leftoverEdges = new ArrayList<Edges>();
    this.wrap = wrap;
    getPotentialPath();
    kruskal();
    rand = rand1;
    increaseInterconnectivity();

    setLocationType();
    setStart();
    setEnd();
    noOfCaves = getNoOfCaves();
    this.percentage = percentage;
    addArrows(percentage);
    addTreasure(percentage);
    initializePlayer();

    noOfTunnels = getNoOfTunnels();
    this.noOfOtyugh = noOfOtyugh;
    addOtyugh(this.noOfOtyugh);
    addThief();
    addPit();

    if (noOfColumns < 5 && noOfRows < 5) {
      throw new IllegalArgumentException("No of Rows or Columns cannot be less than 5.");
    }
  }

  @Override
  public int[][] getDungeon() {
    return dungeon;
  }


  @Override
  public boolean isWrap() {
    return wrap;
  }

  @Override
  public int getPercentage() {
    return percentage;
  }


  @Override
  public int getNoOfArrowLocations() {
    return noOfArrowLocations;
  }

  @Override
  public int getNoOfOtyugh() {
    return noOfOtyugh;
  }

  @Override
  public List<Location> getLocations() {
    return locations;
  }

  @Override
  public int getNoOfTreasureCaves() {
    return noOfTreasureCaves;
  }


  @Override
  public Location getLocationStart() {
    return locations.get(locationStart);
  }

  @Override
  public Location getLocationEnd() {
    return locations.get(locationEnd);
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public void setLocationType() {
    for (int i = 0; i < locations.size(); i++) {
      locations.get(i).setEntrances();
    }
  }

  @Override
  public boolean movePlayer(Direction direction) {

    if (killPlayer()) {
      player.setDead(true);
      return false;
    }

    if (player.canMove(direction)) {

      int nextLocation = locations.get(player.getCurrLocation()).getNeighbours()
          .get(direction);
      player.moveTo(locations.get(nextLocation));

      //Check if location has Thief
      if (locations.get(player.getCurrLocation()).isHasThief()) {
        locations.get(player.getCurrLocation()).getThief().stealTreasures(player.getTreasure());
        player.removeTreasures();
      }

      //Check if location has Pit
      if (locations.get(player.getCurrLocation()).isHasThief()) {
        player.setDead(true);
      }

      return true;
    }
    return true;
  }

  @Override
  public boolean isPlayerDead() {
    return player.isDead();
  }

  @Override
  public boolean pickTreasure(Treasure t) {

    int currLocation = player.getCurrLocation();

    // Player collects all the treasure at the location
    if (locations.get(currLocation).hasTreasure()) {
      //while (locations.get(nextLocation).hasTreasure()) {
      player.collectTreasure(t);
      locations.get(currLocation).removeTreasure(t);
      return true;
    }

    return false;
  }

  @Override
  public boolean pickArrow() {

    int currLocation = player.getCurrLocation();

    // Player collects all the treasure at the location
    if (locations.get(currLocation).getArrows() > 0) {
      //while (locations.get(nextLocation).hasTreasure()) {
      player.collectArrows(locations.get(currLocation).getArrows());
      locations.get(currLocation).removeArrows();
      return true;
    }

    return false;
  }

  @Override
  public boolean locationHasTreasure() {
    return locations.get(player.getCurrLocation()).hasTreasure();
  }

  @Override
  public int locationHasArrows() {
    return locations.get(player.getCurrLocation()).getArrows();
  }

  @Override
  public int playerHasArrows() {
    return player.getArrows();
  }

  @Override
  public String exitDungeon() {
    return "\n Player has exited the Dungeon with the treasures: !!" + player.getTreasure();
  }

  private void initializePlayer() {
    player = new PlayerImpl(locations.get(locationStart));
  }

  private void addPit() {

    for (int i = 0; i < 2; i++) {
      int min = 0;
      int max = locations.size() - 2;
      //Random rand = new Random();

      int j = rand.getRandomNumber(min, max);
      locations.get(j).addPit();
    }

  }

  private void addThief() {
    int min = 0;
    int max = locations.size() - 2;
    //Random rand = new Random();

    int j = rand.getRandomNumber(min, max);
    locations.get(j).addThief();
  }

  private void setStart() {

    int min = 0;
    int max = locations.size() / 2;
    //Random rand = new Random();

    int j = rand.getRandomNumber(min, max);

    for (int i = 0; i < locations.size() / 2; i++) {
      if (i == j && locations.get(j).isCave()) {
        locations.get(j).setStart(true);
        locationStart = j;
        break;
      }
    }
  }

  @Override
  public void setEnd() {

    //The graph is set such that the location ID of start + 10
    // will give the end location with at least 5 edges from start
    //if(locations.get(noOfColumns * noOfRows - 1).isStart())
    locations.get(locations.get(locationStart + 7).getId()).setEnd(true);
    locationEnd = locations.get(locationStart + 7).getId();
  }

  private void kruskal() {

    Random random = new Random();

    for (int i = 0; i < allEdges.size(); i++) {
      paths.add(allEdges.get(i));
    }

    //create a parent list []
    parent = new int[vertices];

    //make set
    makeSets(parent);

    //process vertices – 1 edges
    int index = 0;
    int max = 0;
    int min = 0;
    while (index < vertices + noOfColumns + noOfColumns + 2) {
      //rand.nextInt((maxTr - minTr) + 1) + minTr;
      max = (this.paths.size() - 1);
      int i = random.nextInt((max - min) + 1) + min;
      Edges edge = paths.get(i);
      //check if adding this edge creates a cycle
      int x_set = find(parent, edge.getSource());
      int y_set = find(parent, edge.getDestination());
      //System.out.println("1");
      if (x_set == y_set) {
        if (!leftoverEdges.contains(edge)) {
          leftoverEdges.add(edge);
        }
      } else {
        //add it to our final result
        mst.add(edge);

        //Add direction and neighbour ID to that location

        if (wrap) {
          //Wrapping North South
          if (edge.getSource() - edge.getDestination() == (noOfRows * noOfColumns) - noOfColumns) {
            locations.get(edge.getSource()).setDirections(Direction.South);
            locations.get(edge.getSource()).setNeighbours(edge.getDestination(), Direction.South);

            locations.get(edge.getDestination()).setDirections(Direction.North);
            locations.get(edge.getDestination()).setNeighbours(edge.getSource(), Direction.North);
          }

          //East West
          else if (edge.getSource() - edge.getDestination() + 1 == noOfColumns) {
            locations.get(edge.getSource()).setDirections(Direction.East);
            locations.get(edge.getSource()).setNeighbours(edge.getDestination(), Direction.East);

            locations.get(edge.getDestination()).setDirections(Direction.West);
            locations.get(edge.getDestination()).setNeighbours(edge.getSource(), Direction.West);
          }
        }
        //North South
        if (edge.getSource() - edge.getDestination() == noOfColumns) {
          locations.get(edge.getSource()).setDirections(Direction.North);
          locations.get(edge.getSource()).setNeighbours(edge.getDestination(), Direction.North);

          locations.get(edge.getDestination()).setDirections(Direction.South);
          locations.get(edge.getDestination()).setNeighbours(edge.getSource(), Direction.South);
        }

        //East West
        else if (edge.getSource() - edge.getDestination() == 1) {
          locations.get(edge.getSource()).setDirections(Direction.West);
          locations.get(edge.getSource()).setNeighbours(edge.getDestination(), Direction.West);

          locations.get(edge.getDestination()).setDirections(Direction.East);
          locations.get(edge.getDestination()).setNeighbours(edge.getSource(), Direction.East);
        }


      }
      paths.remove(i);
      index++;
      union(parent, x_set, y_set);
    }

  }

  private void makeSets(int[] parent) {
    for (int i = 0; i < (noOfRows * noOfColumns); i++) {
      parent[i] = i;
    }
  }

  private int find(int[] parent, int vertex) {
    //chain of parent pointers from x upwards through the tree
    // until an element is reached whose parent is itself
    if (parent[vertex] != vertex) {
      return find(parent, parent[vertex]);
    }
    return vertex;
  }

  private void union(int[] parent, int x, int y) {
    int x_set_parent = find(parent, x);
    int y_set_parent = find(parent, y);
    //make x as parent of y
    parent[y_set_parent] = x_set_parent;
  }


  @Override
  public void getEdges() {
    for (int i = 0; i < allEdges.size(); i++) {
      System.out.println("All Edge " + (i + 1) + ": " + allEdges.get(i).getSource()
          + " to " + allEdges.get(i).getDestination() + "");
    }

    for (int i = 0; i < leftoverEdges.size(); i++) {
      System.out.println("Leftover Edge " + (i + 1) + ": " + leftoverEdges.get(i).getSource()
          + " to " + leftoverEdges.get(i).getDestination() + "");
    }

  }

  private void addEdge(int x1, int y1, int x2, int y2) {
    Edges edge = new Edges(locations.get(dungeon[x1][y1]), locations.get(dungeon[x2][y2]));
    allEdges.add(edge); //add to total edges
  }

  private void getPotentialPath() {
    int count = -1;

    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        locations.add(new LocationImpl(i, j, ++count));
        dungeon[i][j] = count;
      }
    }

    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {

        if ((i - 1 >= 0)) {
          if (!(allEdges.contains(
              new Edges(locations.get(dungeon[i - 1][j]), locations.get(dungeon[i][j]))))) {
            addEdge(i - 1, j, i, j);
          }
          //Add (i-1,j)
        }

        if ((j - 1 >= 0)) {
          if (!(allEdges.contains(
              new Edges(locations.get(dungeon[i][j - 1]), locations.get(dungeon[i][j]))))) {
            addEdge(i, j - 1, i, j);
          }
          //Add (i,j-1)
        }

        if ((j + 1 < noOfColumns)) {
          //Add (i,j+1)
          if (!(allEdges.contains(
              new Edges(locations.get(dungeon[i][j + 1]), locations.get(dungeon[i][j]))))) {
            addEdge(i, j + 1, i, j);
          }
        }

        if ((i + 1 < noOfRows)) {
          //Add (i+1,j)
          if (!(allEdges.contains(
              new Edges(locations.get(dungeon[i + 1][j]), locations.get(dungeon[i][j]))))) {
            addEdge(i + 1, j, i, j);
          }
        }


      }
    }

    if (wrap) {
      for (int i = 0; i < noOfRows; i++) {
        int j = noOfColumns - 1;
        if (!(allEdges.contains(
            new Edges(locations.get(dungeon[i][j]), locations.get(dungeon[i][0]))))) {
          addEdge(i, j, i, 0);
        }
      }

      for (int j = 0; j < noOfColumns; j++) {
        int i = noOfRows - 1;
        if (!(allEdges.contains(
            new Edges(locations.get(dungeon[i][0]), locations.get(dungeon[0][j]))))) {
          addEdge(i, j, 0, j);
        }
      }

    }
  }

  @Override
  public int getRows() {
    return noOfRows;
  }

  @Override
  public Location getStart() {

    for (int j = 0; j < locations.size(); j++) {
      if (locations.get(j).isStart()) {
        return locations.get(j);
      }
    }
    return locations.get(locationStart);
  }

  @Override
  public Location getEnd() {
    for (int j = 0; j < locations.size(); j++) {
      if (locations.get(j).isEnd()) {
        return locations.get(j);
      }
    }
    return locations.get(locationEnd);
  }

  @Override
  public int getColumns() {
    return noOfColumns;
  }

  @Override
  public void increaseInterconnectivity() {

    // Interconnectivity degree > no of leftover edges, adding all the leftover edges
    int size = leftoverEdges.size() >= interconnectivityDegree ? interconnectivityDegree
        : leftoverEdges.size();

    int i = 0;
    while (i < size) {
      //add it to our final result
      mst.add(leftoverEdges.get(i));

      //Add direction and neighbour ID to that location

      //North South
      if (leftoverEdges.get(i).getSource() - leftoverEdges.get(i).getDestination() > 1) {
        locations.get(leftoverEdges.get(i).getSource()).setDirections(Direction.North);
        locations.get(leftoverEdges.get(i).getSource())
            .setNeighbours(leftoverEdges.get(i).getDestination(), Direction.North);

        locations.get(leftoverEdges.get(i).getDestination()).setDirections(Direction.South);
        locations.get(leftoverEdges.get(i).getDestination())
            .setNeighbours(leftoverEdges.get(i).getSource(), Direction.South);
      }

      //East West
      if (leftoverEdges.get(i).getSource() - leftoverEdges.get(i).getDestination() == 1) {
        locations.get(leftoverEdges.get(i).getSource()).setDirections(Direction.West);
        locations.get(leftoverEdges.get(i).getSource())
            .setNeighbours(leftoverEdges.get(i).getDestination(), Direction.West);

        locations.get(leftoverEdges.get(i).getDestination()).setDirections(Direction.East);
        locations.get(leftoverEdges.get(i).getDestination())
            .setNeighbours(leftoverEdges.get(i).getSource(), Direction.East);
      }
      i++;
    }
  }


  @Override
  public int getInterconnectivityDegree() {
    return interconnectivityDegree;
  }

  @Override
  public int getNoOfTunnels() {
    return locations.size() - noOfCaves;
  }

  @Override
  public int getNoOfCaves() {
    int count = 0;

    for (int i = 0; i < locations.size(); i++) {
      if (locations.get(i).isCave()) {
        count++;
      }

    }
    return count;
  }

  @Override
  public void addTreasure(int percentage) {

    // Setting number of caves of at least the given percentage to add treasures

    noOfTreasureCaves = noOfCaves * percentage / 100;

    int count = 0;
    int index = 0;
    int i = 1;

    while (count < noOfTreasureCaves) {

      index = rand.getRandomNumber(0, locations.size() - 1);

      if (locations.get(index).isCave()) {
        count++;
        i = rand.getRandomNumber(1, 4);

        //Set random number of treasures in the cave
        for (int j = 0; j < i; j++) {
          locations.get(index).setTreasure();
        }
      }

    }
  }

  @Override
  public void addOtyugh(int noOfOtyugh) {

    int count = 0;
    int index = 1;

    //Adding an Otyugh at the end Cave of the Dungeon
    locations.get(locationEnd).setHasOtyugh(true);
    count++;

    while (count < noOfOtyugh) {

      index = rand.getRandomNumber(1, locations.size() - 1);

      //If the location is Start Cave, skip it
      if (locations.get(index).isStart()) {
        continue;
      }

      //Adding Otyugh if the location is a cave and doesn't have Otyugh
      else if (locations.get(index).isCave() && !locations.get(index).isHasOtyugh()) {
        locations.get(index).setHasOtyugh(true);
        count++;
      }

    }
  }


  @Override
  public void addArrows(int percentage) {
    // Setting number of caves of at least the given percentage to add treasures

    noOfArrowLocations = noOfCaves * percentage / 100;

    int count = 0;
    int index = 0;
    int i = 1;
    int randomPosition = 0;

    while (count < noOfArrowLocations && count < locations.size()) {

      count++;
      i = 2;
      randomPosition = rand.getRandomNumber(0, locations.size() - 1);

      locations.get(randomPosition).setArrows(i);
      //Set random number of arrows in the cave/tunnel

    }
  }

  @Override
  public boolean slayMonster(int caves, String direction) {

    Direction arrowDir;
    Direction entry = null;
    player.removeArrow();
    int flag = 0;

    if (player.getArrows() <= 0) {
      return false;
    }

    //Set the direction for arrow to move based on the Input
    switch (direction) {
      case "S":
        arrowDir = Direction.South;
        break;
      case "N":
        arrowDir = Direction.North;
        break;
      case "E":
        arrowDir = Direction.East;
        break;
      case "W":
        arrowDir = Direction.West;
        break;
      default:
        throw new IllegalArgumentException("Incorrect Direction");
    }

    Location arrowLocation = locations.get(player.getCurrLocation());

    for (int i = 0; i < caves; i++) {

      if (i == 0) {

        if (arrowLocation.getNeighbours().containsKey(arrowDir)) {
          arrowLocation = locations.get(arrowLocation.getNeighbours().get(arrowDir));
          entry = oppositeDir(arrowDir);
          break;
        }

        //If no cave or tunnel present in the direction givem
        else {
          return false;
        }
      }

      //If the location is a cave, the arrow can only exit from the opposite end
      if (arrowLocation.isCave()) {

        //Check if there is an exit in the opposite direction
        if (arrowLocation.getNeighbours().containsKey(oppositeDir(entry))) {
          arrowLocation = locations.get(arrowLocation.getNeighbours().get(oppositeDir(arrowDir)));
          entry = oppositeDir(arrowDir);
        }

        //If no exit at the opposite side from entry point of the cave.
        else {
          return false;
        }

      }

      //If it is a tunnel, the arrow can travel freely in any direction from the tunnel.
      else {
        for (Map.Entry<Direction, Integer> neighbour : arrowLocation.getNeighbours().entrySet()) {

          if (neighbour.getValue() != arrowLocation.getId()) {
            arrowLocation = locations.get(neighbour.getValue());
            entry = oppositeDir(neighbour.getKey());
          }
        }

      }
    }

    //Slaying the monster if it exists
    if (hasOtyugh(arrowLocation.getId())) {
      arrowLocation.getOtyugh().injure();

      if (arrowLocation.getOtyugh().getHealth() == OtyughHealth.dead) {
        locations.get(arrowLocation.getId()).setHasOtyugh(false);
      }
      player.removeArrow();
      return true;
    }

    return false;
  }

  private Direction oppositeDir(Direction direction) {

    switch (direction) {
      case East:
        return Direction.West;
      case West:
        return Direction.East;
      case North:
        return Direction.South;
      case South:
        return Direction.North;
      default:
        return null;
    }
  }

  private boolean hasOtyugh(int locationID) {

    //Checks if a healthy or injured Otyugh exists at the given location
    return locations.get(locationID).isHasOtyugh()
        && locations.get(locationID).getOtyugh().getHealth() != OtyughHealth.dead;
  }


  private void injureOtyugh(int locationID) {
    locations.get(locationID).getOtyugh().injure();
  }


  @Override
  public boolean killPlayer() {

    //Check if Otyugh present in the dungeon
    if (locations.get(player.getCurrLocation()).isHasOtyugh()) {

      //If Otyugh is Healthy Kill the player
      if (locations.get(player.getCurrLocation()).getOtyugh().getHealth() == OtyughHealth.healthy) {
        return true;
      }

      //If Otyugh is Injured , 50% chances of the players survival
      else if (locations.get(player.getCurrLocation()).getOtyugh().getHealth()
          == OtyughHealth.injured) {
        //Randomly takes True/False, hence 50% chance of survival
        return rand.nextBoolean();
      }

      //If Otyugh is dead the player is safe
      else if (locations.get(player.getCurrLocation()).getOtyugh().getHealth()
          == OtyughHealth.dead) {
        return false;
      }

    }

    //If Otyugh not present, the player is safe
    return false;
  }

  @Override
  public Smell getSmell() {

    int otyughCount = 0;

    //Get the immediate neighbours of the players current locations
    // Return More Pungent Smell if the Otyugh is 1 position away
    for (Integer i : locations.get(player.getCurrLocation()).getNeighbours().values()) {
      if (locations.get(i).isHasOtyugh()) {
        return Smell.MorePungent;
      }
    }

    //Get the immediate neighbours of the players current locations
    for (Integer i : locations.get(player.getCurrLocation()).getNeighbours().values()) {

      for (Integer j : locations.get(i).getNeighbours().values()) {

        //No need to check smell if the location is the players current location
        if (j == player.getCurrLocation()) {
          continue;
        }

        //Checks if Otyugh present two locations away
        if (locations.get(j).isHasOtyugh()) {
          otyughCount++;
        }
      }
    }

    // Return More Pungent Smell if multiple Otyugh present 2 positions away
    if (otyughCount > 1) {
      return Smell.MorePungent;
    }

    // Return Less Pungent Smell if single Otyugh present 2 positions away
    else if (otyughCount == 1) {
      return Smell.LessPungent;
    }

    // Return No Pungent Smell if No Otyugh present
    else {
      return Smell.NoSmell;
    }

  }


  @Override
  public String displayDungeon() {

    StringBuilder dung = new StringBuilder();
    dung.append("\n Displaying Dungeon Description: \n");
    for (int i = 0; i < noOfRows; i++) {
      for (int k = 0; k < 3; k++) {
        for (int j = 0; j < noOfColumns; j++) {
          Location temp = locations.get(dungeon[i][j]);
          if (k == 0) {
            if (temp.getDirections().contains(Direction.North)) {
              dung.append("  ").append("  |  ").append("   ");
            } else {
              dung.append("      ");
            }
          } else if (k == 1) {
            if (temp.getDirections().contains(Direction.West)) {
              dung.append(" - ");
            } else {
              dung.append("   ");
            }
            if (temp.getType() == LocationType.Cave) {
              dung.append(" C ");

            } else {
              dung.append(" T ");
            }
            if (temp.getDirections().contains(Direction.East)) {
              dung.append(" - ");
            } else {
              dung.append("   ");
            }
          } else {
            if (temp.getDirections().contains(Direction.South)) {
              dung.append("  ").append("  |  ").append("   ");
            } else {
              dung.append("      ");
            }
          }
          dung.append(" ");
        }
        dung.append("\n");
      }
      dung.append("\n");
    }
    dung.append("\n Dungeon Start Location: " + getStart().getId()
        + ", Dungeon End Location: " + getEnd().getId());
    dung.append("\n \n MST with Interconnectivity " + interconnectivityDegree + ": ");

    for (int i = 0; i < mst.size(); i++) {
      Edges edge = mst.get(i);
      dung.append("\n Edge-" + i + " source: " + edge.getSource()
          + " destination: " + edge.getDestination());
    }

    dung.append("\n \n Location's Description:");
    for (int i = 0; i < locations.size(); i++) {
      dung.append("\n Location : " + locations.get(i).getId()
          + " has neighbours " + locations.get(i).getNeighbours() + ""
          + " has treasures " + locations.get(i).getTreasures() + "");
    }

    return dung.toString();
  }

  @Override
  public boolean playerAtEnd() {
    return (player.getCurrLocation() == locEnd);
  }

  @Override
  public String playerDescription() {
    return player.playerDesc();
  }

  @Override
  public String getTreasures() {
    return locations.get(player.getCurrLocation()).getTreasures().toString();
  }

  @Override
  public int getArrows() {
    return locations.get(player.getCurrLocation()).getArrows();
  }

}


