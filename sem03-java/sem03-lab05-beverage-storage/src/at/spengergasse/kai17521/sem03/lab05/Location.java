package at.spengergasse.kai17521.sem03.lab05;

public class Location {
  private int hall;
  private int rack;
  
  public Location(int hall, int rack) {
    setHall(hall);
    setRack(rack);
  }
  
  @Override
  public String toString() {
    return toString("Hall %d Rack %d");
  }
  
  /**
   * Formats hall and rack according in the given String format
   * @param format String format
   * @return Formatted String
   */
  public String toString(String format) {
    return String.format(format, hall, rack);
  }

  public int getHall() {
    return hall;
  }

  public void setHall(int hall) {
    if (hall < 1) throw new IllegalArgumentException("Hall must be greater than 0");
    this.hall = hall;
  }

  public int getRack() {
    return rack;
  }

  public void setRack(int rack) {
    if (rack < 1) throw new IllegalArgumentException("Rack must be greater than 0");
    this.rack = rack;
  }

  /**
   * Parses a String containng the location data to a Location object.
   * Must match one of the following patterns:
   * - word number word number
   * - number number
   * The first number is used as the hall, the second for the rack.
   * The given words can be any, but you might want to use "Hall" and "Rack".
   * @param location String containing the location data
   * @return Location object
   * @throws IllegalArgumentException Thrown if the String is not correctly formatted
   */
  public static Location parseLocation(String location) throws IllegalArgumentException {
    if (location == null) throw new IllegalArgumentException("Location must not be null!");
    String[] split = location.split("\\s+");
    if (split.length != 2 && split.length != 4) {
      throw new IllegalArgumentException("Location must contain either two numbers " +
        "separated by spaces or two numbers prefixed by words, separated by spaces");
    }
    boolean shortened = split.length == 2;
    int hall, rack;
    try {
      hall = shortened ? Integer.parseInt(split[0]) : Integer.parseInt(split[1]);
      rack = shortened ? Integer.parseInt(split[1]) : Integer.parseInt(split[3]);
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException("String doesn't contain valid numbers " +
        "at the right positions");
    }
    return new Location(hall, rack);
  }
}
