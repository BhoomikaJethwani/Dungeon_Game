package view;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.Location;
import dungeon.Smell;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import treasure.Treasure;

/**
 * This panel represents the region where the dungeon's caves and tunnels must be drawn for the
 * aMAZE game. It extends the JPanel class and draws the components of the TicTacToe board at each
 * move.
 */
public class DungeonPanel extends JPanel {

  List<Location> loc;
  List<Integer> visitedLoc = new ArrayList<Integer>();
  int noOfRuby;
  int noOfSapphire;
  int noOfDiamond;
  int noOfArrow;
  Dungeon model;

  /**
   * Parameterized constructor.
   *
   * @param model the model input.
   */
  public DungeonPanel(Dungeon model) {

    super();
    this.model = model;
    loc = model.getLocations();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(Color.BLACK);
  }


  private void displayCount(Graphics g, int count, int xOffset, int yOffset) {
    if (count > 0) {
      g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
      g.setColor(Color.WHITE);
      g.drawString(String.valueOf(count), xOffset, yOffset);
    }
  }

  /**
   * Draws the dungeon details.
   */
  public void drawDungeon() {

    JLabel playerDesc = new JLabel(
        "Arrows: " + model.getPlayer().getArrows() + ", Treasures:" + model
            .getPlayer().getTreasure().size());

    BufferedImage player;
    BufferedImage location;
    BufferedImage ruby;
    BufferedImage sapphire;
    BufferedImage diamond;
    BufferedImage arrow;
    BufferedImage smell;
    BufferedImage monster;
    BufferedImage thief;

    for (Location l : loc) {

      player = null;
      location = null;
      ruby = null;
      sapphire = null;
      diamond = null;
      arrow = null;
      smell = null;
      monster = null;
      thief = null;
      noOfRuby = 0;
      noOfArrow = 0;
      noOfDiamond = 0;
      noOfSapphire = 0;

      // if the location is Players current Location
      if (model.getPlayer().getCurrLocation() == l.getId()) {
        visitedLoc.add(l.getId());

        if (model.getPlayer().isDead()) {
          try {
            player = ImageIO.read(Objects.requireNonNull(
                getClass().getResource("/images/playerDead.png")));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        } else {
          try {
            player = ImageIO.read(Objects.requireNonNull(
                getClass().getResource("/images/player.png")));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }

        //If the player's location has smell
        // Less Pungent Smell
        if (model.getSmell() == Smell.LessPungent) {
          try {
            smell = ImageIO.read(Objects.requireNonNull(
                getClass().getResource("/images/stench01.png")));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }

        // More Pungent Smell
        else if (model.getSmell() == Smell.MorePungent) {
          try {
            smell = ImageIO.read(Objects.requireNonNull(
                getClass().getResource("/images/stench02.png")));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }
      }

      //If the Location is Visited
      if (visitedLoc.contains(l.getId())) {

        //If Location is a cave
        if (l.isCave()) {

          //location with one entrance
          if (l.getNeighbours().size() == 1) {

            //If entrance is North
            if (l.getNeighbours().containsKey(Direction.North)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/N.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

            //If entrance is South
            else if (l.getNeighbours().containsKey(Direction.South)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/S.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

            //If entrance is East
            else if (l.getNeighbours().containsKey(Direction.East)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/E.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

            //If entrance is West
            else if (l.getNeighbours().containsKey(Direction.West)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/W.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

          }
          //location with three entrances
          else if (l.getNeighbours().size() == 3) {

            //If entrance is North, South, West
            if (l.getNeighbours().containsKey(Direction.North) && l.getNeighbours()
                .containsKey(Direction.South) && l.getNeighbours().containsKey(Direction.West)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/NSW.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

            //If entrance is East, South, West
            else if (l.getNeighbours().containsKey(Direction.East) && l.getNeighbours()
                .containsKey(Direction.South) && l.getNeighbours().containsKey(Direction.West)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/SEW.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

            //If entrance is North, South, East
            else if (l.getNeighbours().containsKey(Direction.North) && l.getNeighbours()
                .containsKey(Direction.South) && l.getNeighbours().containsKey(Direction.East)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/NSE.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }

            //If entrance is North, West, East
            else if (l.getNeighbours().containsKey(Direction.North) && l.getNeighbours()
                .containsKey(Direction.East) && l.getNeighbours().containsKey(Direction.West)) {
              try {
                location = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/images/NEW.png")));
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }
          }

          //location with four entrances
          else if (l.getNeighbours().size() == 4) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/NSEW.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }
        }

        //If Location is a tunnel
        else {
          //If entrance is North, South
          if (l.getNeighbours().containsKey(Direction.North) && l.getNeighbours()
              .containsKey(Direction.South)) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/NS.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          //If entrance is West, South
          else if (l.getNeighbours().containsKey(Direction.South) && l.getNeighbours()
              .containsKey(Direction.West)) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/SW.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          //If entrance is North, West
          else if (l.getNeighbours().containsKey(Direction.North) && l.getNeighbours()
              .containsKey(Direction.West)) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/NW.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          //If entrance is  West, East
          else if (l.getNeighbours().containsKey(Direction.East) && l.getNeighbours()
              .containsKey(Direction.West)) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/EW.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          //If entrance is  North, East
          else if (l.getNeighbours().containsKey(Direction.North) && l.getNeighbours()
              .containsKey(Direction.East)) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/NE.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          //If entrance is  South, East
          else if (l.getNeighbours().containsKey(Direction.South) && l.getNeighbours()
              .containsKey(Direction.East)) {
            try {
              location = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/SE.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }
        }

        //If Location has Treasures
        if (l.hasTreasure()) {
          if (l.getTreasures().contains(Treasure.RUBY)) {
            noOfRuby = Collections.frequency(l.getTreasures(), Treasure.RUBY);
            try {
              ruby = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/ruby.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          if (l.getTreasures().contains(Treasure.SAPPHIRE)) {
            noOfSapphire = Collections.frequency(l.getTreasures(), Treasure.SAPPHIRE);
            try {
              sapphire = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/emerald.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }

          if (l.getTreasures().contains(Treasure.DIAMOND)) {
            noOfDiamond = Collections.frequency(l.getTreasures(), Treasure.DIAMOND);
            try {
              diamond = ImageIO.read(Objects.requireNonNull(
                  getClass().getResource("/images/diamond.png")));
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
          }
        }

        //If the Location has Arrows
        if (l.getArrows() > 0) {
          noOfArrow = l.getArrows();
          try {
            arrow = ImageIO.read(Objects.requireNonNull(
                getClass().getResource("/images/arrow-white.png")));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }

        //If location has monster
        if (l.isHasOtyugh()) {
          try {
            monster = ImageIO.read((Objects.requireNonNull(
                getClass().getResource("/images/otyugh.png"))));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }

        //If location has thief
        if (l.isHasThief()) {
          try {
            monster = ImageIO.read((Objects.requireNonNull(
                getClass().getResource("/images/thief.png"))));
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }

      }
      // If the Location is Unvisited
      else {
        try {
          location = ImageIO.read(Objects.requireNonNull(
              getClass().getResource("/images/blank.png")));
        } catch (IOException ioe) {
          ioe.printStackTrace();
        }
      }

      JLabel p = new JLabel();

      int w = 100;
      int h = 100;

      BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      Graphics g = combined.getGraphics();
      p.setPreferredSize(new Dimension(100, 100));
      g.drawImage(combined, 0, 0, 0, 0, null);
      g.drawImage(location, 0, 0, 100, 100, null);

      g.drawImage(ruby, 5, 5, 20, 20, null);
      displayCount(g, noOfRuby, 20, 15);

      g.drawImage(diamond, 35, 5, 20, 20, null);
      displayCount(g, noOfDiamond, 50, 15);

      g.drawImage(sapphire, 65, 5, 20, 20, null);
      displayCount(g, noOfSapphire, 80, 15);

      g.drawImage(arrow, 5, 75, 15, 5, null);
      displayCount(g, noOfArrow, 25, 80);

      g.drawImage(smell, 30, 30, 30, 30, null);

      if (model.getPlayer().isDead()) {
        g.drawImage(player, 30, 30, 70, 40, null);
      } else {
        g.drawImage(player, 30, 30, 40, 70, null);
      }

      if (l.isHasPit()) {
        g.drawOval(50, 50, 20, 20);
      }

      g.drawImage(monster, 50, 50, 50, 50, null);
      g.drawImage(thief, 30, 30, 70, 40, null);
      g.dispose();

      //  p.setPreferredSize(new Dimension(100, 100));
      Border blackline = BorderFactory.createLineBorder(Color.black);
      p.setBorder(blackline);
      p.setIcon(new ImageIcon(combined));

      this.add(p);


    }
  }


}
