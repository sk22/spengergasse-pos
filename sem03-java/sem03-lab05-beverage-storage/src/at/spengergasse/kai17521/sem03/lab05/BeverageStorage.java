package at.spengergasse.kai17521.sem03.lab05;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Storage for Beverage objects
 * @author Samuel Kaiser
 * @since 10/17/2016
 */
public class BeverageStorage extends Storage<Beverage, Beverage.Builder> {
  /**
   * Stores a Beverage. All fields but the location must be set
   * since the location can be generated automatically.
   * @param builder Beverage Builder containing all information
   * @return Stored Beverage
   */
  @Override
  public Beverage store(Beverage.Builder builder) {
    Random random = new Random();
    if (builder.getLocation() == null) {
      builder.setLocation(new Location(random.nextInt(100) + 1, random.nextInt(50) + 1));
    }
    if (builder.getFields() != builder.fields) {
      throw new IllegalArgumentException("All fields must be set");
    }
    return super.store(builder);
  }

  @Override
  public String toString() {
    return storage.toString();
  }

  /**
   * Sorts the Beverages by their hall and rack number
   */
  public void sort() {
    Collections.sort(storage);
  }

  /**
   * Prints a list of all Beverages sorted by their owners
   */
  public void printSortedByOwner() {
    Set<String> owners = new HashSet<>();
    storage.forEach(beverage -> owners.add(beverage.getOwner()));
    owners.forEach(owner -> {
      List<Beverage> beverages = storage.stream()
        .filter(beverage -> beverage.getOwner().equals(owner))
        .collect(Collectors.toList());

      System.out.println(owner + ": ");
      beverages.forEach(beverage -> System.out.println("  " + beverage));
    });
  }
}
