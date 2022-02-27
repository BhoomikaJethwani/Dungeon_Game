package view;


import control.MVCControllerIntr;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class extends the MouseAdapter.
 */
class MouseAdapterImpl extends MouseAdapter {

  private final MVCControllerIntr listener;

  /**
   * Parameterized Constructor.
   *
   * @param listener the controller object
   */
  public MouseAdapterImpl(MVCControllerIntr listener) {
    this.listener = listener;
  }

  @Override
  public void mouseReleased(MouseEvent e) {

    super.mouseReleased(e);
    // arithmetic to convert panel coords to grid coords
    int grid_x = getGridCoordinate(e.getX(), listener.getPanelColumns());
    int grid_y = getGridCoordinate(e.getY(), listener.getPanelRows());

    listener.handleCellClick(grid_x, grid_y);

  }

  // Helper method to convert panel coords to grid coords
  private int getGridCoordinate(int x, int num) {
    int val = 0;
    val = (900 * x) / num;
    return val;
  }
}


