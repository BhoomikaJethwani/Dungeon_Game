package control.commands;

import dungeon.Dungeon;

/**
 * Class for the command Shoot monster.
 */
public class Shoot implements Commands {

  private final int distance;
  private final String direction;

  /**
   * Constructor.
   *
   * @param distance  the Distance of caves to shoot.
   * @param direction the direction to shoot.
   */
  public Shoot(int distance, String direction) {
    this.distance = distance;
    this.direction = direction;
  }

  @Override
  public boolean playGame(Dungeon d) {

    if (d == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    return d.slayMonster(distance, direction);
  }
}
