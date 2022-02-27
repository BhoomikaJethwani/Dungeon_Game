package control;

import control.commands.Commands;
import control.commands.Move;
import control.commands.PickUp;
import control.commands.Shoot;
import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.Smell;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is a console controller class which implements the DungeonController interface for the
 * dungeon MVC model.
 */
public class DungeonControllerImpl implements DungeonController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonControllerImpl(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public boolean playGame(Dungeon d) {

    if (d == null) {
      throw new IllegalArgumentException();
    }

    Commands cmd = null;

    try {
      out.append("\n Welcome to the Mighty Dungeon !! "
          + "Save yourself from the Otyugh and Run while you can !!");
      while (!d.playerAtEnd()) {

        out.append(d.playerDescription());

        if (d.locationHasTreasure()) {
          out.append("\nYou find  ");
          out.append(d.getTreasures() + " here !");
        }

        if (d.locationHasArrows() > 0) {
          out.append("\nYou find ");
          out.append(d.getArrows() + " Arrows  here !");
        }

        if (d.getSmell() == Smell.LessPungent) {
          out.append("\nYou smell something terrible nearby (less pungent)");
        } else if (d.getSmell() == Smell.MorePungent) {
          out.append("\nYou smell something terrible nearby (more pungent)");
        }
        out.append("\nMove, Pickup, Shoot or Quit (M-P-S-Q)?");
        String in = scan.next();
        switch (in) {
          case "q":
          case "quit":
            out.append("\nYou quit the game!\n"
                + " Better luck next time");
            return false;
          case "M":
            out.append("\nWhere to?");
            String str = scan.next();
            Direction dir = null;
            switch (str) {

              case "N":
                dir = Direction.North;
                break;
              case "S":
                dir = Direction.South;
                break;
              case "E":
                dir = Direction.East;
                break;
              case "W":
                dir = Direction.West;
                break;
              default:
                break;
            }
            cmd = new Move(dir);
            boolean flag1 = cmd.playGame(d);

            //If false is returned means player is killed by Otyugh
            if (d.isPlayerDead()) {
              out.append("\nChomp, chomp, chomp, you are eaten by an Otyugh!\n"
                  + " Better luck next time");
              return false;
            }
            break;
          case "P":
            out.append("\nWhat?");
            String str2 = scan.next();
            cmd = new PickUp(str2);
            boolean flag = cmd.playGame(d);

            if (str2.equalsIgnoreCase("Arrow")) {
              if (flag) {
                out.append("\nYou pick up an arrow");
              } else {
                out.append("\nNo arrow in the location");
              }
            } else {
              if (flag) {
                out.append("\nYou pick up a " + str2);
              } else {
                out.append("\nNo treasure in the location");
              }
            }
            break;

          case "S":
            out.append("\nNo. of caves (1-5)?");
            int num = scan.nextInt();
            out.append("\nWhere to?");
            String str1 = scan.next();
            boolean flag2 = false;
            //controller shoudnt check - model must check.
            if (d.playerHasArrows() == 0) {
              out.append("\nYou are out of arrows");
              break;
            } else {
              cmd = new Shoot(num, str1);
              flag2 = cmd.playGame(d);
              if (flag2) {
                out.append(
                    "\nYou hear a great howl in the distance !! You have attacked the Otyugh !! ");
              } else {
                out.append("\nYou shoot an arrow into the darkness");
              }
            }

            break;
          default:
            break;
        }

      }
      out.append("Congrats You have reached the end of the cave !! You Win\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Not a " + ioe + "\n");
    }

    return true;
  }
}



