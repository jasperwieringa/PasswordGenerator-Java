package org.openjfx.passwordgenerator;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public abstract class Controller {
  protected ObservableList<String> controllerTypes;
  protected ComboBox<String> controllerBox;

  protected abstract void initialize();

  protected abstract void generatePassword() throws IOException;

  protected abstract void copyPassword() throws IOException;

  protected abstract void switchTo() throws IOException;
} 