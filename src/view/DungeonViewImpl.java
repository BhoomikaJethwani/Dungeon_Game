package view;

import control.MVCController;
import control.MVCControllerIntr;
import dungeon.Dungeon;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * This class implements the DungeonView interface and uses Java Swing to draw the results of the
 * game. It shows the current game state and any messages using a pop-up dialog box.
 */
public class DungeonViewImpl extends JFrame implements DungeonView {

  DungeonPanel panel;
  Dungeon model;
  MVCControllerIntr c;

  /**
   * Parameterized constructor.
   *
   * @param model the model input.
   */
  public DungeonViewImpl(Dungeon model) {
    super("aMAZE Game    \t    Powered By: BDJ");
    this.setSize(900, 900);
    this.setBackground(Color.BLACK);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.model = model;
    this.panel = new DungeonPanel(model);
    panel.setLayout(new GridLayout(model.getRows(), model.getColumns(), 0, 0));
    panel.drawDungeon();
    JScrollPane jsp = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    add(jsp);
    addMenu();
  }

  @Override
  public void addMenu() {

    JMenuBar menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);

    JMenuItem restartGame = new JMenuItem(" Restart Game");
    menu.add(restartGame);
    this.dispose();
    restartGame.addActionListener(e -> c = new MVCController(null));

    JMenuItem exitGame = new JMenuItem(" Exit Game");
    menu.add(exitGame);
    exitGame.addActionListener(e -> this.dispose());

    JMenuItem resetGame = new JMenuItem(" Reset Game");
    menu.add(resetGame);
    resetGame.addActionListener(e -> c = new MVCController(model));

    JMenu help = new JMenu("Help");
    menuBar.add(help);

    JMenuItem gameInstructions = new JMenuItem(" Game Instructions");
    help.add(gameInstructions);
    String msg = "Press The following keys: \n"
        + "Up Key - Move Up\n"
        + "Down Key - Move Down\n"
        + "Right Key - Move Right\n"
        + "Left Key - Move Left\n"
        + "D Key - Pick a Diamond\n"
        + "R Key - Pick a Ruby\n"
        + "E Key - Pick an Emerald\n"
        + "S Key - Shoot Key\n"
        + "1 Key - Shoot at a distance of 1\n"
        + "2 Key - Shoot at a distance of 2\n"
        + "I Key - Shoot North\n"
        + "J Key - Shoot West\n"
        + "K Key - Shoot South\n"
        + "L Key - Shoot East\n"
        + "Z Key - Player's Description\n";
    gameInstructions.addActionListener(e -> this.printMessage(msg));

    JMenuItem gameRules = new JMenuItem(" Game Rules");
    String msg1 = "Collect the treasures, "
        + "Kill the monsters by shooting at them twice"
        + "and Exit Dungeon to win the Game !!\n";
    help.add(gameRules);
    gameRules.addActionListener(e -> this.printMessage(msg1));
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }


  @Override
  public void printMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void refresh() {
    panel.removeAll();
    panel.drawDungeon();
    setVisible(true);
  }


  @Override
  public void setKeyListener(KeyListener listener) {
    this.addKeyListener(listener);
    panel.addKeyListener(listener);
  }

  @Override
  public void addClickListener(MVCControllerIntr listener) {
    // create the MouseAdapter
    MouseAdapter clickAdapter = new MouseAdapterImpl(listener);
    panel.addMouseListener(clickAdapter);
  }
}
