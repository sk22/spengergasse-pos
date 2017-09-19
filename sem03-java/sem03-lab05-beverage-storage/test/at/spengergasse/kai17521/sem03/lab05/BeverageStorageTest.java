package at.spengergasse.kai17521.sem03.lab05;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 10/17/2016
 */
public class BeverageStorageTest {
  @Test
  public void store() {
    BeverageStorage storage = new BeverageStorage();
    Beverage drink = storage.store(new Beverage.Builder()
      .setLabel("Frucade").setOwner("Opa").setShape(Shape.BOTTLE).setQuantity(8));
    System.out.println(drink);
    assertTrue("Hall is in range 1-100",
      drink.getLocation().getHall() > 0 && drink.getLocation().getHall() <= 100);
    assertTrue("Rack is in range 1-50",
      drink.getLocation().getRack() > 0 && drink.getLocation().getRack() <= 50);
  }

  @Test
  public void idsGeneratedProperly() {
    BeverageStorage storage = new BeverageStorage();
    for (int i = 0; i < 22; i++) {
      storage.store(new Beverage.Builder()
        .setLabel("Cafe").setShape(Shape.BARREL).setOwner("Jonas")
        .setLocation("Hall 2 Rack 8").setQuantity(3));
    }
    Beverage drink = storage.store(new Beverage.Builder()
      .setLabel("Has a cool ID").setLocation("Hall 3 Rack 4")
      .setOwner("Samuel").setShape(Shape.CAN).setQuantity(9));

    System.out.println(drink);
    assertEquals("Drink's ID is greater than 0", 22, drink.id);
  }

  @Test (expected = IllegalArgumentException.class)
  public void notAllFieldsSet() {
    BeverageStorage storage = new BeverageStorage();
    storage.store(new Beverage.Builder().setLabel("Incomplete!"));
  }

  @Test
  public void takeUsingID() {
    BeverageStorage storage = new BeverageStorage();
    Beverage ayran = storage.store(new Beverage.Builder()
      .setLabel("Ayran").setQuantity(50).setShape(Shape.CAN).setOwner("Dönermann"));
    assertEquals("Storage's size must be 1", 1, storage.size());
    System.out.println(storage.take(ayran.id));
    assertEquals("Storage's size must be 0", 0, storage.size());
  }

  @Test
  public void sort() {
    BeverageStorage storage = new BeverageStorage();
    for (int i = 0; i < 10; i++) {
      storage.store(new Beverage.Builder()
        .setOwner("Old MacDonald").setShape(Shape.BARREL).setQuantity(3).setLabel("Coca Cola"));
    }
    storage.sort();
    System.out.println(storage);
    assertEquals("First element is minimum", Collections.min(storage.storage), storage.get(0));
  }

  @Test
  public void print() {
    BeverageStorage storage = new BeverageStorage();
    for (int i = 0; i < 10; i++) {
      storage.store(new Beverage.Builder()
        .setOwner("Old MacDonald").setShape(Shape.BARREL).setQuantity(3).setLabel("Coca Cola"));
    }
    storage.print();
  }

  @Test
  public void printSortedByOwner() {
    Beverage.Builder builder = new Beverage.Builder()
      .setLabel("Pepsi Cola").setQuantity(4).setShape(Shape.CAN);

    BeverageStorage storage = new BeverageStorage();
    for (int i = 0; i < 4; i++) storage.store(builder.setOwner("John Doe"));
    for (int i = 0; i < 6; i++) storage.store(builder.setOwner("Jane Doe"));
    for (int i = 0; i < 2; i++) storage.store(builder.setOwner("Jeff Doe"));
    storage.printSortedByOwner();
  }
}