package dungeon;

import javax.swing.JOptionPane;

/**
 * Class to create a Dungeon by accepting values from User.
 */
public class DungeonCreate {

  int noOfRows;
  int noOfColumns;
  boolean wrap;
  int interconnectivity;
  int noOfOtyugh;
  int percentage;

  /**
   * Default Constructor.
   */
  public DungeonCreate() {

    this.noOfRows = Integer.parseInt(
        JOptionPane.showInputDialog("Enter the Number of Rows inside the Dungeon: "));
    this.noOfColumns = Integer.parseInt(
        JOptionPane.showInputDialog("Enter the Number of Columns inside the Dungeon: "));
    this.wrap = Boolean.parseBoolean(
        JOptionPane.showInputDialog("Is it a wrapping dungeon (true/false): "));
    this.interconnectivity = Integer.parseInt(
        JOptionPane.showInputDialog("Enter the interconnectivity inside the Dungeon: "));
    this.noOfOtyugh = Integer.parseInt(
        JOptionPane.showInputDialog("Enter the Number of Otyugh inside the Dungeon: "));
    this.percentage = Integer.parseInt(JOptionPane.showInputDialog(
        "Enter the percentage of treasures and arrows inside the Dungeon: "));
  }


  public int getNoOfRows() {
    return noOfRows;
  }

  public int getNoOfColumns() {
    return noOfColumns;
  }

  public boolean isWrap() {
    return wrap;
  }

  public int getInterconnectivity() {
    return interconnectivity;
  }

  public int getNoOfOtyugh() {
    return noOfOtyugh;
  }

  public int getPercentage() {
    return percentage;
  }
}
