package at.spengergasse.kai17521.sem04.lab20.model;

import javafx.util.Pair;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Samuel Kaiser
 * @since 4/3/2017
 */
public class Game {
  private Cell[][] grid;
  private Set<Pair<Integer, Integer>> linked = new HashSet<>();
  private Set<Pair<Integer, Integer>> guessed = new HashSet<>();
  private Cell active;
  private boolean won;
  private int rows;
  private int cols;
  private int fails;
  private LocalTime started = LocalTime.now();
  private LocalTime ended;

  public Game(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;

    grid = new Cell[rows][cols];

    for (int y = 0; y < rows; y++) {
      Cell[] row = grid[y];
      for (int x = 0; x < cols; x++) {
        row[x] = new Cell(new Pair<>(x, y));
      }
    }

    for (int y = 0; y < rows; y++) {
      Cell[] row = grid[y];
      for (int x = 0; x < cols; x++) {
        if (!linked.contains(row[x].getCoordinates())) {
          int value = (int) Math.floor(Math.random() * 10);
          row[x].setValue(value);
          linked.add(new Pair<>(x, y));
          Pair<Integer, Integer> randomCoordinates = generateCoordinates();

          if (randomCoordinates != null) {
            grid[randomCoordinates.getValue()][randomCoordinates.getKey()]
              .setValue(value);
            linked.add(randomCoordinates);
          }
        }
      }
    }
  }

  private Pair<Integer, Integer> generateCoordinates() {
    if (linked.size() == rows * cols) return null;
    Pair<Integer, Integer> random = new Pair<>(
      (int) Math.floor(Math.random() * cols),
      (int) Math.floor(Math.random() * rows)
    );
    return linked.contains(random) ? generateCoordinates() : random;
  }

  public Cell[][] getGrid () {
    return grid;
  }

  public void click(Cell cell) {
    handleCell(cell);
    won = guessed.size() >= linked.size() - 1;
  }

  private void handleCell(Cell cell) {
    if (cell == active || cell.getState() == State.VISIBLE) {
      return;
    }

    if (active == null) {
      setActive(cell);
      return;
    }

    if (cell.getValue().equals(active.getValue())) {
      setGuessed(cell);
      setGuessed(active);
      this.active = null;
    } else {
      setHidden(active);
      setActive(cell);
      fails++;
    }
  }

  public boolean isWon() {
    return won;
  }

  private void setActive(Cell cell) {
    this.active = cell;
    cell.setState(State.ACTIVE);
  }

  private void setGuessed(Cell cell) {
    guessed.add(cell.getCoordinates());
    cell.setState(State.VISIBLE);
  }

  private void setHidden(Cell hidden) {
    hidden.setState(State.HIDDEN);
  }

  public int getFails() {
    return fails;
  }

  public LocalTime getStarted() {
    return started;
  }

  public Duration getElapsedTime() {
    return Duration.between(
      started, ended == null ? ended = LocalTime.now() : ended
    );
  }
}
