package test;

import static org.junit.Assert.assertEquals;

import dungeon.Location;
import dungeon.LocationImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests a Location Interface and all its methods.
 */
public class LocationTest {

  Location loc;

  /**
   * Setting up objects for all the tests.
   */
  @Before
  public void setUp() throws Exception {
    loc = new LocationImpl(2, 2, 3);
  }

  /**
   * Tests location ID.
   */
  @Test
  public void testId() {
    assertEquals(3, loc.getId());
  }

  /**
   * Tests location ID.
   */
  @Test
  public void testX() {
    assertEquals(2, loc.getX());
  }

  /**
   * Tests location ID.
   */
  @Test
  public void testY() {
    assertEquals(2, loc.getY());
  }

}