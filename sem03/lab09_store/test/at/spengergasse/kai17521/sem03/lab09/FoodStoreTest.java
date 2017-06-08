package at.spengergasse.kai17521.sem03.lab09;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 12/5/2016
 */
public class FoodStoreTest {
  private FoodStore store;

  @Before
  public void setUp() {
    store = new FoodStore(FoodStore.Category.DELICATESSEN,
      5, true, "GmbH");
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalConstructor() {
    new FoodStore(FoodStore.Category.SUPERMARKET, 0, false, "");
  }

  @Test
  public void getFunding() {
    assertEquals(150, new FoodStore(FoodStore.Category.SUPERMARKET,
      2, false, "y").getFunding(), .1);
    assertEquals(100, new FoodStore(FoodStore.Category.SUPERMARKET,
      1, false, "x").getFunding(), .1);
    assertEquals(225, new FoodStore(FoodStore.Category.SUPERMARKET,
      3, false, "z").getFunding(), .1);

    assertEquals(425, new FoodStore(FoodStore.Category.SUPERMARKET,
      3, true, "c").getFunding(), .1);
    assertEquals(500, new FoodStore(FoodStore.Category.SUPERMARKET,
      10, true, "d").getFunding(), .1);
  }
}