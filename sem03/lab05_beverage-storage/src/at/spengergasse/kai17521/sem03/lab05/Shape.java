package at.spengergasse.kai17521.sem03.lab05;

public enum Shape {
  CAN("Can"),
  BOTTLE("Bottle"),
  BARREL("Barrel");

  public final String name;
  Shape(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
