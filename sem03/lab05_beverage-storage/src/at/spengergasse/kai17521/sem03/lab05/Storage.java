package at.spengergasse.kai17521.sem03.lab05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores objects. Requires the objects no be passed to the store method as
 * an object of their Builder class, since the ID must be passed to the build method
 * @param <T> Type of objects the Storage will store
 * @param <B> The type's Builder class
 */
public class Storage<T extends ID, B extends AbstractBuilder<T>> {
  private int counter;
  List<T> storage = new ArrayList<T>();

  public T store(B builder) {
    T object = builder.build(counter++);
    storage.add(object);
    return object;
  }

  /**
   * Removes the object with the given ID
   * @param id ID of the object that should be removed
   * @return Removed object
   */
  public T take(int id) {
    Optional<T> result = storage.stream().filter(o -> o.id == id).findAny();
    if (!result.isPresent()) return null;
    storage.remove(result.get());
    return result.get();
  }

  /**
   * @return Count of the currently registered entries
   */
  public int size() {
    return storage.size();
  }

  /**
   * Gets the object at the specific index
   * @param index Position
   * @return Object at the position
   */
  public T get(int index) {
    return storage.get(index);
  }

  /**
   * Prints all entries line by line
   */
  public void print() {
    storage.forEach(System.out::println);
  }
}
