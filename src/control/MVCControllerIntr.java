package control;

/**
 * Represents an MVC Controller for Dungeon model: handle user moves by executing them using the
 * model; convey the next move outcomes to the user in some form.
 */
public interface MVCControllerIntr {

  /**
   * Returns the number of Rows on the panel.
   *
   * @return int number of panel rows
   */
  int getPanelRows();

  /**
   * Returns the number of Rows on the panel.
   *
   * @return int number of panel rows
   */
  int getPanelColumns();

  /**
   * Execute a single game of Dungeon Model. When the game is over, the playGame method ends.
   */
  void playGame();

  /**
   * Handle Cell clicked by mouse.
   *
   * @param gridX the x coordinate
   * @param gridY the y coordinate
   */
  void handleCellClick(int gridX, int gridY);
}
