package at.spengergasse.kai17521.sem03.lab05;

import static org.junit.Assert.*;

import at.spengergasse.kai17521.sem03.lab05.Location;
import org.junit.Before;
import org.junit.Test;

import at.spengergasse.kai17521.sem03.lab05.Beverage;
import at.spengergasse.kai17521.sem03.lab05.Shape;

public class BeverageTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void construct() {
    Beverage drink = new Beverage(5, "Josef", "Frucade G'Spritzt", "Halle 3 Regal 92", Shape.BARREL, 3);
    assertEquals("Hall must be 3", 3, drink.getLocation().getHall());
    assertEquals("Owner must be Josef", "Josef", drink.getOwner());
    assertEquals("ID must be 5", 5, drink.id);
    System.out.println(drink);
  }

  @Test (expected = IllegalArgumentException.class)
  public void constructWithInvalidValues() {
    System.out.println("IllegalArgumentException is thrown because a Beverage must have a Location");
    Beverage drink = new Beverage(1, null, null, (String) null, null, -5);
  }

  @Test
  public void buildBeverage() {
    Beverage drink = new Beverage.Builder()
      .setLabel("Almdudler").setLocation(new Location(3, 6)).setOwner("Paul").setQuantity(4).build(8);
    assertEquals("Owner must be Paul", "Paul", drink.getOwner());
    assertEquals("ID must be 8", 8, drink.id);
    assertEquals("Rack must be 6", 6, drink.getLocation().getRack());
  }

}
