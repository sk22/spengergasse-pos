package at.spengergasse.kai17521.sem03.lab09;

/**
 * @author Samuel Kaiser
 * @since 12/5/2016
 */
public class Store {
  private String name = "Unnamed";
  protected boolean city;
  protected int employers;

  public Store(int employers, boolean city, String name) {
    setEmployers(employers);
    setCity(city);
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name must not be null and not empty");
    }
    this.name = name;
  }

  public boolean inCity() {
    return city;
  }

  public void setCity(boolean town) {
    this.city = town;
  }

  public int getEmployers() {
    return employers;
  }

  public void setEmployers(int employers) {
    if (employers < 1) {
      throw new IllegalArgumentException("Employers must be at least 1");
    }
    this.employers = employers;
  }

  public float getFunding() {
    return 0f;
  }


  public void print() {
    System.out.println(toString());
  }

  @Override
  public String toString() {
    return "Store{" +
      "name='" + name + '\'' +
      ", city=" + city +
      ", employers=" + employers +
      '}';
  }
}
