package world;


import control.MVCController;
import dungeon.Dungeon;
import dungeon.DungeonImpl;
import random.RandomInterface;
import random.RandomInterfaceImpl;

/**
 * Driver Class to implement the Dungeon Model.
 */
class WorldImpl {

  /**
   * The Main method implements the Dungeon Model.
   */
  public static void main(String[] args) {

    RandomInterface rand = new RandomInterfaceImpl();

    if (args.length == 0) {
      MVCController c = new MVCController(null);
    } else {
      if (args.length == 14) {
        Dungeon d = new DungeonImpl(Integer.valueOf(args[0]), Integer.valueOf(args[2]),
            Integer.valueOf(args[4]), true, rand, Integer.valueOf(args[11]),
            Integer.valueOf(args[13]));
      } else {
        Dungeon d = new DungeonImpl(Integer.valueOf(args[0]), Integer.valueOf(args[2]),
            Integer.valueOf(args[4]), false, rand, Integer.valueOf(args[12]),
            Integer.valueOf(args[14]));
      }

    }


  }

}
