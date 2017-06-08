package at.spengergasse.kai17521.sem04.lab20.model;

import javafx.util.Pair;

/**
 * @author Samuel Kaiser
 * @since 4/3/2017
 */
public class Cell {
  private final Pair<Integer, Integer> coordinates;
  private Object value;
  private State state = State.HIDDEN;

  Cell(Pair<Integer, Integer> coordinates) {
    this.coordinates = coordinates;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return state;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  public Pair<Integer, Integer> getCoordinates() {
    return coordinates;
  }
}
