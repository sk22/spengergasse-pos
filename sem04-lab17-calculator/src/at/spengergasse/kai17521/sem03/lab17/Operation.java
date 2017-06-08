package at.spengergasse.kai17521.sem04.lab17;

import java.util.function.BiFunction;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
public enum Operation {
  ADDITION((prev, curr) -> prev + curr),
  SUBTRACTION((prev, curr) -> prev - curr),
  MULTIPLICATION((prev, curr) -> prev * curr),
  DIVISION((prev, curr) -> prev / curr);

  public BiFunction<Double, Double, Double> function;

  Operation(BiFunction<Double, Double, Double> function) {
    this.function = function;
  }
}
