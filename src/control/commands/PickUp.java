package control.commands;

import dungeon.Dungeon;
import treasure.Treasure;

/**
 * Class for the command PickUp Treasure and Arrow.
 */
public class PickUp implements Commands {

  private final String type;

  /**
   * Constructor.
   *
   * @param type the type treasure or arrow.
   */
  public PickUp(String type) {
    this.type = type;
  }

  @Override
  public boolean playGame(Dungeon d) {

    boolean status = false;
    if (d == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    if (type.equalsIgnoreCase("ruby")
        || type.equalsIgnoreCase("sapphire")
        || type.equalsIgnoreCase("diamond")) {
      Treasure t = null;

      if (type.equalsIgnoreCase("ruby")) {
        t = Treasure.RUBY;
      }
      else if (type.equalsIgnoreCase("sapphire")) {
        t = Treasure.SAPPHIRE;
      }
      else {
        t = Treasure.DIAMOND;
      }

      status = d.pickTreasure(t);
    } else if (type.equalsIgnoreCase("Arrow")) {
      status = d.pickArrow();
    }

    return status;

  }
}
