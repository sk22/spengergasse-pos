package data;

/**
 * Demonstrates a possible usage of a static attribute.
 * The counter is incremented each time a Student is initialized, and so every Student has a different ID
 *
 * @author Samuel Kaiser
 * @since 09/16/2016
 */
public class Student {
  private String name;
  private int id;
  private static int counter;

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public Student(String name) {
    this.name = name;
    this.id = ++counter;
  }

  @Override
  public String toString() {
    return "Student{" +
      "name='" + name + '\'' +
      ", id=" + id +
      '}';
  }
}
