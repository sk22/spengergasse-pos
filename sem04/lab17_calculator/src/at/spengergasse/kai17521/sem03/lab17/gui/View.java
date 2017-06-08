package at.spengergasse.kai17521.sem04.lab17.gui;

import at.spengergasse.kai17521.sem04.lab17.KeyHandler;
import at.spengergasse.kai17521.sem04.lab17.State;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * @author Samuel Kaiser
 * @since 2/27/2017
 */
class View extends TextField {
  View(Calculator calculator) {
    setEditable(false);
    setPrefHeight(60);
    setFont(new Font(25));
    setAlignment(Pos.CENTER_RIGHT);
    setOnKeyPressed(new KeyHandler(calculator));
  }

  void update(State state) {
    setText(String.valueOf(state.getNumber()));
  }
}
