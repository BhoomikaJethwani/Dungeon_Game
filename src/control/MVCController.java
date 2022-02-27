package control;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonCreate;
import dungeon.DungeonImpl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import random.RandomInterface;
import random.RandomInterfaceImpl;
import treasure.Treasure;
import view.DungeonView;
import view.DungeonViewImpl;

/**
 * This is a console controller class which implements the MVCControllerIntr interface for the
 * dungeon MVC model.
 */
public class MVCController extends KeyAdapter implements MVCControllerIntr {

  Dungeon model;
  DungeonView view;
  DungeonCreate dungeon;
  RandomInterface rand;
  int panelRows = 0;
  int panelColumns = 0;

  /**
   * Constructor for the controller.
   *
   * @param m the Dungeon model
   */
  public MVCController(Dungeon m) {

    rand = new RandomInterfaceImpl();

    if (m == null) {
      dungeon = new DungeonCreate();

      this.model = new DungeonImpl(dungeon.getNoOfRows(), dungeon.getNoOfColumns(),
          dungeon.getInterconnectivity(), dungeon.isWrap(), rand, dungeon.getNoOfOtyugh(),
          dungeon.getPercentage());
    } else {
      this.model = new DungeonImpl(m.getRows(), m.getColumns(), m.getInterconnectivityDegree(),
          m.isWrap(), rand, m.getNoOfOtyugh(), m.getPercentage());
    }
    this.view = new DungeonViewImpl(model);
    this.panelRows = model.getRows();
    this.panelColumns = model.getColumns();
    this.playGame();
  }


  @Override
  public int getPanelRows() {
    return panelRows;
  }

  @Override
  public int getPanelColumns() {
    return panelColumns;
  }

  @Override
  public void playGame() {

    //Check if model is not null
    if (model == null) {
      throw new IllegalArgumentException();
    }

    this.view.makeVisible();
    this.view.setKeyListener(this);
    this.view.addClickListener(this);


  }


  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    int distance = 0;
    boolean flag = false;

    //move the player UP
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      model.movePlayer(Direction.North);
    }

    //move the player DOWN
    else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      model.movePlayer(Direction.South);
    }

    //move the player RIGHT
    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      model.movePlayer(Direction.West);
    }

    //move the player LEFT
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      model.movePlayer(Direction.East);
    }

    //Pick a Diamond
    else if (e.getKeyCode() == KeyEvent.VK_D) {
      model.pickTreasure(Treasure.DIAMOND);
    }

    //Pick a Ruby
    else if (e.getKeyCode() == KeyEvent.VK_R) {
      model.pickTreasure(Treasure.RUBY);
    }

    //Pick an Emerald
    else if (e.getKeyCode() == KeyEvent.VK_E) {
      model.pickTreasure(Treasure.SAPPHIRE);
    }

    //Pick an arrow
    else if (e.getKeyCode() == KeyEvent.VK_A) {
      model.pickArrow();
    }

    //Pick an arrow
    else if (e.getKeyCode() == KeyEvent.VK_1) {
      distance = 1;
    }

    //Pick an arrow
    else if (e.getKeyCode() == KeyEvent.VK_2) {
      distance = 2;
    }

    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_I) {
      flag = model.slayMonster(1, "North");
      if (flag) {
        view.printMessage("You have attacked the Monster !!");
      }
      distance = 0;
    }
    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_J) {
      flag = model.slayMonster(1, "West");
      view.printMessage("You have attacked the Monster !!");
      distance = 0;
    }
    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_K) {
      flag = model.slayMonster(1, "South");
      view.printMessage("You have attacked the Monster !!");
      distance = 0;
    }
    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_L) {
      flag = model.slayMonster(1, "East");
      view.printMessage("You have attacked the Monster !!");
      distance = 0;
    }

    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_Z) {
      view.printMessage(model.playerDescription() + "\n Treasures Collected: " + model.getPlayer()
          .playerTreasureDesc() + "\nYou have " + model.getPlayer().getArrows()
          + " Arrows remaining !!");
    }

    //Help
    else if (e.getKeyCode() == KeyEvent.VK_H) {
      String msg = "Press The following keys: \n"
          + "Up Key - Move Up\n"
          + "Down Key - Move Down\n"
          + "Right Key - Move Right\n"
          + "Left Key - Move Left\n"
          + "D Key - Pick a Diamond\n"
          + "R Key - Pick a Ruby\n"
          + "E Key - Pick an Emerald\n"
          + "S Key - Shoot Key\n"
          + "1 Key - Shoot at a distance of 1\n"
          + "2 Key - Shoot at a distance of 2\n"
          + "I Key - Shoot North\n"
          + "J Key - Shoot West\n"
          + "K Key - Shoot South\n"
          + "L Key - Shoot East\n"
          + "Z Key - Player's Description\n";
      view.printMessage(msg);
    }


  }

  @Override
  public void keyReleased(KeyEvent e) {
    super.keyReleased(e);
    //move the player UP
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      view.refresh();
      if (model.getPlayer().isDead()) {
        view.printMessage("You are killed by the Otyugh !! Better Luck next Time");
      }
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //move the player DOWN
    else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      view.refresh();
      if (model.getPlayer().isDead()) {
        view.printMessage("You are killed by the Otyugh !! Better Luck next Time");
      }
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //move the player RIGHT
    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      view.refresh();
      if (model.getPlayer().isDead()) {
        view.printMessage("You are killed by the Otyugh !! Better Luck next Time");
      }
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }
    //move the player LEFT
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      view.refresh();
      if (model.getPlayer().isDead()) {
        view.printMessage("You are killed by the Otyugh !! Better Luck next Time");
      }
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //Pick a Diamond
    else if (e.getKeyCode() == KeyEvent.VK_D) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //Pick a Ruby
    else if (e.getKeyCode() == KeyEvent.VK_R) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //Pick an Emerald
    else if (e.getKeyCode() == KeyEvent.VK_E) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //Pick an arrow
    else if (e.getKeyCode() == KeyEvent.VK_A) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_S) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_I) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }
    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_J) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }
    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_K) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }
    //Shoot the monster
    else if (e.getKeyCode() == KeyEvent.VK_L) {
      view.refresh();
      if (model.getPlayer().getCurrLocation() == model.getEnd().getId()) {
        view.printMessage("You Win the Game !! Congratulations");
      }
    }

  }


  @Override
  public void handleCellClick(int gridX, int gridY) {

    int[][] dungeon = model.getDungeon();
    int locID = 0;
    int playerLoc = model.getPlayer().getCurrLocation();
    Direction direction = Direction.getRandom();

    for (int i = 0; i < model.getRows(); i++) {
      for (int j = 0; j < model.getColumns(); j++) {
        if (gridX == i && gridY == j) {
          locID = dungeon[i][j];
        }
      }
    }

    if (model.getLocations().get(model.getPlayer().getCurrLocation()).getNeighbours()
        .containsValue(locID)) {
      Map<Direction, Integer> d = model.getLocations().get(model.getPlayer().getCurrLocation())
          .getNeighbours();

      if (locID == playerLoc - 1) {
        direction = Direction.West;
      } else if (locID == playerLoc + 1) {
        direction = Direction.East;
      } else if (locID == playerLoc + model.getRows()) {
        direction = Direction.South;
      } else {
        direction = Direction.North;
      }
      model.movePlayer(direction);

    }
  }
}
