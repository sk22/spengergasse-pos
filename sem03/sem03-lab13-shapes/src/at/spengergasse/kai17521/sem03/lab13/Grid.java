package at.spengergasse.kai17521.sem03.lab13;

import java.util.Arrays;

/**
 * @author Samuel Kaiser
 * @since 1/24/2017
 */
public class Grid {
  private char[][] grid;

  public static final char SYMBOL = '#';

  public Grid(int length) {
    this(length, length);
  }

  public Grid(int width, int height) {
    grid = new char[height][width];
    Arrays.stream(grid).forEach(arr -> Arrays.fill(arr, ' '));
  }

  public Grid set(int x, int y, char value) {
    grid[y][x] = value;
    return this;
  }

  public char get(int x, int y) {
    return grid[y][x];
  }

  public void draw() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    return Arrays.stream(grid)
      .map(String::new)
      .reduce("", (pre, cur) -> pre + cur + '\n');
  }
}
