package at.spengergasse.kai17521.sem04.lab17;

import static at.spengergasse.kai17521.sem04.lab17.Operation.*;
import static at.spengergasse.kai17521.sem04.lab17.Number.ZERO;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
public class State {
  private final Number saved;
  private final Number entered;
  private final Number lastTyped;
  private final Operation operation;
  private final boolean repeat;
  private final boolean clear;
  private final boolean fractional;

  private State(
    Number saved,
    Number entered,
    Number lastTyped,
    Operation operation,
    boolean repeat,
    boolean clear,
    boolean fractional
  ) {
    this.saved = saved;
    this.entered = entered;
    this.lastTyped = lastTyped;
    this.operation = operation;
    this.repeat = repeat;
    this.clear = clear;
    this.fractional = fractional;
  }

  public State() {
    this(ZERO, ZERO, ZERO, ADDITION, false, false, false);
  }

  public State addDigit(String digit) {
    boolean clear = this.clear;
    try {
      final long parsable = Long.parseLong(entered.digits);
    } catch (NumberFormatException e) {
      clear = true;
    }
    final Number newNumber = new Number(
      clear ? digit : entered.digits + digit,
      clear ? 0 : (fractional ? entered.decimals + 1 : entered.decimals)
    );
    return new State(
      saved, newNumber, newNumber, operation, false, false, fractional
    );
  }

  public State setFractional(boolean fractional) {
    return new State(
      saved, entered, lastTyped, operation, repeat, clear, fractional
    );
  }

  public State deleteDigit() {
    final String substring = entered.digits.substring(
      0, entered.digits.length() - 1
    );
    final Number newNumber = new Number(
      substring.length() == 0 ? "0" : substring,
      fractional ? entered.decimals - 1 : entered.decimals
    );
    final boolean fractional = newNumber.decimals != 0;
    return new State(
      saved, newNumber, newNumber, operation, repeat, clear, fractional
    );
  }

  public State changeOperation(Operation operation) {
    final State calculated = saved != entered ? calculate() : this;
    return new State(
      calculated.saved,
      calculated.entered,
      lastTyped,
      operation,
      true,
      true,
      false
    );
  }

  public State calculate() {
    double result = operation.function.apply(
      saved.getNumber(), repeat ? lastTyped.getNumber() : entered.getNumber()
    );
    String resultAsString = Double.toString(result);
    Number number;
    try {
      number = new Number(resultAsString);
    } catch(NumberFormatException e) {
      number = saved;
    }

    return new State(
      number, number, lastTyped, operation, true, false, fractional
    );
  }

  public double getNumber() {
    return entered.getNumber();
  }

  public State clearEntry() {
    return new State(saved, ZERO, ZERO, operation, repeat, false, false);
  }

  public State clear() {
    return new State(ZERO, ZERO, ZERO, ADDITION, false, false, false);
  }

  @Override
  public String toString() {
    return "State{" +
      "saved=" + saved +
      ", entered=" + entered +
      ", lastTyped=" + lastTyped +
      ", operation=" + operation +
      ", repeat=" + repeat +
      ", clear=" + clear +
      ", fractional=" + fractional +
      '}';
  }
}
