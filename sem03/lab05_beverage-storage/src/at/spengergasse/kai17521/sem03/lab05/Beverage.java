package at.spengergasse.kai17521.sem03.lab05;

public class Beverage extends ID implements Comparable<Beverage> {
  private String owner;
  private String label;
  private Location location;
  private Shape shape;
  private Integer quantity;

  /**
   * Constructs a Beverage
   */
  public Beverage(int id, String owner, String label, Location location, Shape shape, Integer quantity) {
    super(id);
    setOwner(owner);
    setLabel(label);
    setLocation(location);
    setShape(shape);
    setQuantity(quantity);
  }

  /**
   * Constructs a Beverage using the alternative String syntax for the location
   * @see #setLocation(String)
   */
  public Beverage(int id, String owner, String label, String location, Shape shape, Integer quantity) {
    super(id);
    setOwner(owner);
    setLabel(label);
    setLocation(location);
    setShape(shape);
    setQuantity(quantity);
  }

  /**
   * Sets the Beverage's location using a String.
   * Must match one of the following patterns:
   * - word number word number
   * - number number
   * The first number is used as the hall, the second for the rack.
   * The given words can be any, but you might want to use "Hall" and "Rack".
   * @param location Must contain a String that matches described pattern
   */
  public void setLocation(String location) throws IllegalArgumentException {
    this.location = Location.parseLocation(location);
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Shape getShape() {
    return shape;
  }

  public void setShape(Shape shape) {
    this.shape = shape;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    if (quantity < 0) throw new IllegalArgumentException("Quantity must not be less than 0");
    this.quantity = quantity;
  }
  
  @Override
  public String toString() {
    return "#" + id + " - " + owner + " - " + label + " - "
      + location + " - "+ shape + " - " + quantity + "x";
  }

  /**
   * Compares the Beverage object to another one based on their hall and rack number
   * @param comparison
   * @return
   */
  @Override
  public int compareTo(Beverage comparison) {
    int hall = this.getLocation().getHall();
    int rack = this.getLocation().getRack();
    int compareHall = comparison.getLocation().getHall();
    int compareRack = comparison.getLocation().getRack();

    if (hall == compareHall) {
      if (rack == compareRack) return 0;
      return compareRack < rack ? 1 : -1;
    }
    return compareHall < hall ? 1 : -1;
  }

  /**
   * Used to build a Beverage
   */
  public static class Builder extends AbstractBuilder<Beverage> {
    public final int fields = 5;

    private String owner;
    private String label;
    private Location location;
    private Shape shape;
    private Integer quantity;
    
    public Builder setOwner(String owner) {
      this.owner = owner;
      return this;
    }
    public Builder setLabel(String label) {
      this.label = label;
      return this;
    }
    public Builder setLocation(Location location) {
      this.location = location;
      return this;
    }
    public Builder setLocation(String location) {
      this.location = Location.parseLocation(location);
      return this;
    }
    public Builder setShape(Shape shape) {
      this.shape = shape;
      return this;
    }
    public Builder setQuantity(Integer quantity) {
      this.quantity = quantity;
      return this;
    }

    public String getOwner() {
      return owner;
    }

    public String getLabel() {
      return label;
    }

    public Location getLocation() {
      return location;
    }

    public Shape getShape() {
      return shape;
    }

    public int getQuantity() {
      return quantity;
    }

    public int getFields() {
      int sum = 0;
      if (owner != null) sum++;
      if (label != null) sum++;
      if (location != null) sum++;
      if (shape != null) sum++;
      if (quantity != null) sum++;

      return sum;
    }

    /**
     * Builds the Beverage object. Usually called by a Storage.
     * @param id ID the Beverage should have
     * @return Built Beverage object
     */
    public Beverage build(int id) {
      return new Beverage(id, owner, label, location, shape, quantity);
    }
  }
}
