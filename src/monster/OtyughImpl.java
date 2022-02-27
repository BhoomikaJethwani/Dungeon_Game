package monster;

/**
 * This class represents a monster Otyugh in dungeon. It stores the monster's health.
 */
public class OtyughImpl implements Otyugh {

  private OtyughHealth health;

  /**
   * Constructs a monster with initial health as Healthy.
   */
  public OtyughImpl() {
    this.health = OtyughHealth.healthy;
  }

  @Override
  public OtyughHealth getHealth() {
    return health;
  }

  @Override
  public void injure() {

    //If the Otyugh is healthy, it gets injured.
    if (health == OtyughHealth.healthy) {
      health = OtyughHealth.injured;
    }

    //If the Otyugh is injured, it gets killed.
    else if (health == OtyughHealth.injured) {
      health = OtyughHealth.dead;
    }

  }

}
