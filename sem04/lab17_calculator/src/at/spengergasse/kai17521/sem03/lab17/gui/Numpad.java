package at.spengergasse.kai17521.sem04.lab17.gui;

import at.spengergasse.kai17521.sem04.lab17.Operation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import static at.spengergasse.kai17521.sem04.lab17.Operation.*;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
class Numpad extends GridPane {
  private Calculator calculator;

  Numpad(Calculator calculator) {
    this.calculator = calculator;

    setHgap(5);
    setVgap(5);

    ColumnConstraints cols = new ColumnConstraints();
    cols.setPercentWidth(25);
    getColumnConstraints().addAll(cols, cols, cols, cols);

    RowConstraints rows = new RowConstraints();
    rows.setPercentHeight(20);
    getRowConstraints().addAll(rows, rows, rows, rows, rows);

    Button button;

    button = new ControlButton("CE", 15);
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().clearEntry()
    ));
    add(button, 0, 0);

    button = new ControlButton("C", 15);
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().clear()
    ));
    add(button, 1, 0);

    button = new ControlButton("DEL", 15);
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().deleteDigit()
    ));
    add(button, 2, 0);

    add(getDigitButton("1"), 0, 1);
    add(getDigitButton("2"), 1, 1);
    add(getDigitButton("3"), 2, 1);
    add(getDigitButton("4"), 0, 2);
    add(getDigitButton("5"), 1, 2);
    add(getDigitButton("6"), 2, 2);
    add(getDigitButton("7"), 0, 3);
    add(getDigitButton("8"), 1, 3);
    add(getDigitButton("9"), 2, 3);
    add(getDigitButton("0"), 1, 4);

    button = new ControlButton(".");
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().setFractional(true)
    ));
    add(button, 0, 4);

    add(getOperationButton("+", ADDITION), 3, 0);
    add(getOperationButton("-", SUBTRACTION), 3, 1);
    add(getOperationButton("×", MULTIPLICATION), 3, 2);
    add(getOperationButton("÷", DIVISION), 3, 3);

    button = new ControlButton("=");
    button.setDefaultButton(true);
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().calculate()
    ));
    add(button, 2, 4, 2, 1);
  }

  private Button getDigitButton(String digit) {
    Button button = new ControlButton(String.valueOf(digit));
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().addDigit(digit))
    );
    return button;
  }


  private Button getOperationButton(String symbol, Operation operation) {
    Button button = new ControlButton(symbol);
    button.setOnAction(event -> calculator.updateState(
      calculator.getState().changeOperation(operation)
    ));
    return button;
  }
}
