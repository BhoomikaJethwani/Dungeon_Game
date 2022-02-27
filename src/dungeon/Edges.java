package dungeon;

/**
 * This class represents an Edge. It is used to create the MST for the dungeon.
 */
class Edges {

  /**
   * Constructs an Edge with Source and destination ID.
   *
   * @param source      The Source Location
   * @param destination The Destination Location
   */
  public Edges(Location source, Location destination) {
    this.source = new LocationImpl(source.getX(), source.getY(), source.getId());
    this.destination = new LocationImpl(destination.getX(), destination.getY(),
        destination.getId());
  }

  Location source;
  Location destination;

  /**
   * Returns the Source ID.
   *
   * @return int Source Id.
   */
  public int getSource() {
    return source.getId();
  }

  /**
   * Returns the destination; ID.
   *
   * @return int destination; Id.
   */
  public int getDestination() {
    return destination.getId();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Edges)) {
      return false;
    }

    Edges obj = (Edges) o;
    if (obj.source.getId() == this.source.getId()
        && obj.destination.getId() == this.destination.getId()) {
      return true;
    }
    return obj.source.getId() == this.destination.getId()
        && obj.destination.getId() == this.source.getId();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.source.getId());
  }

}