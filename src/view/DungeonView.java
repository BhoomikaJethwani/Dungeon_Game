package view;

import control.MVCControllerIntr;
import java.awt.event.KeyListener;

/**
 * The view interface for the Dungeon.
 */
public interface DungeonView {

  /**
   * Add Menu to the view.
   */
  void addMenu();

  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();


  /**
   * Transmit a message to the view.
   *
   * @param message the message to be printed
   */
  void printMessage(String message);

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * Set up the controller to handle key events in this view.
   *
   * @param controller the controller variable
   */
  void setKeyListener(KeyListener controller);

  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the listener parameter
   */
  void addClickListener(MVCControllerIntr listener);
}
