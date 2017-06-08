package at.spengergasse.kai17521.sem03.lab09;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 12/5/2016
 */
public class AdministrationTest {
  private Administration admin;

  @Before
  public void setUp() {
    admin = new Administration();
  }

  @Test
  public void addStore() {
    admin.addStore(new Store(5, false, "GmbH"));
  }

  @Test
  public void sumFundingsWithNoStores() {
    assertEquals(0, admin.sumFundings(), .1);
  }

  @Test
  public void sumFundings() {
    admin.addStore(new FoodStore(FoodStore.Category.SNACK, 2, false, "Food"));
    admin.addStore(new OrganicGroceryStore(50, 1, false, "Organic"));
    assertEquals(150 + 100, admin.sumFundings(), .1);
  }
}