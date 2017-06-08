package at.spengergasse.kai17521.sem03.the20161014.enums;

/**
 * @author Samuel Kaiser
 * @since 2016-10-14
 */
public enum Grade {
  SEHR_GUT(1, "Sehr gut"),
  GUT(2, "Gut"),
  BEFRIEDIGEND(3, "Befriedigend"),
  GENUEGEND(4, "Genügend"),
  NICHT_GENUEGEND(5, "Nicht genügend");
  
  public final int value;
  public final String label;
  
  // Constructor
  private Grade(int value, String label) {
    this.value = value;
    this.label = label;
  }
}
